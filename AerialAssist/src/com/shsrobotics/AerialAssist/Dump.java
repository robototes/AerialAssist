package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.Task;
import edu.wpi.first.wpilibj.Timer;

/*
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
            if (Pickup.arms.get() == ARMS_IN) {
                Pickup.arms.set(ARMS_OUT);
                Timer.delay(3.0);
            }
            if (Catapult.latch.get() == LOCKED) {
                Catapult.latch.set(UNLOCKED);
                Timer.delay(0.2);
            }
            if(pwmMode) {
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
        Timer.delay(0.1);
        inProgress = false;
	}

}