// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.ControlModeValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {

  TalonFX m_climber = new TalonFX(Constants.kClimberCANId, Constants.kCANBusRio);  
  Encoder m_encoder = new Encoder(Constants.kClimberEncoderA, Constants.kClimberEncoderB);
                                                                                                     

  /** Creates a new Climber. */
  public Climber() {
    initClimber();
  }

  public void initClimber() {

    TalonFXConfiguration configuration = new TalonFXConfiguration();
    CurrentLimitsConfigs currentLimitsConfigs = new CurrentLimitsConfigs();
    currentLimitsConfigs.SupplyCurrentLimit = 60; // Limit to 1 amps
    currentLimitsConfigs.SupplyCurrentThreshold = 80; // If we exceed 4 amps
    currentLimitsConfigs.SupplyTimeThreshold = 0.1; // For at least 0.1 seconds
    currentLimitsConfigs.SupplyCurrentLimitEnable = true; // And enable it
    configuration.CurrentLimits = currentLimitsConfigs;
    m_climber.getConfigurator().apply(configuration.CurrentLimits);
    m_climber.setInverted(false);

    /*
     * Motors can be set to idle in brake or coast mode.
     * 
     * Brake mode is best for these mechanisms
     */
    m_climber.setNeutralMode(NeutralModeValue.Brake);

  }

  public void extract() {
    //climber move routine - extract
    m_climber.set(Constants.kClimberExtractPower);
    //m_climber.setControl(new DutyCycleOut(Constants.kClimberExtractPower));
  }

  public void retract() {
    //climber move routine - retract
    m_climber.set(Constants.kClimberRetractPower);
  }

  public void moveExtract() {
    //allow extract as long as we are above the max extract pos
    double pos = m_encoder.get();
    if (pos < Constants.kClimberArmsMaxExtract)
      extract();
  }

   public void moveRetract() {
    //allow retract as long as we are above the min retract pos
    double pos = m_encoder.get();
    if (pos > Constants.kClimberArmsMinRetract)
      retract();
  }

  public double getClimberPosition() {
    //Allow user to know the current pos of climber
    double pos = m_encoder.get();
    System.out.println("Climber encoder position: " + pos);
    return pos;
  }

  public void stopClimber() {
    //use when you want to stop motor movement - set speed to zero
    m_climber.set(0);
  }

  public void resetEncoder() {
    m_encoder.reset();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println("Current encoder pos: " + m_encoder.get());
  }
}
