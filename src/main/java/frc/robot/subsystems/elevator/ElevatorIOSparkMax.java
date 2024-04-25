package frc.robot.subsystems.elevator;



import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;





public class ElevatorIOSparkMax implements Elevator {  

    /*Pre-made Constants.. will edit at a later date */ 
    double armP = 1.0;  
    double armI = 0.0;  
    double armD = 0.0;   
    double armFF = 0.5;
    int armID = 0;
    double distPerRotation = 0.5; 
    double tolerance = 4.0; 
    double outputMax = 12; 
    double outputMin = -12; 
    boolean inverted = false; 
    double boundaryTop = 1.7; 
    double boundaryBottom = 0;
    
    ///////////////////////// 

    private CANSparkMax arm = new CANSparkMax(armID, MotorType.kBrushless); 

    private RelativeEncoder linearEncoder = arm.getEncoder(); 

    private SparkPIDController armController = arm.getPIDController();


    public ElevatorIOSparkMax(){    

        linearEncoder.setPositionConversionFactor(distPerRotation); 
        linearEncoder.setVelocityConversionFactor(distPerRotation / 60);  
        linearEncoder.setPosition(0); 
        linearEncoder.setInverted(inverted); 

        armController.setP(armP); 
        armController.setI(armI); 
        armController.setD(armD);  
        armController.setFF(armFF);
        
        armController.setOutputRange(outputMin, outputMax);

        armController.setFeedbackDevice(linearEncoder);   
          
    }
    

    public void updateInputs(ElevatorIOInputs inputs){   

        inputs.currentOutput = arm.getAppliedOutput() * arm.getBusVoltage(); 
        inputs.encoderPos = linearEncoder.getPosition();
        inputs.velocity = linearEncoder.getVelocity(); 

    } 

    public void setManualArm(double volts){  

        double pos = linearEncoder.getPosition(); 
        if ((pos >= boundaryBottom) && (pos <= boundaryTop)){ 
            armController.setReference(MathUtil.clamp(volts, outputMin, outputMax),ControlType.kVoltage);
        } 

    } 

    public void setArmReference(double refMeters){  

        if ((refMeters <= boundaryTop) && (refMeters >= boundaryBottom)){ 
           armController.setReference(refMeters, ControlType.kPosition);
        }
        
    }

}
