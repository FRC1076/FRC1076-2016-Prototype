package org.usfirst.frc.team1076.robot.test;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import org.usfirst.frc.team1076.robot.input.MotorOutput;
import org.usfirst.frc.team1076.robot.input.SingleJoystick;
import org.usfirst.frc.team1076.robot.test.mocks.ConstantGamepad;

public class TestSingleJoystick {
	final double DELTA = 0.0000001;
	
	SingleJoystick control;
	ConstantGamepad gamepad;
	
	public TestSingleJoystick() {
		control = new SingleJoystick();
		gamepad = new ConstantGamepad();
	}
	
	@Before
	public void reset() {
		gamepad.reset();
	}
	
	@Test
	public void testNoMotion() {
		gamepad.reset();
		MotorOutput out = control.motionForGamepadInput(gamepad);
		assertEquals(0, out.left, DELTA);
		assertEquals(0, out.right, DELTA);
	}
	
	@Test
	public void testForward() {
		gamepad.setRightY(1);
		MotorOutput out = control.motionForGamepadInput(gamepad);
		assertEquals(1, out.left, DELTA);
		assertEquals(1, out.right, DELTA);
	}
	
	@Test
	public void testBackward() {
		gamepad.setRightY(-1);
		MotorOutput out = control.motionForGamepadInput(gamepad);
		assertEquals(-1, out.left, DELTA);
		assertEquals(-1, out.right, DELTA);
	}
	
	@Test
	public void testRight() {
		gamepad.setLeftX(1);
		MotorOutput out = control.motionForGamepadInput(gamepad);
		assertEquals(1, out.left, DELTA);
		assertEquals(-1, out.right, DELTA);
	}
	
	@Test
	public void testLeft() {
		gamepad.setLeftX(-1);
		MotorOutput out = control.motionForGamepadInput(gamepad);
		assertEquals(-1, out.left, DELTA);
		assertEquals(1, out.right, DELTA);
	}
}
