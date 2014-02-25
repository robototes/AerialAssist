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
 
	public static final IIR driverStick = new IIR(20, USB_1);
	public static final Joystick coDriverStick = new Joystick(USB_2);
    
    public static final boolean LOAD_UP = false;
    public static final boolean LOAD_BACKWARD = true;
    
    public static final int TWENTY_FOUR = 1;
    public static final int TWELVE = 2;
    
    public static final boolean LOCKED = true;
    public static final boolean UNLOCKED = false;
    
    public static double PID_P = .05;
    public static double PID_I = .025;
    public static double PID_D = .0166666;
    
    public static final double ROLLER_DIAL = 0.1;
    public static final double SKID_DISTANCE = 0.0; // inches, test this
    
    public static final Timer DRIVE_TIMER = new Timer();
	
	public static final class Camera {	
		public static final AxisCamera.ResolutionT imageResolution = AxisCamera.ResolutionT.k160x120; 
		public static final NIVision.Rect fullImage = new NIVision.Rect(0, 0, imageResolution.height, imageResolution.width);
	}
	
	public static final class Buttons {
        public static final JoystickButton dumper = new JoystickButton(coDriverStick, 6);
        public static final JoystickButton manualDump = new JoystickButton(coDriverStick, 8);
        public static final JoystickButton armsUp = new JoystickButton(coDriverStick, 11);
		public static final JoystickButton roller = new JoystickButton(coDriverStick, 10);
        public static final JoystickButton reverseLoadDirection = new JoystickButton(coDriverStick, 9);
		public static final JoystickButton shiftOverride = new JoystickButton(coDriverStick, 12);
        
        public static final JoystickButton driveScale = new JoystickButton(driverStick, Extreme3DController.side);
        public static final JoystickButton shift = new JoystickButton(driverStick, Extreme3DController.trigger);
        public static final JoystickButton flip = new JoystickButton(driverStick, Extreme3DController.topTopLeft);
        
        public static final JoystickButton launchCatapultHigh = new JoystickButton(coDriverStick, 5);
        public static final JoystickButton launchCatapultLow = new JoystickButton(coDriverStick, 4);
        public static final JoystickButton latch = new JoystickButton(coDriverStick, 7);
        
        
    }
    
    
    public static final class Images {
        public static Image start;
        public static Image after;
		public static Image subtract;
    }
	
	public static final class Drive {
			public static final double DRIVE_SCALE = 0.7;
            public static final boolean HIGH_GEAR = false;
            public static final boolean LOW_GEAR = true;
            public static boolean doneDriving = false;
	}
    
    public static final class SmartDashboardKeys {
//        public static final String KEY_LOADED = "Loaded";
//        public static final String KEY_INRANGE = "In Range";
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
    
    public static final class LoadingValues {
        public static final double SPEED = 1.0;
    }
}
