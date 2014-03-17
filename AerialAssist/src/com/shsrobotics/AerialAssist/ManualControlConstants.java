/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.shsrobotics.AerialAssist;


/**
 *
 * @author RoboTotes Team 2412
 */
public class ManualControlConstants {
    private ManualControlConstants() {
    }
    
    
    public static final String MANUAL_CONTROL_SYS_LIST = "MANCON_SYS_LIST";
    public static final String MANUAL_SYS_COUNT = "MANCON_SYS_NUM";
    public static final String MANUAL_CONTROL_ENABLETAG = "-enable";
    
    private static final String S = ":";
    public static final String MANUAL_CONTROL_CATAPULT = "MANCON_CATAPULT";
    public static final String MANUAL_CONTROL_PICKUP = "MANCON_ARM";
    public static final String MANUAL_CONTROL_COMPRESSOR = "MANCON_COMPRESSOR";
    public static final String MANUAL_CONTROL_DRIVE = "MANCON_DRIVE";
    
    public static final String MANUAL_CONTROL_SUBSYS_LIST = MANUAL_CONTROL_PICKUP + S + MANUAL_CONTROL_CATAPULT + S + MANUAL_CONTROL_COMPRESSOR + S + MANUAL_CONTROL_DRIVE;
    
    public static final String MANUAL_CONTROL_CATAPULT_LEFTSOLENOID = "B!CATAPULTLEFT";
    public static final String MANUAL_CONTROL_CATAPULT_RIGHTSOLENOID = "B!CATAPULTRIGHT";
    public static final String MANUAL_CONTROL_CATAPULT_LATCHSOLENOID = "B!CATAPULTLATCH";
    public static final String MANCON_CONTROL_CATAPULT_ENABLE = MANUAL_CONTROL_CATAPULT + MANUAL_CONTROL_ENABLETAG;
    public static final String MANUAL_CONTROL_CATAPULT_LIST = MANUAL_CONTROL_CATAPULT_LEFTSOLENOID + S + MANUAL_CONTROL_CATAPULT_RIGHTSOLENOID + S + MANUAL_CONTROL_CATAPULT_LATCHSOLENOID;
 
    public static final String MANUAL_CONTROL_PICKUP_ENABLE = MANUAL_CONTROL_PICKUP + MANUAL_CONTROL_ENABLETAG;
    public static final String MANUAL_CONTROL_PICKUP_RIGHT = "B!ARMRIGHT";
    public static final String MANUAL_CONTROL_PICKUP_LEFT = "B!ARMLEFT";
    public static final String MANUAL_CONTROL_PICKUP_ROLLER = "D!ROLLERSPEED";
    public static final String MANUAL_CONTROL_PICKUP_LIST = MANUAL_CONTROL_PICKUP_RIGHT + S + MANUAL_CONTROL_PICKUP_LEFT + S + MANUAL_CONTROL_PICKUP_ROLLER;
    
    public static final String MANUAL_CONTROL_COMPRESSOR_ENABLE = MANUAL_CONTROL_COMPRESSOR + MANUAL_CONTROL_ENABLETAG;
    public static final String MANUAL_CONTROL_COMPRESSOR_STATE = "B!COMPRESSOR";
    public static final String MANUAL_CONTROL_COMPRESSOR_LIST = MANUAL_CONTROL_COMPRESSOR_STATE;
    
    public static final String MANUAL_CONTROL_DRIVE_ENABLE = MANUAL_CONTROL_DRIVE + MANUAL_CONTROL_ENABLETAG;
    public static final String MANUAL_CONTROL_DRIVE_SHIFTER = "B!SHIFTER";
    public static final String MANUAL_CONTROL_DRIVE_WHEELS_LEFT = "D!WHEELSLEFT";
    public static final String MANUAL_CONTROL_DRIVE_WHEELS_RIGHT = "D!WHEELSRIGHT";
    public static final String MANUAL_CONTROL_DRIVE_LIST = MANUAL_CONTROL_DRIVE_SHIFTER + S + MANUAL_CONTROL_DRIVE_WHEELS_LEFT + S + MANUAL_CONTROL_DRIVE_WHEELS_RIGHT;
    
    
}