package frc.robot.subsystems.elevator;

public class ElevatorConstants {

    public static final int armID = 0;
    public static final boolean inverted = false;
    public static final double gearRatio = 0.77;
    public static final int[] encoderPorts = { 5, 7 };
    public static final double rotationsToMeters = 0.66; // Customize for TalonFX
    public static final double pulsesToMeters = 0.66; // Customize for SparkMax

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
        public static final String idleMode = "brake";
    }

    class Simulation {
        public static final double carriageMass = 15;
        public static final double drumRadius = 3;
        public static final double metersArmLength = 2.5;
        public static final double maxHeightMeters = 225;
        public static final double minHeightMeters = -30;
    }

    class Setpoints {
        public static double FLAT = 0;
    }

}
