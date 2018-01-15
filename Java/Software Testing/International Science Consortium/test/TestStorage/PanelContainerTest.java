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

package TestStorage;

import Storage.Repository.*;
import junit.framework.TestCase;

public class PanelContainerTest extends TestCase
{
    
    public PanelContainerTest(String name)
    {
        super(name);
    }

    public void setUp()
    {
        //Setup for test case ISCST_02
        tc2_panelName = "BREU Panel";
        tc2_panelDescription = "Research Experiences for Undergraduates";
        tc2_EID = 7654321;

        //Setup for test case ISCST_03
        tc3_panelistID = 1234567;
        tc3_panelID = 1;

        //Setup for test case ISCST_04
        tc4_panelStatus = "Completed";
        tc4_statusComments = "Panel completed succesfully";
        tc4_panelID = 1;

        //Setup for test case ISCST_05
        tc5_EID = 2468013;
    }

    public void testCreatePanel()
    {
        /*To test that the number of panels stored in the Panel 
         table in the ISC database increases by one when a new panel is created.*/

        int beforeAddition = PanelContainer.getNumPanels();
        PanelContainer.createPanel(tc2_panelName, tc2_panelDescription, tc2_EID);
        assertEquals("Test Case ISCST_02 failed.", beforeAddition + 1, PanelContainer.getNumPanels());
    }

    public void testAddPanelistToPanel()
    {
        /*To test that a panelist cannot be added to a panel if that panelist is
         already serving on the panel.*/
        
        assertFalse("Test Case ISCST_03 failed.", PanelContainer.addPanelistToPanel(tc3_panelistID, tc3_panelID));
    }

    public void testUpdatePanelStatus()
    {
        /*To test that the amount of entries in a panel’s status history 
         increases by one when an employee with access to the panel updates the 
         panel’s status.*/

        int sizeBefore = PanelContainer.getPanel(tc4_panelID).StatusHistory.size();
        PanelContainer.updatePanelStatus(tc4_panelStatus, tc4_statusComments, tc4_panelID);
        int sizeAfter = PanelContainer.getPanel(tc4_panelID).StatusHistory.size();
        assertEquals("Test Case ISCST_04 failed.", sizeBefore + 1, sizeAfter);
    }

    public void testGetPanels()
    {
        /*To test that the Storage subsystem will not return a list of panels
         for an employee that does not exist in the system.*/

        assertEquals("Test Case ISCST_05 failed.", 0, PanelContainer.getPanels(tc5_EID).size());
    }

    String tc2_panelName, tc2_panelDescription, tc4_panelStatus, tc4_statusComments;
    int tc2_EID, tc3_panelistID, tc3_panelID, tc4_panelID, tc5_EID;

}
