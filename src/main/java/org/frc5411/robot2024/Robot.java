package org.frc5411.robot2024;

import org.frc5411.lib.util.Alert;
import org.frc5411.lib.util.Alert.AlertType;

import edu.wpi.first.hal.AllianceStationID;
import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.Threads;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.simulation.DriverStationSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import org.littletonrobotics.junction.LogFileUtil;
import org.littletonrobotics.junction.LoggedRobot;
import org.littletonrobotics.junction.Logger;
import org.littletonrobotics.junction.networktables.NT4Publisher;
import org.littletonrobotics.junction.wpilog.WPILOGReader;
import org.littletonrobotics.junction.wpilog.WPILOGWriter;
import org.littletonrobotics.urcl.URCL;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 *
 *
 * <h1>Robot</h1>
 *
 * <p>Utility class which defines all modes of robot's event-cycle throughout it's lifetime.
 *
 * @see RobotContainer
 */
public final class Robot extends LoggedRobot implements Serializable {
  @Serial
  private static final long serialVersionUID = 9197360083967213848L;
  private static final Alert BATTERY_VOLTAGE_ALERT = new Alert(("Battery Voltage Low"), AlertType.WARNING);

  private static volatile Robot Instance;

  private Robot() {}

  /**
   * <h1>RobotType</h1>
   * 
   * Describes the state of the robot being initialized
   */
  public enum RobotType {
    SIMULATION,
    CONCRETE,
    REPLAY
  }

  @Override
  @SuppressWarnings({"ExtractMethodRecommender", "DataFlowIssue"})
  public void robotInit() {
    Logger.recordMetadata(("ProjectName"), Metadata.MAVEN_NAME);
    Logger.recordMetadata(("BuildDate"), Metadata.BUILD_DATE);
    Logger.recordMetadata(("GitSHA"), Metadata.GIT_SHA);
    Logger.recordMetadata(("GitDate"), Metadata.GIT_DATE);
    Logger.recordMetadata(("GitBranch"), Metadata.GIT_BRANCH);
    switch (Metadata.DIRTY) {
      case 0:
        Logger.recordMetadata(("Changes"), ("Committed"));
        break;
      case 1:
        Logger.recordMetadata(("Changes"), ("Uncommitted"));
        new Alert(("GIT VCS Changes Not Committed"), AlertType.INFO);
        break;
      default:
        Logger.recordMetadata(("Changes"), ("Unknown"));
        new Alert(("GIT VCS Changes Unknown"), AlertType.INFO);
        break;
    }
    switch (Constants.Robot.TYPE) {
      case CONCRETE:
        if (Constants.Logging.LOGGING_ENABLED) {
          String Folder = Constants.Logging.LOGGING_DEPOSIT.get(RobotType.CONCRETE);
          Logger.addDataReceiver(new WPILOGWriter(Folder));
        }  
        Logger.addDataReceiver(new NT4Publisher());
        break;
      case SIMULATION:
        Logger.addDataReceiver(new NT4Publisher());
        break;
      case REPLAY:
        setUseTiming((false));
        String Path = LogFileUtil.findReplayLog();
        Logger.setReplaySource(new WPILOGReader(Path));
        Logger.addDataReceiver(new WPILOGWriter(LogFileUtil.addPathSuffix(Path, ("_sim"))));
        break;
    }
    Logger.start();
    Map<String, Integer> Map = new HashMap<>();
    BiConsumer<Command, Boolean> CommandLogger =
        (Command Command, Boolean Active) -> {
          String Name = Command.getName();
          int Occurrences = Map.getOrDefault(Name, (0)) + (Active ? 1 : -1);
          Map.put(Name, Occurrences);
          Logger.recordOutput(
              "CommandsUnique/" + Name + "_" + Integer.toHexString(Command.hashCode()), Active);
          Logger.recordOutput("CommandsAll/" + Name, Occurrences > (0));
        };
    CommandScheduler.getInstance()
        .onCommandInitialize(
            (Command Command) -> CommandLogger.accept(Command, (true)));
    CommandScheduler.getInstance()
        .onCommandFinish(
            (Command Command) -> CommandLogger.accept(Command, (false)));
    CommandScheduler.getInstance()
        .onCommandInterrupt(
            (Command Command) -> CommandLogger.accept(Command, (false)));
    if (Constants.Robot.TYPE == RobotType.SIMULATION) {
      DriverStationSim.setAllianceStationId(AllianceStationID.Red1);
    } 
    DataLogManager.start();
    Logger.registerURCL(URCL.startExternal());
    RobotController.setBrownoutVoltage((6d)); 
    Shuffleboard.startRecording();
    DriverStation.silenceJoystickConnectionWarning((true));
    PortForwarder.add((5800), ("photonvision.local"), (5800));
  }

  @Override
  public void robotPeriodic() {
    Threads.setCurrentThreadPriority((true), (99));
    BATTERY_VOLTAGE_ALERT.set(RobotController.getBatteryVoltage() < (11.5d));
    CommandScheduler.getInstance().run();
    SmartDashboard.updateValues();
    Logger.recordOutput(("Match Time"), DriverStation.getMatchTime());
    Threads.setCurrentThreadPriority((true), (10));
  }

  /**
   * Prevents potential serialization issues that may result in the creation
   * of multiple un-intended instances of this class.
   * @return This instance, a singleton
   */
  public synchronized Robot readResolve() {
    return Instance;
  }

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}


  @Override
  public void disabledInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {

  } 


  @Override
  public void autonomousInit() {

  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {

  }
 
  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void testInit() {
    super.testInit();
  }

  @Override
  public void testExit() {
    super.testExit();
  }

  /**
   * Retrieves the existing instance of this static utility class
   * @return Utility class's instance
   */
  public static synchronized Robot getInstance() {
    Robot Result = Instance;
    if(Instance == (null)) {
      synchronized(Robot.class) {
        Result = Instance;
        if(Instance == (null)) {
          Instance = Result = new Robot();
        }
      }
    }
    return Result;
  }
}