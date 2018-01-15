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

import java.util.HashMap;
import Storage.Repository.*;

//Driver class to test the Storage subsystem
public class Main
{
    public static void main(String[] args) throws DoubleRegistrationException
    {
    	System.out.println("test has begun");
        //Test Case 1
        HashMap<String, String> newPanelist = new HashMap<String, String>();

        newPanelist.put("pFName","Jairo");
        newPanelist.put("pLName","Pava");
        newPanelist.put("username","Jpava001");
        newPanelist.put("password","Cen4010");
        newPanelist.put("pInstitution","Florida International University");
        newPanelist.put("pAddress","11200 SW 8th Street");
        newPanelist.put("pCity","Miami");
        newPanelist.put("pState","FL");
        newPanelist.put("pZip","33199");
        newPanelist.put("pTelephone","305-348-4100");
        newPanelist.put("pEmail", "Jpava001@fiu.edu");
        newPanelist.put("pGender","0");
        newPanelist.put("pEthnicity","Hispanic");
        newPanelist.put("pExpertise","2");
        newPanelist.put("pISCID","1234567");

        PanelistContainer.createPanelist(newPanelist);

        //Test Case 2
        String panelName = "DREU Panel";
        String panelDescription = "Research Experiences for Undergraduates";
        int employeeID = 9876543;

        PanelContainer.createPanel(panelName, panelDescription, employeeID);

        //Test Case 3
        int pID = 1234567;
        int panelID = 1;

        PanelContainer.addPanelistToPanel(pID, panelID);

        //Test Case 4
        String panelStatus = "Completed";
        String panelComments = "Panel completed succesfully";
        panelID = 1;

        PanelContainer.updatePanelStatus(panelStatus, panelComments, panelID);

        //Test Case 5
        employeeID = 2468013;
        PanelContainer.getPanels(employeeID);

        //Test Case 6
        HashMap<String, String> searchCriteria = new HashMap<String, String>();

        searchCriteria.put("FirstName", "");
        searchCriteria.put("LastName", "");
        searchCriteria.put("Institution", "");
        searchCriteria.put("Address", "");
        searchCriteria.put("City", "");
        searchCriteria.put("State", "");
        searchCriteria.put("ZipCode", "");
        searchCriteria.put("Telephone", "");
        searchCriteria.put("Email", "");
        searchCriteria.put("Gender", "");
        searchCriteria.put("Ethnicity", "");
        searchCriteria.put("Expertise", "");
        searchCriteria.put("ISCID", "");

        PanelistContainer.getPanelists(searchCriteria);

        //Test Case 7
        String username = "jpava002";
        String password = "cen4010";

        AccountManager.validateUsername(username, password);
    }
}
