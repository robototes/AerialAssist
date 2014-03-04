/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.PIDHardware;
import com.shsrobotics.library.Task;
import com.shsrobotics.library.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author RoboTotes Team 2412
 */
public class LaunchAlwaysLoaded extends Task implements Hardware {
    
    public static boolean inProgress = false;
    
    protected void initialize() {
        if (!inProgress) {
            inProgress = true;
            Pickup.arms.set(ARMS_OUT);
            Catapult.latch.set(UNLOCKED);
            Timer.delay(0.5);
            Catapult.setLauncher(RETRACTED);
            Timer.delay(3.0);
            Catapult.latch.set(LOCKED);
        }
    }
    
    protected void execute() {
        
    }
    
    protected boolean isFinished() {
        return true;
    }
    
    protected void end() {
        inProgress = false;
        
    }
    
}
