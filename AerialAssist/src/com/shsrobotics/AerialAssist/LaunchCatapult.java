package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.Task;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	private boolean preCharged;
	public static boolean inProgress = false;
    
	public LaunchCatapult(boolean power) {
		preCharged = power;
	}

	protected void initialize() {
        Pickup.arms.set(LOAD_BACKWARD);
        
		if (!inProgress) {
			inProgress = true;
			if (preCharged) { // high
				Catapult.setLauncher(EXTENDED);
				Timer.delay(5.0);
				Catapult.latch.set(LOCKED);
				Timer.delay(0.5);
			} else { // low
                if(Catapult.latch.get() == UNLOCKED) {
                    Catapult.latch.set(LOCKED); 
                    Timer.delay(0.5); 
                }
				Catapult.setLauncher(EXTENDED);
				Timer.delay(2.0);
			}
            if(Catapult.latch.get() == LOCKED) {
                Timer.delay(0.5);
            }
			inProgress = false;
		}
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		Catapult.setLauncher(RETRACTED);
		Timer.delay(3.0);
        if(Catapult.latch.get() == LOCKED) {
            Catapult.latch.set(UNLOCKED); // set latch in
        }
	}

}