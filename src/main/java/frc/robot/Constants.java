// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
    public static final int kCoDriverControllerPort = 1;

  }

  public static class DriveTrainConstants {
    public static final int kRightMotorFront = 1;
    public static final int kRightMotorBack = 2;
    public static final int kLeftMotorFront = 3;
    public static final int kLeftMotorBack = 4;
  }

  public static class ShooterConstants {
    public static final int kLeftFlywheelMotor = 5;
    public static final int kRightFlywheelMotor = 6;

    public static final double kSpeedLow = 0.4; 
    public static final double kSpeedMid= 0.6; 
    public static final double kSpeedHigh = 0.8;
    public static final double kSpeedMax = 1.0;  
  }
}





