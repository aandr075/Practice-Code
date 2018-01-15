package test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.ControllerTests.*;
import test.SessionTimeoutTests.*;
import test.PanelManagerTests.PanelManagerGetPanelTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ControllerLoginTest.class,
	ControllerGetPanelTest.class,
	ControllerGetEmployeePanelsTest.class,
	ControllerDoSearchTest.class,
	ControllerCreatePanelTest.class,
	ControllerCreatePanelistTest.class,
	ControllerAddToPanelTest.class,
	ControllerLogoutTest.class,
	ControllerUpdatePanelTest.class,
	SessionTimeoutTests.class,
	PanelManagerGetPanelTest.class
	
	
})

public class AppLogicTestSuite {


}
