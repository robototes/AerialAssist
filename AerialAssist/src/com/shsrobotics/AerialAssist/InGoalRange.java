package com.shsrobotics.AerialAssist;

public class InGoalRange implements Hardware {
    public static final double IN_RANGE = 6.7; // feet
    public static final double ERROR = 0.2;  // still feet
    
    
    public static boolean inGoalRange() {
       return Sonar.sonar.getDistance() <= IN_RANGE + ERROR && Sonar.sonar.getDistance() >= IN_RANGE - ERROR;
    }
}
