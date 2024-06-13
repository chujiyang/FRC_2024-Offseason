package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase{

    private final CANSparkMax indexer;

    private static Indexer instance;

    public static Indexer getInstance() {
        if (instance == null) {
            instance = new Indexer();
        }
        return instance;
    }
    
    private Indexer() {
        indexer = Constants.Indexer.INDEXER.createSparkMax();
    }

    @Override
    public void periodic() {}
    
    public Command moveIndexer() {
        return runOnce(() -> {
            indexer.set(0.8);
        });
    }

    public Command reverseIndexer() {
        return runOnce(() -> {
            indexer.set(-0.8);
        });
    }

    public Command stopIndexer() {
        return runOnce(() -> {
            indexer.set(0);
        });
    }
}
