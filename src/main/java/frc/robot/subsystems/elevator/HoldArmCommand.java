package frc.robot.subsystems.elevator;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.elevator.ElevatorConstants.FF;

public class HoldArmCommand extends Command {
    private Elevator elevator; 
    private ArmFeedforward feedForward; 

    public HoldArmCommand(Elevator elevator){ 
        this.elevator = elevator; 
        
        feedForward = new ArmFeedforward(
            FF.armS, 
            FF.armG, 
            FF.armV, 
            FF.armA);
        
        addRequirements(elevator);
    } 

    @Override 
    public void execute(){ 
        double voltageOutput = feedForward.calculate(
            Math.toRadians(elevator.getAngle()),  
            Math.toRadians(elevator.getVelocity()) / 12
        ); 
        elevator.setManualArm(voltageOutput);
    } 

    @Override  
    public void end(boolean interrupted){ 
         elevator.setManualArm(0);
    }
}
