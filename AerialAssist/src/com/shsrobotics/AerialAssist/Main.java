/*
-switched high gear/low gear in hardware, figure out if it also needs to be changed in code
-EVERY SINGLE VARIABLE THAT I /MIGHT/ LIKE TO CHANGE SHOULD BE ON SMART DASHBOARD - Drive team
-fix bringing pickup arm down when shooting - need to test
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

    public void robotInit() {
        autonomousPosition.addDefault("Left", Field.Position.left);
        autonomousPosition.addObject("Right", Field.Position.right);
        
        screen.println(Screen.line1, Screen.tab1, "TEAM 2412");
        screen.println(Screen.line3, Screen.tab4, "2014");
        
        SmartDashboard.putData("autonomousPosition", autonomousPosition);
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
        
        Pickup.arms.set(ARMS_OUT);
        
        System.out.println("Starting vision tracking");
        VisionTracking.run();
        System.out.println("vision tracking done");
        DriveRobot.driveUntilSonarSaysStop(85.0 + SKID_DISTANCE);
        System.out.println("Robot done driving");
        if (VisionTracking.correctSide) {
            System.out.println("Robot is on correct side");
            new LaunchCatapult(CatapultPower.HIGH).start();
            System.out.println("Robot has shot high");
        } else {
            System.out.println("Robot isn't on correct side");
            Timer.delay(4.0);
            new LaunchCatapult(CatapultPower.HIGH).start();
            System.out.println("Robot has shot high");
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
        SmartDashboard.putBoolean("Loaded", Pickup.loaded.get());
        SmartDashboard.putBoolean("In Range", InGoalRange.inGoalRange());
        SmartDashboard.putBoolean("Launcher", Catapult.getLauncher().equals(EXTENDED));
        SmartDashboard.putBoolean("Arms", Pickup.arms.get());
        SmartDashboard.putBoolean("Latch", Catapult.latch.get());
        SmartDashboard.putBoolean("Compressor", compressor.getPressureSwitchValue());
        SmartDashboard.putNumber("Roller Dial", coDriverStick.getZ() * ROLLER_DIAL);
        SmartDashboard.putNumber("Sonar value (in)", Sonar.sonar.getDistance());
        
        // drive
		DriveRobot.advancedArcade();
        
        // gear shifting
        DriveBase.shifter.set((Buttons.shiftOverride.get() || Buttons.shift.held()) ?
            Drive.HIGH_GEAR : Drive.LOW_GEAR);
        
        // drive flipping
        DriveRobot.driveDirection = !Buttons.flip.held();
        DriveRobot.driveDirection = driverStick.getThrottle() > 0;
        
        // arms
		Pickup.arms.set(Buttons.armsUp.get() ? ARMS_IN : ARMS_OUT);
        
        // spin wheels
        speed = 0.75 + coDriverStick.getZ() * ROLLER_DIAL;
        Loading.actualSpeed = SmartDashboard.getNumber("Roller Speed", 1.0);
        Pickup.roller.set(Buttons.rollerForward.held() ? Loading.actualSpeed: 
            Buttons.rollerReverse.held() ? -Loading.actualSpeed : 0.0);
        
        // shoot
        if (Buttons.alwaysCharged.held()) {
            if (!LaunchAlwaysLoaded.inProgress) {
                Catapult.setLauncher(EXTENDED);
                Buttons.launchCatapultHigh.whenPressed(new LaunchAlwaysLoaded());
                Buttons.launchCatapultLow.whenPressed(new LaunchAlwaysLoaded());
            }
        } else {
            Buttons.launchCatapultHigh.whenPressed(new LaunchCatapult(CatapultPower.HIGH));
            Buttons.launchCatapultLow.whenPressed(new LaunchCatapult(CatapultPower.LOW));
        }
        
        // manual latch
        if(Buttons.latch.pressed()) {
            Catapult.latch.set(!Catapult.latch.get());
        }
        
        // dump
        Buttons.dump.whenPressed(new Dump());
        
    }
}
