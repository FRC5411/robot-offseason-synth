package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.AutoLog;

public interface ElevatorIO {  
    
    @AutoLog
    public class ElevatorIOInputs{ 
        double currentOutput = 0; 
        double velocity = 0; 
        double encoderPosRads = 0;  
        double temperature = 0;
    }

    public default void setManualArm(double volts){}  

    public default void updateInputs(ElevatorIOInputs inputs){}  
    
    public default double getEncoderPos(){return 0;} 

    public default double getVelocity(){return 0;}
    

}
