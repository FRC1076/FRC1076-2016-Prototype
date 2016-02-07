package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.IRobot;
import org.usfirst.frc.team1076.robot.IRobotController;
import org.usfirst.frc.team1076.robot.RobotController;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PhysicalRobot extends IterativeRobot implements IRobot {
	Gamepad driverGamepad = new Gamepad(0);
	Gamepad operatorGamepad = new Gamepad(1);
	ControlMethodSelector cms = new ControlMethodSelector();
	IRobotController robotController =
			new RobotController(driverGamepad, operatorGamepad, cms);
	
    CANTalon leftMotor, rightMotor, leftSlave, rightSlave;
    CANTalon intakeMotor;
    CANTalon armMotor;
    
    Compressor compressor;
    DoubleSolenoid intakePneumatic;
	
    static final int LEFT_INDEX = 0;
    static final int RIGHT_INDEX = 2;
    static final int INTAKE_INDEX = 4;
    static final int ARM_INDEX = 5;
    
    static final double MAX_SPEED = 1.0;
    static final double INTAKE_SPEED = 0.5;
    static final double ARM_SPEED = 0.5;
    double driveSpeed = MAX_SPEED;
	
	public void robotInit() {
		robotController.robotInit(this);
		SmartDashboard.putNumber("drive speed", driveSpeed);
		
		rightMotor = new CANTalon(RIGHT_INDEX);
		rightSlave = new CANTalon(RIGHT_INDEX+1);
		rightSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		rightSlave.set(RIGHT_INDEX);
		rightMotor.setInverted(true);

		leftMotor = new CANTalon(LEFT_INDEX);
		leftSlave = new CANTalon(LEFT_INDEX+1);
		leftSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftSlave.set(LEFT_INDEX);

		intakeMotor = new CANTalon(INTAKE_INDEX);
		armMotor = new CANTalon(ARM_INDEX);

		compressor = new Compressor(0);
		compressor.setClosedLoopControl(true);

		intakePneumatic = new DoubleSolenoid(0, 1);
		intakePneumatic.set(DoubleSolenoid.Value.kOff);
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
		driveSpeed = SmartDashboard.getNumber("drive speed");
    	// make drive speed in the range 0-MAX_SPEED
    	if (driveSpeed > MAX_SPEED) {
    		driveSpeed = MAX_SPEED;
    	} else if (driveSpeed < 0) {
    		driveSpeed = 0;
    	}
    	// store the fixed speed back in the SmartDashboard
    	SmartDashboard.putNumber("driveSpeed", driveSpeed);
		robotController.teleopPeriodic(this);
	}

	@Override
	public void setLeftMotor(double speed) {
		leftMotor.set(speed * driveSpeed);
	}

	@Override
	public void setRightMotor(double speed) {
		rightMotor.set(speed * driveSpeed);
	}

	@Override
	public void setArmMotor(double speed) {
		armMotor.set(speed * ARM_SPEED);
	}

	@Override
	public void setIntakeMotor(double speed) {
		intakeMotor.set(speed * INTAKE_SPEED);
	}

	@Override
	public void setIntakeArticulation(IntakeState state) {
		switch (state) {
		case Up:
			intakePneumatic.set(DoubleSolenoid.Value.kForward);
			break;
		case Down:
			intakePneumatic.set(DoubleSolenoid.Value.kReverse);
			break;
		case Neutral:
			intakePneumatic.set(DoubleSolenoid.Value.kOff);
			break;
		}
	}
}
