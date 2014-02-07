package com.shsrobotics.AerialAssist;

public class LoadBall implements Hardware {
    
    public static void load(boolean loadDirection) {
        Pickup.spinWheels.set(loadDirection == LOAD_FORWARD ? 1.0 : -1.0);
    }
    
    public static void stopLoading() {
        Pickup.spinWheels.set(0.0);
    }
}