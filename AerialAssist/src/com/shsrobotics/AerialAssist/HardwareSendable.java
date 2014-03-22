package com.shsrobotics.AerialAssist;

import edu.wpi.first.wpilibj.NamedSendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

/** Enables the driver to edit hardware values via SmartDashboard.
 * Under-construction.
 * @author RoboTotes Team 2412
 */
public class HardwareSendable implements NamedSendable, Hardware {
    private static final String NAME = "Hardware";
    private static final String TYPE = "Hardware list";
    private static HardwareSendable sendable;
    private boolean pickupEnabled = false;
    private boolean catapultEnabled = false;
    private boolean driveBaseEnabled = false;
    private boolean compressorEnabled = false;
    private ITable table;
    
    public static HardwareSendable getInstance() {
        if(sendable == null) {
            sendable = new HardwareSendable();
        }
        return sendable;
    }
    
    public String getName() {
        return NAME;
    }

    public void initTable(ITable subtable) {
        table = subtable;
        
        table.putBoolean("Pickup Enabled", pickupEnabled);
        table.putBoolean("Arm Right", Pickup.arms.get().equals(EXTENDED));
        table.putNumber("Roller Speed", Pickup.roller.get());
        
        table.putBoolean("Catapult Enabled", catapultEnabled);
        table.putBoolean("Catapult Left", Catapult.launchLeft.equals(EXTENDED));
        table.putBoolean("Catapult Right", Catapult.launchRight.equals(EXTENDED));
        table.putBoolean("Catapult Latch", Catapult.latch.get());
        
        table.putBoolean("DriveBase Enabled", driveBaseEnabled);
        table.putBoolean("Shifter", DriveBase.shifter.get());
        table.putNumber("Wheels Left", DriveBase.leftWheels.get());
        table.putNumber("Wheels Right", DriveBase.rightWheels.get());
        
        table.putBoolean("Compressor Enabled", compressorEnabled);
        table.putBoolean("Compressor", Pneumatics.compressor.getPressureSwitchValue());
        
    }
    
    public void update() {
        pickupEnabled = SmartDashboard.getBoolean("Pickup Enabled");
        catapultEnabled = SmartDashboard.getBoolean("Catapult Enabled");
        driveBaseEnabled = SmartDashboard.getBoolean("DriveBase Enabled");
        compressorEnabled = SmartDashboard.getBoolean("Compressor Enabled");
        
        if(pickupEnabled) {
            Pickup.arms.set(SmartDashboard.getBoolean("Arm Right") ? EXTENDED : RETRACTED);
            Pickup.roller.set(SmartDashboard.getNumber("Roller Speed"));
        }
        if(catapultEnabled) {
            Catapult.latch.set(SmartDashboard.getBoolean("Catapult Latch"));
            Catapult.launchLeft.set(SmartDashboard.getBoolean("Catapult Left") ? EXTENDED : RETRACTED);
            Catapult.launchRight.set(SmartDashboard.getBoolean("Catapult Right") ? EXTENDED : RETRACTED);
        }
        if(driveBaseEnabled) {
            DriveBase.leftWheels.set(SmartDashboard.getNumber("Wheels Left"));
            DriveBase.rightWheels.set(SmartDashboard.getNumber("Wheels Right"));
            DriveBase.shifter.set(SmartDashboard.getBoolean("Shifter"));
        }
        if(compressorEnabled) {
        }
    }

    public ITable getTable() {
        return table;
    }

    public String getSmartDashboardType() {
        return TYPE;
    }
    
}
