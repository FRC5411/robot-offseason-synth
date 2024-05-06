package frc.robot.subsystems.elevator;

import com.revrobotics.CANSparkBase.IdleMode;

public class ElevatorConstants {  

    public static final int armID = 0;    
    public static final boolean inverted = false;
    public static final double gearRatio = 0.77;

    public static final int[] encoderPorts = {5,7}; 
    public static final double conversionFactor = (2 * Math.PI) * 4096;

    class ProfiledPID { 
        public static final double armP = 1.0;  
        public static final double armI = 0.0;  
        public static final double armD = 0.0;   
        public static final double tolerance = 4.0; 

        public static final double accelProf = 0;
        public static final double velProf = 0;
    }
    
    class FF { 
        public static final double armS = 0; 
        public static final double armV = 0; 
        public static final double armG = 0; 
        public static final double armA = 0;
    }


    class Safety {
        public static final double outputMax = 12; 
        public static final double outputMin = -12; 
        public static final IdleMode idleMode = IdleMode.kBrake;   
    }
      
    class SIM{
        public static final double kiloArmMass = 15; 
        public static final double metersArmLength = 2.5;  
        public static final double maxAngle = 225; 
        public static final double minAngle = -30;  
    }

    class Setpoints{ 
         public static double flat = 0; 
         public static double low = 0; 
         public static double medium = 0; 
         public static double high = 0;
    }
     
    
    
     
}
