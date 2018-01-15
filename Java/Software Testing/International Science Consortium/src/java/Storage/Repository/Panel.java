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

/**
 * This class is an entity that stores all the information related to a panel (panelID, name, description, a list of serving panelists, a list of employees with access to the panel, and the panel history). All attributes of the class are public for easy access.
**/
public class Panel
{
	public Integer PanelID;
	public String Name;
	public String Description;
	public ArrayList<String> ServingPanelists;
	public ArrayList<String> Employees;
	//public ArrayList<PanelStatus> History;
    public ArrayList<Status> StatusHistory;
}
