package com.shsrobotics.AerialAssist.systems;

import com.shsrobotics.AerialAssist.Maps;
import edu.wpi.first.wpilibj.Compressor;

/**
 * @author Team 2412 <first.robototes.com, github.com/robototes>
 */
public class Pneumatics implements Maps {
	Compressor compressor = new Compressor(Ports.pressureSwitch, Ports.compressorRelay);
}
