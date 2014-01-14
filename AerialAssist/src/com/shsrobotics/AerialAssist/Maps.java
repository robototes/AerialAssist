package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.GLOBAL;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.camera.AxisCamera.ResolutionT;
import edu.wpi.first.wpilibj.image.Image;
import edu.wpi.first.wpilibj.image.NIVision.Rect;

/**
 * @author Team 2412
 */
public interface Maps extends GLOBAL {
	
	public static final Joystick joystick = new Joystick(USB_1);
	
	public static final ResolutionT	imageResolution = ResolutionT.k160x120; 
	public static final Rect fullImage = new Rect(0, 0, imageResolution.height, imageResolution.width);
	
    public static final class Images {
        public static Image start;
        public static Image after;
    }
	
	public static final class Field {
		public static Position robotPosition;
		
		public static final class Goal {
			public static final Goal left = new Goal(1);
			public static final Goal right = new Goal(2);
			
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
			public static final Position left = new Position(1);
			public static final Position right = new Position(2);
			
			private final int value;
			
			private Position(int value) {
				this.value = value;
			}
		}
	}
}
