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
  
public static final String kCANBusRio = "rio";

  public static final int kDriverControllerPort = 0;
  public static final int kOperatorControllerPort = 1;



  public static final int kDriverJoystickLeft = 1;
  public static final int kDriverJoystickRight = 5;
  public static final int kDriverJoystickX = 1;
  public static final int kDriverJoystickZ = 4;
  public static final int kDriveTrainCurrentLimit = 60;
  
  public static final double kClimberRetractPower = -0.4;
  public static final double kClimberExtractPower = 0.4;
  public static final double kClimberArmsMaxExtract = 16000;
  public static final double kClimberArmsMinRetract = 0;
  public static final double kClimberMaxRetractTime = 5.0;
  public static final double kClimberMaxExtractTime = 2.0;

  public static final int kClimberCANId = 20;
  public static final int kClimberEncoderA = 4;
  public static final int kClimberEncoderB = 5;                                                                                                           

  public static final String kAutoDoNothing = "Do Nothing";
  public static final String kAutoCrossLine = "Cross Line";
  public static final double kAutoCrossLineTime = 1.5;
  public static final double kAutoCrossLineSpeed = 0.5;


}
