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

/**
 * This class is in charge of validating the users from the database. It also provides an user type identifier to the client to distinguish between different users.
**/
public class AccountManager {
	/**
	 * Validates if the given username exists in the database and returns its type
	 *
	 * @param    username
	 * @param    password
	**/
	public static UserProfile validateUsername(String username, String password) {
        Connection con = DBConnection.instance().getConnection();

        try{
            Statement stmt = null;
            ResultSet rs = null;
            ResultSet rs2 = null;
            //SQL query command
            String SQL = "SELECT id FROM iscusers WHERE username=\"" + username + "\" AND password=\"" + password +"\"";
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);            

            if(rs.next())
            {
                //User exists in database
                int id = rs.getInt("id");
                UserProfile user = new UserProfile();
                SQL = "SELECT * FROM iscusers WHERE id=" + id;
                stmt = con.createStatement();
                rs = stmt.executeQuery(SQL);
                rs.next();
                user.UserID = rs.getInt("ID");
                user.UserName = rs.getString("Username");
                user.FirstName = rs.getString("FirstName");
                user.LastName = rs.getString("LastName");
                user.Locked = rs.getInt("Locked");
                user.Type = UserType.getType(rs.getInt("UserType"));

                System.out.println("User Type" + user.Type);

                //grab either the employee or ISC id and return the appropriate variable
                if(user.Type == UserType.EMPLOYEE)
                {
                    EmployeeProfile employee = new EmployeeProfile(user);
                    SQL = "SELECT * FROM iscemployee WHERE userid=" + id;
                    rs2=stmt.executeQuery(SQL);
                    rs2.next();
                    employee.EmployeeID = rs2.getInt("EID");
                    employee.TypeOfEmployee = rs2.getInt("Type");

                    return employee;
                }

                return user;
            }

        }catch(SQLException e) {
            System.out.println("SQL Exception: "+ e.toString());
            e.printStackTrace();
        }
        
        return null;
	}
}
