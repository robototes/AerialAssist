package com.shsrobotics.AerialAssist.controls;

import com.shsrobotics.AerialAssist.systems.Pickup;
import com.shsrobotics.library.link.Link;

/**
 * @author Team 2412 <first.robototes.com, github.com/robototes>
 */
public class Links {
	public static final Link
		roller = new Link(ControlBoard.rollerSpeed, Pickup.roller, 1),
		arms = new Link(ControlBoard.armsUp, Pickup.arms, 1);
}
