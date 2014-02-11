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

	public static final boolean	HOT_GOAL_LEFT = false;
	public static final boolean HOT_GOAL_RIGHT = true;
    public static boolean correctSide;
	
    public static final int LEFT_LINE = (int) (Camera.imageResolution.width * 0.25);
    public static final int RIGHT_LINE = (int) (Camera.imageResolution.width * 0.75); 
    
    public static void getInitialImage() {
        try {
            Images.start = camera.getImage(); 
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void run() {
        detectSide();
        setCorrectSide();
    }
    
	private static void detectSide() {
        Goal.right.setState(false);
        Goal.left.setState(false);
		
        try {
			Pointer subtracted = NIVision.imaqCreateImage(NIVision.ImageType.imaqImageRGB, 0);
			Pointer grayscale = NIVision.imaqCreateImage(NIVision.ImageType.imaqImageU8, 0);
			Timer.delay(3); // three second delay
            Images.after = camera.getImage();
			NIVision.subtract(subtracted, Images.after.image, Images.start.image); // subtract two images
			NIVision.extractColorPlanes(
                subtracted, NIVision.ColorMode.IMAQ_HSL, null, null, grayscale); // convert to grayscale
			float[] averages = NIVision.getLinearAverages(
                grayscale, LinearAverages.LinearAveragesMode.IMAQ_COLUMN_AVERAGES,
                Camera.fullImage).getColumnAverages();
			if (averages[LEFT_LINE] > averages[RIGHT_LINE]) {
				Goal.left.setState(true); // hot goal is Left
			} else {
				Goal.right.setState(true); // hot goal is Right
			}
			
			Images.start.free();
			Images.after.free();
			subtracted.release();
			grayscale.release();
        }
        
        catch (Exception e) {
			e.printStackTrace();
		}
        
	}	
    
    public static void setCorrectSide() {
        if (!Goal.left.isHot && Field.robotPosition == Position.left ||
			!Goal.right.isHot && Field.robotPosition == Position.right) { // incorrect side
				correctSide = false;
		} else {
            correctSide = true;
        }
    }
}
