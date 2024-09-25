package org.example.fitnesse;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Trainer extends Visitor implements VisitorInterface {
    List<String> trainerProgramList = new LinkedList<>();

    public Trainer(){}
    public Trainer(String name, String secondName, int age) {
        super(name, secondName, age);
    }
    public Trainer(String name, String secondName, int age, int num) {
        super(name, secondName, age);
        anketa();
    }
    public Trainer(Map<String,String> initParams)
    {
        super(initParams);
    }

    public void anketa() {
        System.out.print("Enter name: ");
        this.name = Main.scanner.nextLine();
        this.name = Main.scanner.nextLine();
        System.out.print("Enter second name: ");
        this.secondName = Main.scanner.nextLine();
        System.out.print("Enter age: ");
        this.age = Main.scanner.nextInt();
        System.out.println("Trainer's id is: " + id);
    }

    @Override
    public void visitorShowMenu() {
        do {
            System.out.println();
            System.out.println("1) Open my current programs");
            System.out.println("2) Open members on my program");
            System.out.println("0) Return to Trainer menu");
            visitorShowLogistic();
        } while (choice != 0);
    }

    @Override
    public void visitorAddMenu() {
        do {
            System.out.println();
            System.out.println("1) Add new program");
            System.out.println("2) Change program");
            System.out.println("0) Return to Trainer menu");
            visitorAddLogistic();
        } while (choice != 0);
    }

    @Override
    public void visitorDelMenu() {
        do {
            System.out.println();
            System.out.println("1) Remove program");
            System.out.println("0) Return to Trainer menu");
            visitorDelLogistic();
        } while (choice != 0);
    }

    @Override
    public void visitorShowLogistic() {
        if (checkChoice()) {
            switch (choice) {
                case 1:
                    System.out.println(trainerProgramList);
                    break;
                case 2:
                    showMyProgramMembers();
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
                    addProgram();
                    break;
                case 2:
                    changeProgram();
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
                    delProgram();
                    break;
                case 0:
                    return;
            }
        }
        System.out.println();
    }

    public void showMyProgramMembers() {
        if (!trainerProgramList.isEmpty()) {
            showMyPrograms();
            choice = Main.scanner.nextInt();
            while (choice < 1 || choice > trainerProgramList.size()) {
                System.out.println("Wrong enter");
                choice = Main.scanner.nextInt();
            }
            for (PublicProgram elem : Main.site.publicProgramList) {
                if (elem.name == trainerProgramList.get(choice - 1)) {
                    System.out.println(elem.programList);
                }
            }
        } else {
            System.out.println("You haven't program");
        }
    }

    public void showMyPrograms() {
        for (int i = 0; i < trainerProgramList.size(); i++) {
            System.out.println((i + 1) + ") " + trainerProgramList.get(i));
        }
    }


    public void changeProgram() {
        System.out.println("Choose program to change:");
        showMyPrograms();
        changeProgramMenu();
        changeProgramRealize();
    }

    private void changeProgramMenu() {
        choice = Main.scanner.nextInt();
        String name = trainerProgramList.get(choice - 1);
        System.out.println("You choose " + trainerProgramList.get(choice - 1));
        System.out.println("What do you want to change?\n" +
                "1) Name\n" +
                "2) Duration\n" +
                "3) Max Members");
        choice = Main.scanner.nextInt();
    }

    private void changeProgramRealize() {
        System.out.print("Enter new value: ");
        for (PublicProgram elem : Main.site.publicProgramList) {
            if (elem.name == name) {
                if (choice == 1) {
                    elem.name = Main.scanner.nextLine();
                    elem.name = Main.scanner.nextLine();
                    trainerProgramList.set(trainerProgramList.indexOf(name), elem.name);
                } else if (choice == 2) {
                    elem.duration = Main.scanner.nextInt();
                } else if (choice == 3) {
                    elem.maxMembers = Main.scanner.nextInt();
                } else {
                    System.out.println("Wrong enter");
                }
            }
        }
    }

    public void addProgram() {
        System.out.print("Enter the name of new program - ");
        String programName = Main.scanner.nextLine();
        programName = Main.scanner.nextLine();
        Main.site.publicProgramList.add(new PublicProgram(programName, this));
        trainerProgramList.add(programName);
    }


    private void delProgram() {
        if (!trainerProgramList.isEmpty()) {
            showMyPrograms();
            choice = Main.scanner.nextInt();
            while (choice < 1 || choice > trainerProgramList.size()) {
                System.out.println("Wrong enter");
                choice = Main.scanner.nextInt();
            }
            for (int i = 0; i < Main.site.publicProgramList.size(); i++) {
                if (Main.site.publicProgramList.get(i).name == trainerProgramList.get(choice - 1)) {
                    for (Member elem : Main.site.publicProgramList.get(i).programList) {
                        elem.visitedList.remove(Main.site.publicProgramList.get(i).name);
                    }
                    Main.site.publicProgramList.remove(i);
                    trainerProgramList.remove(choice - 1);
                    break;
                }
            }


        } else {
            System.out.println("You haven't program");
        }
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", id='" + id + '\'' +
                '}';
    }
}
