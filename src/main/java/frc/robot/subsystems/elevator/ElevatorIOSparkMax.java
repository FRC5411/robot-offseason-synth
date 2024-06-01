package frc.robot.subsystems.elevator;



import java.util.Timer;
import java.util.TimerTask;

import com.revrobotics.CANSparkMax;
//import com.ctre.phoenix6.signals.NeutralModeValue;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;

import edu.wpi.first.wpilibj.Encoder;

import frc.robot.subsystems.elevator.ElevatorConstants.Safety;
//import frc.robot.subsystems.elevator.ElevatorConstants.SparkMax;




public class ElevatorIOSparkMax implements ElevatorIO {  

    private CANSparkMax arm; 
    private Encoder encoder; 
    private double lastVelocity;
    private double acceleration;
    


    public ElevatorIOSparkMax(){  
        this.arm = new CANSparkMax(ElevatorConstants.armID, MotorType.kBrushless); 
        this.encoder = new Encoder(ElevatorConstants.encoderPorts[0],ElevatorConstants.encoderPorts[1]); 
        
        this.arm.setInverted(ElevatorConstants.inverted); 
        
        if (Safety.idleMode == "brake"){ 
            this.arm.setIdleMode(IdleMode.kBrake);
        }else { 
            this.arm.setIdleMode(IdleMode.kCoast);
        } 
        
        this.acceleration = 0;  
        this.lastVelocity = 0;
        long sampleRate = 1000;

        Timer timer = new Timer();  

        timer.scheduleAtFixedRate( 
            new TimerTask(){ 
                @Override  
                public void run(){ 
                   acceleration = (getVelocity() - lastVelocity) / sampleRate;  
                   lastVelocity = getVelocity();
                }
            }, 
            sampleRate, 
            0
        );
            
        this.encoder.setDistancePerPulse(ElevatorConstants.pulsesToMeters);
    } 
     
    private double getVelocity(){ 
       return encoder.getRate();
    }
    
    @Override
    public void updateInputs(ElevatorIOInputs inputs){   
        inputs.currentOutput = arm.getAppliedOutput() * arm.getBusVoltage(); 
        inputs.encoderPosMeters = encoder.getDistance(); 
        inputs.velocity = getVelocity();   
        inputs.temperature = arm.getMotorTemperature(); 
        inputs.acclerationMetersPerSec = this.acceleration; 
        lastVelocity = inputs.velocity;
    
    } 
    @Override
    public void setManualArm(double volts){  
        arm.setVoltage(MathUtil.clamp(volts,Safety.outputMin,Safety.outputMax)); 
    } 

   

}
