package org.usfirst.frc.team1076.robot.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.usfirst.frc.team1076.robot.controller.RobotController;
import org.usfirst.frc.team1076.robot.test.mocks.ConstantControlMethod;
import org.usfirst.frc.team1076.robot.test.mocks.ConstantControls;
import org.usfirst.frc.team1076.robot.test.mocks.ConstantGamepad;
import org.usfirst.frc.team1076.robot.test.mocks.MockRobot;

public class TestRobotController {
	final double DELTA = 0.00001;
	
	ConstantControls controlMethod;
	ConstantGamepad driverGamepad;
	ConstantGamepad operatorGamepad;
	RobotController controller;
	MockRobot robot;
	
	public TestRobotController() {
		driverGamepad = new ConstantGamepad();
		operatorGamepad = new ConstantGamepad();
		robot = new MockRobot();
		controlMethod = new ConstantControls();
		ConstantControlMethod controlMethodSelector = new ConstantControlMethod(controlMethod);
		controller = new RobotController(driverGamepad, operatorGamepad, controlMethodSelector);
	}
	
	@Test
	public void testTeleopInit() {
		controller.teleopInit(robot);
		assertSame("The control method on the controller should be set during initialization",
				controlMethod, controller.getDrivetrainJoystick());
	}
	
	@Test
	public void testNeutralControls() {
		controller.teleopInit(robot);
		controlMethod.reset();
		controller.teleopPeriodic(robot);
		assertEquals("If the controls are in a neutral position, the left motor shouldn't move",
				0, robot.getLeft(), DELTA);
		assertEquals("If the controls are in a neutral position, the right motor shouldn't move",
				0, robot.getRight(), DELTA);
	}
	
	@Test
	public void testForwardControls() {
		controller.teleopInit(robot);
		controlMethod.reset().setLeft(1).setRight(1);
		controller.teleopPeriodic(robot);
		assertEquals("If the controls are forward, the robot's left motor should run forward",
				1, robot.getLeft(), DELTA);
		assertEquals("If the controls are forward, the robot's right motor should run forward",
				1, robot.getRight(), DELTA);
	}
	
	@Test
	public void testBackwardControls() {
		controller.teleopInit(robot);
		controlMethod.reset().setLeft(-1).setRight(-1);
		controller.teleopPeriodic(robot);
		assertEquals("If the controls are backward, the robot's left motor should run backward",
				-1, robot.getLeft(), DELTA);
		assertEquals("If the controls are forward, the robot's right motor should run backward",
				-1, robot.getRight(), DELTA);
	}
	
	@Test
	public void testArmControls() {
		controller.teleopInit(robot);
		operatorGamepad.reset().setRightY(0);
		controller.teleopPeriodic(robot);
		assertEquals("With the operator right stick netrual, the arm shouldn't move",
				0, robot.getArm(), DELTA);
		
		operatorGamepad.setRightY(1);
		controller.teleopPeriodic(robot);
		assertEquals("With the operator right stick forward, the arm should move forward",
				1, robot.getArm(), DELTA);
		
		operatorGamepad.setRightY(-1);
		controller.teleopPeriodic(robot);
		assertEquals("With the operator right stick backward, the arm should move backward",
				-1, robot.getArm(), DELTA);
	}
	
	@Test
	public void testIntakeSpeeds() {
		controller.teleopInit(robot);
		driverGamepad.reset();
		controller.teleopPeriodic(robot);
		assertEquals("With neither trigger pressed, the intake shouldn't run",
				0, robot.getIntake(), DELTA);
		
		driverGamepad.reset().setLT(1);
		controller.teleopPeriodic(robot);
		assertEquals("With the left trigger pressed, the intake should run forward",
				1, robot.getIntake(), DELTA);
		
		driverGamepad.reset().setLT(0.5);
		controller.teleopPeriodic(robot);
		assertEquals("With the left trigger half pressed, the intake should run at half speed",
				0.5, robot.getIntake(), DELTA);
		
		driverGamepad.reset().setRT(1);
		controller.teleopPeriodic(robot);
		assertEquals("With the right trigger pressed, the intake should run backward",
				-1, robot.getIntake(), DELTA);
		
		driverGamepad.setLT(1).setRT(1);
		controller.teleopPeriodic(robot);
		assertEquals("With both triggers pressed, the intake shouldn't run",
				0, robot.getIntake(), DELTA);
	}
}
