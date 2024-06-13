package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Indexer extends SubsystemBase{ 

    private final CANSparkMax indexer;

    private static Indexer instance;
w
    public static Indexer getInstance() {
        if (instance == null) {
            instance = new Indexer();
        }
        return instance;
    }
    
    private Indexer() {
        indexer = Constants.Indexer.INDEXER.createSparkMax();
    }
    
    public Command moveIndexer() {
        return runOnce(() -> {
            indexer.set(Constants.Indexer.DEFAULT_SPEED);
        });
    }

    public Command reverseIndexer() {
        return runOnce(() -> {
            indexer.set(Constants.Indexer.DEFAULT_SPEED * (-1));
        });
    }

    public Command stopIndexer() {
        return runOnce(() -> {
            indexer.set(0);
        });
    }
}
