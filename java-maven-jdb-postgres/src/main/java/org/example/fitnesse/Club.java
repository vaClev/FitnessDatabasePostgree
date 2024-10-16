package org.example.fitnesse;
import java.util.*;

public class Club {

    List<Member> memberList = new LinkedList<>();
    List<Admin> adminList = new LinkedList<>();
    List<Trainer> trainerList = new LinkedList<>();
    List<PublicProgram> publicProgramList = new ArrayList<>();
    Map<Integer, Integer> prices = new HashMap<>();

    public void createPrices() {
        prices.put(2500, Calendar.MONTH);
        prices.put(25000, Calendar.YEAR);
    }

    public void createAdmins() {
        adminList.add(new Admin("John", "Smith", 23));
        adminList.add(new Admin("Lasly", "Blanket", 19));
    }

    public void createMembers() {
        memberList.add(new Member("Jack", "Nicolson", 45, 'm', 12345));
        memberList.add(new Member("Nickol", "Kidman", 34, 'f', 3214));
        //memberList.add(new Member("Bob", "Dilan", 30, 'm', 3214, "12345"));

    }

    public void createTrainers() {
        trainerList.add(new Trainer("Steven", "Smith", 30));
        trainerList.add(new Trainer("Jonathan", "Wallet", 34));
    }

    public void createPublicPrograms() {
        publicProgramList.add(new PublicProgram("CrossFit", trainerList.get(0), 1, 15));
        publicProgramList.add(new PublicProgram("Pilates", trainerList.get(1), 2, 15));
    }

    Director director = new Director("Super Boss", "Ivanov", 62);
}
