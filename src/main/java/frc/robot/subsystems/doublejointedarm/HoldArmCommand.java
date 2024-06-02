package frc.robot.subsystems.doublejointedarm;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.doublejointedarm.DJArmConstants.Setpoints;
//import frc.robot.subsystems.doublejointedarm.DJArmConstants.TuningConstants;
import frc.robot.subsystems.doublejointedarm.DJArmConstants.TuningConstantsIN;
import frc.robot.subsystems.doublejointedarm.DJArmConstants.TuningConstantsOUT;

public class HoldArmCommand extends Command {
    private DJArm djArm;

    private ArmFeedforward innerFeedForward;
    private ArmFeedforward outerFeedForward;

    public HoldArmCommand(DJArm djArm) {
        this.djArm = djArm;

        innerFeedForward = new ArmFeedforward(
                TuningConstantsIN.ffS,
                TuningConstantsIN.ffG,
                TuningConstantsIN.ffV);

        outerFeedForward = new ArmFeedforward(
                TuningConstantsOUT.ffS,
                TuningConstantsOUT.ffG,
                TuningConstantsOUT.ffV);

        addRequirements(djArm);
    }

    @Override
    public void execute() {
        double innerVoltage = innerFeedForward.calculate(
                djArm.getInnerArmAngle().getRadians() - Setpoints.flatAngleIN.getRadians(), // Parrallel Angle
                djArm.getInnerVelocity() // Velocity
        );
        double outerVoltage = outerFeedForward.calculate(
                djArm.getOuterArmAngle().getRadians() - Setpoints.flatAngleOUT.getRadians(), // Parrallel Angle
                djArm.getOuterVelocity() // Velocity
        );

        djArm.setInnerJoint(innerVoltage);
        djArm.setOuterJoint(outerVoltage);
    }

    @Override
    public void end(boolean interrupted) {
        djArm.setInnerJoint(0);
        djArm.setOuterJoint(0);
    }

}
