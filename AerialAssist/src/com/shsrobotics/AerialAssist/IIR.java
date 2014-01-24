package com.shsrobotics.AerialAssist;

/**
 *
 * @author RoboTotes Team 2412
 */
public class IIR {
	private double firstDelay;
	private double secondDelay;
	
	public IIR() {
		firstDelay = 0.0;
		secondDelay = 0.0;
	}
	
	public double output(double input) {
		double avg = average(firstDelay, secondDelay);
		double newAvg = average(avg, input);
		double output = newAvg;
		secondDelay = firstDelay;
		firstDelay = output;
		return output;
	}
	
	private double average(double a, double b) {
		return (a + b) / 2;
	}
}
