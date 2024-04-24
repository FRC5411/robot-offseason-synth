package org.frc5411.robot2024;

import org.frc5411.robot2024.Robot.RobotType;

import edu.wpi.first.wpilibj.RobotBase;

import java.util.Map;
/**
 *
 *
 * <h1>RobotConstants</h1>
 *
 * <p>Contains all robot-wide constants, does not contain subsystem specific constants.
 *
 * @see RobotContainer
 */
public final class Constants {
  /**
   * Checks if the robot is deployed with a valid mode
   * @param Options Additional options applied via the command line
   */
  public static void main(String... Options) {
    if (Robot.TYPE == RobotType.SIMULATION) {
      System.err.println("Cannot Deploy, Invalid Robot Type: " + Robot.TYPE);
      System.exit((1));
    }
  }

  public static final class Robot {
    public static final Boolean IS_REAL_ROBOT = RobotBase.isReal();
    public static final RobotType TYPE = 
      (Logging.REPLAY_FROM_LOG)?
       (RobotType.REPLAY):
      ((IS_REAL_ROBOT)? 
        (RobotType.CONCRETE):
        (RobotType.SIMULATION)
      );
  }

  public static final class Logging {
    public static final Map<RobotType,String> LOGGING_DEPOSIT = Map.of(
      RobotType.CONCRETE, ("/media/sda1/"),
      RobotType.SIMULATION, ("logs/simulation")
    );
    public static final Boolean LOGGING_ENABLED = (false);
    public static final Boolean REPLAY_FROM_LOG = (false);
  }
}