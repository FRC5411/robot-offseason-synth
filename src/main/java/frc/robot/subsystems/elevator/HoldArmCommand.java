package frc.robot.subsystems.elevator;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.elevator.ElevatorConstants.FF;

public class HoldArmCommand extends Command {
    private Elevator elevator;
    private ElevatorFeedforward feedForward;

    public HoldArmCommand(Elevator elevator) {
        this.elevator = elevator;

        feedForward = new ElevatorFeedforward(
                FF.armS,
                FF.armG,
                FF.armV,
                FF.armA);

        addRequirements(elevator);
    }

    @Override
    public void execute() {
        double voltageOutput = feedForward.calculate(
                elevator.getVelocity(),
                elevator.getAcceleration());
        elevator.setManualArm(voltageOutput);
    }

    @Override
    public void end(boolean interrupted) {
        elevator.setManualArm(0);
    }
}
