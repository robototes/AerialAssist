package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.Task;

/**Task for driving a given amount of time.
 * @author RoboTotes Team 2412
 */
public class DriveForTime extends Task {
    
    private double time;
    private double speed;
    
    public DriveForTime(double time, double speed) {
        this.time = time;
        this.speed = speed;
    }
    
    protected void initialize() { }
    
    protected void execute() {
        DriveRobot.DRIVE_TIMER.reset();
        DriveRobot.DRIVE_TIMER.start();
        
        while (DriveRobot.DRIVE_TIMER.get() < time) {
            DriveRobot.basicArcade(speed, 0);
        }
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
        DriveRobot.basicArcade(0, 0);
    }
}
