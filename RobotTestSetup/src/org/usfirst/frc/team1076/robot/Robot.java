
package org.usfirst.frc.team1076.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CANTalon;
import org.usfirst.frc.team1076.robot.GamepadReal;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    final String defaultAuto = "Default";
    final String customAuto = "My Auto";
    final String tankControl = "Tank";
    final String stickControl = "Stick";
    final String stickControlBetter = "Stick But Better";
    String autoSelected;
    SendableChooser chooser;
    String controlSelected;
    SendableChooser controlMethod;
    GamepadReal gamepad;
    CANTalon leftMotor;
    CANTalon rightMotor;
	
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", defaultAuto);
        chooser.addObject("My Auto", customAuto);
        SmartDashboard.putData("Auto choices", chooser);
        
        controlMethod = new SendableChooser();
        controlMethod.addDefault("Tank Control", tankControl);
        controlMethod.addObject("Stick Control", stickControl);
        SmartDashboard.putData("Control method", controlMethod);
        
        leftMotor = new CANTalon(0);
        rightMotor = new CANTalon(1);
        gamepad = new GamepadReal(0);
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
    public void autonomousInit() {
    	autoSelected = (String) chooser.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Auto selected: " + autoSelected);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
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

	public void teleopInit() {
    	controlSelected = (String) controlMethod.getSelected();
//		autoSelected = SmartDashboard.getString("Auto Selector", defaultAuto);
		System.out.println("Control method selected selected: " + controlSelected);
    }
	
    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	switch (controlSelected) {
    	case tankControl:
    		tankControl();
    		break;
    	case stickControl:
    		stickControl();
    		break;
    	case stickControlBetter:
    		claytonControl();
    		break;
    	default:
    		break;
    	}
    }

    static final double MAX_SPEED = 0.25;
    
    public void tankControl() {
    	leftMotor.set(gamepad.getLeftY() * MAX_SPEED);
    	rightMotor.set(gamepad.getRightY() * MAX_SPEED);
    }

    // The quadrant that the control stick is in
    enum Quadrant {
		UpperLeft,
		UpperRight,
    	LowerLeft,
    	LowerRight,
	};
    
	// Maps a value from an input range to an output range
	double map(double val, double bot0, double top0, double bot1, double top1) {
		double range0 = bot0 - top0;
		double range1 = bot1 - top1;
		return (val - bot0) / range0 * range1 + bot1;
	}
    
    public void stickControl() {
       	double rawX = gamepad.getLeftX();
    	double rawY = gamepad.getLeftY();
    	// map the squre input to a circle, as described in
    	// http://mathproofs.blogspot.com/2005/07/mapping-square-to-circle.html
    	double x = rawX * Math.sqrt(1 - rawY*rawY/2);
    	double y = rawY * Math.sqrt(1 - rawX*rawX/2);
    	double power = Math.sqrt(x*x + y*y);
    	double angle = Math.atan2(y, x);
    	
    	Quadrant quadrant;
    	if (x > 0) {
    		quadrant = y > 0 ? Quadrant.UpperRight : Quadrant.LowerRight;
    	} else {
    		quadrant = y > 0 ? Quadrant.UpperLeft : Quadrant.LowerLeft;
    	}
    	
    	double left, right;
    	switch (quadrant) {
		case LowerLeft:
	    	left = -1;
	    	right = map(angle, -Math.PI/2, -Math.PI, -1f, 1f);
			break;
		case LowerRight:
			right = -1;
			left = map(angle, 0f, -Math.PI/2, 1f, -1f);
			break;
		case UpperLeft:
			right = 1;
			left = map(angle, 0f, Math.PI/2, -1f, 1f);
			break;
		case UpperRight:
			left = 1;
			right = map(angle, Math.PI/2, Math.PI, 1f, -1f);
			break;
		default:
			left = 0f;
			right = 0f;
			break;
    	}
    	leftMotor.set(left * power * MAX_SPEED);
    	rightMotor.set(right * power * MAX_SPEED);
    }
    
    public void claytonControl() {
       	double rawX = gamepad.getLeftX();
    	double rawY = gamepad.getLeftY();
    	double newX = Math.sqrt(Math.pow((rawX - rawY) / 2, 2) + rawX*rawX);
    	double newY = Math.sqrt(Math.pow((rawY - rawX) / 2, 2) + rawY*rawY);
    	if(rawY < -rawX) {
    		newX = -newX;
    	}
    	if(rawY < rawX) {
    		newY = -newY;
    	}
    	leftMotor.set(newX * MAX_SPEED);
    	rightMotor.set(newY * MAX_SPEED);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    
    }
    
}
