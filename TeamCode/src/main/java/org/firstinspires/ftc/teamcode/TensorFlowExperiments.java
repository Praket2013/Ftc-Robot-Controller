package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import java.io.IOException;


@TeleOp
public class TensorFlowExperiments extends OpMode {
    @Override
    public void init() {
        try {
            hardwareMap.appContext.getAssets().open("model.tflite");
        } catch (IOException e) {
            throw new RuntimeException("Could not open model.tflite", e);
        }


    }

    @Override
    public void loop() {

    }
}
