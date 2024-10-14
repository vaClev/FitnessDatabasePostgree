package org.example.fitnesse;
import java.sql.SQLException;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Site site = new Site();

    public static void main(String[] args) throws SQLException {
        site.createPrices();
        site.greeting();
    }
}
