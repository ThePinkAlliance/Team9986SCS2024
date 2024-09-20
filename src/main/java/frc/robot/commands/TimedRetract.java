// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class TimedRetract extends Command {

  private Climber m_climber;
  private Timer m_timer;


  /** Creates a new DefaultDrive. */
  public TimedRetract(Climber climber) {
    //Assign the in params to their respective class member fields.
    this.m_climber = climber; // how many seconds to hold power
    this.m_timer = new Timer();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(this.m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    //This is a timed command so prepare the timer
    this.m_timer.stop();
    this.m_timer.reset();
    this.m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    //Each time the execute() method is called pass in the constant speed
    m_climber.retract();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    //Stop the motors when the command is finished
    m_climber.stopClimber();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    //Return true to signal that the command is done and should go through the clean up process
    boolean value = false;
    //Using the built in feature of timer, check if enough time has elapsed
    if (m_timer.hasElapsed(Constants.kClimberMaxRetractTime) == true)
      value = true;
    return value;
  }
}
