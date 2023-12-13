package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;


public class DataManager {

    private DataSource dataSource;

    public DataManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    static Scanner keyboard = new Scanner(System.in);

    public static void displayAllActors(BasicDataSource dataSource) throws SQLException {


        String query = "SELECT * FROM actor";
        //try with resources eliminates the need for a "finally" statement
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery(query);
        ) {

            // Processing the result set
            while (resultSet.next()) {
                // Replace with your column names and types
                System.out.print(resultSet.getString("actor_id") + "|");
                System.out.print(resultSet.getString("first_name") + "|");
                System.out.print(resultSet.getString("last_name") + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<Actor> searchActorsByName(String firstName, String lastName) {
        List<Actor> actors = new ArrayList<>();
        String query = "SELECT first_name, last_name, actor_id " +
                "FROM actor " +
                "WHERE first_name LIKE ? AND last_name LIKE ? " +
                "ORDER BY first_name;";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement prepStatement = conn.prepareStatement(query)) {

            prepStatement.setString(1, "%" + firstName + "%");
            prepStatement.setString(2, "%" + lastName + "%");
            ResultSet result = prepStatement.executeQuery();

            while (result.next()) {
                Actor actor = new Actor();
                actor.setFirstName(result.getString("first_name"));
                actor.setLastName(result.getString("last_name"));
                actor.setActorID(result.getInt("actor_id"));
                actors.add(actor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actors;
    }

    public List<Film> searchFilmsByActorId(int actorID) {
        List<Film> filmographies = new ArrayList<>();
        String query = "SELECT film.film_id, film.release_year, film.length, actor.first_name, actor.last_name, film.title, film.description " +
                "FROM actor " +
                "JOIN film_actor ON actor.actor_id = film_actor.actor_id " +
                "JOIN film ON film_actor.film_id = film.film_id " +
                "WHERE actor.actor_id = ? " +
                "ORDER BY actor.first_name";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement prepStatement = conn.prepareStatement(query)) {

            prepStatement.setInt(1, actorID);
            ResultSet result = prepStatement.executeQuery();

            while (result.next()) {
                Film filmography = new Film();
                filmography.setFilmID(result.getInt("film_id"));
                filmography.setReleaseYear(result.getInt("release_year"));
                filmography.setLength(result.getInt("length"));
                filmography.setFirstName(result.getString("first_name"));
                filmography.setLastName(result.getString("last_name"));
                filmography.setFilmTitle(result.getString("title"));

                // Handling CLOB for description
                Clob descriptionClob = result.getClob("description");
                if (descriptionClob != null) {
                    String description = descriptionClob.getSubString(1, (int) descriptionClob.length());
                    filmography.setDescription(description);
                }

                filmographies.add(filmography);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filmographies;
    }

}