package test.ControllerTests;

import ApplicationLogic.*;
import Storage.Repository.*;

import static org.junit.Assert.*;
import java.util.ArrayList;
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


public class ControllerDoSearchTest {

	/*
	Test Case ID: ISCUC-10-Subsystem-ApplicationLogic-009
	Purpose: Test to ensure that the ApplicationLogic subsystem responds correctly if no panelists are found from a panelist search.
	Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, HttpServletRequest contains parameters “action-type”, “pFName”, “pLName”, “username”, “password”, “pInstitution”, “pAddress”, “pCity”, “pState”, “pZip”, “pTelephone”, “pEmail”, “pGender”, “pEthnicity”, “pExpertise”, “ISCID”. Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
	Input: HttpServletRequest “action-type” parameter is set to “doSearch”. 
	Expected Output: Session contains no parameters.
	
	
	*/
	@Test
	public void ISCUC_10_Subsystem_ApplicationLogic_009() throws Exception {
				
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();
			
		//setting up a logged in session
		HttpSession testSession = req.getSession(true);
				
		//spies on controller class
		Controller mockCon = PowerMockito.spy(new Controller());
		
		//mock methods
		PowerMockito.doReturn("doSearch").when(mockCon).getInitParameter("action-type");
		
		//Set initial Parameters
		req.addParameter("pFName", "test");
		req.addParameter("pLName", "testson");
		req.addParameter("username", "username");
		req.addParameter("password", "password");
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
		req.addParameter("pISCID", "");
		
		//execute the actual method
		mockCon.doPost(req, res);
		
		//testing
		assertFalse("session should have no variables set", testSession.getAttributeNames().hasMoreElements() );
		assertEquals("Response status after sendRedirect should be 302", 302,res.getStatus());
		assertEquals("messagePage?messageCode=No Panelists Found.", res.getRedirectedUrl());	
	}

	/*
	Test Case ID: ISCUC-10-Subsystem-ApplicationLogic-010
	Purpose: Test to ensure that the ApplicationLogic subsystem returns an HttpSession with an ArrayList of panelist profiles after a successful search.
	Test Setup: Test to ensure that the ApplicationLogic subsystem returns an HttpSession with an ArrayList of panelist profiles after a successful search.
	Input: HttpServletRequest “action-type” parameter is set to “doSearch”. Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
	Expected Output: Session contains a “panelist” parameter containing a panelist profile.
	
	
	*/
	@Test
	public void ISCUC_10_Subsystem_ApplicationLogic_010() throws Exception {
			
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();
		
		
		//setting up a logged in session
		HttpSession testSession = req.getSession(true);
		

		//spies on controller class
		Controller mockCon = PowerMockito.spy(new Controller());
		
		
		//mock methods
		PowerMockito.doReturn("doSearch").when(mockCon).getInitParameter("action-type");
		req.addParameter("pFName", "test");
		req.addParameter("pLName", "testson");
		req.addParameter("username", "username");
		req.addParameter("password", "password");
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
		req.addParameter("pISCID", "123");
		
		//execute the actual method
		mockCon.doPost(req, res);
		
		//testing
		assertTrue("session should have variables set", testSession.getAttributeNames().hasMoreElements() );
		assertEquals("Response status after sendRedirect should not be 302", 302,res.getStatus());
		assertEquals("displayPanelists.jsp", res.getRedirectedUrl());
		assertEquals("session should have a PanelistProfile", PanelistProfile.class, ((ArrayList<PanelistProfile>) testSession.getAttribute("Panelists")).get(0).getClass());
		
	}
}
