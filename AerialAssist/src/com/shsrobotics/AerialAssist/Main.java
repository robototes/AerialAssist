
package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.FRCRobot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * @author Team 2412
 */
public class Main extends FRCRobot implements Hardware {
//    SendableChooser autonomousPosition = new SendableChooser();
    double speed;  // roller speed
    boolean inProgress;

    public void robotInit() {
//        autonomousPosition.addDefault("Left", Field.Position.left);
//        autonomousPosition.addObject("Right", Field.Position.right);
        
        screen.println(Screen.line1, Screen.tab1, "TEAM 2412");
        screen.println(Screen.line3, Screen.tab4, "2014");
        
//        SmartDashboard.putData("autonomousPosition", autonomousPosition);
        SmartDashboard.putNumber("Sonar stopping distance", 68.0);
        Timer.delay(7);
        VisionTracking.initializer();
        VisionTracking.getInitialImage();
    }

	public void disabledInit() {
    }
	
	public void disabledPeriodic() {
        VisionTracking.getInitialImage();
        Timer.delay(1);
	}
	
    public void autonomousInit() {
        System.out.println("Autonomous Started");
        if(DriverStation.getInstance().getDigitalIn(1)) {
            Field.robotPosition = Field.Position.left;
            System.out.println("Robot is on left");
        } else {
            Field.robotPosition = Field.Position.right;
            System.out.println("Robot is on right");
        }
        compressor.start();
        Pickup.arms.set(ARMS_OUT);
        Timer.delay(1.3);
        DriveRobot.driveForTime(-1.0, 1.0);
        System.out.println("Starting vision tracking");
        VisionTracking.run();
        System.out.println("vision tracking done");
        DriveRobot.driveUntilSonarSaysStop(SmartDashboard.getNumber("Sonar stopping distance") + SKID_DISTANCE);
        System.out.println("Robot done driving");
        Timer.delay(0.2);
        if (VisionTracking.correctSide) {
            System.out.println("Robot is on correct side");
            new LaunchCatapult(CatapultPower.HIGH).start();
            System.out.println("Robot has shot high");
        } else {
            System.out.println("Robot isn't on correct side");
            Timer.delay(1.5);
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
        
        inProgress = Dump.inProgress || LaunchAlwaysLoaded.inProgress || LaunchCatapult.inProgress;
        // Smart Dashboard
        SmartDashboard.putBoolean("In Range", InGoalRange.inGoalRange());
        SmartDashboard.putBoolean("Latch", Catapult.latch.get());
        SmartDashboard.putBoolean("Compressor", compressor.getPressureSwitchValue());
        SmartDashboard.putNumber("Sonar Distance (ft)", Sonar.sonar.getDistanceInFeet());
        
        // drive
		DriveRobot.advancedArcade();
        
        // gear shifting
        DriveBase.shifter.set((Buttons.shift.held()) ?
            Drive.HIGH_GEAR : Drive.LOW_GEAR);
        
        // drive flipping
        DriveRobot.driveDirection = !Buttons.flip.held();
        DriveRobot.driveDirection = driverStick.getThrottle() > 0;
        
     
        
        // spin wheels
        speed = Loading.actualSpeed + coDriverStick.getX() * ROLLER_DIAL;
        if (speed > 1.0) {
            speed = 1.0;
        }
        if (speed < 0.0) {
            speed = 0;
        }
        if (!Buttons.armsUp.held()) {
            Pickup.roller.set(Buttons.rollerIn.held() ? speed: 
                Buttons.rollerOut.held() ? -speed : 0.0);
        }
        
        
        // catapult
        if (!inProgress) {
            
            // arms
            Pickup.arms.set(!Buttons.armsUp.get());
        
            // dump
            Buttons.dump.whenPressed(new Dump(Buttons.dumpMode.get() ?
                DumpMode.PWM : DumpMode.SINGLE));
            
            // shoot
            if (Buttons.charge.held()) {
                new ChargeSequence().start();
                Buttons.launchCatapult.whenPressed(new LaunchAlwaysLoaded());
            } else {
                Catapult.setLauncher(RETRACTED);
                if (Buttons.latch.held()) {
                    Catapult.latch.set(UNLOCKED);
                    Buttons.launchCatapult.whenPressed(new LaunchCatapult(CatapultPower.LOW));
                } else {
                    Catapult.latch.set(LOCKED);
                    Buttons.launchCatapult.whenPressed(new LaunchCatapult(CatapultPower.HIGH));
                }
            }
        }
    }
}
