package frc.robot.subsystems.elevator;



import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Encoder;

import frc.robot.subsystems.elevator.ElevatorConstants.Safety;




public class ElevatorIOSparkMax implements ElevatorIO {  

    private CANSparkMax arm = new CANSparkMax(ElevatorConstants.armID, MotorType.kBrushless); 
    private Encoder linearEncoder = new Encoder(ElevatorConstants.encoderPorts[0],ElevatorConstants.encoderPorts[1]); 
    


    public ElevatorIOSparkMax(){    
        ensureHardwareSafety(arm, linearEncoder);
    } 


    private void ensureHardwareSafety(CANSparkMax motor, Encoder encoder){ 
        motor.clearFaults();  
        motor.restoreFactoryDefaults(); 
        motor.setInverted(ElevatorConstants.inverted); 
        motor.setIdleMode(Safety.idleMode); 
    
        encoder.setDistancePerPulse(ElevatorConstants.conversionFactor);

        motor.burnFlash();
    }
    
    @Override
    public double getEncoderPos(){ 
       return linearEncoder.getDistance();
    }
    
    @Override
    public double getVelocity(){ 
       return linearEncoder.getRate();
    }
    
    @Override
    public void updateInputs(ElevatorIOInputs inputs){   
        inputs.currentOutput = arm.getAppliedOutput() * arm.getBusVoltage(); 
        inputs.encoderPosRads = linearEncoder.getDistance(); 
        inputs.velocity = linearEncoder.getRate();   
        inputs.temperature = arm.getMotorTemperature();
    
    } 

    public void setManualArm(double volts){  
        arm.setVoltage(MathUtil.clamp(volts,Safety.outputMin,Safety.outputMax)); 
    } 

   

}
