package com.shsrobotics.AerialAssist;

import com.shsrobotics.library.GLOBAL;
import com.sun.cldc.jna.Pointer;
import edu.wpi.first.wpilibj.image.Image;

/**
 * @author Team 2412
 */
public interface Maps extends GLOBAL {
    public static final class Images {
        public static Image startImage;
        public static Image afterImage;
		public static Pointer subtractedImage;
    }
}
