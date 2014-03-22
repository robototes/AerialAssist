package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.Task;
import edu.wpi.first.wpilibj.Timer;

/**Task to enter Robot into charged shooting sequence.
 * To be used in combination with LaunchAlwaysLoaded.
=======
/**
 * Task to enter Robot into charged shooting sequence. To be used in combination
 * with LaunchAlwaysLoaded.
 *
>>>>>>> 38345ad27e79c0972cf32349c604b82c10f1d9a6
 * @author RoboTotes Team 2412
 */
public class ChargeSequence extends Task implements Hardware {

    protected void initialize() {
        if (Catapult.latch.get() == Latch.UNLOCKED) {
            Catapult.latch.set(Latch.LOCKED);
            Timer.delay(0.1);
        }
        Catapult.setLauncher(EXTENDED);
    }
    
    protected void execute() { }
    
    protected boolean isFinished() {
        return true;
    }
    
    protected void end() { }
}
