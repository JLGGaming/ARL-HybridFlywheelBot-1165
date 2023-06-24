// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.AutoCommands.ShootHighDrive;
import frc.robot.commands.AutoCommands.ShootLowDrive;
import frc.robot.commands.AutoCommands.ShootMidDrive;
import frc.robot.commands.DriveCommands.DriveArcade;
import frc.robot.commands.ShooterCommands.ShootHigh;
import frc.robot.commands.ShooterCommands.ShootLow;
import frc.robot.commands.ShooterCommands.ShootMax;
import frc.robot.commands.ShooterCommands.ShootMid;
import frc.robot.commands.ShooterCommands.ShooterLoad;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.DriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final static DriveSubsystem m_driveSubsystem = new DriveSubsystem();
  public final static ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public final static CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

      public final CommandXboxController m_coDriverController =
      new CommandXboxController(OperatorConstants.kCoDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    m_driveSubsystem.setDefaultCommand(new DriveArcade());
    
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    // new Trigger(m_exampleSubsystem::exampleCondition)
    //     .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    // m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
    new Trigger(m_coDriverController.povUp()).onTrue(new ShootHigh());
    new Trigger(m_coDriverController.povLeft()).onTrue(new ShootMid());
    new Trigger(m_coDriverController.povRight()).onTrue(new ShootMid());
    new Trigger(m_coDriverController.povDown()).onTrue(new ShootLow());

    new Trigger(m_coDriverController.a()).onTrue(new ShootMax());

    new Trigger(m_coDriverController.rightTrigger(0.6)).onTrue(RobotContainer.m_shooterSubsystem.extendPlunger());
    new Trigger(m_coDriverController.leftTrigger(0.6)).onTrue(RobotContainer.m_shooterSubsystem.retractPlunger());

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand(String auto) {
    // An example command will be run in autonomous
    if(auto.equals("ShootHighDrive")){
      return new ShootHighDrive();
    }
    else if(auto.equals("ShootMidDrive")){
      return new ShootMidDrive();
    } 
    else if(auto.equals("ShootHigh")){
      return new ShootHigh();
    }
    else if(auto.equals("ShootMid")){
        return new ShootMid();
    }
    else if (auto.equals("ShootLow")){
      return new ShootLow();
    }
    else if (auto.equals("ShootLowDrive")){
      return new ShootLowDrive();
    }
    
    else if (auto.equals("None")){
      System.out.println("No Auto Running");
      return null;
    }
    else {
      System.out.println("Failed to select auto!");
      return null;
    }
  }
}