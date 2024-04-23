package frc.robot.subsystems.elevator;

public interface Elevator {
    public class ElevatorIOInputs { 
       double encoderPos = 0; 
       double currentOutput = 0;  
       double velocity; 
    } 

    public default void setArm(double speed){} 

    public default void updateInputs(ElevatorIOInputs inputs){}  

    

}
