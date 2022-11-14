package application;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PizzaDatabase {
    static final String db_students = "jdbc:sqlite:db/students.db";
    static final String db_employees = "jdbc:sqlite:db/employees.db";

    //Used to connect to the Pizza Database and gather the requested information
    public static String[] getStudent(Integer id) {
        //Declare variables
        Connection conn = null;
        Statement statement;
        String[] data = new String[3];

        //Connect to students database
        try {
            //Create the database connection
            conn = DriverManager.getConnection(db_students);
            statement = conn.createStatement();
            statement.setQueryTimeout(15);
            System.out.println("Student pizza database connected!");

            //Select query for getting the user with specified ID
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM users WHERE id=%d", id));

            //Set user information
            data[0] = String.valueOf(rs.getInt("id"));
            data[1] = rs.getString("name");
            data[2] = String.valueOf(rs.getInt("balance"));
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

        //Debug
        System.out.println("Requested user: " + Arrays.toString(data) + "\n");

        //Return data array with requested info; NULL if failed
        return data;
    }

    /**Used to connect to the Pizza Database and gather the requested information
     * returnValue meaning: 0 = Password mismatch, 1 = Chef worker, 2 = Agent worker
     */
    public static int getEmployee(String username, String password) {
        //Declare variables
        Connection conn = null;
        Statement statement;
        String pass;
        String worker;
        int returnValue = 0;

        //Connect to employee database
        try {
            //Create the database connection
            conn = DriverManager.getConnection(db_employees);
            statement = conn.createStatement();
            statement.setQueryTimeout(15);
            System.out.println("Employee pizza database connected!");

            //Select query for getting the employee with specified username
            ResultSet rs = statement.executeQuery(String.format("SELECT password, worker FROM employees WHERE username='%s'", username));

            //Set employee information
            pass = rs.getString("password");
            worker = rs.getString("worker");

            //Check if the passwords are equal
            if (Objects.equals(password, pass)) {
                if (Objects.equals(worker, "Chef")) {
                    returnValue = 1;
                }
                else if (Objects.equals(worker, "Agent")) {
                    returnValue = 2;
                }
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

        //Debug
        System.out.println("Return Value: " + returnValue + "\n");

        //Return password check boolean
        return returnValue;
    }

    //Used to connect to the Pizza Database and gather the requested information
    public static void changeStudentBalance(Integer id, Integer balance) {
        //Declare variables
        Connection conn = null;
        Statement statement;
        String[] data = new String[3];

        //Connect to students database
        try {
            //Create the database connection
            conn = DriverManager.getConnection(db_students);
            statement = conn.createStatement();
            statement.setQueryTimeout(15);
            System.out.println("Student pizza database connected!");

            //Select query for getting the user with specified ID
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM users WHERE id=%d", id));

            //Set user information
            data[0] = String.valueOf(rs.getInt("id"));
            data[1] = rs.getString("name");
            data[2] = String.valueOf(rs.getInt("balance"));

            //Update balance
            statement.executeUpdate(String.format("UPDATE users SET balance=%d WHERE id=%d", (Integer.parseInt(data[2]) - balance), id));
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

    //Used to create the databases
    public static void createDatabases() {
        //Declare variables
        Connection conn = null;
        Statement statement;
        List<String> data;
        String[] splitData;

        try {
            //Create the student database connection
            conn = DriverManager.getConnection(db_students);
            statement = conn.createStatement();
            statement.setQueryTimeout(15);
            System.out.println("Student pizza database connected!\n");

            //Drop the current table and create a new one with data (Used to refresh balances, can be removed)
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            statement.executeUpdate("CREATE TABLE users (" +
                    "id INTEGER, " +
                    "name VARCHAR(32), " +
                    "balance INTEGER, " +
                    "PRIMARY KEY (id)" +
                    ")");

            //Get all the data from the students CSV file
            data = getdata("students");

            //Insert the student data into the database
            for (String user : data) {
                splitData = user.split(",");
                statement.executeUpdate(String.format("INSERT INTO users VALUES (%d, '%s', 100)",
                        Integer.parseInt(splitData[0]), splitData[1]));
            }

            //Create the employee database connection
            conn = DriverManager.getConnection(db_employees);
            statement = conn.createStatement();
            statement.setQueryTimeout(15);
            System.out.println("Employee pizza database connected!\n");

            //Drop the current table and create a new one with data (Used to refresh information, can be removed)
            statement.executeUpdate("DROP TABLE IF EXISTS employees");
            statement.executeUpdate("CREATE TABLE employees (" +
                    "username VARCHAR(32), " +
                    "password VARCHAR(32), " +
                    "worker VARCHAR(32), " +
                    "PRIMARY KEY (username)" +
                    ")");

            //Get all the data from the employee CSV file
            data = getdata("employees");

            //Insert the employee data into the database
            for (String user : data) {
                splitData = user.split(",");
                statement.executeUpdate(String.format("INSERT INTO employees VALUES ('%s', '%s', '%s')",
                        splitData[0], splitData[1], splitData[2]));
            }
        }
        //Most likely database isn't found so throw an error
        catch (SQLException | IOException e) {
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

    //Prints all the columns in the database to the terminal; Used for debugging purposes
    public static void printDatabase(String database) {
        Connection conn = null;
        Statement statement;
        try {
            if (Objects.equals(database, "students")) {
                //Create the student database connection
                conn = DriverManager.getConnection(db_students);
                statement = conn.createStatement();
                statement.setQueryTimeout(15);
                System.out.println("Student pizza database connected!\n");

                //Print out all the data from the table
                ResultSet rs = statement.executeQuery("SELECT * FROM users");
                while (rs.next()) {
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("NAME: " + rs.getString("name"));
                    System.out.println("BALANCE: " + rs.getInt("balance") + "\n");
                }
            }
            else if (Objects.equals(database, "employees")) {
                //Create the student database connection
                conn = DriverManager.getConnection(db_employees);
                statement = conn.createStatement();
                statement.setQueryTimeout(15);
                System.out.println("Employee pizza database connected!\n");

                //Print out all the data from the table
                ResultSet rs = statement.executeQuery("SELECT * FROM employees");
                while (rs.next()) {
                    System.out.println("USERNAME: " + rs.getString("username"));
                    System.out.println("PASSWORD: " + rs.getString("password"));
                    System.out.println("WORKER: " + rs.getString("worker") + "\n");
                }
            }
        }
        //Couldn't connect to DB
        catch (SQLException e) {
            e.printStackTrace();
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

    public static List<String> getdata(String fileName) throws IOException {
        //Get current file directory on the system
        String dir = System.getProperty("user.dir");
        String fileLocation = String.format("%s/db/%s.csv", dir, fileName);

        //Create a BufferedReader to parse data and store it inside a list
        BufferedReader reader = new BufferedReader(new FileReader(fileLocation));
        List<String> lines = new ArrayList<>();
        String line = null;

        //Take individual lines and put them into a list
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        //Prints out all the data inside the *.csv file; Uncomment for debugging
        //for (String str : lines) {
        //    System.out.println(str);
        //}

        //Return String list of data
        return lines;
    }
}
