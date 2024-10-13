package org.example.fitnesse;

import org.example.DBconnectors.DBEmployeeBehavior;
import org.example.DBconnectors.DBMembersBehavior;
import org.example.DBconnectors.DBTrainingsBehavior;

import java.sql.Date;
import java.util.*;

public class Member extends Visitor implements VisitorInterface {
    static Calendar calendar1 = new GregorianCalendar();
    public char gender;
    private int wallet;
    List<String> visitedList = new LinkedList<>();
    Calendar calendar;

    public Member(String name, String secondName, int age, char gender, int wallet) {
        super(name, secondName, age);
        this.gender = gender;
        this.wallet = wallet;
        id = UUID.randomUUID().toString().substring(0, 5);
    }

    public Member(String name, String secondName, int age, char gender, int wallet, String id, Date ticketdate) {
        this(name, secondName, age, gender, wallet);
        this.id = id;
        if (ticketdate != null) {
            calendar = Calendar.getInstance();
            calendar.setTime(ticketdate);
        }
    }

    public Member(String name, String secondName, int age) {
        super(name, secondName, age);
        anketa();
    }

    public void anketa() {
        System.out.println("Hello our new member!");
        System.out.print("What is your name? - ");
        name = Main.scanner.nextLine();
        name = Main.scanner.nextLine();
        System.out.println("Nice to meet you " + name + "! How old are you?");
        age = Main.scanner.nextInt();
        if (age < 18) {
            System.out.println("You so young little kitty");
            System.exit(0);
        }
        System.out.println("What's your gender?");
        String temp = Main.scanner.nextLine().toLowerCase();
        temp = Main.scanner.nextLine().toLowerCase();
        gender = temp.charAt(0);
        System.out.println();
        id = UUID.randomUUID().toString().substring(0, 5);
        System.out.println("Welcome to our club!\nYour password is: " + id);
    }

    public void visitorShowMenu() {
        do {
            System.out.println();
            System.out.println("1) Open current trainers");
            System.out.println("2) Open current programs");
            System.out.println("3) Open your wallet-balance");
            System.out.println("4) Open your programs");
            System.out.println("5) Open your ticket");
            System.out.println("0) Return to member menu");
            visitorShowLogistic();
        } while (choice != 0);
    }

    public void visitorAddMenu() {
        do {
            System.out.println();
            System.out.println("1) Added your wallet");
            System.out.println("2) Sign-in program");
            System.out.println("3) Buy new ticket-season");
            System.out.println("0) Return to member menu");
            visitorAddLogistic();
        } while (choice != 0);
    }

    public void visitorDelMenu() {
        do {
            System.out.println();
            System.out.println("1) Sign-out program");
            System.out.println("0) Return to member menu");
            visitorDelLogistic();
        } while (choice != 0);
    }

    public void visitorShowLogistic() {
        if (checkChoice()) {
            switch (choice) {
                case 1:
                    System.out.println(new DBEmployeeBehavior().getAll("Trainer"));
                    break;
                case 2:
                    System.out.println(new DBTrainingsBehavior().getAll());;
                    break;
                case 3:
                    System.out.println("Your balance is: " + this.wallet);
                    break;
                case 4:
                    showMyPrograms();
                    break;
                case 5:
                    showMyTime();
                    break;
                case 0:
                    return;
                default:
                    System.out.println();
                    System.out.println("Wrong enter");
            }
        }
        System.out.println();
    }

    public void visitorAddLogistic() {
        if (checkChoice()) {
            switch (choice) {
                case 1:
                    pay();
                    break;
                case 2:
                    joinProgram();
                    break;
                case 3:
                    buyTicket();
                    break;
                case 0:
                    return;
                default:
                    System.out.println();
                    System.out.println("Wrong enter");
            }
        }
        System.out.println();
    }

    public void visitorDelLogistic() {
        if (checkChoice()) {
            switch (choice) {
                case 1:
                    deleteProgram();
                    break;
                case 0:
                    return;
                default:
                    System.out.println();
                    System.out.println("Wrong enter");
            }
        }
        System.out.println();
    }

    public void showMyTime() {
        if (calendar != null) {
            System.out.println(calendar.getTime());
        } else {
            System.out.println("You have not any ticket");
        }
    }

    public void showMyPrograms() {
        if (calendar != null && calendar.getTime().after(calendar1.getTime())) {
            for (int i = 0; i < visitedList.size(); i++) {
                System.out.println((i + 1) + ") " + visitedList.get(i));
            }
        } else {
            System.out.println("You should buy new ticket");
        }
    }

    public void pay() {
        int newMoney;
        System.out.println("How many money you want pay?");
        System.out.print("Cost: ");
        newMoney = Main.scanner.nextInt();

        new DBMembersBehavior().updateWallet(this.id, newMoney);
        Member me = (Member) new DBMembersBehavior().getById(this.id);
        this.wallet = me.wallet;
        System.out.println("Success!\nNow your balance is: " + this.wallet);
    }

    public void joinProgram() {
        if (calendar != null && calendar.getTime().after(calendar1.getTime())) {
            System.out.println("What program do you want to visit?");
            Main.site.showPublicProgram();
            choice = Main.scanner.nextInt();
            if (!visitedList.contains(Main.site.publicProgramList.get(choice - 1).name)) {
                Main.site.publicProgramList.get(choice - 1).programList.add(this);
                visitedList.add(Main.site.publicProgramList.get(choice - 1).name);
                System.out.println(Main.site.publicProgramList.get(choice - 1).programList);
            } else {
                System.out.println("You are already on it");
            }
        } else {
            System.out.println("You should buy new ticket");
        }
    }

    public void buyTicket() {
        Main.site.showTicketList();
        System.out.print("What package do you want? Enter the cost: ");
        choice = Main.scanner.nextInt();

        if (Main.site.prices.containsKey(choice)) {
            if (choice <= wallet) {
                wallet -= choice;
                new DBMembersBehavior().updateWallet(this.id, -choice);
                Member me = (Member) new DBMembersBehavior().getById(this.id);
                this.wallet = me.wallet;

                //TODO Calendar something
                if (calendar == null || calendar.getTime().before(new GregorianCalendar().getTime())) {
                    calendar = new GregorianCalendar();
                }
                calendar.add(Main.site.prices.get(choice), 1);
                new DBMembersBehavior().updateDate(id, calendar.getTime().toString());

            } else {
                System.out.println("Error: not enough money");
            }
        } else {
            System.out.println("Wrong enter");
        }
    }

    public void deleteProgram() {
        if (calendar != null && calendar.getTime().after(calendar1.getTime())) {
            if (!visitedList.isEmpty()) {
                for (int i = 0; i < visitedList.size(); i++) {
                    System.out.println((i + 1) + ") " + visitedList.get(i));
                }

                choice = Main.scanner.nextInt();
                for (PublicProgram elem : Main.site.publicProgramList) {
                    if (elem.name == visitedList.get(choice - 1)) {
                        elem.programList.remove(this);
                    }
                }
                visitedList.remove(choice - 1);
            } else {
                System.out.println("You haven't current programs");
            }
        } else {
            System.out.println("You should buy new ticket");
        }
    }

    @Override
    public String toString() {
        String date = null;
        if (calendar != null) date = calendar.getTime().toString();
        return "Member{" +
                "name=" + name +
                ", secondName=" + secondName +
                ", age=" + age +
                ", id=" + id +
                ", gender=" + gender +
                ", wallet=" + wallet +
                ", visitedList=" + visitedList +
                ", calendar=" + date +
                '}';
    }

}