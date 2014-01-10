package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.FRCRobot;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 * @author Team 2412
 */
public class Main extends FRCRobot implements Hardware {
    
    
    
    public void robotInit() {
        try {
            Images.startImage = camera.getImage();
        }
        catch (AxisCameraException ace) { }
        catch (NIVisionException nive) { }
        
    }
    
    public void autonomousInit() {
        try {
            Images.afterImage = camera.getImage();
            Images.subtractedImage = NIVision.imaqCreateImage(NIVision.ImageType.imaqImageRGB, 0);
			
			NIVision.subract(Images.subtractedImage, Images.afterImage.image, Images.startImage.image);
			
			//compare here
        }
        catch (AxisCameraException ace) { }
        catch (NIVisionException nive) { }
    }
    
    public void autonomousPeriodic() {
        
    }
    
    public void teleopInit() {
        
    }
    
    public void teleopPeriodic() {
        
    }
    
}
