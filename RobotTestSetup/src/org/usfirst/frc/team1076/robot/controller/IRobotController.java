package org.usfirst.frc.team1076.robot.controller;

import org.usfirst.frc.team1076.robot.IRobot;

public interface IRobotController {
	public void robotInit(IRobot robot);
	
	// Autonomous
	public void autonomousInit(IRobot robot);
	public void autonomousPeriodic(IRobot robot);
	
	// Teleop
	public void teleopInit(IRobot robot);
	public void teleopPeriodic(IRobot robot);
}
