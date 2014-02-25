package com.shsrobotics.AerialAssist;

public class InGoalRange implements Hardware {
    public static final double MIN_RANGE = 70; // in
    public static final double MAX_RANGE = 105; // in
    
    
    public static boolean inGoalRange() {
        return Sonar.sonar.getDistance() < MAX_RANGE && Sonar.sonar.getDistance() > MIN_RANGE;
    }
}
