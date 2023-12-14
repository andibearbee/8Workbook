package com.pluralsight;
import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

public class DAO {

    // Get the username and password
    static String url = "jdbc:mysql://127.0.0.1:3306/sakila";
    static String user = "root"; //username
    static String password = System.getenv("MY_SQL_PASSWORD");

    // Create the datasource
     static BasicDataSource dataSource = new BasicDataSource();

// Configure the datasource
    static {
        dataSource.setUrl("jdbc:mysql://localhost:3306/Northwind");
        dataSource.setUsername(user);
        dataSource.setPassword(password);
}
    //Datasource constructor
    public DAO(DataSource dataSource) throws SQLException {
        this.dataSource = (BasicDataSource) dataSource;
    }

    // Create the DataManager
    DAO dataManager = new DAO(dataSource);

    // Interact with the database
    List<Shippers> shippers = dataManager.getAllShippers();



    public DAO(BasicDataSource dataSource) throws SQLException {
    }


    public static List<Shippers> getAllShippers() throws SQLException {
        List<Shippers> shippers = new ArrayList<>();
        String sql = "SELECT ShipperId " +
                " , CompanyName " +
                " , Phone " +
                "FROM shippers;";
        Connection conn = dataSource.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet results = statement.executeQuery(sql);
        while (results.next()) {
            int id = results.getInt("ShipperID");
            String name = results.getString("CompanyName");
            String phone = results.getString("Phone");
            Shippers shipper = new Shippers (id, name, phone);
            shippers.add(shipper);
        }

        // display shippers
        for (Shippers shipper : shippers) {
            System.out.println("ShipperId: " + shipper.getShipperId());
            System.out.println("CompanyName:  " + shipper.getCompanyName());
            System.out.println("Phone:   " + shipper.getPhoneNum());
            System.out.println("--------");
        }
        return shippers;
    }




}
