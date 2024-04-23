package frc.robot.subsystems.elevator;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DutyCycleEncoder;


public class ElevatorIOSparkMax implements Elevator {  

    /*"Pre-made" Constants */ 
    int[] pid = {1,0,0}; 
    int encoderID = 5; 
    int armID = 0;
    
    ///////////////////////// 

    private CANSparkMax arm = new CANSparkMax(armID, MotorType.kBrushless); 
    private DutyCycleEncoder encoder; 
    private PIDController controller;

    public void updateInputs(ElevatorIOInputs inputs){ 
    } 

}
