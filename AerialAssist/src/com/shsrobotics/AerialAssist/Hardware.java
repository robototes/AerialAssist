package com.shsrobotics.AerialAssist;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.camera.AxisCamera;

/**
 * Hardware
 * @author Team 2412
 */
public interface Hardware extends Maps {
    public static final AxisCamera camera = AxisCamera.getInstance();
	public static final Compressor compressor = new Compressor(DIGITAL_IO_1, RELAY_1);
	
	public static final class DriveBase {
		public static final Talon leftWheels = new Talon(PWM_1);
		public static final Talon rightWheels = new Talon(PWM_2);
		
		public static final RobotDrive drive = new RobotDrive(leftWheels, rightWheels);
	}
	
	public static final class Pickup {
		public static final DoubleSolenoid raiseArms = new DoubleSolenoid(SOLENOID_1, SOLENOID_2);
		public static final Relay spinWheels = new Relay(RELAY_2);
	}
	
	public static final class Catapult {
		public static final DoubleSolenoid highPower = new DoubleSolenoid(SOLENOID_3, SOLENOID_4);
		public static final DoubleSolenoid lowPower = new DoubleSolenoid(SOLENOID_5, SOLENOID_6);
		public static final DoubleSolenoid launchCatapult = new DoubleSolenoid(SOLENOID_7, SOLENOID_8);
		public static final DoubleSolenoid launch = new DoubleSolenoid(SOLENOID_3, SOLENOID_4);
        public static final Solenoid latch = new Solenoid(SOLENOID_5);
        public static final DigitalInput loaded = new DigitalInput(DIGITAL_IO_2);
	}
	
	public static final class LaserPoint {
		public static final Servo servo = new Servo(PWM_4);
	}
	
}
