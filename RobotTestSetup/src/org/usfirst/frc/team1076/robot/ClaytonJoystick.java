package org.usfirst.frc.team1076.robot;

public class ClaytonJoystick implements IDrivetrainJoystick {

	@Override
	public MotorOutput motionForGamepadInput(IGamepad gamepad) {
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
		
		return new MotorOutput(newX, newY);
	}	
}
