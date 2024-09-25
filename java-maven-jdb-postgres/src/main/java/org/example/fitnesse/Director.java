package org.example.fitnesse;
import java.util.Map;
import java.util.Objects;

public class Director extends Visitor implements VisitorInterface {

    public Director(){}
    public Director(String name, String secondName, int age) {
        super(name, secondName, age);
    }

    public Director(Map<String, String> initParams) {
        super(initParams);
    }

    @Override
    public void visitorShowMenu() {
        do {
            System.out.println();
            System.out.println("1) Open current members");
            System.out.println("2) Open current trainers");
            System.out.println("3) Open current admins");
            System.out.println("4) Open current programs");
            System.out.println("5) Open admins id");
            System.out.println("6) Open trainers id");
            System.out.println("0) Return to Director menu");
            visitorShowLogistic();
        } while (choice != 0);
    }

    @Override
    public void visitorAddMenu() {
        do {
            System.out.println();
            System.out.println("1) Add new admin");
            System.out.println("2) Add new trainer");
            System.out.println("0) Return to Director menu");
            visitorAddLogistic();
        } while (choice != 0);
    }

    @Override
    public void visitorDelMenu() {
        do {
            System.out.println();
            System.out.println("1) Remove trainer");
            System.out.println("2) Remove admin");
            System.out.println("0) Return to Director menu");
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
                    System.out.println(Main.site.adminList);
                    break;
                case 4:
                    System.out.println(Main.site.publicProgramList);
                    break;
                case 5:
                    showAdminList();
                    break;
                case 6:
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
                    addAdmin();
                    break;
                case 2:
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
            switch (choice) {
                case 1:
                    delTrainer();
                    visitorDelMenu();
                    break;
                case 2:
                    delAdmin();
                    visitorDelMenu();
                    break;

                case 0:
                    return;
            }
        }
        System.out.println();
    }

    private void showAdminList() {
        for (Admin elem : Main.site.adminList) {
            System.out.println(elem.name + " - " + elem.id);
        }
    }

    private void addAdmin() {
        Main.site.adminList.add(new Admin("", "", 23, 1));
    }

    private void delAdmin() {
        System.out.print("Enter admin id for remove: ");
        String enter = Main.scanner.nextLine();
        enter = Main.scanner.nextLine();
        for (int i = 0; i < Main.site.adminList.size(); i++) {
            if (Objects.equals(Main.site.adminList.get(i).id, enter)) {
                Main.site.adminList.remove(i);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Director{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                '}';
    }
}
