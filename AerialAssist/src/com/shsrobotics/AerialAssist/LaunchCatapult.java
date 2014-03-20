package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.Task;
import edu.wpi.first.wpilibj.Timer;

/** Task for shooting the catapult.
 * Two modes: high power (latched) or low (unlatched)
 * @author RoboTotes Team 2412
 */
public class LaunchCatapult extends Task implements Hardware {
    private boolean high;
    public static boolean inProgress = false;

    public LaunchCatapult(boolean power) {
            high = power;
    }

    protected void initialize() {
        if (!inProgress) {
            inProgress = true;
            Pickup.checkArms();
            if (high) {
                Catapult.setLauncher(EXTENDED);
                Timer.delay(0.2);
                Catapult.latch.set(Latch.UNLOCKED);
            } else { // low
                Catapult.setLauncher(EXTENDED);
                Timer.delay(2.0);
            }
            Timer.delay(0.5);
        }
    }

    protected void execute() { }

    protected boolean isFinished() {
            return true;
    }

    protected void end() {
        Catapult.setLauncher(RETRACTED);
        Timer.delay(2.0);
        if(high) { // aka latch = locked
            Catapult.latch.set(Latch.LOCKED); // set latch in
        }
        inProgress = false;
    }
}