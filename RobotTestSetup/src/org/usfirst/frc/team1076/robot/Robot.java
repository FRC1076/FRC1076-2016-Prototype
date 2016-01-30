
package org.usfirst.frc.team1076.robot;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1076.robot.Gamepad;
import org.usfirst.frc.team1076.robot.IGamepad.GamepadAxis;
import org.usfirst.frc.team1076.robot.IGamepad.GamepadButton;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot implements IRobotController {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    final TankJoystick tankControl = new TankJoystick();
    final SingleJoystick stickControl = new SingleJoystick();
    final ClaytonJoystick claytonControl = new ClaytonJoystick();
 
	IDrivetrainJoystick drivetrainJoystick;
    String autoSelected;
    SendableChooser chooser;
    SendableChooser controlMethod;
    IGamepad driverGamepad;
    IGamepad operatorGamepad;
    
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit(IRobot robot) {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        controlMethod = new SendableChooser();
        controlMethod.addDefault("Tank Control", tankControl);
        controlMethod.addObject("Stick Control", stickControl);
        controlMethod.addObject("Clayton Control", claytonControl);
        SmartDashboard.putData("Control method", controlMethod);
        
		driverGamepad = new Gamepad(0);
		operatorGamepad = new Gamepad(1);
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
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic(IRobot robot) {
    	switch(autoSelected) {
    	case customAuto:
        //Put custom auto code here   
            break;
    	case defaultAuto:
    	default:
    	//Put default auto code here
            break;
    	}
    }

    @Override
    public void teleopInit(IRobot robot) {
		drivetrainJoystick = (IDrivetrainJoystick) controlMethod.getSelected();
		//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		//System.out.println("Control method selected selected: " + controlSelected);
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
    
}
