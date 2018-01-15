package test.ControllerTests;
import ApplicationLogic.*;
import Storage.DBManager;
import Storage.Repository.*;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpServletRequest;

@RunWith(PowerMockRunner.class)
@PrepareForTest({DBManager.class})

public class ControllerGetPanelTest {

	/*
	Test Case ID: ISCUC-19-Subsystem-ApplicationLogic-022
	Purpose: Verifies that the “getPanel” method in the PanelManager class has not been implemented.
	Test Setup: HttpServletRequest mocked (req)
	Input: HttpServletRequest instance. Call method PanelManager.getPanel(req)
	Expected Output: null
	
	
	*/
	@Test
	public void ISCUC_19_Subsystem_ApplicationLogic_022() throws Exception {
		
		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();
	
		//spies on controller class
		Controller mockCon = PowerMockito.spy(new Controller());
		
		//execute the actual method
		Panel testpanel = Controller.getPanel(req);
		
		assertEquals("function not implemented, returns null no matter what", null, testpanel );
	}
}
