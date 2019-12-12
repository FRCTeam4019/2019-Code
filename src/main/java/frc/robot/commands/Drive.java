/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Drive extends Command {
  double power;
  public Drive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double forward = -Robot.m_oi.driveJoystick.getRawAxis(RobotMap.DRIVE_AXIS_FORWARD);
    double rotation = Robot.m_oi.driveJoystick.getRawAxis(RobotMap.DRIVE_AXIS_ROTATION);
    // double throttle = (-Robot.m_oi.driveJoystick.getRawAxis(RobotMap.DRIVE_AXIS_THROTTLE) + 1) / 2;
    double throttle = Math.abs(Robot.m_oi.driveJoystick.getRawAxis(RobotMap.DRIVE_AXIS_THROTTLE))

    Robot.m_drivetrain.setPower(throttle * (forward + rotation), throttle * (forward - rotation));
    /*if (Robot.m_oi.driveJoystick.getRawButton(RobotMap.AUTO_FLOOR_BUTTON_ID)) {

    } else if (Robot.m_oi.driveJoystick.getRawButton(RobotMap.AUTO_CARGO_TOGGLE_BUTTON_ID)) {
      if (Robot.m_oi.driveJoystick.getRawButton(RobotMap.AUTO_HATCH_1_BUTTON_ID)) {
        Robot.m_drivetrain.set(0);
      } else if (Robot.m_oi.driveJoystick.getRawButton(RobotMap.AUTO_HATCH_2_BUTTON_ID)) {
        Robot.m_drivetrain.set(5);
      } else if (Robot.m_oi.driveJoystick.getRawButton(RobotMap.AUTO_HATCH_3_BUTTON_ID)) {
        Robot.m_drivetrain.set(10);
      }
    } else {
      if (Robot.m_oi.driveJoystick.getRawButton(RobotMap.AUTO_HATCH_1_BUTTON_ID)) {
        
      } else if (Robot.m_oi.driveJoystick.getRawButton(RobotMap.AUTO_HATCH_2_BUTTON_ID)) {
        
      } else if (Robot.m_oi.driveJoystick.getRawButton(RobotMap.AUTO_HATCH_3_BUTTON_ID)) {
        
      }
    }
    System.out.println(Robot.m_drivetrain.getTest());*/
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
