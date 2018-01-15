/*
 * Arelys De La Guardia
 * Deisy Hernandez
 * Michael Smythers
 * Daniel Galano
 * Jairo Pava
 *
 * International Science Consoritum Control System
 *
 * December 1, 2009
 */

package Storage.Repository;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection
{
    private static DBConnection _instance;
    private Connection con;

    protected DBConnection()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String connectionUrl = "jdbc:mysql://127.0.0.1/mydb?" +
                                   "user=admin&password=cen4010" +
                                   "&useSSL=false";

            con = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            System.out.println("SQL Exception: "+ e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Class Not Found Exception: "+ cE.toString());
        }
    }

    public static DBConnection instance()
    {
        try {
            
            if (_instance == null || _instance.getConnection().isClosed())
                _instance = new DBConnection();

        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return _instance;
    }

    public Connection getConnection()
    {
        return con;
    }
}
