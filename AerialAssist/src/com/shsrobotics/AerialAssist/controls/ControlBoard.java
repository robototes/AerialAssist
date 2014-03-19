package com.shsrobotics.AerialAssist.controls;

import com.shsrobotics.AerialAssist.Maps;
import com.shsrobotics.library.JoystickButton;
import com.shsrobotics.library.link.ContinuousInput;
import com.shsrobotics.library.link.DiscreteInput;
import edu.wpi.first.wpilibj.Joystick;

/**
 * @author Team 2412 <first.robototes.com, github.com/robototes>
 */
public class ControlBoard implements Maps {
	static final Joystick system = new Joystick(USB_2);
	
	public static final ContinuousInput rollerSpeed = new ContinuousInput() {
			final JoystickButton rollerIn = new JoystickButton(system, 7);
			final JoystickButton rollerOut = new JoystickButton(system, 8);

			public double get() {
				double scale = rollerIn.get() ? 1.0 : rollerOut.get() ? -1.0 : 0.0;
				return scale * (0.75 - 0.25 * system.getX());
			}
		};
	
	public static final DiscreteInput armsUp = new JoystickButton(system, 4);
}
