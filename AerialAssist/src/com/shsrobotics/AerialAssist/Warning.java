package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.Task;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author RoboTotes Team 2412
 */
public class Warning extends Task implements Hardware {

    protected void initialize() {
        SmartDashboard.putBoolean("WARNING", true);
        Timer.delay(5.0);
        SmartDashboard.putBoolean("WARNING", false);
    }
    
    protected void execute() { }

    protected boolean isFinished() { return true; }

    protected void end() { }
}
