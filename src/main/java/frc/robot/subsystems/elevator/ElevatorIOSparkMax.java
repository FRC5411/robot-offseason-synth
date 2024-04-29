package frc.robot.subsystems.elevator;



import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;





public class ElevatorIOSparkMax implements ElevatorIO {  

    private CANSparkMax arm = new CANSparkMax(ElevatorConstants.armID, MotorType.kBrushless); 
    private RelativeEncoder linearEncoder = arm.getEncoder(); 
    


    public ElevatorIOSparkMax(){    
        linearEncoder.setPositionConversionFactor(ElevatorConstants.distPerRotation); 
        linearEncoder.setVelocityConversionFactor(ElevatorConstants.distPerRotation / 60);  
        linearEncoder.setPosition(0); 
        linearEncoder.setInverted(ElevatorConstants.inverted); 
        
        ensureHardwareSafety(arm, linearEncoder);
    } 


    private void ensureHardwareSafety(CANSparkMax motor, RelativeEncoder encoder){ 
        motor.clearFaults(); 
        motor.restoreFactoryDefaults(); 
        motor.setInverted(ElevatorConstants.inverted); 
        motor.setIdleMode(ElevatorConstants.idleMode); 
    
        encoder.setPositionConversionFactor(ElevatorConstants.distPerRotation);  

        motor.burnFlash();
    }
    

    public void updateInputs(ElevtorIOInputs inputs){   
        inputs.currentOutput = arm.getAppliedOutput() * arm.getBusVoltage(); 
        inputs.encoderPos = linearEncoder.getPosition();
        inputs.velocity = linearEncoder.getVelocity();   
        inputs.temperature = arm.getMotorTemperature();
    
    } 

    public void setManualArm(double volts){  
        arm.setVoltage(MathUtil.clamp(volts,-12,12)); 
    } 

   

}
