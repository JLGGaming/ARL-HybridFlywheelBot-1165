// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.robot.Constants.ShooterConstants;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterSubsystem. */
  WPI_TalonSRX leftFlywheelMotor = new  WPI_TalonSRX (ShooterConstants.kLeftFlywheelMotor);
  WPI_TalonSRX  rightFlywheelMotor = new  WPI_TalonSRX (ShooterConstants.kRightFlywheelMotor);

  Compressor phCompressor = new Compressor(1, PneumaticsModuleType.CTREPCM);
  Solenoid plungerSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);

  public ShooterSubsystem() {
    configMotors();
    plungerSolenoid.set(false);

  }

  private void configMotors() {
    leftFlywheelMotor.setNeutralMode(NeutralMode.Coast);
    rightFlywheelMotor.setNeutralMode(NeutralMode.Coast);

    leftFlywheelMotor.setInverted(true);
    
  }

  public CommandBase setFlywheelSpeed(double speed){
    return runOnce(() -> {
      leftFlywheelMotor.set(speed);
      rightFlywheelMotor.set(speed);
    });
  }

  public CommandBase extendPlunger() {
    return runOnce(() -> { 
      plungerSolenoid.set(true);
    });
  }

  public CommandBase retractPlunger() {
    return runOnce(() -> {
    plungerSolenoid.set(false);
    });
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("TL/FLYL %", leftFlywheelMotor.getMotorOutputPercent());
    SmartDashboard.putNumber("TL/FLYL Amps" , leftFlywheelMotor.getMotorOutputVoltage());
    SmartDashboard.putString("FLYL Volts/TL IdleMode" , "" + leftFlywheelMotor.getStatorCurrent());

    SmartDashboard.putNumber("TR/FLYR %", rightFlywheelMotor.getMotorOutputPercent());
    SmartDashboard.putNumber("TR/FLYR Amps" , rightFlywheelMotor.getMotorOutputVoltage());
    SmartDashboard.putString("FLYR Volts/TL IdleMode" , "" + rightFlywheelMotor.getStatorCurrent());
  }
}
