package frc.robot.subsystems.elevator;

import com.revrobotics.CANSparkBase.IdleMode;

public class ElevatorConstants {
    public static final double armP = 1.0;  
    public static final double armI = 0.0;  
    public static final double armD = 0.0;   
    public static final double armFF = 0.5;
    public static final int armID = 0;
    public static final double distPerRotation = 0.5; 
    public static final double tolerance = 4.0; 
    public static final double outputMax = 12; 
    public static final double outputMin = -12; 
    public static final boolean inverted = false; 
    public static final double boundaryTop = 1.7; 
    public static final double boundaryBottom = 0; 
    public static final IdleMode idleMode = IdleMode.kBrake;
}
