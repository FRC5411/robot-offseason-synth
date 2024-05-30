package frc.robot.subsystems.elevator;



import com.revrobotics.CANSparkMax;
//import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Encoder;

import frc.robot.subsystems.elevator.ElevatorConstants.Safety;
//import frc.robot.subsystems.elevator.ElevatorConstants.SparkMax;




public class ElevatorIOSparkMax implements ElevatorIO {  

    private CANSparkMax arm; 
    private Encoder encoder;
    


    public ElevatorIOSparkMax(){  
        this.arm = new CANSparkMax(ElevatorConstants.armID, MotorType.kBrushless); 
        this.encoder = new Encoder(ElevatorConstants.encoderPorts[0],ElevatorConstants.encoderPorts[1]); 
        ensureHardwareSafety();
    } 


    private void ensureHardwareSafety(){ 
        this.arm.clearFaults();  
        this.arm.restoreFactoryDefaults(); 
        this.arm.setInverted(ElevatorConstants.inverted); 
        
        if (Safety.idleMode == "brake"){ 
            this.arm.setIdleMode(IdleMode.kBrake);
        }else { 
            this.arm.setIdleMode(IdleMode.kCoast);
        }  

        this.encoder.setDistancePerPulse(Units.rotationsToRadians(1) * 4096);

        this.arm.burnFlash();
    }
    
    @Override
    public double getEncoderPos(){ 
       return encoder.getDistance();
    }
    
    @Override
    public double getVelocity(){ 
       return encoder.getRate();
    }
    
    @Override
    public void updateInputs(ElevatorIOInputs inputs){   
        inputs.currentOutput = arm.getAppliedOutput() * arm.getBusVoltage(); 
        inputs.encoderPosRads = encoder.getDistance(); 
        inputs.velocity = encoder.getRate();   
        inputs.temperature = arm.getMotorTemperature();
    
    } 
    @Override
    public void setManualArm(double volts){  
        arm.setVoltage(MathUtil.clamp(volts,Safety.outputMin,Safety.outputMax)); 
    } 

   

}
