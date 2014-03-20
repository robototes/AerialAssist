package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.FRCRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Team 2412
 */
public class Main extends FRCRobot implements Hardware {
    
    public void robotInit() {
        screen.println(Screen.line1, Screen.tab1, "TEAM 2412");
        screen.println(Screen.line3, Screen.tab4, "2014");
        
        SmartDashboard.putNumber("Sonar Stopping Distance", SonarValues.DEFAULT_SONAR_DISTANCE);
        SmartDashboard.putNumber("Autonomous Drive Time", 3.0);
    }
	
    public void autonomousInit() {
        compressor.start();
        Pickup.arms.set(Arms.IN);
        Timer.delay(1.3);
        if(Autonomous.TWO_BALL_AUTONOMOUS){
            Pickup.roller.set(-0.6);
            if(Autonomous.DRIVE_FOR_TIME) {
                DriveRobot.driveForTime(1.0, SmartDashboard.getNumber("Autonomous Drive Time"));
            } else {
                DriveRobot.driveForTime(1.0, 1.0); // ensures that we will get accurate sonar reading
                DriveRobot.driveUntilSonarSaysStop();
            }
            new DriveForTime(10.0, 0.0).start(); // ensures that robot will not move
            Pickup.roller.set(0.0);
            new LaunchCatapult(CatapultPower.HIGH).start();
            Timer.delay(3.0);
            Pickup.roller.set(-1.0);
            Timer.delay(1.5);
            Pickup.roller.set(0.0);
            new LaunchCatapult(CatapultPower.HIGH).start();
        } else { // one ball autonomous, no vision tracking
          if(Autonomous.DRIVE_FOR_TIME) {
                DriveRobot.driveForTime(1.0, SmartDashboard.getNumber("Autonomous Drive Time"));
            } else {
                DriveRobot.driveForTime(1.0, 1.0);
                DriveRobot.driveUntilSonarSaysStop();
            }
            new DriveForTime(10.0, 0.0).start();
            new LaunchCatapult(CatapultPower.HIGH).start();
        }
    }
    
    public void autonomousPeriodic() {
        if(!LaunchCatapult.inProgress) {
             DriveRobot.basicArcade(0, 0);
        }
    }
    
    public void teleopInit() {
        compressor.start();
        Catapult.setLauncher(RETRACTED);
        DriveBase.shifter.set(Drive.LOW_GEAR);
    }
	       
    public void teleopPeriodic() {
        boolean inProgress = Dump.inProgress || LaunchAlwaysLoaded.inProgress || LaunchCatapult.inProgress;
        
        // Smart Dashboard
        SmartDashboard.putBoolean("In Range", !Sonar.teleopSonar.get());
        SmartDashboard.putBoolean("Latch", Catapult.latch.get());
        SmartDashboard.putBoolean("Compressor", compressor.getPressureSwitchValue());
//        SmartDashboard.putNumber("Sonar Distance (ft)", Sonar.sonar.getDistanceInFeet());
        SmartDashboard.putNumber("Transducer Value", Trans.transducer.getPressure() * 2);
        
        // drive
        DriveRobot.advancedArcade();
        
        // gear shifting
        DriveBase.shifter.set((Buttons.shift.held()) ?
            Drive.HIGH_GEAR : Drive.LOW_GEAR);
        
        // drive flipping
        Drive.DRIVE_DIRECTION = !Buttons.flip.held();
        Drive.DRIVE_DIRECTION = driverStick.getThrottle() > 0;
        
        // spin wheels
        double rollerSpeed = RollerValues.ACTUAL_SPEED + coDriverStick.getX() * RollerValues.ROLLER_DIAL;
        if (rollerSpeed > 1.0) {
            rollerSpeed = 1.0;
        }
        if (rollerSpeed < 0.0) {
            rollerSpeed = 0;
        }
        if (!Buttons.armsUp.held()) {
            Pickup.roller.set(Buttons.rollerIn.held() ? -rollerSpeed: 
                Buttons.rollerOut.held() ? rollerSpeed : 0.0);
        }
        
        // catapult
        if (!inProgress) {
            
            // arms
            Pickup.arms
                .set(!Buttons.armsUp.get() ? Arms.IN : Arms.OUT);
        
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
                    Catapult.latch.set(Latch.UNLOCKED);
                    Buttons.launchCatapult.whenPressed(new LaunchCatapult(CatapultPower.LOW));
                } else {
                    Catapult.latch.set(Latch.LOCKED);
                    Buttons.launchCatapult.whenPressed(new LaunchCatapult(CatapultPower.HIGH));
                }
            }
        }
    }
}
