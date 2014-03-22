package com.shsrobotics.AerialAssist;

import com.shsrobotics.AerialAssist.Maps.Field.Position;
import com.shsrobotics.library.FRCRobot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Team 2412
 */
public class Main extends FRCRobot implements Hardware {
    
    DriveForTime stop = new DriveForTime(10.0, 0.0);
    
    public void robotInit() {
        screen.println(Screen.line1, Screen.tab1, "TEAM 2412");
        screen.println(Screen.line3, Screen.tab4, "2014");
        screen.updateLCD();
        
        SmartDashboard.putNumber("Autonomous Drive Time", 2.5);
        SmartDashboard.putNumber("Autonomous Shoot Time", 1.2);
        
//        Timer.delay(7);
//        VisionTracking.initializer();
//        VisionTracking.getInitialImage();
    }

    public void autonomousInit() {
        Autonomous.TWO_BALL_AUTONOMOUS = DriverStation.getInstance().getDigitalIn(2);
        Autonomous.LEFT_SIDE = DriverStation.getInstance().getDigitalIn(1);
        double shootNow = SmartDashboard.getNumber("Autonomous Shoot Time");
        Autonomous.DRIVING_TIME =  SmartDashboard.getNumber("Autonomous Drive Time");
        Pneumatics.compressor.start();
        Catapult.latch.set(Latch.LOCKED);
        Pickup.arms.set(Arms.OUT);
        DriveForTime dft = new DriveForTime(Autonomous.DRIVING_TIME, 1.0);
        DriveForTime stop = new DriveForTime(10, 0.0);
        Timer.delay(1.3);
        if (Autonomous.TWO_BALL_AUTONOMOUS) {
            Pickup.roller.set(-0.6);
            Catapult.setLauncher(EXTENDED);
            dft.start();
            Timer.delay(Autonomous.DRIVING_TIME - shootNow);
            Catapult.latch.set(Latch.UNLOCKED);
            Timer.delay(shootNow);
            Catapult.setLauncher(RETRACTED);
            stop.start(); // ensures that robot will not move
            Pickup.roller.set(0.0);
            Timer.delay(1.5);
            Pickup.roller.set(-0.8);
            Timer.delay(0.2);
            Pickup.roller.set(-1.0);
            Catapult.latch.set(Latch.LOCKED);
            Timer.delay(1.0);
            Pickup.roller.set(0.0);
            Timer.delay(1.5);
            new LaunchCatapult(CatapultPower.HIGH).start();
        } else { // one ball autonomous, vision tracking
            Field.Position.setRobotPosition(Autonomous.LEFT_SIDE ? Position.LEFT : Position.RIGHT);
//            VisionTracking.run();
            DriveRobot.driveForTime(1.0, SmartDashboard.getNumber("Autonomous Drive Time"));
            stop.start();
            Timer.delay(1.0);
//            if (!VisionTracking.correctSide) {
//                Timer.delay(1.5);
//            }
            new LaunchCatapult(CatapultPower.HIGH).start();
        }
    }

    public void autonomousPeriodic() {
        if (!LaunchCatapult.inProgress) {
            DriveRobot.basicArcade(0, 0);
        }
    }

    public void disabledPeriodic() {
//        VisionTracking.getInitialImage();
//        Timer.delay(0.5);
        CatapultPower.LEFT_SOLENOID_OFF = false;
        CatapultPower.RIGHT_SOLENOID_OFF = false;
    }

    public void teleopInit() {
        Pneumatics.compressor.start();
        Catapult.setLauncher(RETRACTED);
    }

    public void teleopPeriodic() {
        boolean inProgress = Dump.inProgress || LaunchAlwaysLoaded.inProgress || LaunchCatapult.inProgress;

        // Smart Dashboard
        SmartDashboard.putBoolean("Latch", Catapult.latch.get());
        SmartDashboard.putNumber("Transducer Value", Pneumatics.transducer.getPressure() * 2);
        SmartDashboard.putBoolean("Left Solenoid Off", CatapultPower.LEFT_SOLENOID_OFF);
        SmartDashboard.putBoolean("Right Solenoid Off", CatapultPower.RIGHT_SOLENOID_OFF);

        // drive
        DriveRobot.advancedArcade();

        // gear shifting
        DriveBase.shifter.set((Buttons.shift.held())
            ? Drive.HIGH_GEAR : Drive.LOW_GEAR);

        // drive flipping
        Drive.DRIVE_DIRECTION = driverStick.getThrottle() > 0;

        // spin wheels
        double rollerSpeed = RollerValues.ACTUAL_SPEED + coDriverStick.getX() * RollerValues.ROLLER_DIAL;
        if (rollerSpeed > 1.0) {
            rollerSpeed = 1.0;
        }
        if (rollerSpeed < 0.0) {
            rollerSpeed = 0;
        }
        Pickup.roller.set(Buttons.rollerIn.held() ? -rollerSpeed
            : Buttons.rollerOut.held() ? rollerSpeed : 0.0);

        if (Buttons.leftSolenoid.pressed() && Buttons.leftSolenoidB.pressed()) {
            CatapultPower.LEFT_SOLENOID_OFF = !CatapultPower.LEFT_SOLENOID_OFF;
        }

        if (Buttons.rightSolenoid.pressed() && Buttons.rightSolenoidB.pressed()) {
            CatapultPower.RIGHT_SOLENOID_OFF = !CatapultPower.RIGHT_SOLENOID_OFF;
        }

        if (Buttons.pneumaticsOff.pressed() && Buttons.pneumaticsOffB.pressed()) {
            Flags.pneumaticsOff = !Flags.pneumaticsOff;
            if (Flags.pneumaticsOff) {
                Pneumatics.compressor.stop();
            } else {
                Pneumatics.compressor.start();
            }
        }

        // catapult
        if (!inProgress) {
            // arms
            Pickup.arms.set(Buttons.armsUp.get() ? Arms.IN : Arms.OUT);

            // dump
            Buttons.dump.whenPressed(new Dump(Buttons.dumpMode.get()
                ? DumpMode.PWM : DumpMode.SINGLE));

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
