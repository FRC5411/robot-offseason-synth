package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.AutoLog;

public interface ElevatorIO { 
    @AutoLog
    public class ElevtorIOInputs{ 
        double currentOutput = 0; 
        double velocity = 0; 
        double encoderPos = 0;  
        double temperature = 0;
    }

    public default void setManualArm(double volts){}  

    
    public default void updateInputs(ElevtorIOInputs inputs){}  

    

}
