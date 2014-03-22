package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.Task;
import edu.wpi.first.wpilibj.Timer;

/**
 * Task for the Dumping sequence. Two modes: PWM or Single.
 *
 * @author RoboTotes Team 2412
 */
public class Dump extends Task implements Hardware {

    private boolean pwmMode; // true = pwm, false = single
    public static boolean inProgress = false;

    protected void initialize() {
    }

    public Dump(boolean mode) {
        pwmMode = mode;
    }

    protected void execute() {
        if (!inProgress) {
            inProgress = true;
            if (Catapult.latch.get() == Latch.LOCKED) {
                Catapult.latch.set(Latch.UNLOCKED);
                Timer.delay(0.2);
            }
            if (pwmMode) {
                Pickup.checkArms();
                Catapult.setLauncher(EXTENDED);
                Timer.delay(0.2);
                Catapult.setLauncher(RETRACTED);
                Timer.delay(0.5);
            } else {
                Catapult.launchRight.set(EXTENDED);
                Timer.delay(3.0);
                Catapult.launchRight.set(RETRACTED);
                Timer.delay(2.0);
            }
        }
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
        Timer.delay(1.5);
        if (Catapult.latch.get() == Latch.UNLOCKED) {
            Catapult.latch.set(Latch.LOCKED); // set latch in
        }
        inProgress = false;
    }
}
