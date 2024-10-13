import java.util.ArrayList;

import org.example.DBconnectors.DBEmployeeBehavior;
import org.example.DBconnectors.DBMembersBehavior;
import org.example.DBconnectors.DBTrainingsBehavior;
import org.example.fitnesse.*;

public class TesterDBClasses {
    public static void main(String[] args) {
        getMemberByID("qwert");
        getMemberByID("dasw");
        System.out.println("\nall Members:");
        testGetAllMembers();
        System.out.println();

        /*getEmployeeByID("dfghj");
        getEmployeeByID("rewt1");
        System.out.println("\nall Employees:");
        testGetAllEmployees();

        System.out.println("\nall PublicPrograms:");
        testGetAllPublicPrograms();
        testGetPublicProgramById("2");
        */
        //-----------------------------29/09/2024-
        /*testAddTrainer();
        testAddAdmin();
        testAddDirector();*/
        //testAddMember();
        //testAddMemberAgain();
        //testAddAdmin();
        //testDeleteAdmin();
        testAddMoney();
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
    public static void testAddTrainer()
    {
        Trainer newTr = new Trainer("hell", "sd",25);
        new DBEmployeeBehavior().add(newTr,0);
    }
    public static void testAddAdmin()
    {
        Admin newAdmin = new Admin("newAdmin", "try",25);
        new DBEmployeeBehavior().add(newAdmin);
    }
    public static void testAddDirector()
    {
        Director newDir = new Director("hell", "sd",25);
        new DBEmployeeBehavior().add(newDir,2);
    }
    public static void testAddMember()
    {
        Member newMem = new Member("next1751", "test2",25,'m',0);
        new DBMembersBehavior().add(newMem);
    }
    public static void testAddMemberAgain()
    {
        Member newMem = new Member("membMan", "test2",25,'m',0);
        newMem.id="7aa40";
        new DBMembersBehavior().add(newMem);
    }
    public static void testDeleteAdmin()
    {
        new DBEmployeeBehavior().delete("0882f");
    }
    public static void testAddMoney()
    {
        new DBMembersBehavior().updateWallet("asdfg", 15230);
    }
}