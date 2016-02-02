package org.usfirst.frc.team1076.robot.test.mocks;

import org.usfirst.frc.team1076.robot.IControlMethodSelector;
import org.usfirst.frc.team1076.robot.input.IDrivetrainJoystick;

public class ConstantControlMethod implements IControlMethodSelector {

	IDrivetrainJoystick controlMethod;
	
	public ConstantControlMethod(IDrivetrainJoystick controlMethod) {
		this.controlMethod = controlMethod;
	}
	
	@Override
	public IDrivetrainJoystick getControlMethod() {
		return controlMethod;
	}

}
