package org.usfirst.frc.team1076.robot.test;

import org.junit.Test;
import org.junit.Assert;
import org.usfirst.frc.team1076.robot.MotorOutput;
import org.usfirst.frc.team1076.robot.TankJoystick;
import org.usfirst.frc.team1076.robot.test.mocks.ConstantGamepad;

public class TestTankJoystick {
	final double DELTA = 0.0000001;
	
	TankJoystick control;
	
	public TestTankJoystick() {
		control = new TankJoystick();
	}
	
	@Test
	public void TestNoMotion() {
		ConstantGamepad gamepad = new ConstantGamepad(0, 0, 0, 0);
		MotorOutput out = control.motionForGamepadInput(gamepad);
		Assert.assertEquals(out.left, 0, DELTA);
		Assert.assertEquals(out.right, 0, DELTA);
	}
	
	@Test
	public void TestForward() {
		ConstantGamepad gamepad = new ConstantGamepad(0, 1, 0, 1);
		MotorOutput out = control.motionForGamepadInput(gamepad);
		Assert.assertEquals(out.left, 1, DELTA);
		Assert.assertEquals(out.right, 1, DELTA);
	}
	
	@Test
	public void TestBackward() {
		ConstantGamepad gamepad = new ConstantGamepad(0, -1, 0, -1);
		MotorOutput out = control.motionForGamepadInput(gamepad);
		Assert.assertEquals(out.left, -1, DELTA);
		Assert.assertEquals(out.right, -1, DELTA);
	}
	
	@Test
	public void TestRight() {
		ConstantGamepad gamepad = new ConstantGamepad(0, 1, 0, -1);
		MotorOutput out = control.motionForGamepadInput(gamepad);
		Assert.assertEquals(out.left, 1, DELTA);
		Assert.assertEquals(out.right, -1, DELTA);
	}
	
	@Test
	public void TestLeft() {
		ConstantGamepad gamepad = new ConstantGamepad(0, -1, 0, 1);
		MotorOutput out = control.motionForGamepadInput(gamepad);
		Assert.assertEquals(out.left, -1, DELTA);
		Assert.assertEquals(out.right, 1, DELTA);
	}
}
