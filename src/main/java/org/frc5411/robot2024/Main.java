package org.frc5411.robot2024;

import edu.wpi.first.wpilibj.RobotBase;
/**
 *
 *
 * <h1>Main</h1>
 *
 * <p>Robot Project runner class, responsible for robot initialization by starting the
 * Driverstation, CameraServer, and HAL services.
 *
 * @see Robot
 */
public final class Main {
  /**
   * Initializes the robot and underlying systems
   * @param Options Additional options applied via the command line
   */
  public static void main(String... Options) {
      RobotBase.startRobot(Robot::getInstance);
  }
}