package org.usfirst.frc.team1076.robot.test;

import org.junit.Test;
import static org.junit.Assert.*;
import org.usfirst.frc.team1076.robot.input.MotorOutput;
import org.usfirst.frc.team1076.robot.input.SingleJoystick;
import org.usfirst.frc.team1076.robot.test.mocks.ConstantGamepad;

public class TestSingleJoystick {
	final double DELTA = 0.0000001;
	
	SingleJoystick control;
	
	public TestSingleJoystick() {
		control = new SingleJoystick();
	}
	
	@Test
	public void TestNoMotion() {
		ConstantGamepad gamepad = new ConstantGamepad(0, 0, 0, 0);
		MotorOutput out = control.motionForGamepadInput(gamepad);
		assertEquals(0, out.left, DELTA);
		assertEquals(0, out.right, DELTA);
	}
	
	@Test
	public void TestForward() {
		ConstantGamepad gamepad = new ConstantGamepad(0, 0, 0, 1);
		MotorOutput out = control.motionForGamepadInput(gamepad);
		assertEquals(1, out.left, DELTA);
		assertEquals(1, out.right, DELTA);
	}
	
	@Test
	public void TestBackward() {
		ConstantGamepad gamepad = new ConstantGamepad(0, 0, 0, -1);
		MotorOutput out = control.motionForGamepadInput(gamepad);
		assertEquals(-1, out.left, DELTA);
		assertEquals(-1, out.right, DELTA);
	}
	
	@Test
	public void TestRight() {
		ConstantGamepad gamepad = new ConstantGamepad(1, 0, 0, 0);
		MotorOutput out = control.motionForGamepadInput(gamepad);
		assertEquals(1, out.left, DELTA);
		assertEquals(-1, out.right, DELTA);
	}
	
	@Test
	public void TestLeft() {
		ConstantGamepad gamepad = new ConstantGamepad(-1, 0, 0, 0);
		MotorOutput out = control.motionForGamepadInput(gamepad);
		assertEquals(-1, out.left, DELTA);
		assertEquals(1, out.right, DELTA);
	}
}
