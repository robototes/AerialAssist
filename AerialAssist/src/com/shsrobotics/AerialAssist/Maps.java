package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.GLOBAL;
import com.sun.cldc.jna.Pointer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.Image;
import edu.wpi.first.wpilibj.image.NIVision;

/**
 * @author Team 2412
 */
public interface Maps extends GLOBAL {
 
	public static final Joystick joystick = new Joystick(USB_1);
	
	public static final AxisCamera.ResolutionT	imageResolution = AxisCamera.ResolutionT.k160x120; 
	public static final NIVision.Rect fullImage = new NIVision.Rect(0, 0, imageResolution.height, imageResolution.width);
	
    public static final class Images {
        public static Image start;
        public static Image after;
		public static Image subtract;
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
	}
}
