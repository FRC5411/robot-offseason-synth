package frc.robot.managers;

public class ManagerConstants {
    public static class k{
        
    }

    public enum SwerveStates {
        STOPPED,
        IDLE,
        FIELDORI,
        ROBOTORI,
        XLOCK
    }

    public enum ClimbStates {
        STOPPED,
        IDLE, 
        RETURNING,
        GRABBING,
        HOLD
    }

    public enum IntakeStates {
        STOPPED,
        IDLE,
        INTAKING,
        OUTTAKING
    }

    public enum LauncherStates {
        STOPPED,
        IDLE,
        EJECT,
        SHOOT
    }

    public enum IndexerStates {
        STOPPED,
        IDLE,
        STOWING,
        OUTDEXING,
        INDEXING
    }
}
