package test.ControllerTests;
import ApplicationLogic.*;
import static org.junit.Assert.*;
import javax.servlet.http.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Controller.class})


public class ControllerLogoutTest {

	/*
	Test Case ID: ISCUC-04-Subsystem-ApplicationLogic-004
	Purpose: Test to ensure that the ApplicationLogic subsystem properly invalidates a previously created HttpSession.
	Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, HttpServletRequest contains parameters “action-type” Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doGet( req, res).
	Input: HttpServletRequest “action-type” parameter is set to “doLogout”
	Expected Output: HttpSession should be null (invalidated)
	
	
	*/
	@Test
	public void ISCUC_04_Subsystem_ApplicationLogic_004() throws Exception {
		
		
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();
	
		//setting up a logged in session
		req.getSession(true);
		assertNotEquals("Session should have been created", null, req.getSession(false) );
		
		
		//spies on controller class
		Controller mockCon = PowerMockito.spy(new Controller());

		//mock methods
		PowerMockito.doReturn("doLogout").when(mockCon).getInitParameter("action-type");
		
		//execute the actual method
		mockCon.doGet(req, res);
		
		
		
		Mockito.verify(mockCon).doGet(req, res);
		Mockito.verify(mockCon).doLogout(Matchers.any(HttpServletResponse.class), Matchers.any(HttpSession.class));
		assertEquals("Session should have been invalidated", null, req.getSession(false) );
		assertEquals("index.jsp", res.getRedirectedUrl());
	}

}
