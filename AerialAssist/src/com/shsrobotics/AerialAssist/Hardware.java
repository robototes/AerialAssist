package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.MaxBotixSonar;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.camera.AxisCamera;


/**
 * Hardware
 * @author Team 2412
 */
public interface Hardware extends Maps {
    public static final Compressor compressor = new Compressor(DIGITAL_IO_14, RELAY_7);
    public static final AxisCamera camera = AxisCamera.getInstance("10.24.12.11"); 
 
	public static final class DriveBase {
		public static final Talon leftWheels = new Talon(PWM_1);
		public static final Talon rightWheels = new Talon(PWM_2);
		public static final Solenoid shifter = new Solenoid(TWELVE, SOLENOID_1);
		
		public static final RobotDrive drive = new RobotDrive(leftWheels, rightWheels);
	}
	
	public static final class Pickup {
		public static final Solenoid arms = new Solenoid(TWELVE, SOLENOID_8);
		public static final Talon roller = new Talon(PWM_3);
        public static final DigitalInput loaded = new DigitalInput(DIGITAL_IO_2);
	}
	
	public static final class Catapult {
        public static final DoubleSolenoid launchLeft = new DoubleSolenoid(TWENTY_FOUR, SOLENOID_8, SOLENOID_7);
		public static final DoubleSolenoid launchRight = new DoubleSolenoid(TWENTY_FOUR, SOLENOID_4, SOLENOID_3);
        public static final Solenoid latch = new Solenoid(TWELVE, SOLENOID_6);
        public static void setLauncher(DoubleSolenoid.Value value) {
            launchLeft.set(value);
            launchRight.set(value);
        }
        public static DoubleSolenoid.Value getLauncher() {
            return launchLeft.get();
        }
	}
	
	public static final class LaserPoint {
//        public static final Servo servo = new Servo(PWM_4);
	}
	
	public static final class Sonar {
        public static final MaxBotixSonar sonar = new MaxBotixSonar(ANALOG_7);
	}
}
