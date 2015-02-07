package org.usfirst.frc.team1072.robot.subsystems;

import org.usfirst.frc.team1072.harkerrobolib.wrappers.EncoderWrapper;
import org.usfirst.frc.team1072.harkerrobolib.wrappers.TalonWrapper;
import org.usfirst.frc.team1072.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivetrain extends Subsystem {
    
	private RobotDrive robotDrive;
	private static Drivetrain drivetrain;
	private TalonWrapper leftFront, leftBack, rightFront, rightBack;
	private EncoderWrapper encLeftFront;
	private EncoderWrapper encLeftBack;
	private EncoderWrapper encRightFront;
	private EncoderWrapper encRightBack;
	private I2C gyro;
	private boolean isRelative;
	private static final double DEADZONE_X = 0.15;
	private static final double DEADZONE_Y = 0.15;
	private static final double DEADZONE_R = 0.2;
	private static final double ROTATION_SCALE = 0.5;
	private static final double SMOOTH = .1;
	
	private double prevX, prevY, prevR;
	private double prevAngle;
	private final int LEFT_FRONT_TALON = 3;
	private final int LEFT_BACK_TALON = 2;
	private final int RIGHT_FRONT_TALON = 0;
	private final int RIGHT_BACK_TALON = 1;
	private final int GYRO_ADDRESS = 0x69;
	
	private Drivetrain() {		
		leftFront = new TalonWrapper(LEFT_FRONT_TALON, true);
		leftBack = new TalonWrapper(LEFT_BACK_TALON, true);
		rightFront = new TalonWrapper(RIGHT_FRONT_TALON);
		rightBack = new TalonWrapper(RIGHT_BACK_TALON);
		
		encLeftBack = new EncoderWrapper(0, 1);
		encLeftFront = new EncoderWrapper(2, 3);
		encRightFront = new EncoderWrapper(4, 5);
		encRightBack = new EncoderWrapper(6, 7);
		
		robotDrive = new RobotDrive(leftFront, leftBack, rightFront, rightBack);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, false);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, false);
		robotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		robotDrive.setSafetyEnabled(false);
		
		gyro = new I2C(I2C.Port.kOnboard, GYRO_ADDRESS);
		prevAngle = 0;
		
		SmartDashboard.putData("Left Back Encoder", encLeftBack);
		SmartDashboard.putData("Left Front Encoder", encLeftFront);
		SmartDashboard.putData("Right Back Encoder", encRightBack);
		SmartDashboard.putData("Right Front Encoder", encRightFront);
		
		//vX = vY = vR = 0;
		
		isRelative = false;
	}
	
	public static Drivetrain getInstance() {
		if (drivetrain == null) drivetrain = new Drivetrain();
		return drivetrain;
	}
	
	public static void initialize() {
		if (drivetrain == null) drivetrain = new Drivetrain();
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    
    public void drive(double left, double right)
    {
    	robotDrive.tankDrive(-left, -right);
    }
    
    public void drive(double x, double y, double rot) {

        double xIn = (Math.abs(x) < DEADZONE_X) ? 0 : x;
        double yIn = (Math.abs(y) < DEADZONE_Y) ? 0 : y;
        double rotation = (Math.abs(rot) < DEADZONE_R) ? 0 : rot * ROTATION_SCALE;
        
        if (xIn > prevX + SMOOTH) {
        	xIn = prevX + SMOOTH;
        } else if (xIn < prevX - SMOOTH) {
        	xIn = prevX - SMOOTH;
        }
        if (yIn > prevY + SMOOTH) {
        	yIn = prevY + SMOOTH;
        } else if (yIn < prevY - SMOOTH) {
        	yIn = prevY - SMOOTH;
        }
        if (rotation > prevR + SMOOTH) {
        	rotation = prevR + SMOOTH;
        } else if (rotation < prevR - SMOOTH) {
        	rotation = prevR - SMOOTH;
        }
        
        prevX = xIn;
		prevY = yIn;
		prevR = rotation;
        
        if (rotation == 0) {
        	rotation -= (getCurrentHeading() - prevAngle);
        }
        
        if (isRelative)
        	robotDrive.mecanumDrive_Cartesian(xIn, yIn, rotation, getCurrentHeading());
        else
        	robotDrive.mecanumDrive_Cartesian(xIn, yIn, rotation, 0);
		
		prevAngle = getCurrentHeading();
        
        // Compenstate for gyro angle. !!!NOTE:!!! use mecanuDrive_Polar for this
//        double rotated[] = rotateVector(xIn, yIn, 0);
//        xIn = rotated[0];
//        yIn = rotated[1];
//        System.out.println("Vx:" + xIn + " Vy:" + yIn + " Vr:" + rotation);
//        double wheelSpeeds[] = new double[4];
//        wheelSpeeds[LEFT_FRONT_TALON] = xIn + yIn + rotation;
//        wheelSpeeds[RIGHT_FRONT_TALON] = -xIn + yIn - rotation;
//        wheelSpeeds[LEFT_BACK_TALON] = -xIn + yIn + rotation;
//        wheelSpeeds[RIGHT_BACK_TALON] = xIn + yIn - rotation;
//
//        normalize(wheelSpeeds);
//        
//        leftFront.set(wheelSpeeds[LEFT_FRONT_TALON]);
//        rightFront.set(wheelSpeeds[RIGHT_FRONT_TALON]);
//        leftBack.set(wheelSpeeds[LEFT_BACK_TALON]);
//        rightBack.set(wheelSpeeds[RIGHT_BACK_TALON]);
    }
    
    public void setRelative(boolean flag)
    {
    	isRelative = flag;
    }
    
    public void toggleRelative()
    {
    	isRelative = ! isRelative;
    }
    
    public double getCurrentHeading() {
    	System.out.println(gyro.addressOnly());
    	return 0;
    	//resetGyro();
    	//return gyro.getAngle();
    }
    
    public void resetGyro() {
    	//gyro.reset();
    }
	
	public void updateEncoders() {
		System.out.println("LB:" + encLeftBack.get());
		System.out.println("LF:" + encLeftFront.get());
		System.out.println("RB:" + encRightBack.get());
		System.out.println("RF:" + encRightFront.get());
		System.out.println("G0:" + getCurrentHeading());
		encLeftBack.updateRate();
		encLeftFront.updateRate();
		encRightBack.updateRate();
		encRightFront.updateRate();
	}
}

