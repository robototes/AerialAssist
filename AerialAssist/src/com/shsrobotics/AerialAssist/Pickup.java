package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.Task;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Pickup extends Task implements Hardware {

	protected void initialize() {
		
	}

	protected void execute() {
        Pickup.arms.set(EXTENDED);
    }

	protected boolean isFinished() {
		return Pickup.loaded.get();
	}

	protected void end() {
        Pickup.arms.set(RETRACTED);
	}

}