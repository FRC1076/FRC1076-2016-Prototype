package org.usfirst.frc.team1076.robot.test.mocks;

import org.usfirst.frc.team1076.robot.IRobot;

public class MockRobot implements IRobot {
	private double left, right, arm, intake;
	IntakeState articulation;

	@Override
	public void setLeftMotor(double speed) {
		left = speed;
	}

	@Override
	public void setRightMotor(double speed) {
		right = speed;
	}

	@Override
	public void setArmMotor(double speed) {
		arm = speed;
	}

	@Override
	public void setIntakeMotor(double speed) {
		intake = speed;
	}

	@Override
	public void setIntakeArticulation(IntakeState state) {
		articulation = state;
	}

	public double getIntake() {
		return intake;
	}

	public double getLeft() {
		return left;
	}
	
	public double getRight() {
		return right;
	}
	
	public double getArm() {
		return arm;
	}
}
