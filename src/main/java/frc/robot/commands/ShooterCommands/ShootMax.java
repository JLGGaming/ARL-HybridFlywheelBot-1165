// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ShooterCommands;
import frc.robot.RobotContainer;
import frc.robot.Constants.ShooterConstants;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootMax extends SequentialCommandGroup {
  /** Creates a new ShootMax. */
  public ShootMax() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      RobotContainer.m_driveSubsystem.setBrakeMode(),
      RobotContainer.m_shooterSubsystem.retractPlunger(),
      RobotContainer.m_shooterSubsystem.setFlywheelSpeed(ShooterConstants.kSpeedMax),
      new WaitCommand(1),
      RobotContainer.m_shooterSubsystem.extendPlunger(),
      new WaitCommand(0.5),
      RobotContainer.m_shooterSubsystem.retractPlunger(),
      RobotContainer.m_shooterSubsystem.setFlywheelSpeed(0),
      RobotContainer.m_driveSubsystem.setCoastMode()
    );
  }
}
