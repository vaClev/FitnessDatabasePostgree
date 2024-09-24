package org.example.fitnesse;
import java.util.InputMismatchException;

public interface VisitorInterface {

    void setAll(String name, String secondName, int age, String id);
    default void visitorMenu(){
        do {
        System.out.println("Menu:");
        System.out.println("1) Open your current parameters");
        System.out.println("2) Add new parameters");
        System.out.println("3) Delete some parameters");
        System.out.println("0) Return to main menu");
        } while (visitorLogistic());
    }
    void visitorShowMenu();
    void visitorAddMenu();
    void visitorDelMenu();
    default boolean visitorLogistic() {
        int choice = -1;
        try {
            System.out.print("Enter your answer - ");
            choice = Main.scanner.nextInt();
            System.out.println();
        } catch ( InputMismatchException ex) {
            System.out.println("Error");
            Main.scanner.nextLine();
            return true;
        }
        switch (choice){
            case 1:
                visitorShowMenu();
                break;
            case 2:
                visitorAddMenu();
                break;
            case 3:
                visitorDelMenu();
                break;
            case 0:
                return false;
            default:
                System.out.println("Wrong answer");
                visitorMenu();
        }
        return true;
    }
    void visitorShowLogistic();
    void visitorAddLogistic();
    void visitorDelLogistic();
}
