package org.example.DBconnectors;

import org.example.fitnesse.Member;
import org.example.fitnesse.VisitorInterface;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBMembersBehavior implements DBVisitorBehavior {
    private final DBHelper db = new DBHelper();
    private ResultSet resultSet;
    private VisitorInterface foundedMember;
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
    private VisitorInterface buildMemberFromResultSet() throws SQLException {
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
    public VisitorInterface getById(String ID) {
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
    public boolean add(VisitorInterface visitor) {
        Member member = (Member) visitor;
        try {
            insertDB(member);
        } catch (SQLException e) {
            System.out.println("такой есть в базе!!!!!");
            throw new RuntimeException(e);
            //return false;
        }
        return true;
    }
    private void insertDB(Member member) throws SQLException {
        String table = "Members";
        int genderId=member.gender=='m'? 1:0;
        String query = String.format(
                "insert into %s values('%s', '%s', '%s', '%d', '%d')",
                table,
                member.id,
                member.name,
                member.secondName,
                member.age,
                genderId
                );
        db.executeUpdate(query);
    }

    @Override
    public boolean update(VisitorInterface visitor) {
        return false;
    }

    public void updateWallet(String id, int wallet)
    {
        String query ="UPDATE Members" +
                "\nSET wallet = wallet +"+wallet+" "+
                "\nWHERE id='"+id+"';";
        try {
            db.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateDate(String id, String date)
    {
        String query ="UPDATE Members" +
                "\nSET ticketdate = '"+date+"'"+
                "\nWHERE id='"+id+"';";
        try {
            db.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(VisitorInterface visitor) {
        return false;
    }

}
