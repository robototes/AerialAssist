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
        if (Pickup.arms.get() == ARMS_IN) {
            Pickup.arms.set(ARMS_OUT);
            Timer.delay(3.0);
        }
        System.out.println("In shooting method");
		if (!inProgress) {
			inProgress = true;
			if (preCharged) { // high
                if (Catapult.latch.get() == UNLOCKED) {
                    Catapult.latch.set(LOCKED);
                    Timer.delay(0.2);
                }
				Catapult.setLauncher(EXTENDED);
				Timer.delay(0.20);
				Catapult.latch.set(UNLOCKED);
				Timer.delay(0.5);
			} else { // low
                if(Catapult.latch.get() == LOCKED) {
                    Catapult.latch.set(UNLOCKED); 
                    Timer.delay(0.01); 
                }
				Catapult.setLauncher(EXTENDED);
				Timer.delay(2.0);
			}
            if(Catapult.latch.get() == UNLOCKED) {
                Timer.delay(0.5);
            }
		}
	}

	protected void execute() { }

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
		Catapult.setLauncher(RETRACTED);
		Timer.delay(2.0);
        if(Catapult.latch.get() == UNLOCKED) {
            Catapult.latch.set(LOCKED); // set latch in
        }
        inProgress = false;
	}

}