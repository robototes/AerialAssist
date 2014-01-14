package com.shsrobotics.AerialAssist;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * @author Team 2412 <first.robototes.com, github.com/robototes>
 */
public final class Initializer implements Hardware {
	/**
	 * Initialize robot components;
	 */
	public static final void initialize() {
		camera.writeResolution(imageResolution);
		
		NetworkTable configuration = NetworkTable.getTable("configuration");
		Field.robotPosition = (Field.Position) configuration.getValue("Initial Position", Field.Position.right); // default is right
	}
}
