package com.ctre;

import org.usfirst.frc.team4183.robot.Constants;

/**
* We should use this class to fix up whatever we don't like in CTRE's CANTalon class.
* Instantiate "ourCANTalon" rather than CANTalon.
* @author Tim Wilson
*/
public class ourCANTalon extends CANTalon {
	
	public ourCANTalon(int deviceNumber) {
		super(deviceNumber);
		
		// Set a longer Motor Safety timeout 
		// (default is .1 sec, seems that isn't long enough)
		// TODO get rid of magic number
		setExpiration( Constants.MOTORSAFETY_TIMEOUT);  	
	}
	
}
