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

package Storage;

import Storage.Repository.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is the facade to the complete Repository. It serves as the main entry point for all database access operations.
**/
public class DBManager {

	/**
	 * Facade method to Create Panelist 
	 *
	 * @param    panelist
	**/
	public static boolean createPanelist(HashMap<String,String> panelist) throws DoubleRegistrationException
    {
        return PanelistContainer.createPanelist(panelist);
	}
	
	/**
	 * Facade method to Create Panel
	 *
	 * @param    panel
	**/
	public static boolean createPanel(String panelName, String panelDescription, int employeeID)
    {
        return PanelContainer.createPanel(panelName, panelDescription, employeeID);
	}
	
	/**
	 * Facade method to add panelist to panel
	 *
	 * @param    panelist
	 * @param    panel
	**/
	public static boolean addPanelistToPanel(int panelistID, int panelID)
    {
        return PanelContainer.addPanelistToPanel(panelistID, panelID);
	}
	
	/**
	 * Facade method to update panel status
	 *
	 * @param    status
	 * @param    panel
	**/
	public static boolean updatePanelStatus(String theStatus, String comments, int panelID)
    {
        return PanelContainer.updatePanelStatus(theStatus, comments, panelID);
	}
	
	/**
	 * Facade method to get panels
	 *
	 * @param    criteria
	**/
	public static ArrayList<Panel> getPanels(int employeeID)
    {
        return PanelContainer.getPanels(employeeID);
	}
	
	/**
	 * Facade method to get panelist 
	 *
	 * @param    criteria
	**/
	public static ArrayList<PanelistProfile> getPanelists(HashMap<String, String> searchCriteria)
    {
        return PanelistContainer.getPanelists(searchCriteria);
	}
	
	/**
	 * Facade method to validate user name
	 *
	 * @param    username
	 * @param    password
	**/
	public static UserProfile validateUsername(String username, String password)
    {
        return AccountManager.validateUsername(username, password);
	}
	
}
