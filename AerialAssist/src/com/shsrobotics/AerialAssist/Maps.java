package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.GLOBAL;

/**
 * @author Team 2412 <first.robototes.com, github.com/robototes>
 */
public interface Maps extends GLOBAL {
	
	public static final int 
			VOLTS_12 = 2,
			VOLTS_24 = 1;
	
	public static final class Ports {
		public static final int
				pressureSwitch = DIGITAL_IO_14,
				compressorRelay = RELAY_7,
				
				leftTalons = PWM_1,
				rightTalons = PWM_2,
				
				shifter = SOLENOID_1,
				
				arms_A = SOLENOID_1, 
					arms_B = SOLENOID_2,
				
				roller = PWM_3,
				
				launchLeft_A = SOLENOID_8, 
					launchLeft_B = SOLENOID_7,
				launchRight_A = SOLENOID_4, 
					launchRight_B = SOLENOID_3,
				
				sonar = ANALOG_7;
	}
}
