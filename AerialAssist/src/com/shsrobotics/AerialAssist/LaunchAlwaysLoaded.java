package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.Task;
import edu.wpi.first.wpilibj.Timer;

/** Task for shooting the ball fully charged regardless of anything.
=======
/**
 * Task for shooting the ball fully charged regardless of anything.
 *
>>>>>>> 38345ad27e79c0972cf32349c604b82c10f1d9a6
 * @author RoboTotes Team 2412
 */
public class LaunchAlwaysLoaded extends Task implements Hardware {

    public static boolean inProgress = false;

    protected void initialize() {
        if (!inProgress) {
            inProgress = true;
            Pickup.checkArms();
            Catapult.latch.set(Latch.UNLOCKED);
            Timer.delay(0.5);
            Catapult.setLauncher(RETRACTED);
            Timer.delay(2.0);
            Catapult.latch.set(Latch.LOCKED);
        }
    }
    
    protected void execute() { }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
        inProgress = false;
    }
}
