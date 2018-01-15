package test.ControllerTests;
import ApplicationLogic.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Controller.class})

public class ControllerUpdatePanelTest {

	/*
	Test Case ID: ISCUC-17-Subsystem-ApplicationLogic-013
	Purpose: Test to ensure that the ApplicationLogic subsystem responds correctly when a panel is successfully updated.
	Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, HttpServletRequest contains parameters “action-type”, “panelStatus”, “statusComments”, “panelID”.
	Input: HttpServletRequest “action-type” parameter is set to “updatePanelStatus”. Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
	Expected Output: res.getRedirectedUrl() = “messagePage?messageCode=Panel status updated.”
	
	
	*/
	@Test
	public void ISCUC_17_Subsystem_ApplicationLogic_013() throws Exception {
		
		
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();
		//spies on controller class
		Controller mockCon = PowerMockito.spy(new Controller());

		//mock methods
		PowerMockito.doReturn("updatePanelStatus").when(mockCon).getInitParameter("action-type");
		req.addParameter("panelStatus", "status");
		req.addParameter("statusComments", "a comment");
		req.addParameter("panelID", "1");
		
		//execute the actual method
		mockCon.doPost(req, res);
		
		//testing
		assertEquals("Response status after sendRedirect should be 302", 302,res.getStatus());
		assertEquals("messagePage?messageCode=Panel status updated.", res.getRedirectedUrl());
		assertNotEquals("errorPage?errorCode=Error updating panel. Please try again.", res.getRedirectedUrl());

	}
	
	/*
	Test Case ID: ISCUC-17-Subsystem-ApplicationLogic-014
	Purpose: Test to ensure that the ApplicationLogic subsystem responds correctly when a panel is unsuccessfully updated.
	Test Setup: HttpServletRequest mocked, HttpServletResponse mocked, HttpServletRequest contains parameters “action-type”, “panelStatus”, “statusComments”, “panelID”.
	Input: HttpServletRequest “action-type” parameter is set to “updatePanelStatus”. Mocked HTTP servlet request (req) and response (res) passed to method.Calls controller.doPost( req, res).
	Expected Output: res.getRedirectedUrl() = “errorPage?errorCode=Error updating panel. Please try again.”
	
	
	*/
	@Test
	public void ISCUC_17_Subsystem_ApplicationLogic_014() throws Exception {
			
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
		MockHttpServletResponse res = new MockHttpServletResponse();
		//spies on controller class
		Controller mockCon = PowerMockito.spy(new Controller());

		//mock methods
		PowerMockito.doReturn("updatePanelStatus").when(mockCon).getInitParameter("action-type");
		req.addParameter("panelStatus", "status");
		req.addParameter("statusComments", "a comment");
		req.addParameter("panelID", "2");
		
		//execute the actual method
		mockCon.doPost(req, res);
		
		//testing
		assertEquals("Response status after sendRedirect should be 302", 302,res.getStatus());
		assertNotEquals("messagePage?messageCode=Panel status updated.", res.getRedirectedUrl());
		assertEquals("errorPage?errorCode=Error updating panel. Please try again.", res.getRedirectedUrl());

	}

}



