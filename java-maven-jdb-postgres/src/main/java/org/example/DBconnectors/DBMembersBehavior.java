package org.example.DBconnectors;

import org.example.fitnesse.Member;
import org.example.fitnesse.Visitor;
import org.example.fitnesse.VisitorInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBMembersBehavior implements DBVisitorBehavior {
    private final DBHelper db = new DBHelper();
    private ResultSet resultSet;
    private Member foundedMember;
    private ArrayList<VisitorInterface> members;

    @Override
    public ArrayList<VisitorInterface> getAll() {
        members = new ArrayList<>();
        resultSet = db.getResultSet("""
                SELECT Members.id, Members.name, Members.secondname, Members.age, genders.gender,
                Members.Wallet, Members.VisitedList, Members.TicketDate
                FROM Members
                JOIN Genders ON Members.gender = genders.Id"""
        );
        try {
            collectMembers();
        } catch (SQLException ignored) {
        }
        db.close();
        return members;
    }
    private void collectMembers() throws SQLException {
        boolean isEmpty = true;
        while (resultSet.next()) {
            isEmpty = false;
            foundedMember = buildMemberFromResultSet();
            members.add(foundedMember);
        }
        if(isEmpty) System.out.println("Таблица Members пустая");
    }
    private Member buildMemberFromResultSet() throws SQLException {
        //TODO сейчас без visitedlist  изучить как пройтись по массиву и как его полчить
        //System.out.println(resultSet.getString("visitedlist"));
        return new Member(
                resultSet.getString("name"),
                resultSet.getString("secondName"),
                resultSet.getInt("age"),
                resultSet.getString("gender").toCharArray()[0],
                resultSet.getInt("wallet"),
                resultSet.getString("id"),
                resultSet.getDate("ticketdate")
        );
    }

    @Override
    public VisitorInterface getByID(String ID) {
        foundedMember = null;
        resultSet = db.getResultSet("SELECT Members.id, Members.name, Members.secondname, Members.age, genders.gender," +
                "\nMembers.Wallet, Members.VisitedList, Members.TicketDate" +
                "\nFROM Members" +
                "\nJOIN Genders ON Members.gender = genders.Id" +
                "\nWHERE Members.id ='" + ID + "'");
        try {
            findMember();
        } catch (SQLException ignored) {
        }
        db.close();
        return foundedMember;
    }
    private void findMember() throws SQLException {
        if (resultSet.next()) {
            foundedMember = buildMemberFromResultSet();
        } else {
            System.out.println("Member not found in DB");
        }
    }

    @Override
    public boolean add(Visitor visitor) {
        return false;
    }

    @Override
    public boolean update(Visitor visitor) {
        return false;
    }

    @Override
    public boolean delete(Visitor visitor) {
        return false;
    }

}
