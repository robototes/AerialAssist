/*
-switched drive flipping to be on throttle (it is the way Evan wants it, don't change
-switched high gear/low gear in hardware, figure out if it also needs to be changed in 
code
-added in 67 inches for autonomous sonar distance
-EVERY SINGLE VARIABLE THAT I /MIGHT/ LIKE TO CHANGE SHOULD BE ON SMART DASHBOARD - Drive
-Switch that switches between current shooting mode and the mode that:
	Makes the pistons always charged and pressing the shoot button just releases the 
latch, so for most of the match the pistons are fully charged
-fix bringing pickup arm down when shooting
-high power put latch in at the beginning
-in range
-sync to github
*/
package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.FRCRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * @author Team 2412
 */
public class Main extends FRCRobot implements Hardware {
    SendableChooser autonomousPosition = new SendableChooser();
    double speed;  // roller speed
    double actualSpeed;

    public void robotInit() {
        autonomousPosition.addDefault("Left", Field.Position.left);
        autonomousPosition.addObject("Right", Field.Position.right);
        
        screen.println(Screen.line1, Screen.tab1, "TEAM 2412");
        screen.println(Screen.line3, Screen.tab4, "2014");
        
        SmartDashboard.putData("autonomousPosition", autonomousPosition);
        SmartDashboard.putNumber("Sonar stopping distance", 60.0);
        Timer.delay(7);
    }
    
	public void disabledInit() {
        VisionTracking.initializer();
    }
	
	public void disabledPeriodic() {
        VisionTracking.getInitialImage();
        Timer.delay(1);
	}
	
    public void autonomousInit() {
        System.out.println("Autonomous Started");
        
        if(autonomousPosition.getSelected() == Field.Position.left) {
            Field.robotPosition = Field.Position.left;
        } else {
            Field.robotPosition = Field.Position.right;
        }
        compressor.start();
        
        Pickup.arms.set(LOAD_BACKWARD);
        
        System.out.println("Starting vision tracking");
        VisionTracking.run();
        System.out.println("vision tracking done");
        DriveRobot.driveUntilSonarSaysStop(85.0 + SKID_DISTANCE);
        System.out.println("Robot done driving");
        if (VisionTracking.correctSide) {
            System.out.println("Robot is on correct side");
            new LaunchCatapult(CatapultPower.HIGH);
        } else {
            System.out.println("Robot isn't on correct side");
            Timer.delay(2.0);
            new LaunchCatapult(CatapultPower.HIGH);
        }
    }
    
    public void autonomousPeriodic() {
        if (!LaunchCatapult.inProgress && !SonarDriveTask.inProgress) {
            DriveRobot.basicArcade(0.0, 0.0);
        }
    }
    
    public void teleopInit() {
        compressor.start();
        Catapult.setLauncher(RETRACTED);
        DriveBase.shifter.set(Drive.LOW_GEAR);
    }
	
    public void teleopPeriodic() {
        // Smart Dashboard 
//        SmartDashboard.putBoolean("Loaded", Pickup.loaded.get());
        SmartDashboard.putBoolean("In Range", InGoalRange.inGoalRange());
        SmartDashboard.putBoolean("Launcher", Catapult.getLauncher().equals(EXTENDED));
        SmartDashboard.putBoolean("Arms", Pickup.arms.get());
        SmartDashboard.putBoolean("Latch", Catapult.latch.get());
        SmartDashboard.putBoolean("Compressor", compressor.getPressureSwitchValue());
        SmartDashboard.putNumber("Roller Dial", coDriverStick.getZ() * ROLLER_DIAL);
        
        // drive
		DriveRobot.advancedArcade();
        
        // gear shifting
        DriveBase.shifter.set((Buttons.shiftOverride.get() || Buttons.shift.held()) ?
            Drive.HIGH_GEAR : Drive.LOW_GEAR);
        
        // drive flipping
        DriveRobot.driveDirection = !Buttons.flip.held();
        DriveRobot.driveDirection = driverStick.getThrottle() > 0;
        
        // arms
		Pickup.arms.set(Buttons.armsUp.get() ? LOAD_UP : LOAD_BACKWARD);
        
        // spin wheels
        speed = 0.5 + coDriverStick.getZ() * ROLLER_DIAL;
        actualSpeed = SmartDashboard.getNumber("Roller Speed", 0.75);
        Pickup.roller.set(Buttons.roller.held() ? actualSpeed: 
            Buttons.reverseLoadDirection.held() ? -actualSpeed : 0.0);
        
        // shoot
		Buttons.launchCatapultHigh.whenPressed(new LaunchCatapult(CatapultPower.HIGH));
		Buttons.launchCatapultLow.whenPressed(new LaunchCatapult(CatapultPower.LOW));
        
        // manual latch
        if(Buttons.latch.pressed()) {
            Catapult.latch.set(!Catapult.latch.get());
        }
        
    }
}
