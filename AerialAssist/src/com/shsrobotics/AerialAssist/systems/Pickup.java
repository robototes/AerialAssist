package com.shsrobotics.AerialAssist.systems;

import com.shsrobotics.library.link.Output;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Talon;

/**
 * @author Team 2412 <first.robototes.com, github.com/robototes>
 */
public class Pickup extends Pneumatics {
	public static Talon roller = new Talon(Ports.roller);
	
	public static Output arms = new Output() {
		final DoubleSolenoid arms = new DoubleSolenoid(VOLTS_24, Ports.arms_A, Ports.arms_B);
		
		public void set(boolean b) {
			arms.set(b ? EXTENDED : RETRACTED);
		}

		public void set(double d) { }
	};
}
