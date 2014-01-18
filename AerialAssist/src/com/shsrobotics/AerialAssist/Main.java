package com.shsrobotics.AerialAssist;

import com.shsrobotics.AerialAssist.Maps.Field.CatapultPower;
import com.shsrobotics.library.FRCRobot;
import com.sun.cldc.jna.Pointer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.image.LinearAverages;
import edu.wpi.first.wpilibj.image.NIVision;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 * @author Team 2412
 */
public class Main extends FRCRobot implements Hardware {
    
    public void robotInit() {
		Images.start = null;
    }
    
	public void disabledInit() {
		try {
			if ( Images.start == null ) {
				Images.start.free();
			}
		}
		catch (NIVisionException nive) {
			System.out.println("Failed to free start image, watch for memory leak.");
		}
	}
	
	public void disabledPeriodic() {
		try {
			if ( Images.start == null ) {
				Images.start.free();
			}
            Images.start = camera.getImage();
        }
        catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    public void autonomousInit() {
		
			System.out.println("FIRE!");
    }
    
    
    public void teleopInit() {
        compressor.start();
    }
	
    public void teleopPeriodic() {
		DriveBase.drive();
		
		Buttons.launchCatapultHigh.whenPressed(new LaunchCatapult(CatapultPower.HIGH));
		Buttons.launchCatapultLow.whenPressed(new LaunchCatapult(CatapultPower.LOW));
    }
	
    
	
}
