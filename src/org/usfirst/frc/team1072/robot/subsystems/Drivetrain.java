package org.usfirst.frc.team1072.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
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
	private DigitalInput[] inputArray = new DigitalInput[10];
	
	private Drivetrain() {
		leftFront = new Talon(0);
		leftBack = new Talon(1);
		rightFront = new Talon(2);
		rightBack = new Talon(3);
		for (int i = 0; i < inputArray.length; i++)
			inputArray[i] = new DigitalInput(i);
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
    
    public void drive(double left, double right) {
    	leftFront.set(-left);
    	leftBack.set(-left);
    	rightFront.set(right);
    	rightBack.set(right);
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
    
    public void pollAllInputs() {
    	for (int i = 0; i < 10; i++) {
    		try {
    			pollInput(i);
    		} catch (Exception ex) {
    			System.err.println("Error with input " + i);
    		}
    	}
    }
    
    public void pollInput(int inputPort) throws Exception {
    	System.out.println(inputArray[inputPort].get());
    }
}

