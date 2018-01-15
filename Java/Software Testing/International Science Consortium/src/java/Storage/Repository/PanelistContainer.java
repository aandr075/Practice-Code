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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This class is responsible for all panelist related operations in the database (create panelist, get panelist). It receives the respective entity objects and converts them into the appropriate SQL queries.
**/
public class PanelistContainer {
	/**
	 * Create Panelist in the Database 
	 *
	 * @param    panelist
	**/
	public static boolean createPanelist(HashMap<String, String> panelist) throws DoubleRegistrationException
    {
        Connection con = DBConnection.instance().getConnection();
        try { 
            con.setAutoCommit(true);
            Statement stmt = con.createStatement();
            ResultSet rs = null;

            //Lets check if the ISC ID is valid
            String SQL = "SELECT count(*) FROM iscids WHERE iscid =" + panelist.get("pISCID");
            rs = stmt.executeQuery(SQL);
            rs.next();
            int result = rs.getInt("count(*)");

            if(result == 0)
                return false; //Oops! ISC ID does not exist

            //Now that we know the ISC ID is valid, we will check to see if someone else has registered with it
            SQL = "SELECT count(*) FROM iscpanelist where ISCID=" + panelist.get("pISCID");
            rs = stmt.executeQuery(SQL);
            rs.next();
            result = rs.getInt("count(*)");

            if(result > 0)
                throw new DoubleRegistrationException(); //Oops! ISC ID has already been registered

            //SQL query command to get the next available id for the registrant
            SQL = "SELECT MAX(id) FROM iscusers";
            rs = stmt.executeQuery(SQL);
            rs.next();
            int id = rs.getInt("MAX(id)") + 1;

            //Alright, let's register this panelist!
            //First, we'll Insert values into the ISC Users table
            SQL = "INSERT INTO iscusers(ID, Username, Password, FirstName, LastName, Locked, UserType) " +
                    "VALUES(" + id + ", '" + panelist.get("username") + "', '" + panelist.get("password") + "', '" +
                    panelist.get("pFName") + "', '" + panelist.get("pLName") + "', 0, 0)";
            stmt.executeUpdate(SQL);
            System.out.print(panelist.get("pGender"));
            //Ok, now we'll Insert values into the ISC Panelist table
            SQL = "INSERT INTO iscpanelist(ISCID, UserID, Institution, Address, City, State, ZipCode, Telephone, Email, Gender, Ethnicity) " +
                    "VALUES(" + panelist.get("pISCID") + ", " + id + ", '" + panelist.get("pInstitution") + "', '" +
                    panelist.get("pAddress") + "', '" + panelist.get("pCity") + "', '" + panelist.get("pState")
                     + "', '" + panelist.get("pZip") + "', '" + panelist.get("pTelephone") + "', '" + panelist.get("pEmail")
                      + "', " + panelist.get("pGender") + ", '" + panelist.get("pEthnicity") + "')";          
            stmt.executeUpdate(SQL);
            System.out.print(panelist.get("pExpertise"));
            SQL = "INSERT INTO panelistexpertise(pid, expertiseid) values(" + panelist.get("pISCID") + ", " + panelist.get("pExpertise") + ")";
            stmt.executeUpdate(SQL);

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException ex) {
            Logger.getLogger(PanelistContainer.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (Exception ex1) {
                Logger.getLogger(PanelistContainer.class.getName()).log(Level.SEVERE, null, ex1);
            }

            return false;
        } catch (DoubleRegistrationException ex){
            try {
                con.rollback();
                con.setAutoCommit(true);
            } catch (Exception ex1) {
                Logger.getLogger(PanelistContainer.class.getName()).log(Level.SEVERE, null, ex1);
            }
            throw new DoubleRegistrationException();
        }

        return true;
	}
	
	/**
	 * Get a list of panelist given criteria from the Database 
	 *
	 * @param    criteria
	**/
	public static ArrayList<PanelistProfile> getPanelists(HashMap<String, String> sCriteria)
    {
        ArrayList<PanelistProfile> panelists = getAllPanelists();
        ArrayList<PanelistProfile> result = new ArrayList<PanelistProfile>();
        boolean remove = false;

        for(PanelistProfile panelist: panelists)
        {
            //For every panelist check if he or she meets the criteria.
            //If he or she does not meet the criteria, remove them from the list
            Iterator it = sCriteria.entrySet().iterator();

            while(it.hasNext())
            {
                Map.Entry<String, String> pairs = (Map.Entry<String, String>)it.next();
                remove = false;

                if(pairs.getValue().equals(""))
                    continue;

                if(!panelist.get(pairs.getKey()).equals(pairs.getValue()))
                {
                    remove = true;
                    break;
                }
            }

            if(!remove)
                result.add(panelist);
        }

        return result;
	}

    public static ArrayList<PanelistProfile> getAllPanelists()
    {
        ArrayList<PanelistProfile> panelists = new ArrayList<PanelistProfile>();
        try {
            //Let's get a connection to the database
            Connection con = DBConnection.instance().getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = null;
            PanelistProfile panelist;

            String SQL = "SELECT * FROM iscpanelist as t1, iscusers as t2, " +
                    "panelistexpertise as t3, expertise as t4 WHERE t1.userid = t2.id " +
                    "AND t1.iscid = t3.pid AND t3.expertiseid = t4.id";

            rs = stmt.executeQuery(SQL);

            while(rs.next())
            {
                panelist = new PanelistProfile();

                panelist.Address = rs.getString("Address");
                panelist.City = rs.getString("City");
                panelist.Email = rs.getString("Email");
                panelist.Ethnicity = rs.getString("Ethnicity");
                panelist.Expertise = rs.getString("Name");
                panelist.FirstName = rs.getString("FirstName");
                panelist.Gender = rs.getString("Gender");
                panelist.ISCID = rs.getString("ISCID");
                panelist.Institution = rs.getString("Institution");
                panelist.LastName = rs.getString("LastName");
                panelist.Locked = rs.getInt("Locked");
                panelist.State = rs.getString("State");
                panelist.Telephone = rs.getString("Telephone");
                panelist.Type = UserType.getType(rs.getInt("UserType"));
                panelist.UserID = rs.getInt("UserID");
                panelist.UserName = rs.getString("UserName");
                panelist.ZipCode = rs.getString("ZipCode");

                panelists.add(panelist);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PanelistContainer.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return panelists;
    }
}
