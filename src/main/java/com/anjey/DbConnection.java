package com.anjey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Anjey on 11.01.2018.
 */
public class DbConnection {

    private final static String driver = "org.mariadb.jdbc.Driver";
    private final static String url = "jdbc:mariadb://localhost:3306/vaadindb";
    private final static String username = "root";
    private final static String password = "qwerty";
    private static Connection connection;

    public static Connection getConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        }
        catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return connection;
    }
}
