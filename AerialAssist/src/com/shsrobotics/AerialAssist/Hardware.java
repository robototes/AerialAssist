package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.GLOBAL;
import edu.wpi.first.wpilibj.camera.AxisCamera;

/**
 * @author Team 2412
 */
public interface Hardware extends GLOBAL {
    public static final AxisCamera camera = AxisCamera.getInstance();
}
