package org.usfirst.frc.team1076.robot;

import org.usfirst.frc.team1076.robot.IGamepad.GamepadAxis;

public class SingleJoystick implements IDrivetrainJoystick {

	 // The quadrant that the control stick is in
    enum Quadrant {
		UpperLeft,
		UpperRight,
    	LowerLeft,
    	LowerRight,
	};
    
	// Maps a value from an input range to an output range
	double map(double val, double bot0, double top0, double bot1, double top1) {
		double range0 = bot0 - top0;
		double range1 = bot1 - top1;
		return (val - bot0) / range0 * range1 + bot1;
	}
	
	@Override
	public MotorOutput motionForGamepadInput(IGamepad gamepad) {
       	double rawX = gamepad.getAxis(GamepadAxis.LeftX) * -0.5f;
    	double rawY = gamepad.getAxis(GamepadAxis.LeftY);
    	// map the square input to a circle, as described in
    	// http://mathproofs.blogspot.com/2005/07/mapping-square-to-circle.html
    	double x = rawX * Math.sqrt(1 - rawY*rawY/2);
    	double y = rawY * Math.sqrt(1 - rawX*rawX/2);
    	double power = Math.sqrt(x*x + y*y);
    	double angle = Math.atan2(y, x);
    	
    	Quadrant quadrant;
    	if (x > 0) {
    		quadrant = y > 0 ? Quadrant.UpperRight : Quadrant.LowerRight;
    	} else {
    		quadrant = y > 0 ? Quadrant.UpperLeft : Quadrant.LowerLeft;
    	}
    	
    	double left, right;
    	switch (quadrant) {
		case LowerLeft:
	    	left = -1;
	    	right = map(angle, -Math.PI/2, -Math.PI, -1f, 1f);
			break;
		case LowerRight:
			right = -1;
			left = map(angle, 0f, -Math.PI/2, 1f, -1f);
			break;
		case UpperLeft:
			right = 1;
			left = map(angle, 0f, Math.PI/2, -1f, 1f);
			break;
		case UpperRight:
			left = 1;
			right = map(angle, Math.PI/2, Math.PI, 1f, -1f);
			break;
		default:
			left = 0f;
			right = 0f;
			break;
    	}
    	return new MotorOutput(left * power, right * power);
	}

}
