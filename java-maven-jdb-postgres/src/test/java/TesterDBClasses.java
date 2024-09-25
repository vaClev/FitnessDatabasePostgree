import java.util.ArrayList;

import org.example.DBconnectors.DBEmployeeBehavior;
import org.example.DBconnectors.DBHelper;
import org.example.DBconnectors.DBMembersBehavior;
import org.example.fitnesse.VisitorInterface;

public class TesterDBClasses {
    static DBHelper db = new DBHelper();

    public static void main(String[] args) {
        getMemberByID("qwert");
        getMemberByID("dasw");
        System.out.println("\nall Members:");
        testGetAllMembers();
        System.out.println();

        getEmployeeByID("dfghj");
        getEmployeeByID("rewt1");
        System.out.println("\nall Employees:");
        testGetAllEmployees();
    }
    public static void getMemberByID(String ID) {
        VisitorInterface member = new DBMembersBehavior().getByID(ID);
        System.out.println(member);
    }
    public static void getEmployeeByID(String ID) {
        VisitorInterface employee = new DBEmployeeBehavior().getByID(ID);
        System.out.println(employee);
    }

    public static void testGetAllMembers()
    {
        ArrayList<VisitorInterface> arrayList = new DBMembersBehavior().getAll();
        for (var elem: arrayList)
        {
            System.out.println(elem);
        }
    }
    public static void testGetAllEmployees()
    {
        ArrayList<VisitorInterface> arrayList = new DBEmployeeBehavior().getAll();
        for (var elem: arrayList)
        {
            System.out.println(elem);
        }
    }

    /*public static void testGetUserByID1() {
        Statement statement = db.getStatement();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM members WHERE id ='qwert'");
            db.printResultSet(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        db.close();
    }

    public static void testGetUserByID2() {
        ResultSet resultSet = db.getResultSet("SELECT * FROM members WHERE id ='qwert'");
        db.printResultSet(resultSet);
        db.close();
    }*/


}