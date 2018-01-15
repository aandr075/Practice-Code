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

import Interface.UserForms;
import javax.servlet.http.*;

public class SessionTimeout implements HttpSessionListener{

    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session Created");
        se.getSession().setAttribute("User Form", new UserForms());
        se.getSession().setMaxInactiveInterval(900);
    }

    public void sessionDestroyed(HttpSessionEvent se) {

        System.out.println("Session Destroyed");
    }
}

