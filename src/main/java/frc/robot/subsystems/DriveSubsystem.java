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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */

  WPI_TalonSRX frontLeft = new  WPI_TalonSRX (DriveTrainConstants.kLeftMotorFront);
  // WPI_TalonSRX  backLeft = new  WPI_TalonSRX (DriveTrainConstants.kLeftMotorBack);
  // WPI_TalonSRX  frontRight = new  WPI_TalonSRX (DriveTrainConstants.kRightMotorFront);
  WPI_TalonSRX  backRight = new  WPI_TalonSRX (DriveTrainConstants.kRightMotorBack);

  // MotorControllerGroup leftDriveMotors = new MotorControllerGroup(frontLeft, backLeft);
  // MotorControllerGroup rightDriveMotors = new MotorControllerGroup(frontRight, backRight);

  DifferentialDrive drive = new DifferentialDrive(frontLeft, backRight);

  public DriveSubsystem() {
    configMotors();
    updateSmartDashboard(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    updateSmartDashboard(false);
  }

  private void configMotors() {
    frontLeft.setNeutralMode(NeutralMode.Brake);
    // frontRight.setNeutralMode(NeutralMode.Coast);
    // backLeft.setNeutralMode(NeutralMode.Coast);
    backRight.setNeutralMode(NeutralMode.Brake);

    frontLeft.setSafetyEnabled(false);
    // frontRight.setSafetyEnabled(false);
    // backLeft.setSafetyEnabled(false);
    backRight.setSafetyEnabled(false);

    frontLeft.setInverted(true);
    // backLeft.setInverted(true);

    frontLeft.configOpenloopRamp(0.15);
    // frontRight.configOpenloopRamp(0.15);
    // backLeft.configOpenloopRamp(0.15);
    backRight.configOpenloopRamp(0.15);

    System.out.println("Motors Configured!"); 
  }

  public void DriveArcade(double xSpeed, double ySpeed) {
    drive.arcadeDrive(xSpeed, ySpeed);
  
    
  }

  public void DriveTank(double left, double right) {
    drive.tankDrive(left, right);
  }
  
  public CommandBase setBrakeMode() {
    return runOnce(() -> {
      frontLeft.setNeutralMode(NeutralMode.Brake);
      // frontRight.setNeutralMode(NeutralMode.Brake);
      // backLeft.setNeutralMode(NeutralMode.Brake);
      backRight.setNeutralMode(NeutralMode.Brake);
    });
  }
  
  public CommandBase setCoastMode() {
    return runOnce(() -> {
      frontLeft.setNeutralMode(NeutralMode.Coast);
      // frontRight.setNeutralMode(NeutralMode.Coast);
      // backLeft.setNeutralMode(NeutralMode.Coast);
      backRight.setNeutralMode(NeutralMode.Coast);
    });
  }

  public void updateSmartDashboard(boolean firstRun) {
    if (firstRun) {
      SmartDashboard.putString("TalonSRX Current Limit", "N/A");
      SmartDashboard.putString("TalonSRX Current Limit Timeout", "N/A");

      SmartDashboard.putString("SparkMAX Freespin Current Limit", "N/A");
      SmartDashboard.putString("SparkMAX Pushing Current Limit", "N/A");

      SmartDashboard.putNumber("pSpeed" , -999);

      SmartDashboard.putString("Active Robot", "Speedy Scavenger | DENISE");
    }

    SmartDashboard.putNumber("FL %", frontLeft.getMotorOutputPercent());
    SmartDashboard.putNumber("FL Volts", frontLeft.getMotorOutputVoltage());
    SmartDashboard.putNumber("FL Amps", frontLeft.getStatorCurrent());

    // SmartDashboard.putNumber("FR %", frontRight.getMotorOutputPercent());
    // SmartDashboard.putNumber("FR Volts", frontRight.getMotorOutputVoltage());
    // SmartDashboard.putNumber("FR Amps", frontRight.getStatorCurrent());

    // SmartDashboard.putNumber("BL %", backLeft.getMotorOutputPercent());
    // SmartDashboard.putNumber("BL Volts", backLeft.getMotorOutputVoltage());
    // SmartDashboard.putNumber("BL Amps", backLeft.getStatorCurrent());
    
    SmartDashboard.putNumber("BR %", backRight.getMotorOutputPercent());
    SmartDashboard.putNumber("BR Volts", backRight.getMotorOutputVoltage());
    SmartDashboard.putNumber("BR Amps", backRight.getStatorCurrent());

    SmartDashboard.updateValues(); 
  }
}
