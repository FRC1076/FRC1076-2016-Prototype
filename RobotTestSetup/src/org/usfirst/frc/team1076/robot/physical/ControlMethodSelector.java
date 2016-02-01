package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.ClaytonJoystick;
import org.usfirst.frc.team1076.robot.IControlMethodSelector;
import org.usfirst.frc.team1076.robot.IDrivetrainJoystick;
import org.usfirst.frc.team1076.robot.SingleJoystick;
import org.usfirst.frc.team1076.robot.TankJoystick;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ControlMethodSelector implements IControlMethodSelector {
    final TankJoystick tankControl = new TankJoystick();
    final SingleJoystick stickControl = new SingleJoystick();
    final ClaytonJoystick claytonControl = new ClaytonJoystick();
    
    SendableChooser controlMethod;
    
    public ControlMethodSelector() {
        controlMethod = new SendableChooser();
        controlMethod.addDefault("Tank Control", tankControl);
        controlMethod.addObject("Stick Control", stickControl);
        controlMethod.addObject("Clayton Control", claytonControl);
        SmartDashboard.putData("Control method", controlMethod);
    }
    
    public IDrivetrainJoystick getControlMethod() {
    	return (IDrivetrainJoystick) controlMethod.getSelected();		
    }
}
