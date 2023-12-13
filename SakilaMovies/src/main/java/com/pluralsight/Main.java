package com.pluralsight;

import java.sql.*;
import java.util.Scanner;
import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import com.pluralsight.DataManager.*;

import static com.pluralsight.DataManager.displayAllActors;
import com.pluralsight.DataManager.searchActorsByName;
import com.pluralsight.DataManager.searchFilmsByActorId;



public class Main {
    static String url = "jdbc:mysql://127.0.0.1:3306/sakila";
    static String user = "root"; //username
    static String password = System.getenv("MY_SQL_PASSWORD");

    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        homeScreen();

    }

    public static void homeScreen() throws SQLException {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/sakila");
        dataSource.setUsername(user);
        dataSource.setPassword(password);

        System.out.println("Welcome to the Sakila Database. What would you like to do?" +
                "\n 1) Display all actors" +
                "\n 2) Search Actor by Name" +
                "\n 3) Search Films by Actor" +
                "\n 0) Exit");

        int selection = keyboard.nextInt();
        keyboard.nextLine();

        switch (selection) {
            case 1 -> displayAllActors(dataSource);
            case 2 -> searchActorsByName(String firstName, String lastName);
            case 3 -> searchFilmsByActorId(int actorId);
            case 0 -> System.exit(0);
            default -> {
                System.out.println("\nThat's not a valid selection. Please select 1, 2 or 0.");
                homeScreen();
            }
        }

    }


}
