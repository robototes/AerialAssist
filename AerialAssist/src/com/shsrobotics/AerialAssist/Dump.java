package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.Task;
import edu.wpi.first.wpilibj.Timer;

/*
 * @author RoboTotes Team 2412
 */
public class Dump extends Task implements Hardware {
    private boolean singleMode; // true ==  
	protected void initialize() {
   
	}
    
    public Dump(boolean mode) {
        singleMode = mode;
    }
    
	protected void execute() {
        Pickup.arms.set(ARMS_OUT);
        if (Catapult.latch.get() == LOCKED) {
            Catapult.latch.set(UNLOCKED);
            Timer.delay(0.2);
        }
        if(singleMode) {
            Catapult.launchLeft.set(EXTENDED);
            Timer.delay(1.0);
            Catapult.launchLeft.set(RETRACTED);
            Timer.delay(3.0);
        } else {
            Catapult.setLauncher(EXTENDED);
            Timer.delay(0.2);
            Catapult.setLauncher(RETRACTED);
            Timer.delay(0.5);
        }
    }

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
        Catapult.latch.set(LOCKED);
	}

}