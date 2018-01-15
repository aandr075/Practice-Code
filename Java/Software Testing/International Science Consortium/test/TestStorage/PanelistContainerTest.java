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

import java.util.HashMap;
import junit.framework.TestCase;
import Storage.Repository.*;

public class PanelistContainerTest extends TestCase
{
    public PanelistContainerTest(String name)
    {
        super(name);
    }

    public void setUp()
    {
        //Setup for test case ISCST_01
        newPanelist = new HashMap<String, String>();

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
        newPanelist.put("pExpetise","Computer Science");
        newPanelist.put("pISCID","1234567");

        //Setup for test case ISCST_06
        searchCriteria = new HashMap<String, String>();

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
    }

    public void testCreatePanelists() throws DoubleRegistrationException
    {
        /*To test that the Storage subsystem does not store a new panelist in
         the database if a panelist with the same ISC ID already exists in the
         database.*/
        
        assertFalse("Test Case ISCST_01 Failed. Expected False, but returned True", PanelistContainer.createPanelist(newPanelist));
    }

    public void testGetPanelists()
    {
        /*To test that a search with no search criteria will return a list of
         all panelists in the system.*/
        
        assertEquals("Test Case ISCST_06 Failed.", 3, PanelistContainer.getPanelists(searchCriteria).size());
    }

    HashMap<String, String> newPanelist;
    HashMap<String, String> searchCriteria;
}
