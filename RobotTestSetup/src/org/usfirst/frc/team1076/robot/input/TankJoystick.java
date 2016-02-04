package org.usfirst.frc.team1076.robot.input;

import org.usfirst.frc.team1076.robot.gamepad.IGamepad;
import org.usfirst.frc.team1076.robot.gamepad.IGamepad.GamepadAxis;

public class TankJoystick implements IDrivetrainJoystick {

	@Override
	public MotorOutput motionForGamepadInput(IGamepad gamepad) {
		return new MotorOutput(gamepad.getAxis(GamepadAxis.LeftY), 
				gamepad.getAxis(GamepadAxis.RightY));
	}

}
