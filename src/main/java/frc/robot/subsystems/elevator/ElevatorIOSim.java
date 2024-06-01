package frc.robot.subsystems.elevator;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.system.plant.DCMotor;
//import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import frc.robot.subsystems.elevator.ElevatorConstants.Simulation;

public class ElevatorIOSim implements ElevatorIO { 

    private Encoder realEncoder = new Encoder(ElevatorConstants.encoderPorts[0],ElevatorConstants.encoderPorts[1]);
    private EncoderSim encoderSim = new EncoderSim(realEncoder);  

    private PWMSparkMax pseudoMotor = new PWMSparkMax(0);  

    private double acceleration; 
    private double lastVelocity;

    private ElevatorSim armSim = new ElevatorSim(
        DCMotor.getNEO(1), 
        ElevatorConstants.gearRatio, 
        Simulation.carriageMass,
        Simulation.drumRadius, 
        Simulation.minHeightMeters, 
        Simulation.maxHeightMeters, 
        true, 
        0); 
    
    
    
    public ElevatorIOSim(){ 
        realEncoder.setDistancePerPulse(1); 

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
    } 
    
    private double getVelocity(){ 
        return armSim.getVelocityMetersPerSecond(); 
    }

    public void updateInputs(ElevatorIOInputs inputs){ 
        armSim.update(0.02); 
        inputs.velocity = getVelocity();
        inputs.encoderPosMeters = encoderSim.getDistance(); 
        inputs.currentOutput = armSim.getCurrentDrawAmps();   
        inputs.accleration = this.acceleration;
        
    } 

    public void setManualArm(double volts){ 
        pseudoMotor.setVoltage(MathUtil.clamp(volts,-12,12));
    }

}
