package test.PanelManagerTests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.mock.web.MockHttpServletRequest;

import ApplicationLogic.PanelManager;
import Storage.Repository.Panel;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PanelManager.class})

public class PanelManagerGetPanelTest {

	/*
	Test Case ID: ISCUC-19-Subsystem-ApplicationLogic-015
	Purpose: Test to ensure that the ApplicationLogic subsystem returns null when attempting to get a panel (it is not yet implemented). verifies call to method.
	Test Setup: HttpServletRequest mocked
	Input: Mocked HTTP servlet request (req) passed to method.Calls controller.getPanel( req).
	Expected Output: null
	
	
	*/
	@Test
	public void ISCUC_19_Subsystem_ApplicationLogic_015() throws Exception {

		//mock objects
		MockHttpServletRequest req = new MockHttpServletRequest();

		//spies on controller class
		PanelManager mockPan = PowerMockito.spy(new PanelManager());

		//execute the actual method
		Panel test = PanelManager.getPanel(req);
		
		
		assertEquals("Method just returns null", null, test );
		
	}
}
