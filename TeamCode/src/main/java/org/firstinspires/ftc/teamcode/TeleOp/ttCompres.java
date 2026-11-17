package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TT-COMP", group = "TESTE")
public class ttCompres extends OpMode {

    Servo servo;
    double comp;
    boolean add = false;
    boolean rmv = false;

    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "servo");
    }

    @Override
    public void loop() {
        if (gamepad1.dpad_up && !add) {
            comp += .05;
        } else if (gamepad1.dpad_down && !rmv) {
            comp -= .05;
        }
        add = gamepad1.dpad_up;
        rmv = gamepad1.dpad_down;

        if (gamepad1.right_bumper) {
            servo.setPosition(comp);
        }

        telemetry.addData("Comp", comp);
        telemetry.update();
    }
}