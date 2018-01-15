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
import java.util.HashMap;
import javax.servlet.http.*;

/**
 * This class has the only purpose of returning a list of panelists given criteria. This is a singleton class as no many instances are required. Given a criteria it returns a list of panelists.
**/
public class SearchPanelist {
	/**
	 * This method returns an array of panelists based on a given criteria
	 *
	 * @param    panelist
	**/
	public static void searchPanelists(HttpServletRequest req, HttpServletResponse res, HttpSession hs) throws IOException
    {
        HashMap<String, String> searchCriteria = new HashMap<String, String>();
        
        //Let's pull the values from the search form.        
        searchCriteria.put("FirstName", req.getParameter("pFName"));
        searchCriteria.put("LastName", req.getParameter("pLName"));
        searchCriteria.put("Institution", req.getParameter("pInstitution"));
        searchCriteria.put("Address", req.getParameter("pAddress"));
        searchCriteria.put("City", req.getParameter("pCity"));
        searchCriteria.put("State", req.getParameter("pState"));
        searchCriteria.put("ZipCode", req.getParameter("pZip"));
        searchCriteria.put("Telephone", req.getParameter("pTelephone"));
        searchCriteria.put("Email", req.getParameter("pEmail"));
        searchCriteria.put("Gender", req.getParameter("pGender"));
        searchCriteria.put("Ethnicity", req.getParameter("pEthnicity"));
        searchCriteria.put("Expertise", req.getParameter("pExpertise"));
        searchCriteria.put("ISCID", req.getParameter("pISCID"));

        ArrayList<PanelistProfile> userProfile = DBManager.getPanelists(searchCriteria);

        if(userProfile == null)
            res.sendRedirect("messagePage?messageCode=No Panelists Found.");
        else
        {
            hs.setAttribute("Panelists", userProfile);
            res.sendRedirect("displayPanelists.jsp");
        }
	}
}
