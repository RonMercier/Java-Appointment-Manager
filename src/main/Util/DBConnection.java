package main.Util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Ron Mercier - 001406973
 *
 * This class handles connections to the database.
 *
 */
public class DBConnection {
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//wgudb.ucertify.com:3306/";
    private static final String dbName = "WJ07Rbm";
    private static final String autoReconnect = "?autoReconnect=true";

    private static final String jdbcURL = protocol+vendorName+ipAddress+dbName+autoReconnect;

    private static final String MYSQLJDBCDriver = "com.mysql.cj.jdbc.Driver";

    private static final String username = "U07Rbm";

    private static final String password = "53689107299";

    private static Connection conn = null;

    /**
     * This static method starts a connection using the stored variables above.
     */
    public static void startConnection(){
        try{
            Class.forName(MYSQLJDBCDriver);
            conn = DriverManager.getConnection(jdbcURL,username,password);
            System.out.println("Connection started!");
        }catch (SQLException s){
            System.out.println("SQL Exception");
            s.printStackTrace();
        }catch (ClassNotFoundException c){
            System.out.println("MYSQLJDBCDriver not found");
        }
    }

    /**
     * This method gets the stored database connection.
     * @return Database connection
     */
    public static Connection getConnection(){
        return conn;
    }

    /**
     * This static method closes the database connection.
     */
    public static void closeConnection(){
        try {
            conn.close();
        }catch (Exception ignored){

        }
    }
}
