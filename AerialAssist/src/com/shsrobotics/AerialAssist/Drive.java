/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shsrobotics.AerialAssist;

/**
 *
 * @author RoboTotes Team 2412
 */
public class Drive implements Hardware {
	public static void drive() {
		if (!Maps.Buttons.driveScale.held()) {
			DriveBase.drive.arcadeDrive(driveScaleFunction(driveStick.getX()), driveScaleFunction(noGyroscope));
		}
		else {
			DriveBase.drive.arcadeDrive(Hardware.driveStick.getX(), driveStick.getY());
		}
	}
	
	private static double driveScaleFunction(double x) {
		return (x-Maps.Drive.DRIVE_SCALE)*(x-Maps.Drive.DRIVE_SCALE)*(x-Maps.Drive.DRIVE_SCALE) -  Maps.Drive.DRIVE_SCALE;
	}
}
