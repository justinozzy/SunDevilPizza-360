package application;

import java.sql.*;

public class PizzaDatabase {
    static final String db_url = "jdbc:sqlite:db/test.db";

    //TODO: Need to move void function to string value so that Linked Lists can be populated from a return
    //TODO: Add parameters for the connection to determine which user to select and which database to contact

    //Used to connect to the Pizza Database and gather the requested information
    public static void connect() {
        Connection conn = null;
        Statement statement = null;
        try {
            //Create the database connection
            conn = DriverManager.getConnection(db_url);
            statement = conn.createStatement();
            statement.setQueryTimeout(15);
            System.out.println("Pizza database connected!\n");

            statement.executeUpdate("DROP TABLE IF EXISTS testable");
            statement.executeUpdate("CREATE TABLE testable (age INTEGER, name VARCHAR(32), PRIMARY KEY (age))");
            statement.executeUpdate("INSERT INTO testable VALUES (1, 'FIRST')");
            statement.executeUpdate("INSERT INTO testable VALUES (2, 'SECOND')");
            statement.executeUpdate("INSERT INTO testable VALUES (3, 'THIRD')");

            ResultSet rs = statement.executeQuery("SELECT * FROM testable");
            while (rs.next()) {
                //TODO: Parse database information for requested employee / student
                System.out.println("ID: " + rs.getInt("age"));
                System.out.println("ID: " + rs.getString("name"));
            }
        }
        //Most likely database isn't found so throw an error
        catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            //Close the database connection
            try {
                if (conn != null) {
                    conn.close();
                }
            }
            //Failure to close connection
            catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
