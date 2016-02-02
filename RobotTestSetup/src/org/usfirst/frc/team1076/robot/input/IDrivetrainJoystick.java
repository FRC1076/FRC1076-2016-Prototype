package org.usfirst.frc.team1076.robot.input;

import org.usfirst.frc.team1076.robot.gamepad.IGamepad;

public interface IDrivetrainJoystick {
	MotorOutput motionForGamepadInput(IGamepad gamepad);
}
