package org.example.fitnesse;
import java.util.LinkedList;
import java.util.List;

public class PublicProgram extends DataBase{
    static int numOfPrograms = 0;
    String name;
    Trainer trainer;
    int duration;
    int maxMembers;
    List<Member> programList = new LinkedList<>();

    PublicProgram(String name, Trainer trainer) {
        this.trainer = trainer;
        this.name = name;
        System.out.print("Enter duration - ");
        duration = Main.scanner.nextInt();
        System.out.print("Enter max member - ");
        maxMembers = Main.scanner.nextInt();
    }

    PublicProgram(String name, Trainer trainer, int duration, int maxMembers) {
        this.name = name;
        this.trainer = trainer;
        this.duration = duration;
        this.maxMembers = maxMembers;
        Main.site.trainerList.get(numOfPrograms).trainerProgramList.add(this.name);
        numOfPrograms++;
    }

    @Override
    public String toString() {
        return "PublicProgram{" +
                "name='" + name + '\'' +
                ", trainer=" + trainer +
                '}';
    }
}
