package org.usfirst.frc.team4183.robot.subsystems;

import org.usfirst.frc.team4183.robot.commands.KillCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;


public class ExampleSubsystem extends Subsystem {
	
	// Encoder pulses per rev (set w/ DIP switches)
	private final int ENCODER_CODES_PER_ENCODER_REV = 256;

	private final int MOTOR_NUM = 2;  // Left front motor on practice robot
	private final double LOW_SPEED = 1200; // RPM
	private final double HIGH_SPEED = 1400;  // RPM
	
	private int loopcnt = 0;
	private CANTalon motor;	
	private LoggerBase logger;
	
	
	public ExampleSubsystem() {
		
		motor = new CANTalon(MOTOR_NUM);			
		logger = new TalonLogger(motor);

		setupSpeedMode();
		
		// Be disabled initially, good practice
		disable(); 
	}
	
	
	// This is the best way to enable/disable (start/stop) the CANTalon.
	// Enable/disable leave the motor setup completely intact.
	// Don't do it any other way. 
	public void enable() {
		motor.enableControl();
	}
	
	public void disable() {		
		motor.disableControl();
	}
	
	
	public void motorSlow() {
		motor.set(LOW_SPEED);
		printDebugLine();
	}
	
	public void startLogger() {
		logger.start();
	}
	
	public void motorFast() {
		motor.set(HIGH_SPEED);
		printDebugLine();
	}
	
	public void stopLogger() {
		logger.stop();
	}
	
	private void setupSpeedMode() {

		motor.changeControlMode(CANTalon.TalonControlMode.Speed);
		motor.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		motor.configEncoderCodesPerRev(ENCODER_CODES_PER_ENCODER_REV);

		// These 2 probably not needed but what the heck
		motor.setInverted(false);        
		motor.setVoltageRampRate(0.0);
		
		// Get things moving in the right direction,
		// and make it a NEGATIVE feedback loop!
		motor.reverseOutput(false);
		motor.reverseSensor(false);
		
		// All these params are stored in Flash on the Talon (manual sec. 11)
		// so must set them all if you don't want surprises!

		// The *.001, *1000 are there because Talon doesn't scale by 
		// their own deltaT (=1msec) in their loop implementation, 
		// so we must do it for them here.
		// i.e. if we want a Ki of 0.5, then must pass 0.5*0.001;
		// if we want Kd of 0.01 must pass 0.01*1000.0 (thanks guys!!).
		motor.setPID(0.6, 1.2*0.001, .02*1000.0);		
		motor.setF(0.0);
		
		motor.setIZone(0);
		motor.setCloseLoopRampRate(0.0);  // Works better disabled
		motor.setAllowableClosedLoopErr(0);
		motor.configNominalOutputVoltage(0.0, 0.0);
		motor.configPeakOutputVoltage(+12.0, -12.0);
	}
	

	
	private void printDebugLine() {
		
		if (++loopcnt >= 50) {
			loopcnt = 0;
			System.out.println(
				"SP:" + motor.getSetpoint() + " FB:" + motor.get() + " Err:" + motor.getError() + " Drv:" + motor.getOutputVoltage() );
		}
	}

	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new KillCommand());
	}
}
