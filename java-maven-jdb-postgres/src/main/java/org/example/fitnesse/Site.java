package org.example.fitnesse;
import org.example.DBconnectors.DBEmployeeBehavior;
import org.example.DBconnectors.DBMembersBehavior;
import org.example.DBconnectors.DBTrainingsBehavior;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Objects;

public class Site extends Club {

    public void greeting() {
        System.out.println("We glad to see you on out site!");
        System.out.println("\'Description\'");
        while (true) {
            menu();
        }
    }

    public void menu()  {
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

    public void logistic() {
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

    private void endOfWork() {
        System.exit(0);
    }

    private void joinEmployee() {
        System.out.print("Please enter the password: ");
        String employeePassword = Main.scanner.nextLine();
        employeePassword = Main.scanner.nextLine();
        VisitorInterface employee = new DBEmployeeBehavior().getById(employeePassword);
        employee.visitorMenu();
    }

    private void joinMember() {
        System.out.print("Please enter the password: ");
        String memberPassword = Main.scanner.nextLine();
        memberPassword = Main.scanner.nextLine();
        VisitorInterface member = new DBMembersBehavior().getById(memberPassword);
        member.visitorMenu();
    }

    //TODO переделать на Базу данных
    public void showTicketList() {
        for (Map.Entry<Integer, Integer> elem : prices.entrySet()) {
            System.out.println(elem.getValue() + " " + elem.getKey());
        }
    }

    public void showPublicProgram() {
        ArrayList<PublicProgram> programs = new DBTrainingsBehavior().getAll();
        int i=0;
        for (var program:programs) {
            System.out.println((i + 1) + ") " +program.name);
            i++;
        }
    }

    public void showTrainerList() {
        ArrayList<VisitorInterface> trainers = new DBEmployeeBehavior().getAll("Trainer");
        int i=0;
        for (var trainer : trainers) {
            Trainer trainerI = (Trainer)trainer;
            System.out.println((i + 1) + ") " +trainerI.name +" "+trainerI.age);
            i++;
        }
    }
}
