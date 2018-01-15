package test.ControllerTests;
import ApplicationLogic.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Controller.class})



public class ControllerAddToPanelTest {

		
		/*
		Test Case ID: ISCUC-11-Subsystem-ApplicationLogic-011
		Purpose:  Test to ensure that the ApplicationLogic subsystem responds correctly when a panelist is successfully added to a panel.
		Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, HttpServletRequest contains parameters “action-type”, “addToPanel”, “panelistID”. 
		Input: HttpServletRequest “action-type” parameter is set to “addToPanel”.Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
		Expected Output: res.getRedirectedUrl() = “messagePage?messageCode=Panelist has been successfully added.”
		
		
		*/
		@Test
		public void ISCUC_11_Subsystem_ApplicationLogic_011() throws Exception {
						
			//mock objects
			MockHttpServletRequest req = new MockHttpServletRequest();
			MockHttpServletResponse res = new MockHttpServletResponse();
			HttpSession testSession = req.getSession(true);
			//spies on controller class
			Controller mockCon = PowerMockito.spy(new Controller());

			//mock methods
			PowerMockito.doReturn("addToPanel").when(mockCon).getInitParameter("action-type");
			req.addParameter("addToPanel", "1");
			req.addParameter("panelistID", "1");
			
			//execute the actual method
			mockCon.doPost(req, res);
			
			//testing
			Mockito.verify(mockCon, never()).doLogin(req, res, testSession);
			Mockito.verify(mockCon, never()).doGet(req, res);
			Mockito.verify(mockCon, never()).doLogout(res, req.getSession(true));
			Mockito.verify(mockCon, never()).createPanelistAccount(req, res);
			assertEquals("Response status after sendRedirect should be 302", 302,res.getStatus());
			assertEquals("messagePage?messageCode=Panelist has been successfully added.", res.getRedirectedUrl());
			assertNotEquals("errorPage?errorCode=Error adding panelist. Please try again.", res.getRedirectedUrl());
	
		}
		
		
		/*
		Test Case ID: ISCUC-11-Subsystem-ApplicationLogic-012
		Purpose: Test to ensure that the ApplicationLogic subsystem responds correctly when a panelist is unsuccessfully added to a panel.
		Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, HttpServletRequest contains parameters “action-type”, “addToPanel”, “panelistID”.
		Input: HttpServletRequest “action-type” parameter is set to “addToPanel”. Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
		Expected Output: res.getRedirectedUrl() = “errorPage?errorCode=Error adding panelist. Please try again.”
		
		
		*/
		@Test
		public void ISCUC_11_Subsystem_ApplicationLogic_012() throws Exception {
			
			//mock objects
			MockHttpServletRequest req = new MockHttpServletRequest();
			MockHttpServletResponse res = new MockHttpServletResponse();
			HttpSession testSession = req.getSession(true);
			//spies on controller class
			Controller mockCon = PowerMockito.spy(new Controller());

			//mock methods
			PowerMockito.doReturn("addToPanel").when(mockCon).getInitParameter("action-type");
			req.addParameter("addToPanel", "2");
			req.addParameter("panelistID", "2");
			
			//execute the actual method
			mockCon.doPost(req, res);
			
			//testing
			Mockito.verify(mockCon, never()).doLogin(req, res, testSession);
			Mockito.verify(mockCon, never()).doGet(req, res);
			Mockito.verify(mockCon, never()).doLogout(res, req.getSession(true));
			Mockito.verify(mockCon, never()).createPanelistAccount(req, res);
			assertEquals("Response status after sendRedirect should be 302", 302,res.getStatus());
			assertNotEquals("messagePage?messageCode=Panelist has been successfully added.", res.getRedirectedUrl());
			assertEquals("errorPage?errorCode=Error adding panelist. Please try again.", res.getRedirectedUrl());
	
		}
}
