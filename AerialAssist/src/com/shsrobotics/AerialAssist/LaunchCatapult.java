package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.Task;
import edu.wpi.first.wpilibj.Timer;

/**
 * For high shooter
 * 1. Load the pistons with air
 * 2. Wait for the pistons to be fully loaded (test time)
 * 3. Release the firing pin
 * 4. Wait a little for the launch to complete (test time)
 * 5. Turn off air in pistons
 * 6. Wait for pistons to come back down (test time)
 * 7. Latch the firing pin
 * 
 * For low shooter
 * 1. Release the firing pin
 * 2. Load the pistons with air
 * 3. Wait a little for the launch to complete (test time)
 * 4. Turn off air in pistons
 * 5. Wait for pistons to come back down (test time)
 * 6. Latch the firing pin
 * 
 * @author RoboTotes Team 2412
 */
public class LaunchCatapult extends Task implements Hardware {
	private int preCharged;
	public static boolean inProgress = false;
    
	public LaunchCatapult(int power) {
		preCharged = power;
	}

	protected void initialize() {
		if (!inProgress) {
			inProgress = true;
			if (preCharged == LAUNCH_POWER_HIGH) {
				System.out.println("High is running");
				Catapult.launch.set(EXTENDED);
				Timer.delay(1.0);
				Catapult.latch.set(false); // release latch
				Timer.delay(0.5);
			} else {
				System.out.println("Low is running");
				Catapult.latch.set(false); // release latch
				Catapult.launch.set(EXTENDED);
				Timer.delay(2.0);
			}
			inProgress = false;
		}
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		Catapult.launch.set(RETRACTED);
		Timer.delay(3.0);
		Catapult.latch.set(true); // set the latch
	}

}