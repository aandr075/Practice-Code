package test.SessionTimeoutTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import ApplicationLogic.SessionTimeout;
import Interface.UserForms;

public class SessionTimeoutTests {
	private final ByteArrayOutputStream print = new ByteArrayOutputStream();
	
	//for test case: ISCUC_30_Subsystem_ApplicationLogic_021  to compare the string printed
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(print));
	}
	
	
	/*
	Test Case ID: ISCUC-30-Subsystem-ApplicationLogic-020
	Purpose: Test to ensure that the ApplicationLogic subsystem sets the HttpSession to expire after 15 minutes of inactivity.
	Test Setup: HttpServletRequest mocked, A Session exists, and a session event exists.
	Input: An HttpSession instance exists
	Expected Output: The HttpSession has a max inactive time of 900
	
	
	*/
	@Test
	public void ISCUC_30_Subsystem_ApplicationLogic_020() {
		MockHttpServletRequest req = new MockHttpServletRequest();
	
		//a test session
		HttpSession testSession = req.getSession(true);
		HttpSessionEvent testevent = new HttpSessionEvent(testSession);
		SessionTimeout mockSes = spy(SessionTimeout.class);
		
		mockSes.sessionCreated(testevent);
		verify(mockSes).sessionCreated(testevent);
		assertTrue("session should have variables set", testSession.getAttributeNames().hasMoreElements() );
		assertEquals("session should contain a UserForms object", UserForms.class,((UserForms)testSession.getAttribute("User Form")).getClass());
		assertEquals("Max Inactive time should be 900", 900,testSession.getMaxInactiveInterval());
		
	}
	
	/*
	Test Case ID: ISCUC-30-Subsystem-ApplicationLogic-021
	Purpose: Tests the actions of the “sessionDestroyed” method, 
	Test Setup: HttpServletRequest mocked, A Session, sessionTimeout (test), and a session event (testevent) object exists.
	Input: An HttpSessionEvent instance. Call the test.sessionDestroyed(testevent).
	Expected Output: "Session Destroyed\r\n"
	
	
	*/
	@Test
	public void ISCUC_30_Subsystem_ApplicationLogic_021() {
		//a test session
		MockHttpServletRequest req = new MockHttpServletRequest();
		HttpSession testSession = req.getSession(true);
		HttpSessionEvent testevent = new HttpSessionEvent(testSession);
	
		SessionTimeout test = new SessionTimeout();
		test.sessionDestroyed(testevent);
		
		assertEquals("Session Destroyed\r\n", print.toString());
		
	}

}
