package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.ArduinoRGBLightController;

/**
 * Translates the pressure values into colors for DriveBoard.
 *
 * @author RoboTotes Team 2412
 */
public class PressureColors {

    public static void setColor(double pressure) {
        ArduinoRGBLightController.setColor((120 - pressure) / 120, pressure / 120, 0);
    }
}
