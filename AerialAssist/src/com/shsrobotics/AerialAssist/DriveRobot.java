package com.shsrobotics.AerialAssist;

import com.sun.squawk.util.MathUtils;

/**
 *
 * @author RoboTotes Team 2412
 */
public class DriveRobot implements Hardware {
	public static IIR xIIR = new IIR();
	public static IIR yIIR = new IIR();
    public static boolean driveDirection = true; // true positive, false negative
    
	public static void drive() {
		double x = xIIR.output(driveStick.getX());
		double y = yIIR.output(driveStick.getY());
		x = MathUtils.pow(x, 3);
		y = MathUtils.pow(y, 5);
		
		if (!Maps.Buttons.driveScale.held()) {
            if (driveDirection) {
                DriveBase.drive.arcadeDrive(driveScaleFunction(x), driveScaleFunction(y));
            } else {
                DriveBase.drive.arcadeDrive(driveScaleFunction(-x), driveScaleFunction(-y));
            }
		}
		else {
            if (driveDirection) {
                DriveBase.drive.arcadeDrive(x, y);
            } else {
                DriveBase.drive.arcadeDrive(-x, -y);
            }
		}
	}
    public static void driveForTime(double seconds) {
        DRIVE_TIMER.reset();
        DRIVE_TIMER.start();
        
        while (DRIVE_TIMER.get() < seconds) {
            drive();
        }
    }
	
    public static void reverseDirection() {
        driveDirection = !driveDirection;
    }
	private static double driveScaleFunction(double x) {
		return (x-Maps.Drive.DRIVE_SCALE)*(x-Maps.Drive.DRIVE_SCALE)*(x-Maps.Drive.DRIVE_SCALE) -  Maps.Drive.DRIVE_SCALE;
	}
}
