package frc.robot.subsystems.elevator;
//import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.MathUtil;

import frc.robot.subsystems.elevator.ElevatorConstants.Safety;

public class ElevatorIOTalonFX implements ElevatorIO {  

    private TalonFX arm;      
    

    public ElevatorIOTalonFX(){  

        this.arm = new TalonFX(ElevatorConstants.armID);   
        
        this.arm.setInverted(false); 

        if (Safety.idleMode == "brake"){ 
            this.arm.setNeutralMode(NeutralModeValue.Brake);
        }else { 
            this.arm.setNeutralMode(NeutralModeValue.Coast);
        } 

    } 
    
    @Override
    public void updateInputs(ElevatorIOInputs inputs){  

       StatusSignal<Double> positionSignal,  
                            velocitySignal, 
                            tempSignal, 
                            outputSignal, 
                            accelerationSignal;  

       positionSignal = this.arm.getPosition(); 
       velocitySignal = this.arm.getVelocity(); 
       tempSignal = this.arm.getDeviceTemp(); 
       outputSignal = this.arm.getClosedLoopOutput();   
       accelerationSignal = this.arm.getAcceleration(); 
       
       inputs.encoderPosMeters = positionSignal.getValueAsDouble() * ElevatorConstants.rotationsToMeters; 
       inputs.velocity = velocitySignal.getValueAsDouble() * ElevatorConstants.rotationsToMeters; 
       inputs.temperature = tempSignal.getValueAsDouble(); 
       inputs.currentOutput = outputSignal.getValueAsDouble(); 
       inputs.acclerationMetersPerSec = accelerationSignal.getValueAsDouble() * ElevatorConstants.rotationsToMeters;  

    } 


    @Override 
    public void setManualArm(double volts){ 
       this.arm.setVoltage(MathUtil.clamp(volts, Safety.outputMin, Safety.outputMax));
    }

    

    

}
