package com.shsrobotics.AerialAssist;


import com.shsrobotics.library.Task;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Priming extends Task implements Hardware {

	protected void initialize() {
		
	}

	protected void execute() {
        Pickup.roller.set(Loading.actualSpeed);
    }

	protected boolean isFinished() {
		return !Pickup.loaded.get();
	}

	protected void end() {
        Timer.delay(0.5);
        Pickup.roller.set(0.0);
	}

}