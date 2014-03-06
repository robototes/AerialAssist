/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shsrobotics.AerialAssist;
import com.shsrobotics.library.Task;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author RoboTotes Team 2412
 */
public class ChargeSequence extends Task implements Hardware {
    
    protected void initialize() {
        if (Catapult.latch.get() == UNLOCKED) {
            Catapult.latch.set(LOCKED);
            Timer.delay(0.1);
        }
        Catapult.setLauncher(EXTENDED);
    }
    
    protected void execute() {
        
    }
    
    protected boolean isFinished() {
        return true;
    }
    
    protected void end() {
    }
    
}
