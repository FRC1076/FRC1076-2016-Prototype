package org.usfirst.frc.team1076.robot;

import org.usfirst.frc.team1076.robot.IGamepad.GamepadButton;

public interface IGamepad {
	enum GamepadButton {
		A(1),
		B(2),
		X(3),
		Y(4),
		LB(5),
		RB(6),
		Back(7),
		Start(8),
		LStick(9),
		RStick(10);
		
		private byte value;
		GamepadButton (int value) {
			this.value = (byte) value;
		}
		byte value () {
			return this.value;
		}
	};
	enum GamepadAxis {
		LeftX(0),
		LeftY(1),
		LeftTrigger(2),
		RightTrigger(3),
		RightX(4),
		RightY(5);
		
		private byte value;
		GamepadAxis (int value) {
			this.value = (byte) value;
		}
		byte value () {
			return this.value;
		}
	};
	
	// Constants for D-pad values
	public static final int POV_UP = 0;
	public static final int POV_UP_RIGHT = 45;
	public static final int POV_RIGHT = 90;
	public static final int POV_DOWN_RIGHT = 135;
	public static final int POV_DOWN = 180;
	public static final int POV_DOWN_LEFT = 225;
	public static final int POV_LEFT = 270;
	public static final int POV_UP_LEFT = 315;
	public static final int POV_OFF = -1;

	double getAxis(GamepadAxis Axis);

	boolean getButton(GamepadButton Button);
	boolean ifButtonChange(GamepadButton Button);
	
	int getPOV();
	boolean ifPOVChange();
}