package frc.robot.subsystems.doublejointedarm;

import java.util.function.Consumer;
import java.util.function.Supplier;

import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.doublejointedarm.DJArmConstants.TuningConstantsIN;
import frc.robot.subsystems.doublejointedarm.DJArmConstants.TuningConstantsOUT;

public class PIDToSetpoint extends Command {
    private ProfiledPIDController controller;
    private double setpoint;
    private Supplier<Double> armAngle;
    private Consumer<Double> setVolts;

    public PIDToSetpoint(DJArm djArm, double setpoint, String arm) {
        this.setpoint = setpoint;

        switch (arm) {
            case "inner":

                this.controller = new ProfiledPIDController(
                        TuningConstantsIN.armP,
                        TuningConstantsIN.armI,
                        TuningConstantsIN.armD,
                        new TrapezoidProfile.Constraints(
                                TuningConstantsIN.velocityProf,
                                TuningConstantsIN.accelerationProf));

                armAngle = () -> djArm.getInnerArmAngle().getRadians();
                setVolts = (volts) -> djArm.setInnerJoint(volts);

                break;
            case "outer":
                this.controller = new ProfiledPIDController(
                        TuningConstantsOUT.armP,
                        TuningConstantsOUT.armI,
                        TuningConstantsOUT.armD,
                        new TrapezoidProfile.Constraints(
                                TuningConstantsOUT.velocityProf,
                                TuningConstantsOUT.accelerationProf));
                armAngle = () -> djArm.getOuterArmAngle().getRadians();
                setVolts = (volts) -> djArm.setOuterJoint(volts);
                break;
        }

        this.controller.setTolerance(DJArmConstants.tolerance);
        controller.reset(armAngle.get());

        addRequirements(djArm);
    }

    @Override
    public void execute() {
        double voltageOutput = controller.calculate(armAngle.get(), setpoint);
        setVolts.accept(voltageOutput);
    }

    @Override
    public void end(boolean interrupted) {
        setVolts.accept((double) 0);
    }
}
