package com.shsrobotics.AerialAssist;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author RoboTotes Team 2412
 */
public class Drive implements Hardware {
	public static IIR xIIR = new IIR();
	public static IIR yIIR = new IIR();
    
	public static void drive() {
		double x = xIIR.output(driveStick.getX());
		double y = yIIR.output(driveStick.getY());
		x = MathUtils.pow(x, 3);
		y = MathUtils.pow(y, 5);
		
		if (!Maps.Buttons.driveScale.held()) {
			DriveBase.drive.arcadeDrive(driveScaleFunction(x), driveScaleFunction(y));
		}
		else {
			DriveBase.drive.arcadeDrive(x, y);
		}
	}
    public static void driveForTime(double seconds) {
        timer.reset();
        timer.start();
        
        while (timer.get() < seconds) {
            drive();
        }
    }
	
	private static double driveScaleFunction(double x) {
		return (x-Maps.Drive.DRIVE_SCALE)*(x-Maps.Drive.DRIVE_SCALE)*(x-Maps.Drive.DRIVE_SCALE) -  Maps.Drive.DRIVE_SCALE;
	}
}
