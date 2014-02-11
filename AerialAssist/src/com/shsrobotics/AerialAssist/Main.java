package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.FRCRobot;
import edu.wpi.first.wpilibj.Timer;
/**
 * @author Team 2412
 */
public class Main extends FRCRobot implements Hardware {
	
    public void robotInit() { }
    
	public void disabledInit() {
        //VisionTracking.initializer();
        Timer.delay(5);
    }
	
	public void disabledPeriodic() {
        //VisionTracking.getInitialImage();
        Timer.delay(1);
	}
	
    public void autonomousInit() {
        compressor.start();
        /*
        VisionTracking.run();
        DriveRobot.driveForTime(3.0);
        if (VisionTracking.correctSide) {
            new LaunchCatapult(CatapultPower.HIGH);
        } else {
            Timer.delay(2.0);
            new LaunchCatapult(CatapultPower.HIGH);
        }*/
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
		Buttons.launchCatapultHigh.whenPressed(new LaunchCatapult(CatapultPower.HIGH));
		Buttons.launchCatapultLow.whenPressed(new LaunchCatapult(CatapultPower.LOW));
        
        // manual latch
        if(Buttons.latch.pressed()) {
            Catapult.latch.set(!Catapult.latch.get());
        }
        
        // reverse the robot's direction
        if(Buttons.flip.pressed()) {
            DriveRobot.reverseDirection();
        }
    }
}
