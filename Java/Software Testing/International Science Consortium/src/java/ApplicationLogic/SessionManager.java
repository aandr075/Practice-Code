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

import Interface.*;
import Storage.*;
import Storage.Repository.*;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This class is in charge of the login/logout related operations and keeping track of the current logged in user. It keeps in a session variable the type of user currently logged in to later displayed the correct forms.
**/
public class SessionManager{
    /**
	 * Validates if the username exists and creates the session variable for the type of user 
	 *
	 * @param    req
	 * @param    hs
	**/
	public static void doLogin(HttpServletRequest req,HttpServletResponse res, HttpSession hs) throws IOException
    {
        String username = (String)req.getParameter("username");
        String password = (String)req.getParameter("password");

        UserProfile userProfile = DBManager.validateUsername(username, password);
        
        if(userProfile == null)
            res.sendRedirect("login");
        else
        {
            hs.setAttribute("User Profile", userProfile);

            switch(userProfile.Type)
            {
                case PANELIST: hs.setAttribute("User Form", new PanelistForms()); break;
                case EMPLOYEE: hs.setAttribute("User Form", new EmployeeForms()); break;
                case ADMINISTRATOR: hs.setAttribute("User Form", new AdministratorForms()); break;
            }

            res.sendRedirect("messagePage?messageCode=You are now logged in. Welcome.");
        }

	}
	
	/**
	 * Destroys the session variable for the active user 
	**/
	public static void doLogout(HttpServletResponse res, HttpSession hs) throws IOException
    {
        hs.invalidate();
        res.sendRedirect("index.jsp");
	}
	
	/**
	 * Creates the user object for the session manager
	 *
	 * @param    userType
	**/
	private static UserForms createUser(UserType userType) {
	return null;
	}
	
	/**
	 * Destroys the user object for the session manager
	**/
	private static void destroyUser() {
	
	}
}
