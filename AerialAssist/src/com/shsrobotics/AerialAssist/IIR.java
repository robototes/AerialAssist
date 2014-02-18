package com.shsrobotics.AerialAssist;

import edu.wpi.first.wpilibj.Joystick;

public class IIR extends Joystick {
	private double smoothing;
    private double smoothingX;
    private double smoothingY;
    private double smoothingZ;
    
    private double accumulatorX = 0;
    private double accumulatorY = 0;
    private double accumulatorZ = 0;
    
    public IIR(int joystick) {
        super(joystick);
        smoothing = 20;
    }
    
    public IIR(int delay, int joystick) {
        super(joystick);
    }
    
    public IIR(double smoo, int joystick) {
        super(joystick);
        smoothing = smoo;
    }
    
    public void setSmoothing(double smoo) {
        smoothing = smoo;
    }
    
    public double outputX() {
        accumulatorX = (accumulatorX*smoothing + this.getX()) / (1+smoothing) ;
        return accumulatorX;
    }
    
    public double outputZ() {
        accumulatorZ = (accumulatorZ*smoothing + this.getZ()) / (1+smoothing) ;
        return accumulatorZ;
    }
    
    public double outputY() {
        accumulatorY = (accumulatorY*smoothing + this.getY()) / (1+smoothing) ;
        return accumulatorY;
    }
    
	private double average(double a, double b) {
		return (a + b) / 2;
	}
    
}
