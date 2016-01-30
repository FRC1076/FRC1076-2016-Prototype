package org.usfirst.frc.team1076.robot;

public interface IRobot {
	enum IntakeState {
		Up,
		Down,
		Neutral;
	}
	
	public void setLeftMotor(double speed);
	public void setRightMotor(double speed);
	public void setArmMotor(double speed);
	public void setIntakeMotor(double speed);
	public void setIntakeArticulation(IntakeState state);
}
