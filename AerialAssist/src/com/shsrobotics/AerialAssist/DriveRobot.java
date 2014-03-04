package com.shsrobotics.AerialAssist;

import com.sun.squawk.util.MathUtils;

/**
 *
 * @author RoboTotes Team 2412
 */
public class DriveRobot implements Hardware {
    public static boolean driveDirection = true; // true positive, false negative
    
    public static void basicTank(double leftAxis, double rightAxis) {
        DriveBase.drive.tankDrive(leftAxis, rightAxis);
    }
    
    public static void advancedTank(double leftAxis, double rightAxis) {
        double left = driverStick.outputX();
        double right = driverStick.outputY();
        left = MathUtils.pow(left, 5);
        right = MathUtils.pow(right, 5);
        
        if (Buttons.driveScale.held()) {
            if (driveDirection) {
                basicTank(joystickCutoff(left), joystickCutoff(right));
            } else {
                basicTank(joystickCutoff(-left), joystickCutoff(-right));
            }
        }
        else {
            if(driveDirection) {
                basicTank(left, right);
            } else {
                basicTank(-left, -right);
            }
        }
    }
    
    public static void basicArcade(double moveValue, double rotationValue) {
        DriveBase.drive.arcadeDrive(moveValue, rotationValue, false);
    }
    
	public static void advancedArcade() {
		double y = driverStick.outputY();
		double z = driverStick.outputZ();
        double scale = Buttons.driveScale.held() ? 0.5 : 1.0;
		y = MathUtils.pow(y, 3) * scale;
		z = MathUtils.pow(z, 5) * 0.9 * scale;
		
        if (driveDirection) {
            basicArcade(joystickCutoff(-y), joystickCutoff(z));
        } else {
            basicArcade(joystickCutoff(y), joystickCutoff(z));
        }
	}
    
    public static void driveUntilSonarSaysStopPID(double distanceInInches) {
        new SonarDriveTask(distanceInInches).start();
    }
    
    public static void driveUntilSonarSaysStop(double distanceInInches) {
        while (Sonar.sonar.getDistance() > distanceInInches) {
            basicArcade(-0.5, 0);
        }
    }
    
    public static void driveForTime(double translationAxis, double rotationAxis, double seconds) {
        DRIVE_TIMER.reset();
        DRIVE_TIMER.start();
        
        while (DRIVE_TIMER.get() < seconds) {
            basicArcade(translationAxis, rotationAxis);
        }
    }
	
	private static double joystickCutoff(double x) {
        if(Math.abs(x) < 0.05) {
            return 0;
        } else {
            return x;
        }
	}
}
