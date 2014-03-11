package com.shsrobotics.AerialAssist;

import edu.wpi.first.wpilibj.Joystick;

public class IIR extends Joystick {
    private double smoothingX;
    private double smoothingY;
    private double smoothingZ;
    
    private double accumulatorX = 0;
    private double accumulatorY = 0;
    private double accumulatorZ = 0;
    
    public IIR(int joystick) {
        super(joystick);
        setSmoothing(new Smoothing(20, 20, 20));
    }
    
    public IIR(int delay, int joystick) {
        super(joystick);
    }
    
    public IIR(Smoothing smoothing, int joystick) {
        super(joystick);
        setSmoothing(smoothing);
    }
    
    public void setSmoothing(Smoothing smoothing) {
        smoothingX = smoothing.x;
        smoothingY = smoothing.y;
        smoothingZ = smoothing.z;
    }
    
    public double outputX() {
        accumulatorX = (accumulatorX*smoothingX + this.getX()) / (1+smoothingX) ;
        return accumulatorX;
    }
    
    public double outputZ() {
        accumulatorZ = (accumulatorZ*smoothingY + this.getZ()) / (1+smoothingY) ;
        return accumulatorZ;
    }
    
    public double outputY() {
        accumulatorY = (accumulatorY*smoothingZ + this.getY()) / (1+smoothingZ) ;
        return accumulatorY;
    }
    
	public static class Smoothing {
        public double x;
        public double y;
        public double z;
        
        public Smoothing(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    
}
