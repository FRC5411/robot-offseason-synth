package frc.robot.subsystems.elevator;
//import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.TalonFXConfigurator;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.util.Units;
import frc.robot.subsystems.elevator.ElevatorConstants.Safety;

public class ElevatorIOTalonFX implements ElevatorIO {
    private TalonFX arm;      
    

    public ElevatorIOTalonFX(){ 
        this.arm = new TalonFX(ElevatorConstants.armID);   
        ensureHardwareSafety();
    } 

    private void ensureHardwareSafety(){  
        TalonFXConfigurator configs = this.arm.getConfigurator(); 
        configs.clearStickyFaults(); 
        configs.setPosition(0); 

        this.arm.setInverted(false); 
         

        if (Safety.idleMode == "brake"){ 
            this.arm.setNeutralMode(NeutralModeValue.Brake);
        }else { 
            this.arm.setNeutralMode(NeutralModeValue.Coast);
        }    
        
        this.arm.getPosition();
        
    }  
    
    @Override
    public void updateInputs(ElevatorIOInputs inputs){ 
       StatusSignal<Double> positionSignal,  
                            velocitySignal, 
                            tempSignal, 
                            outputSignal;  

       positionSignal = this.arm.getPosition(); 
       velocitySignal = this.arm.getVelocity(); 
       tempSignal = this.arm.getDeviceTemp(); 
       outputSignal = this.arm.getClosedLoopOutput();   

       inputs.encoderPosRads = Units.rotationsToRadians(1) * positionSignal.getValueAsDouble(); 
       inputs.velocity = Units.rotationsPerMinuteToRadiansPerSecond(1) * velocitySignal.getValueAsDouble(); 
       inputs.temperature = tempSignal.getValueAsDouble(); 
       inputs.currentOutput = outputSignal.getValueAsDouble();
    } 

    @Override
    public double getEncoderPos(){ 
       StatusSignal<Double> signal = this.arm.getPosition(); 
       return signal.getValueAsDouble() * Units.rotationsToRadians(1); 
    }  

    @Override
    public double getVelocity(){ 
       StatusSignal<Double> signal = this.arm.getVelocity(); 
       return signal.getValueAsDouble() * Units.rotationsPerMinuteToRadiansPerSecond(1); 
    }  

    @Override 
    public void setManualArm(double volts){ 
       this.arm.setVoltage(volts);
    }

    

    

}
