package com.shsrobotics.AerialAssist;
import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class LaserPointer implements Hardware {
	public static final double INITIAL_VELOCITY = 3.9/Math.cos(Math.toRadians(45)); // m/s
	public static final double CATAPULT_ANGLE = 45.0 * Math.PI/180; // converted to radians
	public static final double ROBOT_HEIGHT = 1.00; // m
	public static final double GRAVITY = -9.8; // m/s^2
	
	private static double calculateLaserAngle(double otherHeight) {
		double initV_y = INITIAL_VELOCITY * Math.sin(CATAPULT_ANGLE); // initial y velocity
		double time = (-initV_y - Math.sqrt(MathUtils.pow(initV_y, 2) // amount of time it takes ball to land
			- 2 * GRAVITY * (ROBOT_HEIGHT - otherHeight))) / GRAVITY;
		double d_x = INITIAL_VELOCITY * Math.cos(CATAPULT_ANGLE) * time; // distance x from our robot
		return MathUtils.atan(d_x/ROBOT_HEIGHT); // returns angle of laser pointer in radians
	}
	
	public static void set() {
		//LaserPoint.serv.setAngle(Math.toDegrees(calculateLaserAngle(SmartDashboard.getNumber("Other Robot Height"))));
		System.out.println(Math.toDegrees(calculateLaserAngle(0)));
		LaserPoint.serv.setAngle(Math.toDegrees(calculateLaserAngle(0)));
	}
	
		
}
