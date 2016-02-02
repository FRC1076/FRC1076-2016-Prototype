package org.usfirst.frc.team1076.robot.test;

import static org.junit.Assert.*;
import org.junit.Test;
import org.usfirst.frc.team1076.robot.input.MotorOutput;

public class TestMotorOutput {
	@Test
	public void testConstructor() {
		MotorOutput output = new MotorOutput(12, 42);
		assertEquals(output.left, 12, 0);
		assertEquals(output.right, 42, 0);
		
		output = new MotorOutput(Math.E, Math.PI);
		assertEquals(output.left, Math.E, 0);
		assertEquals(output.right, Math.PI, 0);
	}
}
