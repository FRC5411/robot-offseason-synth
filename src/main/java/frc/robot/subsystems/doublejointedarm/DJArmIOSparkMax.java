package frc.robot.subsystems.doublejointedarm;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.subsystems.doublejointedarm.DJArmConstants.Safety;
import frc.robot.subsystems.doublejointedarm.DJArmConstants.Sparkmax;

public class DJArmIOSparkMax implements DJArmIO {

    private CANSparkMax innerMotor, outerMotor;

    private Encoder innerEncoder, outerEncoder;

    public DJArmIOSparkMax() {

        // Instantiation

        innerMotor = new CANSparkMax(Sparkmax.innerArmID, MotorType.kBrushless);
        outerMotor = new CANSparkMax(Sparkmax.outerArmID, MotorType.kBrushless);

        innerEncoder = new Encoder(DJArmConstants.innerEncoderPins[0], DJArmConstants.innerEncoderPins[1]);
        outerEncoder = new Encoder(DJArmConstants.outerEncoderPins[0], DJArmConstants.outerEncoderPins[1]);

        // Modification

        innerMotor.setInverted(DJArmConstants.isInnerInverted);
        outerMotor.setInverted(DJArmConstants.isOuterInverted);

        if (Safety.idleMode == "brake") {
            innerMotor.setIdleMode(IdleMode.kBrake);
            outerMotor.setIdleMode(IdleMode.kBrake);
        } else {
            innerMotor.setIdleMode(IdleMode.kCoast);
            outerMotor.setIdleMode(IdleMode.kCoast);
        }

        innerEncoder.setDistancePerPulse(DJArmConstants.pulseToRadians);
        outerEncoder.setDistancePerPulse(DJArmConstants.pulseToRadians);

    }

    @Override
    public void setManualInnerJoint(double voltage) {
        innerMotor.setVoltage(MathUtil.clamp(voltage, -Safety.absoluteMaxOutPut, Safety.absoluteMaxOutPut));
    }

    @Override
    public void setManualOuterJoint(double voltage) {
        outerMotor.setVoltage(MathUtil.clamp(voltage, -Safety.absoluteMaxOutPut, Safety.absoluteMaxOutPut));
    }

    @Override
    public void updateInputs(DJArmIOInputs inputs) {
        inputs.temperatureIN = innerMotor.getMotorTemperature();
        inputs.outputIN = innerMotor.getAppliedOutput();
        inputs.angleRadsIN = innerEncoder.getDistance();
        inputs.velocityRadsIN = innerEncoder.getRate();

        inputs.temperatureOUT = outerMotor.getMotorTemperature();
        inputs.outputOUT = outerMotor.getAppliedOutput();
        inputs.angleRadsOUT = outerEncoder.getDistance();
        inputs.velocityRadsOUT = outerEncoder.getRate();
    }

}
