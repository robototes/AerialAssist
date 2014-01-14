package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.FRCRobot;
import com.sun.cldc.jna.Pointer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.image.LinearAverages.LinearAveragesMode;
import edu.wpi.first.wpilibj.image.NIVision;

/**
 * @author Team 2412
 */
public class Main extends FRCRobot implements Hardware {
    
    public void robotInit() {
		super.robotInit();
		
		Initializer.initialize();
		
		Timer.delay(20); // wait twenty seconds for camera to turn on
        try {
            Images.start = camera.getImage();
        }
        catch (Exception e) { }
    }
    
    public void autonomousInit() {
		int leftSide = (int) (imageResolution.width * 0.25); // one-quarter of the way across the image
		int rightSide = (int) (imageResolution.width * 0.75); // three-quarters of the way across the image
		
		try {
			Pointer subtracted = NIVision.imaqCreateImage(NIVision.ImageType.imaqImageHSL, 0);
			Pointer grayscale = NIVision.imaqCreateImage(NIVision.ImageType.imaqImageU16, 0);

			Timer.delay(3); // three second delay
			
            Images.after = camera.getImage();
			
			NIVision.subtract(subtracted, Images.after.image, Images.start.image); // subtract two images
			NIVision.extractColorPlanes(subtracted, NIVision.ColorMode.IMAQ_HSL, null, null, grayscale); // convert to grayscale
			float[] averages = NIVision.getLinearAverages(grayscale, LinearAveragesMode.IMAQ_COLUMN_AVERAGES, fullImage).getColumnAverages();
			
			if (averages[leftSide] > averages[rightSide]) {
				Field.Goal.left.setState(true); // hot goal is Left
			} else {
				Field.Goal.right.setState(true); // hot goal is Right
			}
			
			Images.start.free();
			Images.after.free();
			subtracted.free();
			grayscale.free();
        }
        catch (Exception e) { }
		
		if (Field.robotPosition == Field.Position.left) { // robot is on left
			if (!Field.Goal.left.isHot) { // incorrect side
				Timer.delay(3); // wait until goal switches
			}
		} else { // robot is on right
			if (!Field.Goal.right.isHot) { // incorrect side
				Timer.delay(3); // wait until goal switches
			}
		}
		
		// FIRE
    }
    
    public void autonomousPeriodic() {
        
    }
    
    public void teleopInit() {
        
    }
    
    public void teleopPeriodic() {
        
    }
    
}
