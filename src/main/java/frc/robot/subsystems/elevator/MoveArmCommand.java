package frc.robot.subsystems.elevator;


import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.elevator.ElevatorConstants.ProfiledPID;


public class MoveArmCommand extends Command {

    private Elevator elevator;
    private double setpoint;
    private ProfiledPIDController pidControl;

    public MoveArmCommand(Elevator elevator, double setpoint) {

        this.elevator = elevator;
        this.setpoint = setpoint;

        pidControl = new ProfiledPIDController(
                ProfiledPID.armP,
                ProfiledPID.armI,
                ProfiledPID.armD,
                new TrapezoidProfile.Constraints(ProfiledPID.velProf, ProfiledPID.accelProf));

        addRequirements(elevator);

    }

    @Override
    public void initialize() {
        pidControl.setTolerance(ProfiledPID.tolerance);
        pidControl.reset(elevator.getMeterPos());
    }

    @Override
    public void execute() {
        double voltageOutput = pidControl.calculate(elevator.getMeterPos(), setpoint);
        elevator.setManualArm(voltageOutput);
    }

    @Override
    public void end(boolean interrupted) {

        elevator.setManualArm(0);

    }

    @Override
    public boolean isFinished() {

        return false;

    }

}
