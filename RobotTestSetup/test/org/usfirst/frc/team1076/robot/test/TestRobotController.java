package org.usfirst.frc.team1076.robot.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.usfirst.frc.team1076.robot.controller.RobotController;
import org.usfirst.frc.team1076.robot.test.mocks.ConstantControlMethod;
import org.usfirst.frc.team1076.robot.test.mocks.ConstantControls;
import org.usfirst.frc.team1076.robot.test.mocks.ConstantGamepad;
import org.usfirst.frc.team1076.robot.test.mocks.MockRobot;

public class TestRobotController {

	ConstantControls controlMethod;
	ConstantGamepad driverGamepad;
	ConstantGamepad operatorGamepad;
	RobotController controller;
	MockRobot robot;
	
	public TestRobotController() {
		robot = new MockRobot();
		controlMethod = new ConstantControls();
		ConstantControlMethod controlMethodSelector = new ConstantControlMethod(controlMethod);
		controller = new RobotController(driverGamepad, operatorGamepad, controlMethodSelector);
	}
	
	@Test
	public void testTeleopInit() {
		controller.teleopInit(robot);
		assertSame(controlMethod, controller.getDrivetrainJoystick());
	}
}
