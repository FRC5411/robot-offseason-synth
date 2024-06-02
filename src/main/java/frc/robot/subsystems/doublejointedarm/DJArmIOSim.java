package frc.robot.subsystems.doublejointedarm;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import frc.robot.subsystems.doublejointedarm.DJArmConstants.Safety;
import frc.robot.subsystems.doublejointedarm.DJArmConstants.Simulation;

public class DJArmIOSim implements DJArmIO {
    private SingleJointedArmSim innerArmSim, outerArmSim;

    private PWMSparkMax innerMotor, outerMotor;

    public DJArmIOSim() {
        innerArmSim = new SingleJointedArmSim(
                DCMotor.getNEO(1),
                DJArmConstants.innerGearRatio,
                SingleJointedArmSim.estimateMOI(Simulation.innerArmLengthMeters, Simulation.innerArmMassKilos),
                Simulation.innerArmLengthMeters,
                Units.degreesToRadians(Simulation.innerArmMinAngle),
                Units.degreesToRadians(Simulation.innerArmMaxAngle),
                true,
                Units.degreesToRadians((Simulation.innerArmMinAngle + Simulation.innerArmMaxAngle) / 2));

        outerArmSim = new SingleJointedArmSim(
                DCMotor.getNEO(1),
                DJArmConstants.outerGearRatio,
                SingleJointedArmSim.estimateMOI(Simulation.outerArmLengthMeters, Simulation.outerArmMassKilos),
                Simulation.outerArmLengthMeters,
                Units.degreesToRadians(Simulation.outerArmMinAngle),
                Units.degreesToRadians(Simulation.outerArmMaxAngle),
                true,
                Units.degreesToRadians((Simulation.outerArmMinAngle + Simulation.outerArmMaxAngle) / 2));

        innerMotor = new PWMSparkMax(1);
        outerMotor = new PWMSparkMax(2);

        innerMotor.setInverted(DJArmConstants.isInnerInverted);
        outerMotor.setInverted(DJArmConstants.isOuterInverted);

    }

    private void update() {
        innerArmSim.setInput(innerMotor.get() * RobotController.getBatteryVoltage());
        outerArmSim.setInput(outerMotor.get() * RobotController.getBatteryVoltage());

        innerArmSim.update(0.02);
        outerArmSim.update(0.02);
    }

    @Override
    public void updateInputs(DJArmIOInputs inputs) {

        update();

        inputs.angleRadsIN = innerArmSim.getAngleRads();
        inputs.angleRadsOUT = outerArmSim.getAngleRads();

        inputs.outputIN = innerArmSim.getCurrentDrawAmps();
        inputs.outputOUT = outerArmSim.getCurrentDrawAmps();

        inputs.velocityRadsIN = innerArmSim.getVelocityRadPerSec();
        inputs.velocityRadsOUT = outerArmSim.getVelocityRadPerSec();

    }

    @Override
    public void setManualInnerJoint(double volts) {
        innerMotor.setVoltage(MathUtil.clamp(volts, -Safety.absoluteMaxOutPut, Safety.absoluteMaxOutPut));
    }

    @Override
    public void setManualOuterJoint(double volts) {
        outerMotor.setVoltage(MathUtil.clamp(volts, -Safety.absoluteMaxOutPut, Safety.absoluteMaxOutPut));
    }

}
