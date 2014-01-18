
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.Task;

/**
 * 1. Release the firing pin.
 * 2. Wait a little for the launch to complete.
 * 2. Reload the pistons.
 * 3. Latch the fireing pin.
 * 
 * @author RoboTotes Team 2412
 */
public class LaunchCatapult extends Task {
	
	public static boolean powerLevel;
	public LaunchCatapult(boolean pow) {
		powerLevel = pow;
	}

	protected void initialize() {
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}
	
}

