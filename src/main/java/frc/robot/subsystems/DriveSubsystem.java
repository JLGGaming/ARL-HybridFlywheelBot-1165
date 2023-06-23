// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.Constants.DriveTrainConstants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;


public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */

  WPI_TalonSRX frontLeft = new  WPI_TalonSRX (DriveTrainConstants.kLeftMotorFront);
  WPI_TalonSRX  backLeft = new  WPI_TalonSRX (DriveTrainConstants.kLeftMotorBack);
  WPI_TalonSRX  frontRight = new  WPI_TalonSRX (DriveTrainConstants.kRightMotorFront);
  WPI_TalonSRX  backRight = new  WPI_TalonSRX (DriveTrainConstants.kRightMotorBack);

  MotorControllerGroup leftDriveMotors = new MotorControllerGroup(frontLeft, backLeft);
  MotorControllerGroup rightDriveMotors = new MotorControllerGroup(frontRight, backRight);

  DifferentialDrive drive = new DifferentialDrive(leftDriveMotors, rightDriveMotors);

  public DriveSubsystem() {
    configMotors();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  private void configMotors() {
    frontLeft.setNeutralMode(NeutralMode.Coast);
    frontRight.setNeutralMode(NeutralMode.Coast);
    backLeft.setNeutralMode(NeutralMode.Coast);
    backRight.setNeutralMode(NeutralMode.Coast);

    frontLeft.setSafetyEnabled(false);
    frontRight.setSafetyEnabled(false);
    backLeft.setSafetyEnabled(false);
    backRight.setSafetyEnabled(false);

    System.out.println("Motors Configured!"); 
  }

  public void DriveArcade(double xSpeed, double ySpeed) {
    drive.arcadeDrive(xSpeed, ySpeed);
    // SmartDashboard.putNumber("xSpeed" , xSpeed); 
    // SmartDashboard.putNumber("ySpeed" , ySpeed);
  }

  public void DriveTank(double left, double right) {
    drive.tankDrive(left, right);
  }
  
  public CommandBase setBrakeMode() {
    return runOnce(() -> {
      frontLeft.setNeutralMode(NeutralMode.Brake);
      frontRight.setNeutralMode(NeutralMode.Brake);
      backLeft.setNeutralMode(NeutralMode.Brake);
      backRight.setNeutralMode(NeutralMode.Brake);
    });
  }
  
  public CommandBase setCoastMode() {
    return runOnce(() -> {
      frontLeft.setNeutralMode(NeutralMode.Coast);
      frontRight.setNeutralMode(NeutralMode.Coast);
      backLeft.setNeutralMode(NeutralMode.Coast);
      backRight.setNeutralMode(NeutralMode.Coast);
    });
  }

}
