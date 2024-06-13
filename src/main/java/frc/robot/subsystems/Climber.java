package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
    
    private final CANSparkMax climber;

    private static Climber instance;

    public static Climber getInstance() {
        if (instance == null) {
            instance = new Climber();
        }
        return instance;
    }

    public Climber() {
        climber = Constants.Climber.CLIMBER.createSparkMax();
    }

    @Override
    public void periodic() {}

    public Command moveClimber() {
        return runOnce(() -> {
            climber.set(0.2);
        });
    }
    
    public Command reverseClimber() {
        return runOnce(() -> {
            climber.set(-0.2);
        });
    }

    public Command stopClimber() {
        return runOnce(() -> {
            climber.set(0);
        });
    }
}


