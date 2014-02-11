package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.FRCRobot;
import edu.wpi.first.wpilibj.Timer;
/**
 * @author Team 2412
 */
public class Main extends FRCRobot implements Hardware {
	
    public void robotInit() { }
    
	public void disabledInit() {
        Timer.delay(5);
    }
	
	public void disabledPeriodic() {
        VisionTracking.getInitialImage();
		System.out.println(Sonar.sonar.getDistanceInFeet());
        Timer.delay(1);
	}
	
    public void autonomousInit() {
        compressor.start();
        
        VisionTracking.run();
        DriveRobot.driveForTime(3.0);
        if (VisionTracking.correctSide) {
            new LaunchCatapult(HIGH_POWER);
        } else {
            Timer.delay(2.0);
            new LaunchCatapult(HIGH_POWER);
        }
    }
    
    
    public void teleopInit() {
        compressor.start();
        Catapult.setLauncher(RETRACTED);
        DriveBase.shifter.set(Drive.HIGH_GEAR);
        LaserPointer.set();
    }
	
    public void teleopPeriodic() {
        // arms
		Pickup.arms.set(Buttons.armsForward.held()); // solenoid 1 (extended) & 2 (retracted)
        
        // spin wheels
        if (Buttons.pickup.held()) {
            LoadBall.load(Pickup.arms.get());
        } else {
            LoadBall.stopLoading();
        } 
        
        // gear shifting
        if (Buttons.shift.pressed()) {
            DriveBase.shifter.set(
                DriveBase.shifter.get().equals(Drive.HIGH_GEAR) ? Drive.LOW_GEAR : Drive.HIGH_GEAR);
        }
        
        // drive
		DriveRobot.drive();
		
        // shoot
		Buttons.launchCatapultHigh.whenPressed(new LaunchCatapult(HIGH_POWER));
		Buttons.launchCatapultLow.whenPressed(new LaunchCatapult(LOW_POWER));
        
        // manual latch
        if(Buttons.latch.pressed()) {
            Catapult.latch.set(!Catapult.latch.get());
        }
        
        // flip the robot's direction
        if(Buttons.flip.pressed()) {
            DriveRobot.reverseDirection();
        }
    }
}
