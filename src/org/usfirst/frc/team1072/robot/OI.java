package org.usfirst.frc.team1072.robot;

import org.usfirst.frc.team1072.harkerrobolib.wrappers.GamepadWrapper;
import org.usfirst.frc.team1072.robot.commands.DriveTalonCommand;
import org.usfirst.frc.team1072.robot.commands.ManualDriveCommand;
import org.usfirst.frc.team1072.robot.commands.UpdateEncodersCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private static OI oi;
	
	public static GamepadWrapper gamepad;
	public ManualDriveCommand manualDrive;
	public UpdateEncodersCommand updateEncoders;
	
	private OI() {
		gamepad = new GamepadWrapper(0);
		manualDrive = new ManualDriveCommand();
		manualDrive.start();
		updateEncoders = new UpdateEncodersCommand();
		updateEncoders.start();
	}
	
	public static void initialize() {
		if (oi == null)
			oi = new OI();
	}
}

