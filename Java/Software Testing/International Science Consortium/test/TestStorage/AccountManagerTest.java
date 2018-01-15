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

import junit.framework.TestCase;
import Storage.Repository.AccountManager;

public class AccountManagerTest extends TestCase
{
    public AccountManagerTest(String name)
    {
        super(name);
    }

    public void setUp()
    {
        //Setup for test case ISCST_07
        tc7_username = "jpava002";
        tc7_password = "cen4010";
    }

    public void testValidateUsername()
    {
        /*To test that the Storage subsystem does not validate a username
         that does not exist in the subsystem upon login.*/

        assertNull("Test Case ISCST_07 failed.", AccountManager.validateUsername(tc7_username, tc7_password));
    }

    String tc7_username, tc7_password;
}
