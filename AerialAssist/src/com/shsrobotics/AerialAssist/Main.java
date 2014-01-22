package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.FRCRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Team 2412
 */
public class Main extends FRCRobot implements Hardware {
    
    public void robotInit() {
    }
    
	public void disabledInit() {
		SmartDashboard.putNumber("Other Robot Height", 0);
	}
	
	public void disabledPeriodic() {
	
	}
	
    public void autonomousInit() {
    }
    
    
    public void teleopInit() {
        compressor.start();
		LaserPointer.set();
    }
	
    public void teleopPeriodic() {
		if (Buttons.rotateLaser.pressed()) {
			if (Buttons.switchRotation.held()) {
				LaserPoint.servo.setAngle(LaserPoint.servo.getAngle() + 15); // goes down, from side motor
			} else {
				LaserPoint.servo.setAngle(LaserPoint.servo.getAngle() - 15); // goes up, from side motor
			}
		}
	
    }
}
