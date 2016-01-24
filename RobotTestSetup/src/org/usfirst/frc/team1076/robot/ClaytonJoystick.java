package org.usfirst.frc.team1076.robot;

import org.usfirst.frc.team1076.robot.IGamepad.GamepadAxis;

public class ClaytonJoystick implements IDrivetrainJoystick {

	@Override
	public MotorOutput motionForGamepadInput(IGamepad gamepad) {
		double rawX = gamepad.getAxis(GamepadAxis.LeftX);
		double rawY = gamepad.getAxis(GamepadAxis.LeftY);
        double newX = Math.sqrt(Math.pow((rawX - rawY) / 2 - rawX, 2) + 
                Math.pow((rawY - rawX) / 2 - rawY, 2));
        double newY = Math.sqrt(Math.pow((rawX + rawY) / 2 - rawX, 2) + 
                Math.pow((rawY + rawX) / 2 - rawY, 2));
        if(rawY < -rawX) {
            newX = -newX;
        }
        if(rawY > rawX) {
            newY = -newY;
        }
		return new MotorOutput(newX, newY);
	}	
}
