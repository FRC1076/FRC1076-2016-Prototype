package org.usfirst.frc.team1076.robot.test.mocks;

import org.usfirst.frc.team1076.robot.gamepad.IGamepad;

public class ConstantGamepad implements IGamepad {
	double leftX, leftY, rightX, rightY;
	
	public ConstantGamepad() {
		reset();
	}
	
	public ConstantGamepad reset() {
		leftX = leftY = rightX = rightY = 0;
		return this;
	}
	
	public ConstantGamepad(double leftX, double leftY, double rightX, double rightY) {
		this.leftX = leftX;
		this.leftY = leftY;
		this.rightX = rightX;
		this.rightY = rightY;
	}
	
	public ConstantGamepad setLeft(double x, double y) {
		leftX = x;
		leftY = y;
		return this;
	}
	
	public ConstantGamepad setLeftX(double x) {
		leftX = x;
		return this;
	}
	
	public ConstantGamepad setLeftY(double y) {
		leftY = y;
		return this;
	}
	
	public ConstantGamepad setRight(double x, double y) {
		rightX = x;
		rightY = y;
		return this;
	}
	
	public ConstantGamepad setRightX(double x) {
		rightX = x;
		return this;
	}
	
	public ConstantGamepad setRightY(double y) {
		rightY = y;
		return this;
	}
	
	@Override
	public double getAxis(GamepadAxis axis) {
		switch (axis) {
		case LeftTrigger:
			return 0;
		case LeftX:
			return leftX;
		case LeftY:
			return leftY;
		case RightTrigger:
			return 0;
		case RightX:
			return rightX;
		case RightY:
			return rightY;
		default:
			return 0;
		}
	}

	@Override
	public boolean getButton(GamepadButton button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ifButtonChange(GamepadButton button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPOV() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean ifPOVChange() {
		// TODO Auto-generated method stub
		return false;
	}

}
