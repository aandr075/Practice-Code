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

package ApplicationLogic;

import Storage.DBManager;
import Storage.Repository.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.*;

/**
 * This handles all the functionality related to panels (creating a panel, adding a panelist to a panel, updating the panel status and getting panels). It performs all the validation required to avoid duplicate panelists in the same panel and other related inaccuracies
**/
public class PanelManager {

    /**
	 * It creates a panel depending if the information is correct, and return true if the creation was sucessful and false otherwise
	 *
	 * @param    newPanel
	**/
	public static void createPanel(HttpServletRequest req, HttpServletResponse res, HttpSession hs) throws IOException
    {
        String panelName = req.getParameter("panelName");
        String panelDescription = req.getParameter("panelDescription");
        int employeeID = ((EmployeeProfile)hs.getAttribute("User Profile")).EmployeeID;
        boolean result;

        //Let's validate our fields
        if(panelName.equals("") || panelDescription.equals(""))
            result =  false;
        else
            result = DBManager.createPanel(panelName, panelDescription, employeeID);

        //We'll now display a message indicating the success of the operation to the user
        if(result)
            res.sendRedirect("messagePage?messageCode=Panel has been successfully created.");
        else
            res.sendRedirect("errorPage?errorCode=There was an error creating the panel. Please try again.");

	}
	
	/**
	 * Performs the validation required to add panelist to a given panel
	 *
	 * @param    panelist
	 * @param    panel
	**/
	public static void addPanelistToPanel(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        int panelID = Integer.parseInt(req.getParameter("addToPanel"));
        int panelistID = Integer.parseInt(req.getParameter("panelistID"));

        boolean result = DBManager.addPanelistToPanel(panelistID, panelID);

        if(result)
            res.sendRedirect("messagePage?messageCode=Panelist has been successfully added.");
        else
            res.sendRedirect("errorPage?errorCode=Error adding panelist. Please try again.");
	}
	
	/**
	 * It update the panels status based on the given information
	 *
	 * @param    panelID
	 * @param    status
	**/
	public static void updatePanelStatus(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        String theStatus = req.getParameter("panelStatus");
        String comments = req.getParameter("statusComments");
        int panelID = Integer.parseInt(req.getParameter("panelID"));

        boolean result = DBManager.updatePanelStatus(theStatus, comments, panelID);

        if(result)
            res.sendRedirect("messagePage?messageCode=Panel status updated.");
        else
            res.sendRedirect("errorPage?errorCode=Error updating panel. Please try again.");

	}
	
	/**
	 * Returns the panel information 
	 *
	 * @param    panelID
	**/
	public static Panel getPanel(HttpServletRequest req) {
        return null;
	}
	
	/**
	 * Returns the employee's panels based on the giving  information
	 *
	 * @param    employeeID
	**/
	public static ArrayList<Panel> getEmployeePanels(HttpSession session)
    {
        EmployeeProfile userProfile = (EmployeeProfile)session.getAttribute("User Profile");
        return DBManager.getPanels(userProfile.EmployeeID);
	}
	
	/**
	 * Returns true if the given panelist is registered
	 *
	 * @param    iscid
	**/
	private static boolean isPanelistRegistered(HttpServletRequest req) {
        return false;
	}
}
