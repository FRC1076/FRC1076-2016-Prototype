package org.usfirst.frc.team1076.robot;

import edu.wpi.first.wpilibj.IterativeRobot;

public class PhysicalRobot extends IterativeRobot implements IRobot {
	IRobotController robotController;
	
	public void robotInit() {
		robotController.robotInit(this);
	}
	
	// Autonomous
	public void autonomousInit() {
		robotController.autonomousInit(this);
	}
	
	public void autonomousPeriodic() {
		robotController.autonomousPeriodic(this);
	}
	
	// Teleop
	public void teleopInit() {
		robotController.teleopInit(this);
	}
	
	public void teleopPeriodic() {
		robotController.teleopPeriodic(this);
	}

	@Override
	public void setLeftMotor(double speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRightMotor(double speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setArmMotor(double speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIntakeMotor(double speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIntakeArticulation(IntakeState state) {
		// TODO Auto-generated method stub
		
	}
}
