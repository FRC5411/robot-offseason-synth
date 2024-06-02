package frc.robot.subsystems.doublejointedarm;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DJArm extends SubsystemBase {
    private DJArmIO io;
    private final DJArmIOInputsAutoLogged inputs = new DJArmIOInputsAutoLogged();

    public DJArm(DJArmIO io) {
        this.io = io;
    }

    public void setInnerJoint(double volts) {
        io.setManualInnerJoint(volts);
    }

    public void setOuterJoint(double volts) {
        io.setManualOuterJoint(volts);
    }

    public Rotation2d getInnerArmAngle() {
        return Rotation2d.fromRadians(inputs.angleRadsIN);
    }

    public Rotation2d getOuterArmAngle() {
        return Rotation2d.fromRadians(inputs.angleRadsOUT);
    }

    public double getInnerVelocity() {
        return inputs.velocityRadsIN;
    }

    public double getOuterVelocity() {
        return inputs.velocityRadsOUT;
    } 

}
