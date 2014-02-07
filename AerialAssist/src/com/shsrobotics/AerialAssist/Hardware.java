package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.MaxBotixSonar;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;


/**
 * Hardware
 * @author Team 2412
 */
public interface Hardware extends Maps {
	public static final Compressor compressor = new Compressor(DIGITAL_IO_1, RELAY_1);
    
	public static final class DriveBase {
		public static final Talon leftWheels = new Talon(PWM_1);
		public static final Talon rightWheels = new Talon(PWM_2);
		public static final DoubleSolenoid shifter = new DoubleSolenoid(6, 7);
		
		public static final RobotDrive drive = new RobotDrive(leftWheels, rightWheels);
	}
	
	public static final class Pickup {
		public static final DoubleSolenoid arms = new DoubleSolenoid(SOLENOID_1, SOLENOID_2);
		public static final Talon spinWheels = new Talon(PWM_3);
        public static final DigitalInput loaded = new DigitalInput(DIGITAL_IO_2);
	}
	
	public static final class Catapult {
		public static final DoubleSolenoid launch = new DoubleSolenoid(SOLENOID_3, SOLENOID_4);
        public static final Solenoid latch = new Solenoid(SOLENOID_5);
	}
	
	public static final class LaserPoint {
		public static final Servo servo = new Servo(PWM_4);
	}
	
	public static final class Sonar {
		public static final MaxBotixSonar sonar = new MaxBotixSonar(ANALOG_1);
	}
    
    public static final class ControlSystem {
        public static final Relay inRangeLight = new Relay(RELAY_2);
    }
}
