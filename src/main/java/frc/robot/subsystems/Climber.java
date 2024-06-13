package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
    
    private final CANSparkMax leftMotor;
    private final CANSparkMax rightMotor;

    private static Climber instance;

    public static Climber getInstance() {
        if (instance == null) {
            instance = new Climber();
        }
        return instance;
    }

    private Climber() {
        leftMotor = Constants.Climber.CLIMBER.createSparkMax();
        rightMotor = Constants.Climber.CLIMBER.createSparkMax();
    }

    public Command moveClimber() {
        return runOnce(() -> {
            leftMotor.set(Constants.Climber.DEFAULT_SPEED);
            rightMotor.set(Constants.Climber.DEFAULT_SPEED);
        });
    }
    
    public Command reverseClimber() {
        return runOnce(() -> {
            leftMotor.set(Constants.Climber.DEFAULT_SPEED * (-1));
            rightMotor.set(Constants.Climber.DEFAULT_SPEED * (-1));
        });
    }

    public Command stopClimber() {
        return runOnce(() -> {
            leftMotor.set(0);
            leftMotor.set(0);
        });
    }
}


