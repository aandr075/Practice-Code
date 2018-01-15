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

package Interface;

import java.io.IOException;
import java.util.ArrayList;
import Storage.Repository.*;
import javax.servlet.ServletException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.http.*;
import ApplicationLogic.*;
import java.text.DateFormat;

/**
 * This class generates the forms that are available to the Employee. It creates the navigation menu for the employee (having employee only options), the create panel page, the add panelist page, the update panel page, among many other options available to employees.
**/
public class EmployeeForms extends UserForms {
	/**
	 * Displays the Employee Navigation Menu
	**/
//	public void displayNavigationMenu() {
//
//	}
	
	/**
	 * Displays the Page to create a New Panel
	**/
	public static void displayCreatePanelPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        req.getRequestDispatcher("header.jsp").include(req, res);
        req.getRequestDispatcher("forms/createPanel.html").include(req, res);
        req.getRequestDispatcher("footer.jsp").include(req, res);
	}
	
	/**
	 * Displays a Page to Add a Panelist to Panel
	**/
	public void displayAddPanelistPage() {
	
	}
	
	/**
	 * Displays a Page to Update Panel Information
	**/
	public void displayUpdatePanelPage() {
	
	}
	
	/**
	 * Displays the Employee's all Registered Panels
	**/
	public static void displayEmployeePanels(HttpServletRequest req, HttpSession session, JspWriter out) throws IOException
    {
		
        ArrayList<Panel> panels = Controller.getEmployeePanels(session);

        if(panels == null)
        {
            out.println("<tr>");
            out.println("<td>There was an error accessing the database. Please try again later.</td>");
            out.println("</tr>");
        }
        else
        {
            out.println("<tr>");
            out.println("<td>Panel ID:</td>");
            out.println("<td>Panel Name:</td>");
            out.println("</tr>");

            for(Panel thePanel: panels)
            {
                out.println("<tr>");
                out.println("<td>" + thePanel.PanelID + "</td>");
                out.println("<td>" + thePanel.Name + "</td>");
                out.println("<td><a href=\"displayPanel.jsp?panelID=" + thePanel.PanelID + "\">View Panel</a></td>");
                out.println("</tr>");
            }

            //temporarily store panels in session variable in case user wants to see more details
            session.setAttribute("Panels", panels);
        }
	}
	
	/**
	 * Displays a Page with the selected panel information
	**/
	public static void displayPanelPage(ArrayList<Panel> panels, int panelID, HttpServletRequest req, HttpServletResponse res, JspWriter out) throws IOException, ServletException
    {
        String servingPanelists = "";
        String employees = "";

        for(Panel thePanel: panels)
            if(thePanel.PanelID == panelID)
            {
                out.println("<tr>");
                out.println("<td>Panel ID: </td>");
                out.println("<td>" + thePanel.PanelID + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td>Panel Name: </td>");
                out.println("<td>" + thePanel.Name + "</td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td valign=\"top\">Panelists Serving on Panel: </td>");

                for(String panelist: thePanel.ServingPanelists)
                    servingPanelists +="<tr><td>" + panelist + "</td></tr>" ;

                out.println("<td><table>" + servingPanelists + "</table></td>");
                out.println("</tr>");
                out.println("<td valign=\"top\">Employees on Panel: </td>");

                for(String employee: thePanel.Employees)
                    employees +="<tr><td>" + employee + "</td></tr>" ;

                out.println("<td><table>" + employees + "</table></td>");
                out.println("</tr>");
                out.println("<tr>");
                out.println("<td valign=\"top\">Status History: </td>");
                out.println("<td>" + getStatuses(thePanel) + "</td>");
                out.println("</tr>");

                return;
            }

        //panelID does not exist
        req.getRequestDispatcher("./messagePage.jsp?messageCode=Incorrect Panel ID. Please try again.").include(req, res);
	}
	
	/**
	 * Displays a Page for searching a Panelist
	**/
	public static void displaySearchPanelistPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        req.getRequestDispatcher("header.jsp").include(req, res);
        req.getRequestDispatcher("forms/searchPanelists.html").include(req, res);
        req.getRequestDispatcher("footer.jsp").include(req, res);
	}

    private static String getStatuses(Panel thePanel)
    {
        ArrayList<Status> statuses = thePanel.StatusHistory;
        StringBuffer sb = new StringBuffer();
        sb.append("<table>");
        
        if(statuses.size() > 0)
        {
            for(Status status: statuses)
            {
                sb.append("<tr>");
                sb.append("<td>" + status.Title + "</td>");
                sb.append("<td>" + DateFormat.getDateInstance(DateFormat.MEDIUM).format(status.Date) + "</td>");
                sb.append("</tr>");
                sb.append("<tr>");
                sb.append("<td>&nbsp;&nbsp;&nbsp;&nbsp;" + status.Comments + "</td>");
                sb.append("</tr>");
            }
        }
        else
            sb.append("<td>None Set</td>");

        sb.append("<tr><td><form method=\"POST\" action=\"updateStatus.jsp\"></td>" +
                "</tr><tr><td><input type=\"hidden\" name=\"panelID\" value=\"" + thePanel.PanelID + "\"/></td></tr>" +
                "<tr><td><input type=\"Submit\" value=\"Update Panel Status\"/></td></tr>");

        sb.append("</table>");
        
        return sb.toString();
    }
	
	/**
	 * Will display the given panel to the employee's panels page
	 *
	 * @param    panel
	**/
	private void displayPanel(Panel panel) {
	
	}
	
	/**
	 * Creates a page that will display a List of Panelists
	 *
	 * @param    panelists
	**/
	public static void displayPanelists(HttpServletRequest req, ArrayList<PanelistProfile> panelists, HttpSession session, JspWriter out) throws IOException
    {
        ArrayList<Panel> employeePanels = Controller.getEmployeePanels(session);
        
        out.println("<tr>");
        out.println("<td>Panelist ID:</td>");
        out.println("<td>Panelist Name:</td>");
        out.println("</tr>");

        for(PanelistProfile panelist: panelists)
        {
            out.println("<tr>");
            out.println("<form method=\"POST\" action=\"addPanelist\">");
            out.println("<td>" + panelist.ISCID + "</td>");
            out.println("<td>" + panelist.FirstName + " " + panelist.LastName + "</td>");
            out.println("<td>" + generatePanelDropDown(employeePanels) + "</td>");
            out.println("<td><input type=\"hidden\" name=\"panelistID\" value=\"" + panelist.ISCID + "\"/>");
            out.println("<td> <input type=\"submit\" value=\"Add to Panel\"> </td>");
            out.println("</form>");
            out.println("</tr>");
        }     
	}
	
	/**
	 * Adds the given Panel to the employee's panel list
	 *
	 * @param    panel
	**/
	private void addPanelToList(Panel panel) {
	
	}

    private static String generatePanelDropDown(ArrayList<Panel> employeePanels)
    {
        StringBuffer output = new StringBuffer();
        output.append("<select name=\"addToPanel\">");

        for(Panel employeePanel: employeePanels)
            output.append("<option value=\"" + employeePanel.PanelID + "\">" + employeePanel.Name + "</option>");

        output.append("</select>");

        return output.toString();
    }

    public void displayNavigationMenu(JspWriter out) throws ServletException, IOException
    {
        String HTML = "<table width='100%' border='0' cellspacing='0' cellpadding='3' height='0'>" +
                "    <tr>" +
                "      <td width='10' align='right'><img src='images/square.jpg' width='7' height='7'></td>" +
                "      <td nowrap class='menutext'><a href='.' class='menutext'>Home</a></td>" +
                "    </tr>" +
                "    <tr>" +
                "      <td width='10' align='right'><img src='images/square.jpg' width='7' height='7'></td>" +
                "      <td nowrap class='menutext'><a href='searchPanelists.jsp' class='menutext'>Search for Panelists</a></td>" +
                "    </tr>" +
                "    <tr>" +
                "      <td width='10' align='right'><img src='images/square.jpg' width='7' height='7'></td>" +
                "      <td nowrap class='menutext'><a href='createPanel.jsp' class='menutext'>Create a Panel</a></td>" +
                "    </tr>" +
                "    <tr>" +
                "      <td width='10' align='right'><img src='images/square.jpg' width='7' height='7'></td>" +
                "      <td nowrap class='menutext'><a href='displayPanels.jsp' class='menutext'>Display My Panels</a></td>" +
                "    </tr>" +
                "    <tr>" +
                "      <td width='10' align='right'><img src='images/square.jpg' width='7' height='7'></td>" +
                "      <td nowrap class='menutext'><a href='logout' class='menutext'>Logout</a></td>" +
                "    </tr>" +
                "</table>";

        out.println(HTML);
    }
	
}
