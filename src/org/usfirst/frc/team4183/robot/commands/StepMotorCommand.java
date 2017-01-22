package org.usfirst.frc.team4183.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4183.robot.Robot;

/**
 *
 */
public class StepMotorCommand extends Command {
		
	public StepMotorCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.exampleSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.exampleSubsystem.startLogger();
		Robot.exampleSubsystem.enable();
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.exampleSubsystem.motorFast();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if( timeSinceInitialized() > 5.0)
			return true;
		
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.exampleSubsystem.stopLogger();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
