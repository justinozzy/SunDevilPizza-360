package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PizzaDatabase {
    static final String db_url = "jdbc:sqlite:file://$PROJECT_DIR$/db/test.db";

    public static void connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(db_url);
            Statement statement = conn.createStatement();
            statement.setQueryTimeout(15);

            statement.executeUpdate("SQL STATEMENT GOES HERE");
        }
        catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
