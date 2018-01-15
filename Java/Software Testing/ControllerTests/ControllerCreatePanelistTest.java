package test.ControllerTests;
import ApplicationLogic.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import javax.servlet.http.*;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

//RunWith(PowerMockRunner.class)
//PrepareForTest({DBManager.class})


public class ControllerCreatePanelistTest {

	/*
	Test Case ID: ISCUC-02-Subsystem-ApplicationLogic-018
	Purpose: Test to ensure that the ApplicationLogic subsystem responds as expected when a panelist is successfully created. 
	Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, HttpServletRequest contains parameters “action-type”, “pFName”, “pLName”, “username”, “password”, “pInstitution”, “pAddress”, “pCity”, “pState”, “pZip”, “pTelephone”, “pEmail”, “pGender”, “pEthnicity”, “pExpertise”, “ISCID”. DBManager is stubbed to recognize “42” as an ISCID already registered. 
	Input: HttpServletRequest “ISCID” parameter is set to “42”, “action-type” parameter is set to “createPanelistAccount”, DBManager is stubbed to return true in this case. Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
	Expected Output: res.getRedirectedUrl() = “messagePage?messageCode=Registration Successful”
	
	
	*/
	@Test
	public void ISCUC_02_Subsystem_ApplicationLogic_018() throws Exception {
		
		//variables for testing
		String username = "testcase";
		String password = "password";
		
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();
		HttpSession testSession = req.getSession(true);
		Controller mockCon = Mockito.mock(Controller.class);
		
		
		
		//mock methods
		Mockito.when(mockCon.getInitParameter("action-type")).thenReturn("createPanelistAccount");
		req.addParameter("pFName", "test");
		req.addParameter("pLName", "testson");
		req.addParameter("username", username);
		req.addParameter("password", password);
		req.addParameter("pInstitution", "test state university");
		req.addParameter("pAddress", "123 test st");
		req.addParameter("pCity", "testings");
		req.addParameter("pState", "TN");
		req.addParameter("pZip", "12345");
		req.addParameter("pTelephone", "123 456 7890");
		req.addParameter("pEmail", "test@test.test");
		req.addParameter("pGender", "1");
		req.addParameter("pEthnicity", "testician");
		req.addParameter("pExpertise", "2");
		req.addParameter("pISCID", "42");
		
		//calls to real methods
		Mockito.doCallRealMethod().when(mockCon).doPost(req, res);
		Mockito.doCallRealMethod().when(mockCon).createPanelistAccount(req, res);;
		
		//execute the actual method
		mockCon.doPost(req, res);
		
		//testing
		Mockito.verify(mockCon, never()).doLogin(req, res, testSession);
		Mockito.verify(mockCon, never()).doGet(req, res);
		Mockito.verify(mockCon, never()).doLogout(res, req.getSession(true));
		Mockito.verify(mockCon).createPanelistAccount(req, res);
		assertEquals("Response status after sendRedirect should be 302", 302,res.getStatus());
		assertEquals("checking for successful redirect", "messagePage?messageCode=Registration Successful", res.getRedirectedUrl());
		assertNotEquals("checking for unsuccessful redirect", "errorPage?errorCode=Registration failed. Please try again.", res.getRedirectedUrl());
		assertNotEquals("checking for exception redirect", "errorPage?errorCode=Double Registration Exception: ISCID is already in use.", res.getRedirectedUrl());
	
	}
	
	/*
	Test Case ID: ISCUC-21-Subsystem-ApplicationLogic-017
	Purpose: Test to ensure that the ApplicationLogic subsystem does not allow double registrations for a ISCID. 
	Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, HttpServletRequest contains parameters “action-type”, “pFName”, “pLName”, “username”, “password”, “pInstitution”, “pAddress”, “pCity”, “pState”, “pZip”, “pTelephone”, “pEmail”, “pGender”, “pEthnicity”, “pExpertise”, “ISCID”. DBManager is stubbed to recognize “42” as an ISCID already registered.
	Input: HttpServletRequest “ISCID” parameter is set to “25”, “action-type” parameter is set to “createPanelistAccount”, DBManager is stubbed to return false in this case. Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
	Expected Output: res.getRedirectedUrl() = "errorPage?errorCode=Double Registration Exception: ISCID is already in use.”
	
	
	*/
	@Test
	public void ISCUC_21_Subsystem_ApplicationLogic_017() throws Exception {
		
		//variables for testing
		String username = "testcase";
		String password = "password";
		
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();
		HttpSession testSession = req.getSession(true);
		Controller mockCon = Mockito.mock(Controller.class);
		
		
		//mock methods
		Mockito.when(mockCon.getInitParameter("action-type")).thenReturn("createPanelistAccount");
		req.addParameter("pFName", "test");
		req.addParameter("pLName", "testson");
		req.addParameter("username", username);
		req.addParameter("password", password);
		req.addParameter("pInstitution", "test state university");
		req.addParameter("pAddress", "123 test st");
		req.addParameter("pCity", "testings");
		req.addParameter("pState", "TN");
		req.addParameter("pZip", "12345");
		req.addParameter("pTelephone", "123 456 7890");
		req.addParameter("pEmail", "test@test.test");
		req.addParameter("pGender", "1");
		req.addParameter("pEthnicity", "testician");
		req.addParameter("pExpertise", "2");
		req.addParameter("pISCID", "25");
		
		//calls to real methods
		Mockito.doCallRealMethod().when(mockCon).doPost(req, res);
		Mockito.doCallRealMethod().when(mockCon).createPanelistAccount(req, res);;
		
		//execute the actual method
		mockCon.doPost(req, res);
		
		//testing
		Mockito.verify(mockCon, never()).doLogin(req, res, testSession);
		Mockito.verify(mockCon, never()).doGet(req, res);
		Mockito.verify(mockCon, never()).doLogout(res, req.getSession(true));
		Mockito.verify(mockCon).createPanelistAccount(req, res);
		assertEquals("Response status after sendRedirect should be 302", 302,res.getStatus());
		assertNotEquals("checking for successful redirect", "messagePage?messageCode=Registration Successful", res.getRedirectedUrl());
		assertNotEquals("checking for unsuccessful redirect", "errorPage?errorCode=Registration failed. Please try again.", res.getRedirectedUrl());
		assertEquals("checking for exception redirect", "errorPage?errorCode=Double Registration Exception: ISCID is already in use.", res.getRedirectedUrl());
	}
		
	/*
	Test Case ID: ISCUC-02-Subsystem-ApplicationLogic-019
	Purpose: Test to ensure that the ApplicationLogic subsystem responds as expected when a panelist is unsuccessfully created by not entering sufficient user information. 
	Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, HttpServletRequest contains parameters “action-type”, “pFName”, “pLName”, “username”, “password”, “pInstitution”, “pAddress”, “pCity”, “pState”, “pZip”, “pTelephone”, “pEmail”, “pGender”, “pEthnicity”, “pExpertise”, “ISCID”. DBManager is stubbed to recognize “42” as an ISCID already registered.
	Input: HttpServletRequest “pEmail” parameter is not set , “action-type” parameter is set to “createPanelistAccount”. Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
	Expected Output: res.getRedirectedUrl() = "errorPage?errorCode=Registration failed. Please try again.”
	
	
	*/
	@Test
	public void ISCUC_02_Subsystem_ApplicationLogic_019() throws Exception {
		
		//variables for testing
		String username = "testcase";
		String password = "password";
		
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();
		HttpSession testSession = req.getSession(true);
		Controller mockCon = Mockito.mock(Controller.class);
		
		
		//mock methods
		Mockito.when(mockCon.getInitParameter("action-type")).thenReturn("createPanelistAccount");
		req.addParameter("pFName", "test");
		req.addParameter("pLName", "testson");
		req.addParameter("username", username);
		req.addParameter("password", password);
		req.addParameter("pInstitution", "test state university");
		req.addParameter("pAddress", "123 test st");
		req.addParameter("pCity", "testings");
		req.addParameter("pState", "TN");
		req.addParameter("pZip", "12345");
		req.addParameter("pTelephone", "123 456 7890");
		//req.addParameter("pEmail", "test@test.test");
		req.addParameter("pGender", "1");
		req.addParameter("pEthnicity", "testician");
		req.addParameter("pExpertise", "2");
		req.addParameter("pISCID", "26");
		
		//calls to real methods
		Mockito.doCallRealMethod().when(mockCon).doPost(req, res);
		Mockito.doCallRealMethod().when(mockCon).createPanelistAccount(req, res);;
		
		//execute the actual method
		mockCon.doPost(req, res);
		
		//testing
		Mockito.verify(mockCon, never()).doLogin(req, res, testSession);
		Mockito.verify(mockCon, never()).doGet(req, res);
		Mockito.verify(mockCon, never()).doLogout(res, req.getSession(true));
		Mockito.verify(mockCon).createPanelistAccount(req, res);
		assertEquals("Response status after sendRedirect should be 302", 302,res.getStatus());
		assertNotEquals("checking for successful redirect", "messagePage?messageCode=Registration Successful", res.getRedirectedUrl());
		assertEquals("checking for unsuccessful redirect", "errorPage?errorCode=Registration failed. Please try again.", res.getRedirectedUrl());
		assertNotEquals("checking for exception redirect", "errorPage?errorCode=Double Registration Exception: ISCID is already in use.", res.getRedirectedUrl());
			
				
	}

}
