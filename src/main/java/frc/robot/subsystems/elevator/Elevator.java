package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Elevator extends SubsystemBase {
    ElevatorIO elevatorIO;
    ElevatorIOInputsAutoLogged inputs = new ElevatorIOInputsAutoLogged();

    public Elevator(ElevatorIO io) {
        this.elevatorIO = io;
    }

    public void setManualArm(double volts) {
        elevatorIO.setManualArm(volts);
    }

    public double getMeterPos() {
        return inputs.encoderPosMeters;
    }
    
    // Elevator Feed Forward
    public double getVelocity() {
        return inputs.velocityMeters;
    }

    public double getAcceleration() {
        return inputs.acclerationMetersPerSec;
    }
    // 

    @Override
    public void periodic() {
        elevatorIO.updateInputs(inputs);
        Logger.processInputs("Elevator/", inputs);
    }
}
