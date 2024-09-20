// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {

  CANSparkBase leftRear = new CANSparkMax(1, MotorType.kBrushed);
  CANSparkBase leftFront = new CANSparkMax(2, MotorType.kBrushed);
  CANSparkBase rightRear = new CANSparkMax(3, MotorType.kBrushed);
  CANSparkBase rightFront = new CANSparkMax(4, MotorType.kBrushed);
  DifferentialDrive m_drivetrain;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    initDriveTrain();
  }

  public void initDriveTrain() {
    leftRear.setSmartCurrentLimit(Constants.kDriveTrainCurrentLimit);
    leftFront.setSmartCurrentLimit(Constants.kDriveTrainCurrentLimit);
    rightRear.setSmartCurrentLimit(Constants.kDriveTrainCurrentLimit);
    rightFront.setSmartCurrentLimit(Constants.kDriveTrainCurrentLimit);

    leftRear.follow(leftFront);
    rightRear.follow(rightFront);

    leftFront.setInverted(true);
    rightFront.setInverted(false);

    m_drivetrain = new DifferentialDrive(leftFront, rightFront);
  }

  public void setupForBrake() {
    leftRear.setIdleMode(IdleMode.kBrake);
    leftFront.setIdleMode(IdleMode.kBrake);
    rightRear.setIdleMode(IdleMode.kBrake);
    rightFront.setIdleMode(IdleMode.kBrake);
  }

  public void setupForCoast() {
    leftRear.setIdleMode(IdleMode.kCoast);
    leftFront.setIdleMode(IdleMode.kCoast);
    rightRear.setIdleMode(IdleMode.kCoast);
    rightFront.setIdleMode(IdleMode.kCoast);
  }

  public void tankDrive(double left, double right) {
    m_drivetrain.tankDrive(left, right);
  }

  public void arcadeDrive(double xInput, double zInput) {
    m_drivetrain.arcadeDrive(xInput, zInput);
  }

  public void stopMotors() {
    m_drivetrain.tankDrive(0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
