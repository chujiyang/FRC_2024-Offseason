package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheels extends SubsystemBase {

    private final TalonFX topFlywheel;
    private final TalonFX bottomFlywheel;

    private static Flywheels instance;

    public static Flywheels getInstance() {
        if (instance == null) {
            instance = new Flywheels();
        }
        return instance;
    }

    private Flywheels() {
        
    }
    
}
