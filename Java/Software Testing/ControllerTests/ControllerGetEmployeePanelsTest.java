package test.ControllerTests;
import ApplicationLogic.*;
import Storage.DBManager;
import Storage.Repository.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import javax.servlet.http.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpServletRequest;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DBManager.class})

public class ControllerGetEmployeePanelsTest {

	/*
	Test Case ID: ISCUC-19-Subsystem-ApplicationLogic-016
	Purpose: Test to ensure that the ApplicationLogic subsystem properly sends a employeeID to the DBManager and receives an arraylist of Panel objects
	Test Setup: HttpServletRequest mocked, Session contains parameter “User Profile”.
	Input: Session “User Profile” parameter is an employee profile. Create HTTP servlet session (testSession) to pass to method.Calls controller.getEmployeePanels( testSession).
	Expected Output: An ArrayList of Panel objects.
	
	
	*/
	@Test
	public void ISCUC_19_Subsystem_ApplicationLogic_016() throws Exception {
		
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		HttpSession testSession = req.getSession(true);
		
		//set up a user profile
		EmployeeProfile user = new EmployeeProfile();
		
		Random rand = new Random();
		int value = rand.nextInt(10);
		user.EmployeeID = value;
		
		//set user profile for use in Panel Manager
		testSession.setAttribute("User Profile", user);
		
		//execute the actual method
		ArrayList<Panel> panelArray = Controller.getEmployeePanels(testSession);
		
		assertEquals("Amount of panels in the arraylist should equal the employeeID", user.EmployeeID, panelArray.size() );
	}

}
