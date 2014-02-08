package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.FRCRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Team 2412
 */
public class Main extends FRCRobot implements Hardware {
	
    public void robotInit() { }
    
	public void disabledInit() { }
	
	public void disabledPeriodic() {
		System.out.println(Sonar.sonar.getDistanceInFeet());
        Timer.delay(1);
	}
	
    public void autonomousInit() {
        compressor.start();
        // (before 5 seconds)
        // Get correct side; shoot immediately ?
        // Move to proper location
        // Shoot
        if(VisionTracking.correctSide()) {
            new LaunchCatapult(LAUNCH_POWER_HIGH);
        }
    }
    
    
    public void teleopInit() {
        compressor.start();
		Catapult.launch.set(RETRACTED);
    }
	
    public void teleopPeriodic() {

        // loaded
        SmartDashboard.putBoolean(SmartDashboardKeys.KEY_LOADED, Pickup.loaded.get());
        SmartDashboard.putNumber("Speed", 0.0);
        
        // arms
		// Pickup.arms.set(Buttons.armsForward.held() ? EXTENDED : RETRACTED); // solenoid 1 (extended) & 2 (retracted)
        
        // spin wheels
        /* if (Buttons.pickup.held()) {
            LoadBall.load(Pickup.arms.get() == EXTENDED);
        } else {
            LoadBall.stopLoading();
        } */
        
        if (Buttons.pickup.held()) {
            testWheels.set(SmartDashboard.getNumber("Speed"));
        } else {
            testWheels.set(0.0);
        }
        
        // gear shifting
        if (Buttons.shift.pressed()) {
            if (DriveBase.shifter.get().equals(RETRACTED)) {
                DriveBase.shifter.set(EXTENDED);
            }
            else {
                DriveBase.shifter.set(RETRACTED);
            }
        }
        
        // drive
		DriveBase.drive.arcadeDrive(driveStick);
		
        // laser angle
		if(Buttons.setLaserPointer.pressed()) {
			LaserPointer.set();
		}
		
        // shoot
		Buttons.launchCatapultHigh.whenPressed(new LaunchCatapult(LAUNCH_POWER_HIGH));
		Buttons.launchCatapultLow.whenPressed(new LaunchCatapult(LAUNCH_POWER_LOW));
    }
}
