
package hashmapex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ConnectionDB {
    private final static String URL = "jdbc:mysql://localhost";
    private final static String PORT = "3306";
    private final static String USER = "root";
    private final static String PASS = "";
    private final static String DB = "dreamhome";
    //private Connection con
    
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String urlForConnection = URL+":"+PORT+"/"+DB;
            Connection con = DriverManager.getConnection(urlForConnection, USER, PASS);
            return con;
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
