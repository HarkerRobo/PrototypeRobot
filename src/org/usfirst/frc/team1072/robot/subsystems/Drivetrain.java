package org.usfirst.frc.team1072.robot.subsystems;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
	private RobotDrive robotDrive;
	private static Drivetrain drivetrain;
	private Talon leftFront, leftBack, rightFront, rightBack;
	
	private Drivetrain() {
		leftFront = new Talon(0);
		leftBack = new Talon(1);
		rightFront = new Talon(2);
		rightBack = new Talon(3);
	}
	
	public static Drivetrain getInstance() {
		if (drivetrain == null) drivetrain = new Drivetrain();
		return drivetrain;
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void drive(double speed) {
    	leftFront.set(-speed);
    	leftBack.set(-speed);
    	rightFront.set(speed);
    	rightBack.set(speed);
    }
    
    public void turn(int direction) {
    	leftFront.set(-direction);
    	leftBack.set(-direction);
//    	rightFront.set(direction);
//    	rightBack.set(direction);
    }
    
    public void drive(double speed, int port) {
    	switch(port) {
    		case 0:
    			leftFront.set(-speed);
    			break;
    		case 1: 
    			leftBack.set(-speed);
    			break;
    		case 2:
    			rightFront.set(speed);
    			break;
    		case 3:
    			rightBack.set(speed);
    	}
    }
}

