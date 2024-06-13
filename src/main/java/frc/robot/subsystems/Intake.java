// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.SushiLib.Sensors.BeamBreak.BeamBreak;

public class Intake extends SubsystemBase {
    private final CANSparkMax innerMotor;
    private final CANSparkMax outerMotor;
    private final BeamBreak beamBreak;

    private static Intake instance;

    public static Intake getInstance() {
        if (instance == null) {
            instance = new Intake();
        }
        return instance;
    }

    private Intake() {
        innerMotor = Constants.Intake.INNER_MOTOR.createSparkMax();
        outerMotor = Constants.Intake.OUTER_MOTOR.createSparkMax();
        beamBreak = Constants.Intake.BEAM_BREAK.createBeamBreak();
    }

    public Command runIntake() {
        return runOnce(() -> {
            innerMotor.set(Constants.Intake.DEFAULT_SPEED);
            outerMotor.set(Constants.Intake.DEFAULT_SPEED);
        });
    }

    public Command reverseIntake() {
        return runOnce(() -> {
            innerMotor.set(Constants.Intake.DEFAULT_SPEED * (-1));
            outerMotor.set(Constants.Intake.DEFAULT_SPEED * (-1));
        });
    }

    public Command stopIntake() {
        return runOnce(() -> {
            innerMotor.set(0.0);
            outerMotor.set(0.0);
        });
    }

    public Command intakePiece() {
        return runIntake().until(
            beamBreak.getBlockedSupplier()
        ).andThen(stopIntake());
    }
}
