package com.shsrobotics.AerialAssist;

import com.shsrobotics.AerialAssist.Maps.Field.*;
import com.sun.cldc.jna.Pointer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.image.LinearAverages;
import edu.wpi.first.wpilibj.image.NIVision;

/**
 * Has all the vision tracking routines.
 * @author RoboTotes Team 2412
 */
public class VisionTracking implements Hardware {

	public static final int	HOT_GOAL_LEFT = 0;
	public static final int HOT_GOAL_RIGHT = 1;
	
	private static void detectSide() {
		int leftSide = (int) (Camera.imageResolution.width * 0.25); // one-quarter of the way across the image
		int rightSide = (int) (Camera.imageResolution.width * 0.75); // three-quarters of the way across the image
		
		try {
			Pointer subtracted = NIVision.imaqCreateImage(NIVision.ImageType.imaqImageRGB, 0);
			Pointer grayscale = NIVision.imaqCreateImage(NIVision.ImageType.imaqImageU8, 0);
			Timer.delay(3); // three second delay
            //Maps.Images.after = camera.getImage();
			NIVision.subtract(subtracted, Maps.Images.after.image, Maps.Images.start.image); // subtract two images
			NIVision.extractColorPlanes(subtracted, NIVision.ColorMode.IMAQ_HSL, null, null, grayscale); // convert to grayscale
			float[] averages = NIVision.getLinearAverages(grayscale, LinearAverages.LinearAveragesMode.IMAQ_COLUMN_AVERAGES, Camera.fullImage).getColumnAverages();
			if (averages[leftSide] > averages[rightSide]) {
				Goal.left.setState(true); // hot goal is Left
				System.out.println("Hot Goal on left");
			} else {
				Goal.right.setState(true); // hot goal is Right
				System.out.println("Hot Goal on right");
			}
			
			Maps.Images.start.free();
			Maps.Images.after.free();
			subtracted.release();
			grayscale.release();
        }
        catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static int getHotGoal() {
        VisionTracking.detectSide();
		if(Goal.left.isHot) {
			return HOT_GOAL_LEFT; 
		}
		return HOT_GOAL_RIGHT;
	}
	
	public static boolean correctSide() {
        VisionTracking.detectSide();
		if (!Goal.left.isHot && Field.robotPosition == Position.left ||
			!Goal.right.isHot && Field.robotPosition == Position.right) { // incorrect side
				return false;
		}
		return true;
	}
}
