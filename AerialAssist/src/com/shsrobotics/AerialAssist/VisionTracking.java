package com.shsrobotics.AerialAssist;

import com.shsrobotics.AerialAssist.Maps.Field.*;
import com.sun.cldc.jna.Pointer;
import edu.wpi.first.wpilibj.image.LinearAverages;
import edu.wpi.first.wpilibj.image.NIVision;

/**
 * Has all the vision tracking routines.
 * @author RoboTotes Team 2412
 */

public class VisionTracking implements Hardware {
    public static final boolean	HOT_GOAL_LEFT = false;
    public static final boolean HOT_GOAL_RIGHT = true;
    public static boolean correctSide = false;
	
    public static final int LEFT_LINE_1 = (int) (Camera.imageResolution.width * 0.20);
    public static final int LEFT_LINE_2 = (int) (Camera.imageResolution.width * 0.40);
    public static final int RIGHT_LINE_1 = (int) (Camera.imageResolution.width * 0.60);
    public static final int RIGHT_LINE_2 = (int) (Camera.imageResolution.width * 0.80); 
    
    public static void getInitialImage() {
        try {
            if (Images.start != null) {
                Images.start.free();
            }
            Images.start = camera.getImage(); 
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void run() {
        detectSide();
        setCorrectSide();
    }
    
    public static void initializer() {
         camera.writeResolution(Camera.imageResolution);
    }
    
    private static void detectSide() {
        Goal.RIGHT.setState(false);
        Goal.LEFT.setState(false);
		
        try {
            Pointer subtracted = NIVision.imaqCreateImage(NIVision.ImageType.imaqImageRGB, 0);
            Pointer grayscale = NIVision.imaqCreateImage(NIVision.ImageType.imaqImageU8, 0);
            Images.after = camera.getImage();
            
//            NIVision.subtract(subtracted, Images.after.image, Images.start.image); // subtract two images
            NIVision.writeFile(subtracted, "sub.png");
            NIVision.extractColorPlanes(subtracted, NIVision.ColorMode.IMAQ_HSL, null, null, grayscale); // convert to grayscale
            float[] averages = NIVision.getLinearAverages(
            grayscale, LinearAverages.LinearAveragesMode.IMAQ_COLUMN_AVERAGES,
                Camera.fullImage).getColumnAverages();
            float leftAvg = (averages[LEFT_LINE_1] + averages[LEFT_LINE_2]) / 2;
            float rightAvg = (averages[RIGHT_LINE_1] + averages[RIGHT_LINE_2]) / 2;
            if (leftAvg > rightAvg) {
                Goal.LEFT.setState(true); // hot goal is Left
                System.out.println("Hot goal is on left VT");
            } else {
                Goal.RIGHT.setState(true); // hot goal is Right
                System.out.println("Hot goal is on right VT");
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
    
    private static void setCorrectSide() {
        if (!Goal.LEFT.isHot && Field.robotPosition == Position.LEFT ||
			!Goal.RIGHT.isHot && Field.robotPosition == Position.RIGHT) { // incorrect side
            correctSide = false;
        } else {
            correctSide = true;
        }
    }
}