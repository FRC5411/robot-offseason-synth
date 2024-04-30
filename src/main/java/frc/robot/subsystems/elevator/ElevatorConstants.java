package frc.robot.subsystems.elevator;

import com.revrobotics.CANSparkBase.IdleMode;

public class ElevatorConstants {
    public static final double armP = 1.0;  
    public static final double armI = 0.0;  
    public static final double armD = 0.0;   
    public static final double armFF = 0.5;
    public static final int armID = 0;
    public static final double conversionFactor = (2 * Math.PI) * 4096;; 
    public static final double tolerance = 4.0; 
    public static final double outputMax = 12; 
    public static final double outputMin = -12; 
    public static final boolean inverted = false; 
    public static final double boundaryTop = 1.7; 
    public static final double boundaryBottom = 0; 
    public static final IdleMode idleMode = IdleMode.kBrake; 
    public static final int[] encoderPorts = {5,7}; 
    public static final double gearRatio = 0.77;  
    public static final double kiloArmMass = 15; 
    public static final double metersArmLength = 2.5;  
    public static final double maxAngle = 225; 
    public static final double minAngle = -30; 
}
