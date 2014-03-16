
package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.FRCRobot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 * @author Team 2412
 */
public class Main extends FRCRobot implements Hardware {
    double speed;  // roller speed
    boolean inProgress;
    boolean hasShotTwice = false;

    public void robotInit() {
        
        screen.println(Screen.line1, Screen.tab1, "TEAM 2412");
        screen.println(Screen.line3, Screen.tab4, "2014");
        
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
        compressor.start();
        Pickup.arms.set(EXTENDED);
        Timer.delay(1.3);
        if(!DriverStation.getInstance().getDigitalIn(2)){
            if(DriverStation.getInstance().getDigitalIn(1)) {
                Field.robotPosition = Field.Position.left;
            } else {
                Field.robotPosition = Field.Position.right;
            }
            DriveRobot.driveForTime(-1.0, 1.0);
            VisionTracking.run();
            DriveRobot.driveUntilSonarSaysStop(SmartDashboard.getNumber("Sonar stopping distance") + SKID_DISTANCE);
            Timer.delay(0.2);
            if (VisionTracking.correctSide) {
                new LaunchCatapult(CatapultPower.HIGH).start();
            } else {
                Timer.delay(1.5);
                new LaunchCatapult(CatapultPower.HIGH).start();
            }
        } else {
            Pickup.roller.set(0.2);  //test
            DriveRobot.driveForTime(-1.0, 1.0);
            DriveRobot.driveUntilSonarSaysStop(SmartDashboard.getNumber("Sonar stopping distance") + SKID_DISTANCE);
            Pickup.roller.set(0.0);
            Timer.delay(0.2);
            new LaunchCatapult(CatapultPower.HIGH).start();
        }
    }
    
    public void autonomousPeriodic() {
        if (!LaunchCatapult.inProgress && !SonarDriveTask.inProgress) {
            if (!hasShotTwice && !DriverStation.getInstance().getDigitalIn(2)) {
                Pickup.roller.set(1.0);
                Timer.delay(1.0);
                Pickup.roller.set(0.0);
                new LaunchCatapult(CatapultPower.HIGH).start();
                hasShotTwice = true;
            }
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
            Pickup.roller.set(Buttons.rollerIn.held() ? -speed: 
                Buttons.rollerOut.held() ? speed : 0.0);
        }
        
        // catapult
        if (!inProgress) {
            
            // arms
            Pickup.arms.set(!Buttons.armsUp.get() ? EXTENDED : RETRACTED);
        
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
