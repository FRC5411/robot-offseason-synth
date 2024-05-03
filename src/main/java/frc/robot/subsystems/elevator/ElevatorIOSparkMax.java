package frc.robot.subsystems.elevator;



import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Encoder;





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
        motor.setIdleMode(ElevatorConstants.idleMode); 
    
        encoder.setDistancePerPulse(ElevatorConstants.conversionFactor);

        motor.burnFlash();
    }
    
    public double getEncoderPos(){ 
       return linearEncoder.getDistance();
    }

    public void updateInputs(ElevtorIOInputs inputs){   
        inputs.currentOutput = arm.getAppliedOutput() * arm.getBusVoltage(); 
        inputs.encoderPosRads = getEncoderPos(); 
        inputs.velocity = linearEncoder.getRate();   
        inputs.temperature = arm.getMotorTemperature();
    
    } 

    public void setManualArm(double volts){  
        arm.setVoltage(MathUtil.clamp(volts,-12,12)); 
    } 

   

}
