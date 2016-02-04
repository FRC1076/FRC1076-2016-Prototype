package org.usfirst.frc.team1076.robot.test.mocks;

import org.usfirst.frc.team1076.robot.gamepad.IGamepad;
import org.usfirst.frc.team1076.robot.input.IDrivetrainJoystick;
import org.usfirst.frc.team1076.robot.input.MotorOutput;

public class ConstantControls implements IDrivetrainJoystick {
	
	double left, right;
	
	public ConstantControls() {
		reset();
	}
	
	public ConstantControls(double l, double r) {
		left = l;
		right = r;
	}
	
	public ConstantControls reset() {
		left = right = 0;
		return this;
	}
	
	public ConstantControls setLeft(double l) {
		left = l;
		return this;
	}
	
	public ConstantControls setRight(double r) {
		right = r;
		return this;
	}
	
	@Override
	public MotorOutput motionForGamepadInput(IGamepad gamepad) {
		return new MotorOutput(left, right);
	}

}
