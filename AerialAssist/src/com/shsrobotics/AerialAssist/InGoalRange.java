package com.shsrobotics.AerialAssist;

public class InGoalRange implements Hardware {
    public static boolean inGoalRange() {
//        return Sonar.sonar.getDistance() < SonarValues.MAX_RANGE && 
//                Sonar.sonar.getDistance() > SonarValues.MIN_RANGE;
        return false;
    }
}
