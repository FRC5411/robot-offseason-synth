package frc.robot.subsystems.doublejointedarm;

import edu.wpi.first.math.geometry.Rotation2d;

public class DJArmConstants {

    public static double innerGearRatio = 0.55;
    public static double outerGearRatio = 0.45;

    public static int[] innerEncoderPins = { 1, 2 };
    public static int[] outerEncoderPins = { 3, 4 };

    public static double pulseToRadians = 0.9;

    public static boolean isInnerInverted = false;
    public static boolean isOuterInverted = false;

    class Simulation {
        public static double innerArmLengthMeters = 0;
        public static double innerArmMassKilos = 0;
        public static double innerArmMaxAngle = 0;
        public static double innerArmMinAngle = 0;

        public static double outerArmLengthMeters = 0;
        public static double outerArmMassKilos = 0;
        public static double outerArmMaxAngle = 0;
        public static double outerArmMinAngle = 0;
    }

    class Sparkmax {
        public static int innerArmID = 3;
        public static int outerArmID = 4;
    }

    class TalonFx {
        public static int innerArmID = 5;
        public static int outerArmID = 6;
    }

    class Safety {
        public static String idleMode = "brake";
        public static double absoluteMaxOutPut = 12;
    }

    public static double tolerance = 0.3;

    class TuningConstantsIN {

        public static double armP = 0;
        public static double armI = 0;
        public static double armD = 0;

        public static double accelerationProf = 0;
        public static double velocityProf = 0;

        public static double ffS = 0;
        public static double ffG = 0;
        public static double ffV = 0;
    }

    class TuningConstantsOUT {

        public static double armP = 0;
        public static double armI = 0;
        public static double armD = 0;

        public static double accelerationProf = 0;
        public static double velocityProf = 0;

        public static double ffS = 0;
        public static double ffG = 0;
        public static double ffV = 0;

    }

    class Setpoints {
        public static Rotation2d flatAngleIN = Rotation2d.fromDegrees(45);
        public static Rotation2d flatAngleOUT = Rotation2d.fromDegrees(30);
    }
}
