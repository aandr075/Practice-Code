package test.ControllerTests;
import ApplicationLogic.*;
import Storage.Repository.*;

import static org.junit.Assert.*;
import javax.servlet.http.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Controller.class})

public class ControllerCreatePanelTest {
	
	/*
	Test Case ID: ISCUC-09-Subsystem-ApplicationLogic-005
	Purpose: Test to ensure that the ApplicationLogic subsystem responds correctly if an employee attempts to create a panel without a panel name.
	Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, HttpServletRequest contains parameters “action-type”, “User Profile”, “panelName”, “panelDescription”.
	Input: HttpServletRequest “action-type” parameter is set to “createPanel”, “panelName” parameter is set to “”, “panelDescription” is set to “Description”. Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
	Expected Output: res.getRedirectedUrl() = “errorPage?errorCode=There was an error creating the panel. Please try again.”
	
	
	*/
	@Test
	public void ISCUC_09_Subsystem_ApplicationLogic_005() throws Exception {
		
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();
	
		//setting up a logged in session
		HttpSession testSession = req.getSession(true);
		
		//set up a user profile
		EmployeeProfile user = new EmployeeProfile();
		user.EmployeeID = 1;
		
		//set user profile for use in Panel Manager
		testSession.setAttribute("User Profile", user);
		
		
		//spies on controller class
		Controller mockCon = PowerMockito.spy(new Controller());

		//mock methods
		PowerMockito.doReturn("createPanel").when(mockCon).getInitParameter("action-type");
		req.addParameter("panelName", "");
		req.addParameter("panelDescription", "Description");
		
		//execute the actual method
		mockCon.doPost(req, res);
		
		//testing
		assertEquals("Response status after sendRedirect should be 302", 302,res.getStatus());
		assertNotEquals("messagePage?messageCode=Panel has been successfully created.", res.getRedirectedUrl());
		assertEquals("errorPage?errorCode=There was an error creating the panel. Please try again.", res.getRedirectedUrl());

	}
	
	/*
	Test Case ID: ISCUC-09-Subsystem-ApplicationLogic-006
	Purpose: Test to ensure that the ApplicationLogic subsystem responds correctly if an employee attempts to create a panel without a panel description.
	Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, HttpServletRequest contains parameters “action-type”, “User Profile”, “panelName”, “panelDescription”.
	Input: HttpServletRequest “action-type” parameter is set to “createPanel”, “panelName” parameter is set to “Name”, “panelDescription” is set to “”.Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
	Expected Output: res.getRedirectedUrl() = “errorPage?errorCode=There was an error creating the panel. Please try again.”
	
	
	*/
	@Test
	public void ISCUC_09_Subsystem_ApplicationLogic_006() throws Exception {
		
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();
	
		//setting up a logged in session
		HttpSession testSession = req.getSession(true);
		
		//set up a user profile
		EmployeeProfile user = new EmployeeProfile();
		user.EmployeeID = 1;
		
		//set user profile for use in Panel Manager
		testSession.setAttribute("User Profile", user);
		
		
		//spies on controller class
		Controller mockCon = PowerMockito.spy(new Controller());
		

		//mock methods
		PowerMockito.doReturn("createPanel").when(mockCon).getInitParameter("action-type");
		req.addParameter("panelName", "Name");
		req.addParameter("panelDescription", "");
		
		//execute the actual method
		mockCon.doPost(req, res);
		
		
		//testing
		assertEquals("Response status after sendRedirect should be 302", 302,res.getStatus());
		assertNotEquals("messagePage?messageCode=Panel has been successfully created.", res.getRedirectedUrl());
		assertEquals("errorPage?errorCode=There was an error creating the panel. Please try again.", res.getRedirectedUrl());

	}
	
	/*
	Test Case ID: ISCUC-09-Subsystem-ApplicationLogic-007
	Purpose: Test to ensure that the ApplicationLogic subsystem responds correctly if an employee attempts to create a panel with neither a panel description nor a panel name.
	Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, HttpServletRequestcontains parameters “action-type”, “User Profile”, “panelName”, “panelDescription”.
	Input: HttpServletRequest “action-type” parameter is set to “createPanel”, “panelName” parameter is set to “”, “panelDescription” is set to “”. Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
	Expected Output: res.getRedirectedUrl() = “errorPage?errorCode=There was an error creating the panel. Please try again.”
	
	
	*/
	@Test
	public void ISCUC_09_Subsystem_ApplicationLogic_007() throws Exception {
		
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();
	
		//setting up a logged in session
		HttpSession testSession = req.getSession(true);
		
		//set up a user profile
		EmployeeProfile user = new EmployeeProfile();
		user.EmployeeID = 1;
		
		//set user profile for use in Panel Manager
		testSession.setAttribute("User Profile", user);
		
		
		//spies on controller class
		Controller mockCon = PowerMockito.spy(new Controller());

		//mock methods
		PowerMockito.doReturn("createPanel").when(mockCon).getInitParameter("action-type");
		req.addParameter("panelName", "");
		req.addParameter("panelDescription", "");
		
		//execute the actual method
		mockCon.doPost(req, res);
		
		//testing
		assertEquals("Response status after sendRedirect should be 302", 302,res.getStatus());
		assertNotEquals("messagePage?messageCode=Panel has been successfully created.", res.getRedirectedUrl());
		assertEquals("errorPage?errorCode=There was an error creating the panel. Please try again.", res.getRedirectedUrl());

	}
	
	/*
	Test Case ID: ISCUC-09-Subsystem-ApplicationLogic-008
	Purpose: Test to ensure that the ApplicationLogic subsystem responds correctly if an employee attempts to create a panel with a panel name and description provided.
	Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, HttpServletRequest contains parameters “action-type”, “User Profile”, “panelName”, “panelDescription”.
	Input: HttpServletRequest “action-type” parameter is set to “createPanel”, “panelName” parameter is set to “Name”, “panelDescription” is set to “Description” Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
	Expected Output: Session has no paremeters set
	
	
	*/
	@Test
	public void ISCUC_09_Subsystem_ApplicationLogic_008() throws Exception {
		
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();
	
		//setting up a logged in session
		HttpSession testSession = req.getSession(true);
		
		//set up a user profile
		EmployeeProfile user = new EmployeeProfile();
		user.EmployeeID = 1;
		
		//set user profile for use in Panel Manager
		testSession.setAttribute("User Profile", user);
		
		
		//spies on controller class
		Controller mockCon = PowerMockito.spy(new Controller());
		
		//mock methods
		PowerMockito.doReturn("createPanel").when(mockCon).getInitParameter("action-type");
		req.addParameter("panelName", "Name");
		req.addParameter("panelDescription", "Description");
		
		//execute the actual method
		mockCon.doPost(req, res);
		
		
		//testing
		assertEquals("Response status after sendRedirect should be 302", 302,res.getStatus());
		assertEquals("messagePage?messageCode=Panel has been successfully created.", res.getRedirectedUrl());
		assertNotEquals("errorPage?errorCode=There was an error creating the panel. Please try again.", res.getRedirectedUrl());

	}

}
