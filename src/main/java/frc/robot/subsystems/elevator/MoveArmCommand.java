package frc.robot.subsystems.elevator;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.Command;

public class MoveArmCommand extends Command {
    
    private final Elevator elevator; 
    private final double setpoint;

    private final ProfiledPIDController pidControl = new ProfiledPIDController( 
        ElevatorConstants.armP, 
        ElevatorConstants.armI, 
        ElevatorConstants.armD,  
        new TrapezoidProfile.Constraints(ElevatorConstants.velProf, ElevatorConstants.accelProf)); 
    
    private final ArmFeedforward ffControl = new ArmFeedforward( 
        ElevatorConstants.armS, 
        ElevatorConstants.armG, 
        ElevatorConstants.armV, 
        ElevatorConstants.armA
    ); 

    public MoveArmCommand(Elevator elev, double setp){ 
       this.elevator = elev; 
       this.setpoint = setp; 
       addRequirements(elev);
    }
    
    @Override 
    public void initialize(){ 
      
        
    } 
    
    @Override 
    public void execute(){ 
        
    }  

    @Override 
    public boolean isFinished(){ 
          return false;
    }  



}
