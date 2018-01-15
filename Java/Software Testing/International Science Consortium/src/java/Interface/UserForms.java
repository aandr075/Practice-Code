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
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.JspWriter;

/**
 * This is an abstract class that has the possible pages that could be shown to the user. Every concrete class will implement the specified page as needed. It also contains generic pages like Login Page, Error Page, and Registration Page.
**/
public class UserForms extends HttpServlet{

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String param = getInitParameter("action-type");

        if(param.equals("displayLoginPage"))
            displayLoginPage(req, res);
        else if(param.equals("displayRegistrationPage"))
            displayRegistrationPage(req, res);
        else if(param.equals("displayErrorPage"))
            displayErrorPage(req, res);
        else if(param.equals("displayMessagePage"))
            displayMessagePage(req, res);
    }

    /**
	 * Creates the Login Page
	**/
	public static void displayLoginPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        req.getRequestDispatcher("header.jsp").include(req, res);
        req.getRequestDispatcher("forms/login.htm").include(req, res);
        req.getRequestDispatcher("footer.jsp").include(req, res);
	}
	
	/**
	 * Creates the Error Page
	 *
	 * @param    errorMsg
	**/
	public static void displayErrorPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String url = "errorPage.jsp?errorCode=" + req.getParameter("errorCode");
        req.getRequestDispatcher("header.jsp").include(req, res);
        req.getRequestDispatcher(url).include(req, res);
        req.getRequestDispatcher("footer.jsp").include(req, res);
	}
	
	/**
	 * Creates the Message Page
	 *
	 * @param    msg
	**/
	public static void displayMessagePage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        String url = "messagePage.jsp?messageCode=" + req.getParameter("messageCode");
        req.getRequestDispatcher("header.jsp").include(req, res);
        req.getRequestDispatcher(url).include(req, res);
        req.getRequestDispatcher("footer.jsp").include(req, res);
	}
	
	/**
	 * Creates the Registration Page
	**/
	public static void displayRegistrationPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        req.getRequestDispatcher("header.jsp").include(req, res);
        req.getRequestDispatcher("forms/register.html").include(req, res);
        req.getRequestDispatcher("footer.jsp").include(req, res);
	}
	
	/**
	 * This is an abstract method that will be defined later by the subclasses that will create the correspondent Navigation Menu
	**/

	public void displayNavigationMenu(JspWriter out) throws ServletException, IOException
    {
        String HTML = "<table width='100%' border='0' cellspacing='0' cellpadding='3' height='0'>" +
                "    <tr>" +
                "      <td width='10' align='right'><img src='images/square.jpg' width='7' height='7'></td>" +
                "      <td nowrap class='menutext'><a href='login' class='menutext'>Log In</a></td>" +
                "    </tr>" +
                "</table>";

        out.println(HTML);
    }
}
