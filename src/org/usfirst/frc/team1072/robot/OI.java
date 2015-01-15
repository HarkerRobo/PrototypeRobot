package org.usfirst.frc.team1072.robot;

import org.usfirst.frc.team1072.harkerrobolib.wrappers.GamepadWrapper;
import org.usfirst.frc.team1072.robot.commands.DriveForwardCommand;
import org.usfirst.frc.team1072.robot.commands.DriveReverseCommand;
import org.usfirst.frc.team1072.robot.commands.DriveTalonCommand;
import org.usfirst.frc.team1072.robot.commands.ManualDriveCommand;
import org.usfirst.frc.team1072.robot.commands.PollInputCommand;
import org.usfirst.frc.team1072.robot.commands.StopCommand;
import org.usfirst.frc.team1072.robot.commands.TurnCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private static OI oi;
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	public static GamepadWrapper gamepad;
	public ManualDriveCommand manualDrive;
	
	private OI() {
		gamepad = new GamepadWrapper(0);
		manualDrive = new ManualDriveCommand();
		manualDrive.start();
		gamepad.getButtonA().whenPressed(new PollInputCommand(0));
		gamepad.getButtonB().whenPressed(new PollInputCommand(5));
		gamepad.getButtonX().whenPressed(new PollInputCommand(6));
		gamepad.getButtonY().whenPressed(new PollInputCommand(7));
		gamepad.getButtonBumperLeft().whenPressed(new PollInputCommand(8));
		gamepad.getButtonBumperRight().whenPressed(new PollInputCommand(9));
	}
	
	public static void initialize() {
		if (oi == null)
			oi = new OI();
	}
}

