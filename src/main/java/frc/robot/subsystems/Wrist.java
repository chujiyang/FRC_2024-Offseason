// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.ControlType;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.SushiLib.Motor.MotorHelper;
import frc.robot.SushiLib.Sensors.AbsoluteEncoder.AbsoluteEncoder;
import frc.robot.SushiLib.SmartDashboard.PIDTuning;
import frc.robot.SushiLib.SmartDashboard.TunableNumber;

public class Wrist extends SubsystemBase {
    private final CANSparkMax leftMotor;
    private final CANSparkMax rightMotor;
    private final AbsoluteEncoder absoluteEncoder;

    private final ArmFeedforward ff;

    private final TunableNumber setpoint;

    private final PIDTuning pid;

    private static Wrist instance;

    public static Wrist getInstance() {
        if (instance == null) {
            instance = new Wrist();
        }
        return instance;
    }

    private Wrist() {
        leftMotor = Constants.Wrist.LEFT_MOTOR.createSparkMax();
        rightMotor = Constants.Wrist.RIGHT_MOTOR.createSparkMax();
        absoluteEncoder = new AbsoluteEncoder(0, 0, false);

        MotorHelper.setConversionFactor(leftMotor, Constants.Wrist.GEAR_RATIO);
        MotorHelper.setConversionFactor(rightMotor, Constants.Wrist.GEAR_RATIO);

        leftMotor.follow(rightMotor, true);

        pid = Constants.Wrist.RIGHT_MOTOR.genPIDTuning("Wrist Right Motor", Constants.TUNING_MODE);

        ff = new ArmFeedforward(
            Constants.Wrist.S, 
            Constants.Wrist.G, 
            Constants.Wrist.V
        );

        setpoint = new TunableNumber("Wrist Setpoint", 0, Constants.TUNING_MODE);

        rightMotor.getEncoder().setPosition(absoluteEncoder.getNormalizedPosition());
    }

    public Command moveWrist(double position) {
        return run(() -> {
            setpoint.setDefault(position);
        }).until(() -> atSetpoint());
    }

    public boolean atSetpoint() {
        return Math.abs(rightMotor.getEncoder().getPosition() - setpoint.get()) < 5;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Position", rightMotor.getEncoder().getPosition());
        SmartDashboard.putNumber("Absoulte Position", absoluteEncoder.getPosition());

        pid.updatePID(rightMotor);

        rightMotor.getPIDController().setReference(
            setpoint.get(), 
            ControlType.kPosition,
            0,
            ff.calculate(Math.toRadians(rightMotor.getEncoder().getPosition()), 0)
        );
    }
}
