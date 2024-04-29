package frc.robot.managers;

import frc.robot.managers.ManagerConstants.ClimbStates;
import frc.robot.managers.ManagerConstants.IndexerStates;
import frc.robot.managers.ManagerConstants.IntakeStates;
import frc.robot.managers.ManagerConstants.LauncherStates;
import frc.robot.managers.ManagerConstants.SwerveStates;
import frc.robot.subsystems.climb.ClimbSubsystem;
import frc.robot.subsystems.intake.IntakeSubsystem;
import frc.robot.subsystems.shooter.ShooterSubsystem;
import frc.robot.subsystems.shooter.indexer.IndexerSubsystem;
import frc.robot.subsystems.shooter.launcher.LauncherSubsystem;
import frc.robot.subsystems.swerve.SwerveSubsystem;

public class StateManager {
    protected static StateManager instance;

    protected static SwerveStates swerveState;
    protected static ClimbStates climbState;
    protected static IntakeStates intakeState;
    protected static LauncherStates launcherState;
    protected static IndexerStates indexerState;

    protected static SwerveSubsystem swerveSubsystem;
    protected static ClimbSubsystem climbSubsystem;
    protected static IntakeSubsystem intakeSubsystem;
    protected static LauncherSubsystem launcherSubsystem;
    protected static IndexerSubsystem indexerSubsystem;
    protected static ShooterSubsystem shooterSubsystem;

    public static synchronized StateManager getInstance() {
        if (instance == null) {
            instance = new StateManager();
            initStates();
        }
        return instance;
    }

    public static void initStates() {
        swerveState = SwerveStates.STOPPED;
        climbState = ClimbStates.STOPPED;
        intakeState = IntakeStates.STOPPED;
        launcherState = LauncherStates.STOPPED;
        indexerState = IndexerStates.STOPPED;
    }

    public static void initSubsystems(SwerveSubsystem swerve, ClimbSubsystem climb, IntakeSubsystem intake, LauncherSubsystem launcher, IndexerSubsystem indexer, ShooterSubsystem shooter) {
        swerveSubsystem = swerve;
        climbSubsystem = climb;
        intakeSubsystem = intake;
        launcherSubsystem = launcher;
        indexerSubsystem = indexer;
        shooterSubsystem = shooter;
    }

    // Getter Methods
    public SwerveStates getSwerveState() {
        return swerveState;
    }

    public ClimbStates getClimbState() {
        return climbState;
    }

    public IntakeStates getIntakeState() {
        return intakeState;
    }

    public LauncherStates getLauncherState() {
        return launcherState;
    }

    public IndexerStates getIndexerState() {
        return indexerState;
    }

    // Setter Methods
    public void setSwerveState(SwerveStates state) {
        swerveState = state;
    }

    public void setClimbState(ClimbStates state) {
        climbState = state;
    }

    public void setIntakeState(IntakeStates state) {
        intakeState = state;
    }

    public void setLauncherState(LauncherStates state) {
        launcherState = state;
    }

    public void setIndexerState(IndexerStates state) {
        indexerState = state;
    }
}
