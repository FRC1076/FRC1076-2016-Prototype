package org.usfirst.frc.team1076.robot;

import org.usfirst.frc.team1076.robot.IGamepad.GamepadAxis;

public class TankJoystick implements IDrivetrainJoystick {

	@Override
	public MotorOutput motionForGamepadInput(IGamepad gamepad) {
		return new MotorOutput(gamepad.getAxis(GamepadAxis.LeftY), 
				gamepad.getAxis(GamepadAxis.RightY));
	}

}
