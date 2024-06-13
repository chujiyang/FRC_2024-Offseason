// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.SushiLib.Control.PIDConfig;
import frc.robot.SushiLib.Motor.MotorConfig;
import frc.robot.SushiLib.Motor.MotorConfig.Mode;
import frc.robot.SushiLib.Sensors.BeamBreak.BeamBreakConfig;

public final class Constants {
    public static final boolean TUNING_MODE = false;

    public static class Ports {
        public static final String CANIVORE_NAME = ""; // TODO: Set later
    }

    public static class Intake {
        public static MotorConfig OUTER_MOTOR = new MotorConfig(-1); // TODO: set later
        public static MotorConfig INNER_MOTOR = new MotorConfig(-1); // TODO: set later
        public static BeamBreakConfig BEAM_BREAK = new BeamBreakConfig(-1); // TODO: set later
    }

    public static class Wrist {
        public final static MotorConfig LEFT_MOTOR = new MotorConfig(
            -1,
            20,
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.BRAKE
        );

        public final static MotorConfig RIGHT_MOTOR = new MotorConfig(
            -1,
            20,
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.BRAKE
        );

        public static final double G = 0.0;
        public static final double S = 0.0;
        public static final double V = 0.0;

        public static final double GEAR_RATIO = 1.0;
    }

    public static class Flywheels {
        public final static MotorConfig TOP_FLYWHEEL = new MotorConfig(
            -1,
            20, //?
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.COAST
        );
        public final static MotorConfig BOTTOM_FLYWHEEL = new MotorConfig(
            -1,
            20, //?
            false,
            new PIDConfig(0.0, 0.0, 0.0, 0.0),
            Mode.COAST
        );

        public static final double G = 0.0;
        public static final double S = 0.0;
        public static final double V = 0.0;

        public static final double GEAR_RATIO = 1.0;
    }

    public static class Indexer {
        public final static MotorConfig INDEXER = new MotorConfig(-1);

        public static final double GEAR_RATIO = 1.0;
    }

    public static class Climber {
        public final static MotorConfig CLIMBER = new MotorConfig(-1);
    }
}
