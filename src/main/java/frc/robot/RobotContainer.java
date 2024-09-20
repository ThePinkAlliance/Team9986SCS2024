// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import frc.JoystickMap;
import frc.robot.commands.DefaultDriveArcade;
import frc.robot.commands.ManualExtract;
import frc.robot.commands.ManualRetract;
import frc.robot.commands.TimedMove;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveTrain m_DriveTrain = new DriveTrain();
  private final Climber m_climber = new Climber();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final Joystick m_driverController =
      new Joystick(Constants.kDriverControllerPort);

  private final Joystick m_operatorController =
      new Joystick(Constants.kOperatorControllerPort);

  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    //Setup Auto Chooser
    m_chooser.setDefaultOption(Constants.kAutoDoNothing, Constants.kAutoDoNothing);
    m_chooser.addOption(Constants.kAutoCrossLine, Constants.kAutoCrossLine);
    SmartDashboard.putData("Auto choices", m_chooser);


    // Configure the trigger bindings
    configureBindings();
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
    

    //Setup the default command for the DriveTrain subsystem.  Currently Tank Drive Command
    m_DriveTrain.setDefaultCommand(new DefaultDriveArcade(
      m_DriveTrain,
      () -> -m_driverController.getRawAxis(Constants.kDriverJoystickX),
      () -> -m_driverController.getRawAxis(Constants.kDriverJoystickZ)
    ));

    //Bind the Y button to extract the climbers manually
    new JoystickButton(m_operatorController, JoystickMap.BUTTON_Y).whileTrue( new ManualExtract(m_climber));

    //Bind the A button to extract the climbers manually
    new JoystickButton(m_operatorController, JoystickMap.BUTTON_A).whileTrue( new ManualRetract(m_climber));
     
    //Bind the A button to extract the climbers manually
    new JoystickButton(m_operatorController, JoystickMap.BUTTON_B).onTrue(Commands.runOnce(()-> m_climber.resetEncoder()));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    Command command = null;
    //Prepare the command to use in Auto based on what the Operator selected on the dashboard
    if (m_chooser.getSelected() == Constants.kAutoCrossLine) {
      command = new TimedMove(m_DriveTrain, Constants.kAutoCrossLineTime, Constants.kAutoCrossLineSpeed);
    } else {
      command = Commands.none();
    }

    //return the command object to use
    return command;

  }
}
