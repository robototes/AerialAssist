package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.Task;
import edu.wpi.first.wpilibj.Timer;

/*
 * @author RoboTotes Team 2412
 */
public class Dump extends Task implements Hardware {
	protected void initialize() {
   
	}

	protected void execute() {
        Catapult.latch.set(UNLOCKED);
        Pickup.arms.set(ARMS_OUT);
        Catapult.launchLeft.set(EXTENDED);
        Timer.delay(.5);
    }

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
        Catapult.launchLeft.set(RETRACTED);
        Timer.delay(3.0);
        Catapult.latch.set(LOCKED);
	}

}