package frc.robot.subsystems;

import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.Follower;
import com.ctre.phoenix6.controls.VelocityDutyCycle;
import com.ctre.phoenix6.hardware.TalonFX;
import com.playingwithfusion.CANVenom.ControlMode;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.SushiLib.Motor.MotorHelper;
import frc.robot.SushiLib.SmartDashboard.PIDTuning;
import frc.robot.SushiLib.SmartDashboard.TunableNumber;

public class Flywheels extends SubsystemBase {

    private final TalonFX topFlywheel;
    private final TalonFX bottomFlywheel;

    private static Flywheels instance;

    private static PIDTuning pid;
    private static ArmFeedforward ff;

    private static TunableNumber setpoint;

    private VelocityDutyCycle velocityPid;

    public static Flywheels getInstance() {
        if (instance == null) {
            instance = new Flywheels();
        }
        return instance;
    }

    private Flywheels() {
        topFlywheel = Constants.Flywheels.TOP_FLYWHEEL.createTalon();
        bottomFlywheel = Constants.Flywheels.BOTTOM_FLYWHEEL.createTalon();

        MotorHelper.setConversionFactor(topFlywheel, 0.0);
        MotorHelper.setConversionFactor(bottomFlywheel, 0.0);

        topFlywheel.setControl(new Follower(bottomFlywheel.getDeviceID(), false));
        
        pid = Constants.Flywheels.TOP_FLYWHEEL.genPIDTuning("top flywheel", Constants.TUNING_MODE);

        ff = new ArmFeedforward(
            Constants.Flywheels.S, 
            Constants.Flywheels.G, 
            Constants.Flywheels.V
        );

        setpoint = new TunableNumber("flywheel setpoint", 0, Constants.TUNING_MODE);

        velocityPid = new VelocityDutyCycle(0);
    }

    public boolean atSetpoint(double desiredVelocity) {
        return (Math.abs(Math.abs(topFlywheel.getVelocity().getValueAsDouble()) - Math.abs(desiredVelocity)) < 0.1);
    }

    public Command stopFlywheel() {
        return runOnce(() -> {
            topFlywheel.set(0);
        });
    }

    public Command moveFlywheel(double velocity) {
        return run(() -> {
            setpoint.setDefault(velocity);
        }).until(() -> atSetpoint(velocity));
    }

    public void periodic() {
        SmartDashboard.putNumber("top flywheel", topFlywheel.getVelocity().getValueAsDouble());
        
        pid.updatePID(topFlywheel);

        double velocity = setpoint.get();
        topFlywheel.setControl(velocityPid.withVelocity(velocity));
    }
}
