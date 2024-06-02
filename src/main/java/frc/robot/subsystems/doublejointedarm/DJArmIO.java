package frc.robot.subsystems.doublejointedarm;

import org.littletonrobotics.junction.AutoLog;

public interface DJArmIO {
    @AutoLog
    public class DJArmIOInputs {

        // Inner Joint

        double angleRadsIN = 0;
        double velocityRadsIN = 0;
        double temperatureIN = 0;
        double outputIN = 0;

        // Outer Joint

        double angleRadsOUT = 0;
        double velocityRadsOUT = 0;
        double temperatureOUT = 0;
        double outputOUT = 0;

    }

    public default void updateInputs(DJArmIOInputs inputs) {
    }

    // Moves the inner most arm using voltage
    public default void setManualInnerJoint(double volts) {
    }

    // Moves the outer most arm using voltage
    public default void setManualOuterJoint(double volts) {
    }

}
