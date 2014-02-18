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
        camera.writeResolution(Camera.imageResolution); 
        autonomousPosition.addDefault("Left", Field.Position.left);
        autonomousPosition.addObject("Right", Field.Position.right);
        
        screen.println(Screen.line1, Screen.tab1, "TEAM 2412");
        screen.println(Screen.line3, Screen.tab4, "2014");
        
        SmartDashboard.putData("autonomousPosition", autonomousPosition);
        SmartDashboard.putNumber("Sonar stopping distance", 47.0);
        Timer.delay(7);
    }
    
	public void disabledInit() {
        compressor.start();
        VisionTracking.initializer();
    }
	
	public void disabledPeriodic() {
        VisionTracking.getInitialImage();
        System.out.println(Sonar.sonar.getDistance());
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
        System.out.println("Vision tracking done");
        DriveRobot.driveUntilSonarSaysStopPID(SmartDashboard.getNumber("Sonar stopping distance"));
        System.out.println("Robot done driving");
        if (VisionTracking.correctSide) {
            System.out.println("Robot is on correct side");
            new Dump();
        } else {
            System.out.println("Robot isn't on correct side");
            Timer.delay(2.0);
            new Dump();
        }
    }
    
    public void autonomousPeriodic() {
        if (!SonarDriveTask.inProgress) {
            DriveRobot.basicArcade(0.0, 0.0);
        }
    }
    
    
    public void teleopInit() {
        compressor.start();
        Dumper.launch.set(RETRACTED);
//        Pickup.arms.set(LOAD_BACKWARD);
        DriveBase.shifter.set(Drive.HIGH_GEAR);
    }
	
    public void teleopPeriodic() {
        // Smart Dashboard 
        SmartDashboard.putBoolean("Launcher", Dumper.launch.get() == EXTENDED);
        SmartDashboard.putBoolean("Arms", Pickup.arms.get());
        SmartDashboard.putBoolean("Compressor", compressor.getPressureSwitchValue());
        SmartDashboard.putNumber("Sonar Distance (ft)", Sonar.sonar.getDistanceInFeet());
        
        // drive
		DriveRobot.advancedArcade();
        if(Buttons.flip.pressed()) {
            DriveRobot.reverseDirection();
        }
        DriveRobot.driveDirection = !Buttons.flip.held();
        
        // gear shifting
        DriveBase.shifter.set((Buttons.shiftOverride.get() || Buttons.shift.held()) ?
            Drive.LOW_GEAR : Drive.HIGH_GEAR);
        
        // arms
		Pickup.arms.set(Buttons.armsUp.get() ? LOAD_UP : LOAD_BACKWARD);
        
        // spin wheels
        Pickup.roller.set(Buttons.roller.held() ? 0.5 : 
            Buttons.reverseLoadDirection.held() ? -0.5 : 0);
        
        // dump
		Buttons.dumper.whenPressed(new Dump());
        
        // sets dumper arms
//        Dumper.launch.set(Buttons.manualDump.held() ? EXTENDED : RETRACTED);
    }
}