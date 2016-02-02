package org.usfirst.frc.team1076.robot.controller;

import org.usfirst.frc.team1076.robot.gamepad.IGamepad;
import org.usfirst.frc.team1076.robot.gamepad.IGamepad.GamepadAxis;
import org.usfirst.frc.team1076.robot.gamepad.IGamepad.GamepadButton;
import org.usfirst.frc.team1076.robot.input.IDrivetrainJoystick;
import org.usfirst.frc.team1076.robot.input.MotorOutput;
import org.usfirst.frc.team1076.robot.IControlMethodSelector;
import org.usfirst.frc.team1076.robot.IRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotController implements IRobotController {
	private IDrivetrainJoystick drivetrainJoystick;
    IControlMethodSelector controlMethodSelector;
    
    IGamepad driverGamepad;
    IGamepad operatorGamepad;
    
    public RobotController(IGamepad driver, IGamepad operator,
    		IControlMethodSelector cms) {
    	driverGamepad = driver;
    	operatorGamepad = operator;
    	controlMethodSelector = cms;
    }
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit(IRobot robot) {
        
    }
    
	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString line to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the switch structure below with additional strings.
	 * If using the SendableChooser make sure to add them to the chooser code above as well.
	 */
    @Override
    public void autonomousInit(IRobot robot) {
    	
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic(IRobot robot) {

    }

    @Override
    public void teleopInit(IRobot robot) {
		drivetrainJoystick = controlMethodSelector.getControlMethod();
	}
	
    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic(IRobot robot) {
    	MotorOutput motorOutput = drivetrainJoystick.motionForGamepadInput(driverGamepad);
    	
    	robot.setLeftMotor(motorOutput.left);
    	robot.setRightMotor(motorOutput.right);
    	
    	double in = driverGamepad.getAxis(GamepadAxis.LeftTrigger);
    	double out = driverGamepad.getAxis(GamepadAxis.RightTrigger);
    	robot.setIntakeMotor(in - out);
    	
    	double armMotion = operatorGamepad.getAxis(GamepadAxis.RightY);
    	robot.setArmMotor(armMotion);
    	
    	if (driverGamepad.getButton(GamepadButton.LB)) {
    		robot.setIntakeArticulation(IRobot.IntakeState.Up);	
    	} else if (driverGamepad.getButton(GamepadButton.RB)) {
    		robot.setIntakeArticulation(IRobot.IntakeState.Down);
    	} else {
    		robot.setIntakeArticulation(IRobot.IntakeState.Neutral);
    	}
    }

	public IDrivetrainJoystick getDrivetrainJoystick() {
		return drivetrainJoystick;
	}
    
}
