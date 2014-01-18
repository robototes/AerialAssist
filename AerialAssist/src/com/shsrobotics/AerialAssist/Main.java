package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.FRCRobot;
import edu.wpi.first.wpilibj.image.NIVisionException;

/**
 * @author Team 2412
 */
public class Main extends FRCRobot implements Hardware {
    
    public void robotInit() {
		Images.start = null;
    }
    
	public void disabledInit() {
		/*try {
			if ( Images.start == null ) {
				Images.start.free();
			}
		}
		catch (NIVisionException nive) {
			System.out.println("Failed to free start image, watch for memory leak.");
		}
		*/
	}
	
	public void disabledPeriodic() {
		/*try {
			if ( Images.start == null ) {
				Images.start.free();
			}
            Images.start = camera.getImage();
        }
        catch (Exception e) {
			e.printStackTrace();
		}
		* */
	}
	
    public void autonomousInit() {
		System.out.println("FIRE!");
    }
    
    
    public void teleopInit() {
       // compressor.start();
		//LaserPointer.set();
    }
	
    public void teleopPeriodic() {
		if (Buttons.rotateLaser.pressed()) {
			if (Buttons.switchRotation.held()) {
				LaserPoint.serv.setAngle(LaserPoint.serv.getAngle() + 15); // goes down, from side motor
			} else {
				LaserPoint.serv.setAngle(LaserPoint.serv.getAngle() - 15); // goes up, from side motor
			}
		}
		if (Buttons.setLaserPointer.pressed()) {
			LaserPointer.set();
		}
	
		/* DriveBase.drive();
		Buttons.launchCatapultHigh.whenPressed(new LaunchCatapult(CatapultPower.HIGH));
		Buttons.launchCatapultLow.whenPressed(new LaunchCatapult(CatapultPower.LOW));
		*/
    }
}
