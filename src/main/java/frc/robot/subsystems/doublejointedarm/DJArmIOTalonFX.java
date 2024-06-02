package frc.robot.subsystems.doublejointedarm;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Encoder;
import frc.robot.subsystems.doublejointedarm.DJArmConstants.Safety;
import frc.robot.subsystems.doublejointedarm.DJArmConstants.TalonFx;

public class DJArmIOTalonFX implements DJArmIO {

    private TalonFX innerMotor, outerMotor;

    private Encoder innerEncoder, outerEncoder;

    public DJArmIOTalonFX() {

        innerMotor = new TalonFX(TalonFx.innerArmID);
        outerMotor = new TalonFX(TalonFx.outerArmID);

        innerEncoder = new Encoder(DJArmConstants.innerEncoderPins[0], DJArmConstants.innerEncoderPins[1]);
        outerEncoder = new Encoder(DJArmConstants.outerEncoderPins[0], DJArmConstants.outerEncoderPins[1]);

        innerMotor.setInverted(false);
        outerMotor.setInverted(false);

        if (Safety.idleMode == "brake") {
            innerMotor.setNeutralMode(NeutralModeValue.Brake);
            outerMotor.setNeutralMode(NeutralModeValue.Brake);
        } else {
            innerMotor.setNeutralMode(NeutralModeValue.Coast);
            outerMotor.setNeutralMode(NeutralModeValue.Coast);
        }

        innerEncoder.setDistancePerPulse(DJArmConstants.pulseToRadians);
        outerEncoder.setDistancePerPulse(DJArmConstants.pulseToRadians);

    }

    @Override
    public void setManualInnerJoint(double volts) {
        innerMotor.setVoltage(MathUtil.clamp(volts, -Safety.absoluteMaxOutPut, Safety.absoluteMaxOutPut));
    }

    @Override
    public void setManualOuterJoint(double volts) {
        outerMotor.setVoltage(MathUtil.clamp(volts, -Safety.absoluteMaxOutPut, Safety.absoluteMaxOutPut));
    }

    @Override
    public void updateInputs(DJArmIOInputs inputs) {
        // ----------------------------------------------\\
        StatusSignal<Double> temperatureSignalIN,
                outputSignalIN,
                temperatureSignalOUT,
                outputSignalOUT;

        // ----------------------------------------------\\

        temperatureSignalIN = innerMotor.getDeviceTemp();
        temperatureSignalOUT = outerMotor.getDeviceTemp();

        outputSignalIN = innerMotor.getClosedLoopOutput();
        outputSignalOUT = outerMotor.getClosedLoopOutput();

        // ----------------------------------------------\\

        inputs.angleRadsIN = innerEncoder.getDistance();
        inputs.velocityRadsIN = innerEncoder.getRate();
        inputs.outputIN = outputSignalIN.getValueAsDouble();
        inputs.temperatureIN = temperatureSignalIN.getValueAsDouble();

        inputs.angleRadsOUT = outerEncoder.getDistance();
        inputs.velocityRadsOUT = outerEncoder.getRate();
        inputs.outputOUT = outputSignalOUT.getValueAsDouble();
        inputs.temperatureOUT = temperatureSignalOUT.getValueAsDouble();

    }

}
