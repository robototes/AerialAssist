package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.GLOBAL;
import com.shsrobotics.library.JoystickButton;
import com.shsrobotics.library.joysticks.Extreme3DController;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.Image;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Contains all constants.
 *
 * @author Team 2412
 */
public interface Maps extends GLOBAL {

    public static final Joystick driverStick = new Joystick(USB_1);
    public static final Joystick coDriverStick = new Joystick(USB_2);

    public static final double MAX_PRESSURE = 120.0;
    public static final Timer DRIVE_TIMER = new Timer();

    public static final class Camera {

        public static final AxisCamera.ResolutionT imageResolution = AxisCamera.ResolutionT.k160x120;
        public static final NIVision.Rect fullImage = new NIVision.Rect(0, 0, imageResolution.height, imageResolution.width);
    }

    public static final class Autonomous {

        public static boolean 
            TWO_BALL_AUTONOMOUS,
            LEFT_SIDE;
        public static double DRIVING_TIME =  SmartDashboard.getNumber("Autonomous Drive Time");
    }

    public static final class Arms {
        public static final DoubleSolenoid.Value 
            OUT = EXTENDED,
            IN = RETRACTED;
    }

    public static final class Modules {
        public static final int 
            TWENTY_FOUR = 1,
            TWELVE = 2;
    }

    public static final class Buttons {

        public static final JoystickButton driveScale = new JoystickButton(driverStick, Extreme3DController.side);
        public static final JoystickButton shift = new JoystickButton(driverStick, Extreme3DController.trigger);

        public static final JoystickButton rightSolenoid = new JoystickButton(driverStick, Extreme3DController.baseCenterRight);
        public static final JoystickButton rightSolenoidB = new JoystickButton(driverStick, Extreme3DController.baseRearRight);
        public static final JoystickButton leftSolenoid = new JoystickButton(driverStick, Extreme3DController.baseCenterLeft);
        public static final JoystickButton leftSolenoidB = new JoystickButton(driverStick, Extreme3DController.baseRearLeft);
        public static final JoystickButton pneumaticsOff = new JoystickButton(driverStick, Extreme3DController.baseFrontLeft);
        public static final JoystickButton pneumaticsOffB = new JoystickButton(driverStick, Extreme3DController.baseFrontRight);

        public static final JoystickButton launchCatapult = new JoystickButton(coDriverStick, 1);
        public static final JoystickButton latch = new JoystickButton(coDriverStick, 2);
        public static final JoystickButton dumpMode = new JoystickButton(coDriverStick, 3);
        public static final JoystickButton armsUp = new JoystickButton(coDriverStick, 4);
        public static final JoystickButton charge = new JoystickButton(coDriverStick, 5);
        public static final JoystickButton dump = new JoystickButton(coDriverStick, 6);
        public static final JoystickButton rollerIn = new JoystickButton(coDriverStick, 7);
        public static final JoystickButton rollerOut = new JoystickButton(coDriverStick, 8);
    }

    public static final class Images {

        public static Image start;
        public static Image after;
        public static Image subtract;
    }

    public static final class Drive {

        public static final double DRIVE_SCALE = 0.7;
        public static final boolean 
            HIGH_GEAR = true,
            LOW_GEAR = false;
        public static boolean DRIVE_DIRECTION = true; // true positive, false negative
    }

    public static final class CatapultPower {
        public static final boolean
            HIGH = true,
            LOW = false;
        public static boolean
            RIGHT_SOLENOID_OFF = false,
            LEFT_SOLENOID_OFF = false;
    }

    public static final class Latch {

        public static final boolean UNLOCKED = true,
            LOCKED = false;
    }

    public static final class DumpMode {

        public static final boolean PWM = true,
            SINGLE = false;
    }

    public static final class RollerValues {

        public static final double 
            ACTUAL_SPEED = 0.75,
            ROLLER_DIAL = -0.25;
    }

    public static final class Field {

        public static Position robotPosition;

        public static final class Goal {

            public static final Goal 
                LEFT = new Goal(1),
                RIGHT = new Goal(2);

            private final int value;
            public boolean isHot = false;

            private Goal(int value) {
                this.value = value;
            }

            public void setState(boolean isHot) {
                this.isHot = isHot;
            }
        }

        public static final class Position {

            public static final Position 
                LEFT = new Position(1),
                RIGHT = new Position(2);

            private final int value;

            private Position(int value) {
                this.value = value;
            }

            public static void setRobotPosition(Position position) {
                robotPosition = position;
            }
        }
    }

    public static final class Flags {

        public static boolean useTransducerState = false;
        public static boolean pneumaticsOff = false;

    }
}
