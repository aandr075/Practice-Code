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
import javax.servlet.ServletException;
import javax.servlet.jsp.JspWriter;

/**
 * This class generates the forms that are available to the Administrator. It creates the navigation menu for the administrator (having administrator only options). In a full system the administrator could have other options in here.
**/
public class AdministratorForms extends UserForms {
	/**
	 * Displays Administrator Navigation Menu
	**/

	public void displayNavigationMenu(JspWriter out) throws ServletException, IOException
    {
        String HTML = "<table width='100%' border='0' cellspacing='0' cellpadding='3' height='0'>" +
                "    <tr>" +
                "      <td width='10' align='right'><img src='images/square.jpg' width='7' height='7'></td>" +
                "      <td nowrap class='menutext'><a href='#' class='menutext'>Administer</a></td>" +
                "    </tr>" +
                "    <tr>" +
                "      <td width='10' align='right'><img src='images/square.jpg' width='7' height='7'></td>" +
                "      <td nowrap class='menutext'><a href='./logout' class='menutext'>Logout</a></td>" +
                "    </tr>" +
                "</table>";

        out.println(HTML);
    }
	
}
