package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.AutoLog;

public interface Elevator { 
    @AutoLog
    public class ElevatorIOInputs { 
       double encoderPos = 0; 
       double currentOutput = 0;  
       double velocity = 0;
    } 

    public default void setManualArm(double volts){}  

    public default void setArmReference(double refMeters){}

    public default void updateInputs(ElevatorIOInputs inputs){}  

    

}
