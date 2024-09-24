package org.example.fitnesse;
import java.util.UUID;
public class Admin extends Visitor implements VisitorInterface {

    public Admin(){}
    public Admin(String name, String secondName, int age) {
        super(name, secondName, age);
        id = UUID.randomUUID().toString().substring(0, 5);
    }

    public Admin(String name, String secondName, int age, int num) {
        super(name, secondName, age);
        anketa();
    }

    public void anketa() {
        System.out.print("Enter name: ");
        this.name = Main.scanner.nextLine();
        this.name = Main.scanner.nextLine();
        System.out.print("Enter second name: ");
        this.secondName = Main.scanner.nextLine();
        System.out.print("Enter age: ");
        this.age = Main.scanner.nextInt();
        System.out.println("Admin id is: " + id);
    }

    @Override
    public void visitorShowMenu() {
        do {
            System.out.println();
            System.out.println("1) Open current members");
            System.out.println("2) Open current trainers");
            System.out.println("3) Open current programs");
            System.out.println("4) Open trainers id");
            System.out.println("0) Return to Admin menu");
            visitorShowLogistic();
        } while (choice != 0);
    }

    @Override
    public void visitorAddMenu() {
        do {
            System.out.println();
            System.out.println("1) Add new trainer");
            System.out.println("0) Return to Admin menu");
            visitorAddLogistic();
        } while (choice != 0);
    }

    @Override
    public void visitorDelMenu() {
        do {
            System.out.println();
            System.out.println("1) Remove trainer");
            System.out.println("0) Return to Admin menu");
            visitorDelLogistic();
        } while (choice != 0);
    }

    @Override
    public void visitorShowLogistic() {
        if (checkChoice()) {
            switch (choice) {
                case 1:
                    System.out.println(Main.site.memberList);
                    break;
                case 2:
                    System.out.println(Main.site.trainerList);
                    break;
                case 3:
                    System.out.println(Main.site.publicProgramList);
                    break;
                case 4:
                    Main.site.showTrainerList();
                    break;
                case 0:
                    return;
            }
        }
        System.out.println();
    }

    @Override
    public void visitorAddLogistic() {
        if (checkChoice()) {
            switch (choice) {
                case 1:
                    addTrainer();
                    break;
                case 0:
                    return;
            }
        }
        System.out.println();
    }

    @Override
    public void visitorDelLogistic() {
        if (checkChoice()) {
            System.out.print("Enter your answer - ");
            choice = Main.scanner.nextInt();
            System.out.println();
            switch (choice) {
                case 1:
                    delTrainer();
                    break;
                case 0:
                    return;
            }
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}