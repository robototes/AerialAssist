package com.shsrobotics.AerialAssist;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.camera.AxisCamera;

/**
 * @author Team 2412
 */
public interface Hardware extends Maps {
    public static AxisCamera camera = AxisCamera.getInstance();
	
	public static class DriveBase {
		static Talon leftWheels = new Talon(PWM_1);
		static Talon rightWheels = new Talon(PWM_2);
	}
	
	public static class Pickup {
		static DoubleSolenoid raiseArms = new DoubleSolenoid(SOLENOID_1, SOLENOID_2);
		static Relay spinWheels = new Relay(RELAY_1);
	}
	
	public static class Catapult {
		static DoubleSolenoid highPower = new DoubleSolenoid(SOLENOID_3, SOLENOID_4);
		static DoubleSolenoid lowPower = new DoubleSolenoid(SOLENOID_5, SOLENOID_6);
		static DigitalInput loaded = new DigitalInput(DIGITAL_IO_1);
	}
	
	
}
