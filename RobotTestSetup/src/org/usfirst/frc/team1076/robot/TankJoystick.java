package org.usfirst.frc.team1076.robot;

public class TankJoystick implements IDrivetrainJoystick {

	@Override
	public MotorOutput motionForGamepadInput(IGamepad gamepad) {
		return new MotorOutput(gamepad.getLeftY(), gamepad.getRightY());
	}

}
