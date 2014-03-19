package com.shsrobotics.AerialAssist;

import com.shsrobotics.AerialAssist.controls.Links;
import com.shsrobotics.library.FRCRobot;
import com.shsrobotics.library.link.Linker;

/**
 * @author Team 2412
 */
public class Main extends FRCRobot implements Maps {

	Linker linker = Linker.getInstance();
	
	public void robotInit() {
		screen.println(Screen.line1, Screen.tab1, "TEAM 2412");
		screen.println(Screen.line3, Screen.tab4, "2014");
		
		linker.addLink(Links.arms);
		linker.addLink(Links.roller);
	}
	
	public void teleopPeriodic() {
		Linker.getInstance().update();
	}
}
