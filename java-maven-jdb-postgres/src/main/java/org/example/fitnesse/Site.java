package org.example.fitnesse;
import org.example.DBconnectors.DBEmployeeBehavior;
import org.example.DBconnectors.DBMembersBehavior;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Objects;

public class Site extends Club {

    int index;

    public void greeting() throws SQLException {

        System.out.println("Members:");
        for (int i = 0; i < memberList.size(); i++) {
            System.out.println(memberList.get(i).name + "   " + memberList.get(i).id);
        }
        System.out.println();

        System.out.println("Trainers:");
        for (int i = 0; i < trainerList.size(); i++) {
            System.out.println(trainerList.get(i).name + "   " + trainerList.get(i).id);
        }
        System.out.println();

        System.out.println("Admins");
        for (int i = 0; i < adminList.size(); i++) {
            System.out.println(adminList.get(i).name + "   " + adminList.get(i).id);
        }
        System.out.println();
        System.out.println("Director");
        System.out.println(director.name + "   " + director.id);

        System.out.println();
        System.out.println();

        System.out.println("We glad to see you on out site!");
        System.out.println("\'Description\'");
        while (true) {
            menu();
        }
    }

    public void menu() throws SQLException {
        while (true) {

            System.out.println("" +
                    "Menu:\n" +
                    "1) Become a new member\n" +
                    "2) Look at public programs\n" +
                    "3) Look at price\n" +
                    "4) Sign in like a Member\n" +
                    "5) Sign in like a Trainer\n" +
                    "6) Sign in like a Administrator\n" +
                    "7) Sign in like a Director\n" +
                    "0) Exit from site");
            logistic();

        }
    }

    public void logistic() throws SQLException {
        int answer;
        try {
            System.out.print("Enter your answer - ");
            answer = Main.scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Error");
            Main.scanner.nextLine();
            return;
        }
            switch (answer) {
                case 1:
                    memberList.add(new Member("qwe", "qwe", 2));
                    break;
                case 2:
                    showPublicProgram();
                    break;
                case 3:
                    showTicketList();
                    break;
                case 4:
                    joinMember();
                    break;
                case 5:
                case 6:
                case 7:
                    joinEmployee();
                    break;
                case 0:
                    endOfWork();
                default:
                    System.out.println("Wrong enter");
            }
            System.out.println();
    }

    private void endOfWork() throws SQLException {
        connection.close();
        statement.close();
        System.exit(0);
    }

    private void joinEmployee() throws SQLException {
        System.out.print("Please enter the password: ");
        String employeePassword = Main.scanner.nextLine();
        employeePassword = Main.scanner.nextLine();
        VisitorInterface employee = new DBEmployeeBehavior().getById(employeePassword);
        employee.visitorMenu();
    }

    private void joinMember() throws SQLException {
        System.out.print("Please enter the password: ");
        String memberPassword = Main.scanner.nextLine();
        memberPassword = Main.scanner.nextLine();
        VisitorInterface member = new DBMembersBehavior().getById(memberPassword);
        member.visitorMenu();
    }

    public void showTicketList() {
        for (Map.Entry<Integer, Integer> elem : prices.entrySet()) {
            System.out.println(elem.getValue() + " " + elem.getKey());
        }
    }

    public void showPublicProgram() {
        for (int i = 0; i < publicProgramList.size(); i++) {
            System.out.println((i + 1) + ") " + publicProgramList.get(i).name);
        }
    }

    public void showTrainerList() {
        for (Trainer elem : trainerList) {
            System.out.println(elem.name + " - " + elem.id);
        }
    }
}
