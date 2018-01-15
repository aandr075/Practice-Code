package test.ControllerTests;

//imports
import ApplicationLogic.*;
import Interface.*;
import Storage.DBManager;
import Storage.Repository.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.io.*;
import javax.servlet.http.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpServletRequest;

//necessary to partially mock DBManager using PowerMockito
@RunWith(PowerMockRunner.class)
@PrepareForTest({DBManager.class})

public class ControllerLoginTest {

	/*
	Test Case ID: ISCUC-03-Subsystem-ApplicationLogic-001
	Purpose: Test to ensure that the ApplicationLogic subsystem properly allows an ISC employee to create a HttpSession with an employee user profile.
	Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, DBManager.stubtype() mocked to return an employee UserType, HttpServletRequest contains parameters “action-type”, “username”, “password”.
	Input: HttpServletRequest “action-type” parameter is set to “doLogin”. Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
	Expected Output: HttpSession has an attribute “UserProfile” containing a UserProfile, and “User Form” containing an employee form object.
	
	
	*/
	@Test
	public void ISCUC_03_Subsystem_ApplicationLogic_001() throws Exception {
		
		//variables for testing
		String username = "testcase";
		String password = "password";
		UserType emp = UserType.EMPLOYEE;
	
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		HttpServletResponse res = Mockito.mock(HttpServletResponse.class);
		HttpSession testSession = req.getSession(true);
		Controller mockCon = Mockito.mock(Controller.class);
		
		PowerMockito.spy(DBManager.class);
	
		//mock methods
		Mockito.when(mockCon.getInitParameter("action-type")).thenReturn("doLogin");
		PowerMockito.doReturn(UserType.EMPLOYEE).when(DBManager.class, "stubtype");
		req.addParameter("username", username);
		req.addParameter("password", password);
		
		
		//calls to real methods
		Mockito.doCallRealMethod().when(mockCon).doPost(req, res);
		Mockito.doCallRealMethod().when(mockCon).doLogin(req, res, testSession);
		
		PrintWriter writer = new PrintWriter("text.txt");
		Mockito.when(res.getWriter()).thenReturn(writer);
		
		mockCon.doPost(req, res);
		UserProfile testUser = (UserProfile) testSession.getAttribute("User Profile");	
		
		Mockito.verify(mockCon).doLogin(req, res, testSession);
		Mockito.verify(mockCon, never()).doGet(req, res);
		Mockito.verify(mockCon, never()).doLogout(res, req.getSession(true));
		Mockito.verify(mockCon, never()).createPanelistAccount(req, res);
		assertTrue("session should have variables set", testSession.getAttributeNames().hasMoreElements() );
		assertSame("Session should contain an EmployeeForms object",UserProfile.class,testSession.getAttribute("User Profile").getClass() );
		assertSame("Session should contain an EmployeeForms object",EmployeeForms.class,testSession.getAttribute("User Form").getClass() );
		assertNotSame("Session should not contain an AdministratorForms object",AdministratorForms.class,testSession.getAttribute("User Form").getClass() );
		assertNotSame("Session should contain a PanelistForms object",PanelistForms.class,testSession.getAttribute("User Form").getClass() );
		assertEquals("Username should match test variable", username,testUser.UserName);
		assertEquals("password should match test variable", password,testUser.Password);
		assertEquals("type should match EMPLOYEE", emp,testUser.Type);
			
	}
	
	/*
	Test Case ID: ISCUC-03-Subsystem-ApplicationLogic-002
	Purpose: Test to ensure that the ApplicationLogic subsystem properly allows a panelist to create a HttpSession with a panelist user profile.
	Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, DBManager.stubtype() mocked to return a panelist UserType, HttpServletRequest contains parameters “action-type”, “username”, “password”.
	Input: HttpServletRequest “action-type” parameter is set to “doLogin”. Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
	Expected Output: HttpSession has an attribute “UserProfile” containing a UserProfile, and “User Form” containing a panelist form object.
	
	
	*/
	@Test
	public void ISCUC_03_Subsystem_ApplicationLogic_002() throws Exception {
		
		//variables for testing
		String username = "testcase";
		String password = "password";
		UserType pan = UserType.PANELIST;
		
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		HttpServletResponse res = Mockito.mock(HttpServletResponse.class);
		HttpSession testSession = req.getSession(true);
		Controller mockCon = Mockito.mock(Controller.class);
		
		PowerMockito.spy(DBManager.class);

		//mock methods
		Mockito.when(mockCon.getInitParameter("action-type")).thenReturn("doLogin");
		PowerMockito.doReturn(pan).when(DBManager.class, "stubtype");
		req.addParameter("username", username);
		req.addParameter("password", password);
		
		
		//calls to real methods
		Mockito.doCallRealMethod().when(mockCon).doPost(req, res);
		Mockito.doCallRealMethod().when(mockCon).doLogin(req, res, testSession);
		
		PrintWriter writer = new PrintWriter("text.txt");
		Mockito.when(res.getWriter()).thenReturn(writer);
		
		mockCon.doPost(req, res);
		UserProfile testUser = (UserProfile) testSession.getAttribute("User Profile");	
		
		Mockito.verify(mockCon).doLogin(req, res, testSession);
		Mockito.verify(mockCon, never()).doGet(req, res);
		Mockito.verify(mockCon, never()).doLogout(res, req.getSession(true));
		Mockito.verify(mockCon, never()).createPanelistAccount(req, res);
		assertTrue("session should have variables set", testSession.getAttributeNames().hasMoreElements() );
		assertSame("Session should contain a UserProfile",UserProfile.class,testSession.getAttribute("User Profile").getClass() );
		assertNotSame("Session should contain an EmployeeForms object",EmployeeForms.class,testSession.getAttribute("User Form").getClass() );
		assertNotSame("Session should not contain an AdministratorForms object",AdministratorForms.class,testSession.getAttribute("User Form").getClass() );
		assertSame("Session should contain a PanelistForms object",PanelistForms.class,testSession.getAttribute("User Form").getClass() );
		assertEquals("Username should match test variable", username,testUser.UserName);
		assertEquals("password should match test variable", password,testUser.Password);
		assertEquals("type should match EMPLOYEE", pan,testUser.Type);
			
	}
	
	/*
	Test Case ID: ISCUC-03-Subsystem-ApplicationLogic-003
	Purpose:  Test to ensure that the ApplicationLogic subsystem properly allows an administrator to create a HttpSession with an administrator user profile.
	Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, DBManager.stubtype() mocked to return an administrator UserType, HttpServletRequest contains parameters “action-type”, “username”, “password”.
	Input: HttpServletRequest “action-type” parameter is set to “doLogin”.Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
	Expected Output: HttpSession has an attribute “UserProfile” containing a UserProfile, and “User Form” containing an administrator form object.
	
	
	*/
	@Test
	public void ISCUC_03_Subsystem_ApplicationLogic_003() throws Exception {
		
		//variables for testing
		String username = "testcase";
		String password = "password";
		UserType adm = UserType.ADMINISTRATOR;
		
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		HttpServletResponse res = Mockito.mock(HttpServletResponse.class);
		HttpSession testSession = req.getSession(true);
		Controller mockCon = Mockito.mock(Controller.class);
		
		PowerMockito.spy(DBManager.class);
		
		//mock methods
		Mockito.when(mockCon.getInitParameter("action-type")).thenReturn("doLogin");
		PowerMockito.doReturn(adm).when(DBManager.class, "stubtype");
		req.addParameter("username", username);
		req.addParameter("password", password);
		
		
		//calls to real methods
		Mockito.doCallRealMethod().when(mockCon).doPost(req, res);
		Mockito.doCallRealMethod().when(mockCon).doLogin(req, res, testSession);
		
		PrintWriter writer = new PrintWriter("text.txt");
		Mockito.when(res.getWriter()).thenReturn(writer);
		
		mockCon.doPost(req, res);
		UserProfile testUser = (UserProfile) testSession.getAttribute("User Profile");	
		
		Mockito.verify(mockCon).doLogin(req, res, testSession);
		Mockito.verify(mockCon, never()).doGet(req, res);
		Mockito.verify(mockCon, never()).doLogout(res, req.getSession(true));
		Mockito.verify(mockCon, never()).createPanelistAccount(req, res);
		assertTrue("session should have variables set", testSession.getAttributeNames().hasMoreElements() );
		assertSame("Session should contain an EmployeeForms object",UserProfile.class,testSession.getAttribute("User Profile").getClass() );
		assertNotSame("Session should contain an EmployeeForms object",EmployeeForms.class,testSession.getAttribute("User Form").getClass() );
		assertSame("Session should not contain an AdministratorForms object",AdministratorForms.class,testSession.getAttribute("User Form").getClass() );
		assertNotSame("Session should contain a PanelistForms object",PanelistForms.class,testSession.getAttribute("User Form").getClass() );
		assertEquals("Username should match test variable", username,testUser.UserName);
		assertEquals("password should match test variable", password,testUser.Password);
		assertEquals("type should match EMPLOYEE", adm,testUser.Type);
		
	}

}
