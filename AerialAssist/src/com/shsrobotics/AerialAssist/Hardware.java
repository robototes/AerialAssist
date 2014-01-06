
package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.GLOBAL;
import edu.wpi.first.wpilibj.camera.AxisCamera;

/**
 *
 * @author Max
 */
public interface Hardware extends GLOBAL {
    AxisCamera camera = AxisCamera.getInstance();
    
}
