package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.GLOBAL;
import com.shsrobotics.library.JoystickButton;
import com.shsrobotics.library.joysticks.Extreme3DController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.Image;
import edu.wpi.first.wpilibj.image.NIVision;

/**
 * @author Team 2412
 */
public interface Maps extends GLOBAL {
 
    public static final IIR driverStick = new IIR(new IIR.Smoothing(50, 0, 25), USB_1);
    public static final Joystick coDriverStick = new Joystick(USB_2);
    
    public static final int TWENTY_FOUR = 1;
    public static final int TWELVE = 2;
    
    public static final boolean UNLOCKED = true;
    public static final boolean LOCKED = false;

    public static final double ROLLER_DIAL = -0.25;
    public static final double SKID_DISTANCE = 5.0; // inches, test this
    
    public static final Timer DRIVE_TIMER = new Timer();
    public static final Timer LATCH_TIMER = new Timer();
	
    public static final class Camera {	
        public static final AxisCamera.ResolutionT imageResolution = AxisCamera.ResolutionT.k160x120; 
        public static final NIVision.Rect fullImage = new NIVision.Rect(0, 0, imageResolution.height, imageResolution.width);
    }
	
    public static final class Buttons {
        
        public static final JoystickButton driveScale = new JoystickButton(driverStick, Extreme3DController.side);
        public static final JoystickButton shift = new JoystickButton(driverStick, Extreme3DController.trigger);
        public static final JoystickButton flip = new JoystickButton(driverStick, Extreme3DController.topTopLeft);
        
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
            public static final boolean HIGH_GEAR = true;
            public static final boolean LOW_GEAR = false;
            public static boolean doneDriving = false;
	}
    
    public static final class SmartDashboardKeys {
        public static final String KEY_ARM_STATE = "Arm Position";
    }
    
    public static final class Field {
        public static Field.Position robotPosition;

        public static final class Goal {
            public static final Field.Goal left = new Field.Goal(1);
            public static final Field.Goal right = new Field.Goal(2);
			
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
                public static final Field.Position left = new Field.Position(1);
                public static final Field.Position right = new Field.Position(2);
			
                private final int value;
			
                private Position(int value) {
                    this.value = value;
                }
            }
        
            public static final class RobotPosition {
                public RobotPosition(Field.Position pos) {
                    Field.robotPosition = pos;
                }
            }
	}
    
   public static final class CatapultPower {
       public static final boolean HIGH = true;
       public static final boolean LOW = false;
    }
    
    public static final class DumpMode {
        public static final boolean PWM = true;
        public static final boolean SINGLE = false;
    }
    
    public static final class Loading {
        public static double actualSpeed = 0.75;
    }
}
