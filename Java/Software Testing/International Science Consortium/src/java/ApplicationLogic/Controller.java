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

import Storage.Repository.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * This is the facade class that provides access to the Application Logic subsystem. This class provides all the methods that are needed from the Application Logic subsystem.
**/
public class Controller extends HttpServlet
{

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintWriter out = res.getWriter();
        String param = getInitParameter("action-type");

        if(param.equals("doLogin"))
            doLogin(req, res, req.getSession(true));
        else if(param.equals("createPanelistAccount"))
            createPanelistAccount(req, res);
        else if(param.equals("addToPanel"))
            addPanelistToPanel(req, res);
        else if(param.equals("updatePanelStatus"))
            updatePanelStatus(req, res);
        else if(param.endsWith("createPanel"))
            createPanel(req, res, req.getSession(true));
        else if(param.endsWith("doSearch"))
            searchPanelists(req, res, req.getSession(true));

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
         if(getInitParameter("action-type").equals("doLogout"))
            doLogout(res, req.getSession(true));
    }

	/**
	 * The facade method for Login
	 *
	 * @param    username
	 * @param    password
	**/
	public void doLogin(HttpServletRequest req, HttpServletResponse res, HttpSession hs) throws IOException
    {
        SessionManager.doLogin(req, res, hs);
	}
	
	/**
	 * The facade method for Logout
	**/
	public void doLogout(HttpServletResponse res, HttpSession hs) throws IOException
    {
        SessionManager.doLogout(res, hs);
	}
	
	/**
	 * The facade method for creating a Panelist Account
	 *
	 * @param    newPanelist
	**/
	public void createPanelistAccount(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        RegistrationManager.createPanelistAccount(req, res);
	}
	
	/**
	 * The facade method for creating a Panel
	 *
	 * @param    newPanel
	**/
	public static void createPanel(HttpServletRequest req, HttpServletResponse res, HttpSession hs) throws IOException
    {
        PanelManager.createPanel(req, res, hs);
	}
	
	/**
	 * The facade method for adding a Panelist to a Panel
	 *
	 * @param    panelist
	 * @param    panel
	**/
	public static void addPanelistToPanel(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        PanelManager.addPanelistToPanel(req, res);
	}
	
	/**
	 * The facade method for updating Panel Status
	 *
	 * @param    panelID
	 * @param    status
	**/
	public static void updatePanelStatus(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        PanelManager.updatePanelStatus(req, res);
	}
	
	/**
	 * The facade method for getting the Panel Information
	 *
	 * @param    panelID
	**/
	public static Panel getPanel(HttpServletRequest req)
    {
        return PanelManager.getPanel(req);
	}
	
	/**
	 * The facade method for geting Employee's Panels
	 *
	 * @param    employeeID
	**/
	public static ArrayList<Panel> getEmployeePanels(HttpSession session)
    {
        return PanelManager.getEmployeePanels(session);
	}
	
	/**
	 * The facade method for searching Panelists
	 *
	 * @param    panelist
	**/
	public static void searchPanelists(HttpServletRequest req, HttpServletResponse res, HttpSession hs) throws IOException
    {
        SearchPanelist.searchPanelists(req, res, hs);
	}
}
