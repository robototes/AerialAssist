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
        SmartDashboard.putNumber("Sonar stopping distance", 60.0);
        Timer.delay(7);
    }
    
	public void disabledInit() {
        compressor.start();
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
        DriveRobot.driveUntilSonarSaysStopPID(SmartDashboard.getNumber("Sonar stopping distance"));
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
//        Pickup.arms.set(LOAD_BACKWARD);
        DriveBase.shifter.set(Drive.HIGH_GEAR);
//        LaserPointer.set();
    }
	
    public void teleopPeriodic() {
        // Smart Dashboard 
//        SmartDashboard.putBoolean("Loaded", Pickup.loaded.get());
        SmartDashboard.putBoolean("In Range", InGoalRange.inGoalRange());
        SmartDashboard.putBoolean("Launcher", Catapult.getLauncher().equals(EXTENDED));
        SmartDashboard.putBoolean("Arms", Pickup.arms.get());
        SmartDashboard.putBoolean("Latch", Catapult.latch.get());
        SmartDashboard.putBoolean("Compressor", compressor.getPressureSwitchValue());
        
        // drive
		DriveRobot.advancedArcade();
        
        // gear shifting
        DriveBase.shifter.set((Buttons.shiftOverride.get() || Buttons.shift.held()) ?
            Drive.LOW_GEAR : Drive.HIGH_GEAR);
        
        DriveRobot.driveDirection = !Buttons.flip.held();
        
        // arms
		Pickup.arms.set(Buttons.armsUp.get() ? LOAD_UP : LOAD_BACKWARD);
        
        // spin wheels
        speed = 0.5 + coDriverStick.getZ() * ROLLER_DIAL;
        Pickup.roller.set(Buttons.roller.held() ? speed: 
            Buttons.reverseLoadDirection.held() ? -speed : 0);
        
        // shoot
		Buttons.launchCatapultHigh.whenPressed(new LaunchCatapult(CatapultPower.HIGH));
		Buttons.launchCatapultLow.whenPressed(new LaunchCatapult(CatapultPower.LOW));
        
        // manual latch
        if(Buttons.latch.pressed()) {
            Catapult.latch.set(!Catapult.latch.get());
        }
        
    }
}
