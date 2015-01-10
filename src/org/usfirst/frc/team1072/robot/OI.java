package org.usfirst.frc.team1072.robot;

import org.usfirst.frc.team1072.robot.commands.DriveForwardCommand;
import org.usfirst.frc.team1072.robot.commands.DriveReverseCommand;
import org.usfirst.frc.team1072.robot.commands.DriveTalonCommand;
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
	
	Joystick gamepad;
	JoystickButton button1;
	JoystickButton button2;
	JoystickButton button3;
	JoystickButton button4;
	JoystickButton button5;
	JoystickButton button6;
	
	private OI() {
		System.out.println("Started");
		gamepad = new Joystick(0);
		button1 = new JoystickButton(gamepad, 1);
		button2 = new JoystickButton(gamepad, 2);
		button3 = new JoystickButton(gamepad, 3);
		button4 = new JoystickButton(gamepad, 4);
		button5 = new JoystickButton(gamepad, 5);
		button6 = new JoystickButton(gamepad, 6);
		button1.whenPressed(new TurnCommand(TurnCommand.LEFT));
		button2.whenPressed(new DriveReverseCommand());
		button3.whenPressed(new TurnCommand(TurnCommand.RIGHT));
//		button4.whenPressed(new DriveTalonCommand(3));
		button5.whenPressed(new DriveForwardCommand());
		button6.whenPressed(new StopCommand());
	}
	
	public static void initialize() {
		if (oi == null)
			oi = new OI();
	}
}

