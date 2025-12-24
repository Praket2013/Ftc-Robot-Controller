/*
Copyright 2025 FIRST Tech Challenge Team FTC

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
associated documentation files (the "Software"), to deal in the Software without restriction,
including without limitation the rights to use, copy, modify, merge, publish, distribute,
sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
package org.firstinspires.ftc.teamcode;

import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;        // For iterative OpModes
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;         // For TeleOp annotations

import com.qualcomm.robotcore.hardware.DcMotor;                // For DC motors
import com.qualcomm.robotcore.hardware.Servo;                  // For servos
import com.qualcomm.robotcore.hardware.CRServo;                // For continuous rotation servos
import com.qualcomm.robotcore.hardware.Gamepad;                // For gamepad reference (optional)
import com.qualcomm.robotcore.hardware.HardwareMap;            // For mapping hardware

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ImageRegion;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

/**
 * This file contains a minimal example of an iterative (Non-Linear) "OpMode". An OpMode is a
 * 'program' that runs in either the autonomous or the TeleOp period of an FTC match. The names
 * of OpModes appear on the menu of the FTC Driver Station. When an selection is made from the
 * menu, the corresponding OpMode class is instantiated on the Robot Controller and executed.
 * Remove the @Disabled annotation on the next line or two (if present) to add this OpMode to the
 * Driver Station OpMode list, or add a @Disabled annotation to prevent this OpMode from being
 * added to the Driver Station.
 */

@TeleOp
public class scienceFairCode extends OpMode{
    private DcMotor rightSideDrive=null;
    private DcMotor leftSideDrive=null;

    private WebcamName webcam= null;

    private Servo Servo=null;
    // private DcMotor shooterMotor=null;
    // private Servo leftSideShooterControl;
    // private Servo rightSideShooterControl;

    public void init(){
        rightSideDrive=hardwareMap.get(DcMotor.class, "rightSideMotor");
        leftSideDrive=hardwareMap.get(DcMotor.class, "leftSideMotor");
        webcam=hardwareMap.get(WebcamName.class, "webcam");
        Servo=hardwareMap.get(Servo.class, "sift");
        rightSideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // reverse this side for going forward
        // shooterMotor=hardwareMap.get(DcMotor.class, "shooterMotor");
        // leftSideShooterControl=hardwareMap.get(Servo.class, "leftSideShooterControl");
        // rightSideShooterControl=hardwareMap.get(Servo.class, "rightSideShooterControl");
//        rightSideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        leftSideDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void setSifterPos(double angle){
        Servo.setPosition(angle);
    }
    @Override
    public void loop() {

        double drive = -gamepad1.right_stick_x; // forward/backward
        double turn = -gamepad1.right_stick_y;   // turning
        double speedClipper = 0.5;
        // motor power

//        if(gamepad1.right_bumper){
//            speedClipper=0.7;
//
//        } else if (gamepad1.left_bumper) {
//            speedClipper=0.9;
//
//        } else{
//            speedClipper=0.5;
//        }
        double previousPosition=0;
        if(gamepad1.right_trigger>=0.7){
            Servo.setPosition(previousPosition+0.1);
            previousPosition=previousPosition+0.1;
        } else if (gamepad1.left_trigger>=0.7) {
            Servo.setPosition(previousPosition-0.1);
            previousPosition=previousPosition-0.1;
            sleep(100);
        }
        double leftPower = drive + turn;
        double rightPower = drive - turn;
//        telemetry.addData("leftPower", leftPower);
//        telemetry.addData("rightPower", rightPower);
//        telemetry.addData("speedClipper", speedClipper);
        // clip power values
        leftPower = Math.max(-speedClipper, Math.min(speedClipper, leftPower));
        rightPower = Math.max(-speedClipper, Math.min(speedClipper, rightPower));

        leftSideDrive.setPower(leftPower);
        rightSideDrive.setPower(rightPower);

        // if (gamepad1.dpad_up) {
        //     leftSideShooterControl.setPosition(-0.67);
        //     rightSideShooterControl.setPosition(0.67);
        // } else if (gamepad1.dpad_down) {
        //     leftSideShooterControl.setPosition(0.67);
        //     rightSideShooterControl.setPosition(-0.67);
        // }

    }
}

