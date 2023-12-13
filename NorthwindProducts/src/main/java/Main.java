import java.sql.*;
import java.util.Scanner;

public class Main {
    static String url = "jdbc:mysql://127.0.0.1:3306/northwind";
    static String user = "root"; //username
    static String password = System.getenv("MY-SQL-PASSWORD");
    public static void main(String[] args) throws SQLException {

        homeScreen();

    }

    public static void homeScreen () throws SQLException {
        System.out.println("What would you like to do?" +
                "\n 1) Display all products" +
                "\n 2) Display all customers" +
                "\n 3) Display all categories" +
                "\n 0) Exit");

        Scanner keyboard = new Scanner(System.in);
        int selection = keyboard.nextInt();

        switch (selection) {
            case 1 ->
                displayAll();
            case 2 ->
                displayCustomers();
            case 3 ->
                displayCategories();
            case 0 ->
                System.exit(0);
            default -> {
                System.out.println("\nThat's not a valid selection. Please select 1, 2 or 0.");
                homeScreen();
            }
        }

    }
    public static void displayAll() throws SQLException {

        String query = "SELECT * FROM Products";

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //try/catch/finally
        try {
            // Establishing connection
            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement(query);

            // Executing query
            resultSet = preparedStatement.executeQuery(query);

            // Processing the result set
            while (resultSet.next()) {
                // Replace with your column names and types
                System.out.print(resultSet.getString("ProductId") + "|");
                System.out.print(resultSet.getString("ProductName") + "|");
                System.out.print(resultSet.getString("UnitPrice") + "|");
                System.out.println(resultSet.getString("UnitsInStock") + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Closing resources
        finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }

        homeScreen();

    }
    public static void displayCustomers() throws SQLException {

        String query = "SELECT * FROM Customers ORDER BY Country";
        //try with resources eliminates the need for a "finally" statement
        try (
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery(query);
            )
        {

            // Processing the result set
            while (resultSet.next()) {
                // Replace with your column names and types
                System.out.print(resultSet.getString("ContactName") + "|");
                System.out.println(resultSet.getString("CompanyName") + "|");
                System.out.print(resultSet.getString("City") + "|");
                System.out.println(resultSet.getString("Country") + "|");
                System.out.println(resultSet.getString("Phone") + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        homeScreen();

        }
    public static void displayCategories() throws SQLException {

        String query = "SELECT * FROM Categories ORDER BY CategoryId";
        //try with resources eliminates the need for a "finally" statement
        try (
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery(query);
            )
        {


            // Processing the result set
            while (resultSet.next()) {
                // Replace with your column names and types
                System.out.print(resultSet.getString("CategoryId") + "|");
                System.out.println(resultSet.getString("CategoryName") + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        displayByCatId();

    }

    public static void displayByCatId() throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {

            System.out.println("Enter Category Id of Products you would like to view. ");
            Scanner keyboard = new Scanner(System.in);
            int categoryIdInput = keyboard.nextInt();
            String userQuery = "SELECT * FROM Products WHERE CategoryID = ?";

            connection = DriverManager.getConnection(url, user, password);
            preparedStatement = connection.prepareStatement(userQuery);
            preparedStatement.setInt(1, categoryIdInput);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.print(resultSet.getString("ProductId") + "|");
                System.out.print(resultSet.getString("ProductName") + "|");
                System.out.print(resultSet.getString("UnitPrice") + "|");
                System.out.print(resultSet.getString("UnitsInStock") + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            if (resultSet != null) resultSet.close();
            if (preparedStatement != null) preparedStatement.close();
            if (connection != null) connection.close();
        }
    }





}
