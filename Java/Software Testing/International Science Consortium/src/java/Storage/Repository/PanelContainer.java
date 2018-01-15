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

import java.util.ArrayList;
import java.awt.Panel;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class is responsible for all panel related operations in the database (create panel, add panelist to panel, update panel status, get panels). It receives the respective entity objects and converts them into the appropriate SQL queries.
**/
public class PanelContainer {
	
    public static int getNumPanels()
    {
        try {
            //Get a handle on the connection to the database
            Connection con = DBConnection.instance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = null;

            String SQL = "SELECT Count(*) FROM panel";
            rs = stmt.executeQuery(SQL);
            rs.next();

            return rs.getInt("Count(*)");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        return -1;
    }

    /**
	 * Create Panel to the Database
	 *
	 * @param    panel
	**/
	public static boolean createPanel(String panelName, String panelDescription, int employeeID)
    {
        try {
            //Get a handle on the connection to the database
            Connection con = DBConnection.instance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = null;

            //Let's figure out what ID we are going to give our new panel
            String SQL = "SELECT MAX(PanelID) FROM panel";
            rs = stmt.executeQuery(SQL);
            rs.next();

            int panelID = rs.getInt("MAX(PanelID)") + 1;

            //Ok, now lets store our panel in the panel table
            SQL = "INSERT INTO panel(PanelID, Name, Description) VALUES('" +
                  panelID + "', '" + panelName + "', '" + panelDescription + "')";

            stmt.executeUpdate(SQL);

            //Set the default status to In Progress
            SQL = "INSERT INTO panelhistory(PanelID, PanelStatus, Comments) VALUES(" +
                    panelID + ", 'In Progress', 'Panel Created')";
            stmt.executeUpdate(SQL);

            //We'll give the employee who created the panel access to it
            SQL = "INSERT INTO panelaccess(EmployeeID, PanelID) VALUES(" +
                    employeeID + ", " + panelID + ")";
            stmt.executeUpdate(SQL);

        } catch (SQLException ex) {
            Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

         return true;
	}
	
	/**
	 * Adds Panelist to a Panel in the Database 
	 *
	 * @param    panelist
	 * @param    panel
	**/
	public static boolean addPanelistToPanel(int panelistID, int panelID)
    {
        try {
            //Get a connection to the database
            Connection con = DBConnection.instance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = null;

            //Check if the panelist is arleady serving on the panel
            String SQL = "SELECT count(*) FROM servingpanelists WHERE panelistid=" + panelistID +
                    " AND panelID=" + panelID;
            rs = stmt.executeQuery(SQL);
            rs.next();
            if(rs.getInt("count(*)") > 0)
                return false; //panelist is already serving on panel!

            SQL = "INSERT INTO servingpanelists(PanelistID, PanelID) VALUES(" + panelistID + ", "
                    + panelID + ")";

            stmt.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
	}
	
	/**
	 * Updates Panel Status to the Database 
	 *
	 * @param    status
	 * @param    panel
	**/
	public static boolean updatePanelStatus(String theStatus, String comments, int panelID)
    {
        try {
            Connection con = DBConnection.instance().getConnection();
            Statement stmt = con.createStatement();

            String SQL = "INSERT INTO panelhistory(PanelID, PanelStatus, Comments) VALUES(" +
                    panelID + ", '" + theStatus + "', '" + comments + "')";
            stmt.executeUpdate(SQL);

        } catch (SQLException ex) {
            Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
	}

    public static Panel getPanel(int panelID)
    {
        Connection con = DBConnection.instance().getConnection();
        Panel thePanel = new Panel();

        try {
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            Status status;
            ResultSet rs = null;
            ResultSet rs2 = null;
            System.out.println();
            String SQL = "SELECT name, description FROM panel WHERE panelID=" + panelID;
            rs = stmt.executeQuery(SQL);
            rs.next();
            thePanel.PanelID = panelID;
            thePanel.Name = rs.getString("Name");
            thePanel.Description = rs.getString("Description");
            thePanel.Employees = new ArrayList<String>();
            thePanel.ServingPanelists = new ArrayList<String>();

            //Get the status of the panel
            thePanel.StatusHistory = new ArrayList<Status>();

            SQL = "SELECT PanelStatus, Comments, Date FROM panel as t1, panelhistory as t2" +
                    " WHERE t1.panelid = t2.panelid and t1.panelid =" + thePanel.PanelID +
                    " ORDER BY t2.Date ASC";

            rs2 = stmt2.executeQuery(SQL);

            while(rs2.next())
            {
                status = new Status();
                status.Title = rs2.getString("PanelStatus");
                status.Comments = rs2.getString("Comments");
                status.Date = rs2.getTimestamp("Date");
                thePanel.StatusHistory.add(status);
            }


            //Now lets get a lit of all employees that can add or remove panelists from the panel
            SQL = "SELECT t3.FirstName, t3.LastName FROM panelaccess AS t1, iscemployee AS t2, " +
                    "iscusers AS t3 WHERE t1.PanelID =" + thePanel.PanelID + " AND t1.EmployeeID = t2.EID" +
                    " AND t2.UserID = t3.ID";
            rs2 = stmt2.executeQuery(SQL);

            while(rs2.next())
                thePanel.Employees.add(new String(rs2.getString("FirstName") + " " + rs2.getString("LastName")));

            //Finally, lets get a list of all panelists serving on the panel
            SQL = "SELECT t3.FirstName, t3.LastName FROM servingpanelists as t1, iscpanelist as t2," +
                    " iscusers as t3 WHERE PanelID =" + thePanel.PanelID + " AND " +
                    "t1.PanelistID = t2.ISCID AND t2.UserID = t3.ID";
            rs2 = stmt2.executeQuery(SQL);

            while(rs2.next())
                thePanel.ServingPanelists.add(new String(rs2.getString("FirstName") + " " + rs2.getString("LastName")));

        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

        return thePanel;
    }
	
	/**
	 * Gets Panels from the Database 
	 *
	 * @param    employeeID
	**/
	public static ArrayList<Panel> getPanels(int employeeID)
    {
        Connection con = DBConnection.instance().getConnection();
        ArrayList<Panel> panels = new ArrayList<Panel>();
        Panel thePanel;
        System.out.println("Inside the PanelContainer: "  );

        try {
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            Status status;
            ResultSet rs = null;
            ResultSet rs2 = null;
            //SQL query command to retrieve panels that Employee can control
            String SQL = "SELECT t1.PanelID, Name, Description FROM panelaccess as t1, panel as t2 WHERE t1.EmployeeID =" + employeeID + " AND t1.PanelID = t2.PanelID";
            rs = stmt.executeQuery(SQL);
            System.out.println("Flag1");

            while(rs.next())
            {
                thePanel = new Panel();
                
                System.out.println("Flag2");

                thePanel.PanelID = rs.getInt("PanelID");
                thePanel.Name = rs.getString("Name");
                thePanel.Description = rs.getString("Description");
                thePanel.Employees = new ArrayList<String>();
                thePanel.ServingPanelists = new ArrayList<String>();

                //Get the status of the panel
                thePanel.StatusHistory = new ArrayList<Status>();

                SQL = "SELECT PanelStatus, Comments, Date FROM panel as t1, panelhistory as t2" +
                        " WHERE t1.panelid = t2.panelid and t1.panelid =" + thePanel.PanelID +
                        " ORDER BY t2.date ASC";

                rs2 = stmt2.executeQuery(SQL);
                
                while(rs2.next())
                {
                	System.out.println("Flag3");
                    status = new Status();
                    status.Title = rs2.getString("PanelStatus");
                    status.Comments = rs2.getString("Comments");
                    status.Date = rs2.getTimestamp("Date");
                    thePanel.StatusHistory.add(status);
                }
                    

                //Now lets get a lit of all employees that can add or remove panelists from the panel
                SQL = "SELECT t3.FirstName, t3.LastName FROM panelaccess AS t1, iscemployee AS t2, " +
                        "iscusers AS t3 WHERE t1.PanelID =" + thePanel.PanelID + " AND t1.EmployeeID = t2.EID" +
                        " AND t2.UserID = t3.ID";
                rs2 = stmt2.executeQuery(SQL);

                while(rs2.next())
                    thePanel.Employees.add(new String(rs2.getString("FirstName") + " " + rs2.getString("LastName")));

                //Finally, lets get a list of all panelists serving on the panel
                SQL = "SELECT t3.FirstName, t3.LastName FROM servingpanelists as t1, iscpanelist as t2," +
                        " iscusers as t3 WHERE PanelID =" + thePanel.PanelID + " AND " +
                        "t1.PanelistID = t2.ISCID AND t2.UserID = t3.ID";
                rs2 = stmt2.executeQuery(SQL);

                while(rs2.next())
                    thePanel.ServingPanelists.add(new String(rs2.getString("FirstName") + " " + rs2.getString("LastName")));

                panels.add(thePanel);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PanelContainer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        System.out.println("Flag4");
        return panels;

	}
}
