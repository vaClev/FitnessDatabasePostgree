import java.util.ArrayList;

import org.example.DBconnectors.DBEmployeeBehavior;
import org.example.DBconnectors.DBMembersBehavior;
import org.example.DBconnectors.DBTrainingsBehavior;
import org.example.fitnesse.PublicProgram;
import org.example.fitnesse.VisitorInterface;

public class TesterDBClasses {
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

        System.out.println("\nall PublicPrograms:");
        testGetAllPublicPrograms();
        testGetPublicProgramById("2");
    }

    public static void testGetAllMembers() {
        ArrayList<VisitorInterface> arrayList = new DBMembersBehavior().getAll();
        for (var elem : arrayList) {
            System.out.println(elem);
        }
    }
    public static void getMemberByID(String ID) {
        VisitorInterface member = new DBMembersBehavior().getById(ID);
        System.out.println(member);
    }

    public static void testGetAllEmployees() {
        ArrayList<VisitorInterface> arrayList = new DBEmployeeBehavior().getAll();
        for (var elem : arrayList) {
            System.out.println(elem);
        }
    }
    public static void getEmployeeByID(String ID) {
        VisitorInterface employee = new DBEmployeeBehavior().getById(ID);
        System.out.println(employee);
    }

    public static void testGetAllPublicPrograms() {
        ArrayList<PublicProgram> arrayList = new DBTrainingsBehavior().getAll();
        for (var elem : arrayList) {
            System.out.println(elem);
        }
    }
    public static void testGetPublicProgramById(String id) {
        PublicProgram program = new DBTrainingsBehavior().getById(id);
        System.out.println(program);
    }
}