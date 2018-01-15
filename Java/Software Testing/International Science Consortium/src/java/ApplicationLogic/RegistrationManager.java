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

import Storage.*;
import Storage.Repository.DoubleRegistrationException;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is used to create panelist accounts. It also validates that there is no previous panelist registered with the same ISCID.
**/
public class RegistrationManager {

	/**
	 * This method creates a Panelist Account based in the given Panelist Profile and returns true if it was successful
	 *
	 * @param    newPanelist
	**/
	public static boolean createPanelistAccount(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        HashMap<String, String> newPanelist = new HashMap<String, String>();

        newPanelist.put("pFName",req.getParameter("pFName"));
        newPanelist.put("pLName",req.getParameter("pLName"));
        newPanelist.put("username",req.getParameter("username"));
        newPanelist.put("password",req.getParameter("password"));
        newPanelist.put("pInstitution",req.getParameter("pInstitution"));
        newPanelist.put("pAddress",req.getParameter("pAddress"));
        newPanelist.put("pCity",req.getParameter("pCity"));
        newPanelist.put("pState",req.getParameter("pState"));
        newPanelist.put("pZip",req.getParameter("pZip"));
        newPanelist.put("pTelephone",req.getParameter("pTelephone"));
        newPanelist.put("pEmail", req.getParameter("pEmail"));
        newPanelist.put("pGender",req.getParameter("pGender"));
        newPanelist.put("pEthnicity",req.getParameter("pEthnicity"));
        newPanelist.put("pExpertise",req.getParameter("pExpertise"));
        newPanelist.put("pISCID",req.getParameter("pISCID"));

        boolean result;

        try {
            result = DBManager.createPanelist(newPanelist);

            if(result)
                res.sendRedirect("messagePage?messageCode=Registration Successful");
            else
                res.sendRedirect("errorPage?errorCode=Registration failed. Please try again.");

            return result;

        } catch (DoubleRegistrationException ex) {
            res.sendRedirect("errorPage?errorCode=Double Registration Exception: ISCID is already in use.");
        }

        return false;
	}
}
