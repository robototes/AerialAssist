package com.shsrobotics.AerialAssist;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LaserPointer implements Hardware {
	public static final double INITIAL_VELOCITY = 10.0/Math.cos(Math.toRadians(45)); // ft/s
	public static final double CATAPULT_ANGLE = 45.0 * Math.PI/180; // converted to radians
	public static final double LASER_HEIGHT = 3.00; // ft
	public static final double ROBOT_HEIGHT = 3.00; // ft
	public static final double GRAVITY = -32.2; // ft/s^2
	
	private static double calculateLaserAngle(double otherHeight) {
		double initV_y = INITIAL_VELOCITY * Math.sin(CATAPULT_ANGLE); // initial y velocity
		double time = (-initV_y - Math.sqrt(MathUtils.pow(initV_y, 2.0) // amount of time it takes ball to land
			- 2.0 * GRAVITY * (ROBOT_HEIGHT - otherHeight))) / GRAVITY;
		double d_x = INITIAL_VELOCITY * Math.cos(CATAPULT_ANGLE) * time; // distance x from our robot
		return MathUtils.atan(d_x/LASER_HEIGHT); // returns angle of laser pointer in radians
	}
	
	public static void set() {
	//	LaserPoint.servo.setAngle(Math.toDegrees(calculateLaserAngle(SmartDashboard.getNumber("Other Robot Height"))));
	}	
}
