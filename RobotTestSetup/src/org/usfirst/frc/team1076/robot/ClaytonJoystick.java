package org.usfirst.frc.team1076.robot;

import org.usfirst.frc.team1076.robot.IGamepad.GamepadAxis;

public class ClaytonJoystick implements IDrivetrainJoystick {

	@Override
	public MotorOutput motionForGamepadInput(IGamepad gamepad) {
		double rawX = gamepad.getAxis(GamepadAxis.LeftX);
		double rawY = gamepad.getAxis(GamepadAxis.LeftY);
		if(rawX == 0 && rawY == 0) {
			return new MotorOutput(0, 0);
		}
        double newX = (rawX + rawY) / 2;
        double newY = (rawX - rawY) / 2;
        double powerRatio = magnitude(rawX, rawY) / Math.sqrt(newX*newX + newY*newY);
		return new MotorOutput(newX * powerRatio, newY * powerRatio);
	}	
	
	private double magnitude(double rawX, double rawY) {
    	double x = rawX * Math.sqrt(1 - rawY*rawY/2);
    	double y = rawY * Math.sqrt(1 - rawX*rawX/2);
		return Math.sqrt(x*x + y*y);
	}
}
