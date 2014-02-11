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

/**
 * @author Team 2412
 */
public interface Maps extends GLOBAL {
 
	public static final Joystick driveStick = new Joystick(USB_1);
	public static final Joystick switchStick = new Joystick(USB_2);

	public static final boolean HIGH_POWER = true;
    public static final boolean LOW_POWER = false;
    
    public static final boolean LOAD_FORWARD = true;
    public static final boolean LOAD_BACKWARD = false;
    
    public static final Timer DRIVE_TIMER = new Timer();
	
	public static final class Camera {	
		public static final AxisCamera.ResolutionT	imageResolution = AxisCamera.ResolutionT.k160x120; 
		public static final NIVision.Rect fullImage = new NIVision.Rect(0, 0, imageResolution.height, imageResolution.width);
	}
	
	public static final class Buttons {
		public static final JoystickButton launchCatapultLow = new JoystickButton(driveStick, Extreme3DController.topTopLeft);
        public static final JoystickButton launchCatapultHigh = new JoystickButton(driveStick, Extreme3DController.topTopRight);
        
        public static final JoystickButton armsForward = new JoystickButton(driveStick, Extreme3DController.topBottomLeft);
		public static final JoystickButton pickup = new JoystickButton(driveStick, Extreme3DController.baseCenterRight);
		
        public static final JoystickButton driveScale = new JoystickButton(driveStick, Extreme3DController.side);
        public static final JoystickButton shift = new JoystickButton(driveStick, Extreme3DController.baseRearRight);
        
		public static final JoystickButton setLaserPointer = new JoystickButton(driveStick, Extreme3DController.baseRearLeft);
        
        public static final JoystickButton flip = new JoystickButton(driveStick, Extreme3DController.topBottomRight);
        public static final JoystickButton latch = new JoystickButton(driveStick, Extreme3DController.baseCenterLeft);
        
	}
	
    public static final class Images {
        public static Image start;
        public static Image after;
		public static Image subtract;
    }
	
	public static final class Drive {
			public static final double DRIVE_SCALE = 0.7;
            public static final DoubleSolenoid.Value HIGH_GEAR = RETRACTED;
            public static final DoubleSolenoid.Value LOW_GEAR = EXTENDED;
            public static boolean doneDriving = false;
	}
    
    public static final class SmartDashboardKeys {
        public static final String KEY_LOADED = "Loaded";
        public static final String KEY_INRANGE = "In Range";
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
		
		public static final class CatapultPower {
			public static final boolean HIGH = true;
			public static final boolean LOW = false;
		}
        
	}
}
