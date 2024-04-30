package frc.robot.subsystems.elevator;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class ElevatorIOSim implements ElevatorIO {
    private Encoder realEncoder = new Encoder(ElevatorConstants.encoderPorts[0],ElevatorConstants.encoderPorts[1]);
    private PWMSparkMax pseudoMotor = new PWMSparkMax(0); 

    private SingleJointedArmSim armSim = new SingleJointedArmSim(
        DCMotor.getNEO(1), 
        ElevatorConstants.gearRatio, 
        SingleJointedArmSim.estimateMOI(ElevatorConstants.metersArmLength, ElevatorConstants.kiloArmMass),
        ElevatorConstants.metersArmLength, 
        ElevatorConstants.minAngle, 
        ElevatorConstants.maxAngle, 
        true, 
        0); 
    
    private EncoderSim encoderSim = new EncoderSim(realEncoder); 
    
    public ElevatorIOSim(){ 
        realEncoder.setDistancePerPulse(ElevatorConstants.conversionFactor);
    } 

    public void updateInputs(ElevtorIOInputs inputs){ 
        armSim.update(0.02); 
        inputs.velocity = armSim.getVelocityRadPerSec(); 
        inputs.encoderPos = encoderSim.getDistance(); 
        inputs.currentOutput = armSim.getCurrentDrawAmps(); 
    }

}
