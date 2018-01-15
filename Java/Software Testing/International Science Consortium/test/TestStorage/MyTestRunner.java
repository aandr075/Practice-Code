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

import junit.framework.Test;
import junit.framework.TestSuite;


public class MyTestRunner
{
   public static Test suite()
   {
       TestSuite suite = new TestSuite();

       //Adds test suite containing test cases ISCST_01 and ISCST_06
       suite.addTestSuite(PanelistContainerTest.class);

       //Adds test suite containing test cases ISCST_02, ISCST_03, ISCST_04, ISCST_05
       suite.addTestSuite(PanelContainerTest.class); 

       //Adds test suite containing test case ISCST_07
       suite.addTestSuite(AccountManagerTest.class);

       return suite;
   }
}
