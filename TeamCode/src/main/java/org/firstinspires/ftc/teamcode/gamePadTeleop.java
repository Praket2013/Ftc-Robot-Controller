package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * This file contains a minimal example of an iterative (Non-Linear) "OpMode". An OpMode is a
 * 'program' that runs in either the autonomous or the TeleOp period of an FTC match. The names
 * of OpModes appear on the menu of the FTC Driver Station. When an selection is made from the
 * menu, the corresponding OpMode class is instantiated on the Robot Controller and executed.
 *
 * Remove the @Disabled annotation on the next line or two (if present) to add this OpMode to the
 * Driver Station OpMode list, or add a @Disabled annotation to prevent this OpMode from being
 * added to the Driver Station.
 */

@TeleOp
public class gamePadTeleop extends OpMode{
    private DcMotor rightSideDrive=null;
    private DcMotor leftSideDrive=null;
//    private DcMotor shooterMotor=null;
//    private CRServo leftSideShooterControl;
//    private CRServo rightSideShooterControl;



    public void init(){
        rightSideDrive=hardwareMap.get(DcMotor.class, "rightSideMotor");
        leftSideDrive=hardwareMap.get(DcMotor.class, "leftSideMotor");
        rightSideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // reverse this side for going forward
//        hardwareMap.get(DcMotor.class, "shooterMotor");
//        hardwareMap.get(CRServo.class, "leftSideShooterControl");
//        hardwareMap.get(CRServo.class, "rightSideShooterControl");
//        rightSideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftSideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    @Override
    public void loop() {
        double drive = gamepad1.right_stick_y; // forward/backward
        double turn = gamepad1.right_stick_x;   // turning

        // Compute motor powers for arcade drive
        double leftPower = -(drive + turn);
        double rightPower = drive - turn;

        // Clip power values to stay within [-1, 1]
        leftPower = Math.max(-1, Math.min(1, leftPower));
        rightPower = Math.max(-1, Math.min(1, rightPower));

        leftSideDrive.setPower(leftPower);
        rightSideDrive.setPower(rightPower);
    }

//        if(gamepad1.dpad_up=true){
//            leftSideShooterControl.setPower(1);
//            rightSideShooterControl.setPower(1);
//        } else if(gamepad1.dpad_down=true){
//            leftSideShooterControl.setPower(-1);
//            rightSideShooterControl.setPower(-1);
//        }

}
// if it doesnt work then its start instead of loop