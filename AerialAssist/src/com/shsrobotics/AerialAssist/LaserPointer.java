package com.shsrobotics.AerialAssist;
import com.sun.squawk.util.MathUtils;


public class LaserPointer {
	public static final double INITIAL_VELOCITY = 0.0;
	public static final double CATAPULT_ANGLE = 0.0 * Math.PI/180;
	public static final double ROBOT_HEIGHT = 0.0;
	public static final double GRAVITY = -9.8;
	
	public static double calculateLaserAngle(double otherHeight) {
		double initV_y = INITIAL_VELOCITY * Math.sin(CATAPULT_ANGLE);
		double time = (-initV_y - Math.sqrt(MathUtils.pow(initV_y, 2)
			- 2 * GRAVITY * (ROBOT_HEIGHT - otherHeight))) / GRAVITY;
		double d_x = INITIAL_VELOCITY * Math.cos(CATAPULT_ANGLE) * time;
		return MathUtils.atan(d_x/ROBOT_HEIGHT);
	}
	
}
