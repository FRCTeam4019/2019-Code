/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.Drive;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {

  TalonEncoder talonLF;
  TalonEncoder talonLB;

  TalonEncoder talonRF;
  TalonEncoder talonRB;

  private class TalonEncoder {
    public TalonSRX talon;
    public Double distance = 0.0;
    public Double multiplier = 1.0;

    public TalonEncoder(int id, double multiplier) {
      this.talon = new TalonSRX(id);
      this.multiplier = multiplier;
    }

    public void set(ControlMode controlMode, Double demand) {
      if (Math.abs(demand) > 0.1) {
        this.distance += demand;
      }
      this.talon.set(controlMode, demand);
    }

    public void setInverted(boolean value) {
      this.talon.setInverted(value);
    }

    public Double getDistance() {
      return this.distance * this.multiplier;
    }
  }

  public DriveTrain(){
    /*talonLF = new TalonSRX(RobotMap.TALON_LEFT_FRONT_ID);
    talonLB = new TalonSRX(RobotMap.TALON_LEFT_BACK_ID);
    talonRF = new TalonSRX(RobotMap.TALON_RIGHT_FRONT_ID);
    talonRB = new TalonSRX(RobotMap.TALON_RIGHT_BACK_ID);

    
    talonLF.setInverted(RobotMap.TALON_LEFT_FRONT_INVERT);
    talonLB.setInverted(RobotMap.TALON_LEFT_BACK_INVERT);
    talonRF.setInverted(RobotMap.TALON_RIGHT_FRONT_INVERT);
    talonRB.setInverted(RobotMap.TALON_RIGHT_BACK_INVERT);*/

    talonLF = new TalonEncoder(RobotMap.TALON_LEFT_FRONT_ID, 0.1);
    talonLB = new TalonEncoder(RobotMap.TALON_LEFT_BACK_ID, 0.1);
    talonRF = new TalonEncoder(RobotMap.TALON_RIGHT_FRONT_ID, 0.1);
    talonRB = new TalonEncoder(RobotMap.TALON_RIGHT_BACK_ID, 0.1);

    
    talonLF.setInverted(RobotMap.TALON_LEFT_FRONT_INVERT);
    talonLB.setInverted(RobotMap.TALON_LEFT_BACK_INVERT);
    talonRF.setInverted(RobotMap.TALON_RIGHT_FRONT_INVERT);
    talonRB.setInverted(RobotMap.TALON_RIGHT_BACK_INVERT);

  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new Drive());
  }
  public void setPower(double leftPower, double rightPower){
    talonLF.set(ControlMode.PercentOutput,RobotMap.DRIVE_THROTTLE * leftPower);
    talonLB.set(ControlMode.PercentOutput,RobotMap.DRIVE_THROTTLE * leftPower);

    talonRF.set(ControlMode.PercentOutput,RobotMap.DRIVE_THROTTLE * rightPower);
    talonRB.set(ControlMode.PercentOutput,RobotMap.DRIVE_THROTTLE * rightPower);
  }

  public void set(double position) {
    double LFspeed = (Math.abs(-talonLF.getDistance() + position) > 0.25
        ? Math.copySign(0.25, -talonLF.getDistance() + position)
        : -talonLF.getDistance() + position);
    double LBspeed = (Math.abs(-talonLB.getDistance() + position) > 0.25
        ? Math.copySign(0.25, -talonLB.getDistance() + position)
        : -talonLB.getDistance() + position);
    double RFspeed = (Math.abs(-talonRF.getDistance() + position) > 0.25
        ? Math.copySign(0.25,-talonRF.getDistance() + position)
        : -talonRF.getDistance() + position);
    double RBspeed = (Math.abs(-talonRB.getDistance() + position) > 0.25
        ? Math.copySign(0.25, -talonRB.getDistance() + position)
        : -talonRB.getDistance() + position);

    talonLF.set(ControlMode.PercentOutput, LFspeed);
    talonLB.set(ControlMode.PercentOutput, LBspeed);
    talonRF.set(ControlMode.PercentOutput, RFspeed);
    talonRB.set(ControlMode.PercentOutput, RBspeed);
  }

  public Double getTest() {
    return talonLB.getDistance();
  }

}
