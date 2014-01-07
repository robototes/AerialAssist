/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.FRCRobot;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 *
 * @author Max
 */
public class Main extends FRCRobot implements Maps {
    
    
    
    public void robotInit() {
        try {
            Images.startImage = camera.getImage();
        }
        catch (AxisCameraException ace) {
            
        }
        catch (NIVisionException nive) {
            
        }
        
    }
    
    public void autonomousInit() {
        try {
            Images.afterImage = camera.getImage();
            NIVision.
        }
        catch (AxisCameraException ace) {
            
        }
        catch (NIVisionException nive) {
            
        }
    }
    
    public void autonomousPeriodic() {
        
    }
    
    public void teleopInit() {
        
    }
    
    public void teleopPeriodic() {
        
    }
    
}
