package frc.robot.subsystems.elevator;



import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;





public class ElevatorIOSparkMax implements Elevator {  

    private CANSparkMax arm = new CANSparkMax(ElevatorConstants.armID, MotorType.kBrushless); 
    private RelativeEncoder linearEncoder = arm.getEncoder(); 
    private SparkPIDController armController = arm.getPIDController();


    public ElevatorIOSparkMax(){    

        linearEncoder.setPositionConversionFactor(ElevatorConstants.distPerRotation); 
        linearEncoder.setVelocityConversionFactor(ElevatorConstants.distPerRotation / 60);  
        linearEncoder.setPosition(0); 
        linearEncoder.setInverted(ElevatorConstants.inverted); 

        armController.setP(ElevatorConstants.armP); 
        armController.setI(ElevatorConstants.armI); 
        armController.setD(ElevatorConstants.armD);  
        armController.setFF(ElevatorConstants.armFF);
        
        armController.setOutputRange(ElevatorConstants.outputMin, ElevatorConstants.outputMax);
        armController.setFeedbackDevice(linearEncoder);   
          
    }
    

    public void updateInputs(ElevatorIOInputs inputs){   
        inputs.currentOutput = arm.getAppliedOutput() * arm.getBusVoltage(); 
        inputs.encoderPos = linearEncoder.getPosition();
        inputs.velocity = linearEncoder.getVelocity(); 
    } 

    public void setManualArm(double volts){  
        double pos = linearEncoder.getPosition();
        if ((pos >= ElevatorConstants.boundaryBottom) && (pos <= ElevatorConstants.boundaryTop)){ 
            armController.setReference(MathUtil.clamp(volts, ElevatorConstants.outputMin, ElevatorConstants.outputMax),ControlType.kVoltage);
        } 
    } 

    public void setArmReference(double refMeters){  
        if ((refMeters <= ElevatorConstants.boundaryTop) && (refMeters >= ElevatorConstants.boundaryBottom)){ 
           armController.setReference(refMeters, ControlType.kPosition);
        }
        
    }

}
