package org.usfirst.frc.team1076.robot.physical;

import org.usfirst.frc.team1076.robot.IGamepad;

import java.util.Arrays;

import edu.wpi.first.wpilibj.DriverStation;

public class Gamepad implements IGamepad {
	private int port;
	private DriverStation driverStation;

	// For the change of buttons and the D-pad:
	private boolean[] lastPress;
	private int lastPOV;

	Gamepad(int port) {
		// Initialization of variable values:
		this.port = port;
		driverStation = DriverStation.getInstance();
		lastPress = new boolean[11];
		Arrays.fill(lastPress, false);
		lastPOV = -1;
	}

	Gamepad(int port, String save) {
		this(port);
	}

	public double getAxis(GamepadAxis axis) {
		return driverStation.getStickAxis(port, axis.value());
	}

	public boolean getButton(GamepadButton button) {
		return driverStation.getStickButton(port, button.value());
	}

	public boolean ifButtonChange(GamepadButton Button) {
		return getButton(Button) != lastPress[Button.value()];
	}

	public int getPOV() {
		return driverStation.getStickPOV(port, 0);
	}

	public boolean ifPOVChange() {
		return this.getPOV() != lastPOV;
	}
}