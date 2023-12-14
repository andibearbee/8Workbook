package com.pluralsight;

import java.sql.*;
import java.util.Scanner;

import java.util.*;

import static com.pluralsight.DataManager.*;




public class Main {


    static Scanner keyboard = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        homeScreen();

    }



    public static void homeScreen() throws SQLException {

        List<Actor> actors = new ArrayList<>();

        List<Film> films = new ArrayList<>();

        System.out.println("Welcome to the Sakila Database. What would you like to do?" +
                "\n 1) Display all actors" +
                "\n 2) Search Actor by Name" +
                "\n 3) Search Films by Actor" +
                "\n 0) Exit");

        int selection = keyboard.nextInt();
        keyboard.nextLine();

        switch (selection) {
            case 1 -> displayAllActors(dataSource);
            case 2 -> actorsBYName(actors);
            case 3 -> filmsBYActorId(films);
            case 0 -> System.exit(0);
            default -> {
                System.out.println("\nThat's not a valid selection. Please select 1, 2, 3 or 0.");
                homeScreen();
            }
        }

    }
    public static void actorsBYName(List<Actor> actors) throws SQLException {
        System.out.println("Enter the Actors First Name: ");
        String firstName = keyboard.nextLine();
        System.out.println("Enter the Actors Last Name: ");
        String lastName = keyboard.nextLine();
        dataManager.searchActorsByName(firstName, lastName);

        for (Actor actor : actors) {
            System.out.println("First Name: " + actor.getFirstName());
            System.out.println("Last Name:  " + actor.getLastName());
            System.out.println("Actor ID:   " + actor.getActorID());
            System.out.println("--------");
        }

    }

    public static void filmsBYActorId(List<Film> films) throws SQLException {
        displayAllActors(dataSource);
        System.out.println("Enter Actor Id of Filmography you want to view: ");
        int actorId = keyboard.nextInt();
        keyboard.nextLine();
        dataManager.searchFilmsByActorId(actorId);

        for (Film film : films) {
            System.out.println("Film ID:        " + film.getFilmID());
            System.out.println("First Name:     " + film.getFirstName());
            System.out.println("Last Name:      " + film.getLastName());
            System.out.println("Film Title:     " + film.getFilmTitle());
            System.out.println("Description:    " + film.getDescription());
            System.out.println("Release Year:   " + film.getReleaseYear());
            System.out.println("Runtime:        " + film.getLength());
            System.out.println("--------");
        }


    }


}
