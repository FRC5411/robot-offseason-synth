package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.AutoLog;

public interface ElevatorIO {

    @AutoLog
    public class ElevatorIOInputs {
        double currentOutput = 0;
        double velocityMeters = 0;
        double encoderPosMeters = 0;
        double acclerationMetersPerSec = 0;
        double temperature = 0;
    }

    public default void setManualArm(double volts) {
    }

    public default void updateInputs(ElevatorIOInputs inputs) {
    }

}
