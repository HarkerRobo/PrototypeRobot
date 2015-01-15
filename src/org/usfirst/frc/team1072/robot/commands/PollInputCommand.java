package org.usfirst.frc.team1072.robot.commands;

import org.usfirst.frc.team1072.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PollInputCommand extends Command {
	private int port;
    public PollInputCommand(int port) {
    	this.port = port;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
			Drivetrain.getInstance().pollInput(port);
		} catch (Exception e) {
			System.err.println("Error with port " + port);
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
