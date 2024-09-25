package org.example.fitnesse;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public abstract class Visitor extends DataBase {
    String name;
    String secondName;
    int age;
    String id;
    int choice = -1;

    public Visitor() {
    }

    public Visitor(String name, String secondName, int age, String id) {
        this.name = name;
        this.secondName = secondName;
        this.age = age;
        this.id = id;
    }
    public Visitor(String name, String secondName, int age) {
        this.name = name;
        this.secondName = secondName;
        this.age = age;
        id = UUID.randomUUID().toString().substring(0, 5);
    }
    public Visitor(Map<String, String> initParams) {
        this(initParams.get("name"),
                initParams.get("secondName"),
                Integer.parseInt(initParams.get("age")),
                initParams.get("id")
        );
    }

    public void setAll(String name, String secondName, int age, String id) {
        this.name = name;
        this.secondName = secondName;
        this.age = age;
        this.id = id;
    }

    public void addTrainer() {
        Main.site.trainerList.add(new Trainer("", "", 20, 1));
    }

    public void delTrainer() {
        System.out.print("Enter trainer's id for remove: ");
        String enter = Main.scanner.nextLine();
        enter = Main.scanner.nextLine();
        for (int i = 0; i < Main.site.trainerList.size(); i++) {
            if (Objects.equals(Main.site.trainerList.get(i).id, enter)) {
                Main.site.trainerList.remove(i);
                break;
            }
        }
    }

    boolean checkChoice() {
        try {
            System.out.print("Enter your answer - ");
            choice = Main.scanner.nextInt();
            System.out.println();
            return true;
        } catch (InputMismatchException ex) {
            System.out.println("Error");
            Main.scanner.nextLine();
            choice = -1;
            System.out.println();
            return false;
        }
    }


}
