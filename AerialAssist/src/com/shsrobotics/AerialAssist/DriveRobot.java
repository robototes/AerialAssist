package com.shsrobotics.AerialAssist;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Timer;

/**
 * @author RoboTotes Team 2412
 */
public class DriveRobot implements Hardware {

    public static void basicTank(double leftAxis, double rightAxis) {
        DriveBase.drive.tankDrive(leftAxis, rightAxis);
    }

    public static void advancedTank(double leftAxis, double rightAxis) {
        double left = driverStick.getX();
        double right = driverStick.getY();
        left = MathUtils.pow(left, 5);
        right = MathUtils.pow(right, 5);

        if (Buttons.driveScale.held()) {
            if (Drive.DRIVE_DIRECTION) {
                basicTank(joystickCutoff(left), joystickCutoff(right));
            } else {
                basicTank(joystickCutoff(-left), joystickCutoff(-right));
            }
        } else {
            if (Drive.DRIVE_DIRECTION) {
                basicTank(left, right);
            } else {
                basicTank(-left, -right);
            }
        }
    }

    public static void basicArcade(double moveValue, double rotationValue) {
        DriveBase.drive.arcadeDrive(-moveValue, rotationValue, false);
    }

    public static void advancedArcade() {
        double y = driverStick.getY();
        double z = driverStick.getZ();
        double scale = Buttons.driveScale.held() ? 0.5 : 1.0;
        y = MathUtils.pow(y, 3) * scale;
        z = MathUtils.pow(z, 5) * 0.9 * scale;

        if (Drive.DRIVE_DIRECTION) {
            basicArcade(joystickCutoff(-y), joystickCutoff(z));
        } else {
            basicArcade(joystickCutoff(y), joystickCutoff(z));
        }
    }

    public static void driveUntilSonarSaysStop(double distanceInInches) {
        while (Sonar.sonar.getDistance() > distanceInInches) {
            basicArcade(1.0, 0);
        }
        basicArcade(0.0, 0.0);
    }

    public static void driveForTime(double translationAxis, double seconds) {
        basicArcade(translationAxis, 0);
        Timer.delay(seconds);
        basicArcade(0, 0);
    }

    private static double joystickCutoff(double x) {
        if (Math.abs(x) < 0.05) {
            return 0;
        } else {
            return x;
        }
    }
}
