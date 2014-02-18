/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.PIDHardware;
import com.shsrobotics.library.Task;
import com.shsrobotics.library.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author RoboTotes Team 2412
 */
public class SonarDriveTask extends Task implements Hardware {
    
    PIDHardware hardware = new PIDHardware() {

        public void write(double d) {
            DriveRobot.basicArcade(d, 0);
        }

        public double read() {
            return Sonar.sonar.getDistance();
        }
    };
    
    PIDController control = new PIDController(0, 0, 0, hardware);
    
    public static boolean inProgress = false;
    private boolean done = false;
    private double distance;
    int count = 0;
    
    public SonarDriveTask(double distanceInInches) {
        distance = distanceInInches;
    }
    
    protected void initialize() {
        inProgress = true;
        control.setSetpoint(distance);
        control.enable();
        control.setAbsoluteTolerance(0);
        control.setPID(SmartDashboard.getNumber("P"), 0, 0);
    }
    
    protected void execute() {
        done = control.onTarget();
    }
    
    protected boolean isFinished() {
        return done;
    }
    
    protected void end() {
        control.disable();
        inProgress = false;
    }
    
}
