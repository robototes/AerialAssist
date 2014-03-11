/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.Task;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author RoboTotes Team 2412
 */
public class SpinRoller extends Task implements Hardware {
    
    
    public static boolean inProgress = false;
    
    protected void initialize() {
    }
    
    protected void execute() {
        Pickup.roller.set(1.0);
    }
    
    protected boolean isFinished() {
        return (Catapult.getLauncher() == EXTENDED);
    }
    
    protected void end() {
    }
    
}
