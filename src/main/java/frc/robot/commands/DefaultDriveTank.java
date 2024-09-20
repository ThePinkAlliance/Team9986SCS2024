// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class DefaultDriveTank extends Command {

  private Supplier<Double> leftInput;
  private Supplier<Double> rightInput;
  private DriveTrain m_drive;


  /** Creates a new DefaultDrive. */
  public DefaultDriveTank(DriveTrain drive, Supplier<Double> left, Supplier<Double> right) {
    //Assign the in params to their respective class member fields.
    this.leftInput = left; // Joystick forward and backwards
    this.rightInput = right; // Joystick left and right
    this.m_drive = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //This is a basic default command to handle joystick movement
    //Nothing to initialize
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Each time the execute() method is called pass in the values from the joystick
    m_drive.tankDrive(this.leftInput.get(), this.rightInput.get());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //This is a default command for the drive subsystem thus it should never end
    //To protect from misuse, as we are all subject to mistakes I am shutting off the motors on end
    m_drive.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //Default commands should always return false.  Default commands execute when no other command is using the subsystem
    return false;
  }
}
