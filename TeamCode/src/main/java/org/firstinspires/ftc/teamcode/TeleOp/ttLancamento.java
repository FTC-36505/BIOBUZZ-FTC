package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TT-Lancamento", group = "TESTE")
public class ttLancamento extends OpMode {

    Servo servo;
    DcMotor shooter;
    double power = .9;
    boolean add = false;
    boolean rmv = false;
    boolean aaa = false;
    boolean bbb = false;
    double comp;



    public void compres() {
        if (gamepad1.dpad_right && !aaa) {
            comp+= .01;
        } else if (gamepad1.dpad_left && !bbb) {
            comp-= .01;
        }
        aaa = gamepad1.dpad_right;
        bbb = gamepad1.dpad_left;

        if (gamepad1.a) {
            servo.setPosition(comp);
        }
    }

    public void lancar() {
        if (gamepad1.dpad_up && !add) {
            power+= .01;
        }
        else if (gamepad1.dpad_down && !rmv) {
            power-= .01;
        }
        add = gamepad1.dpad_up;
        rmv = gamepad1.dpad_down;

        if (gamepad1.right_trigger >= .2) {
            shooter.setPower(power);
        }
        else {
            shooter.setPower(.2);
        }
    }

    @Override
    public void init() {
        shooter = hardwareMap.get(DcMotor.class, "shooter");

        servo = hardwareMap.get(Servo.class, "servo");
        servo.setPosition(.35);

    }

    @Override
    public void loop() {
        compres();
        lancar();
        telemetry.addData("Potencia", power);
        telemetry.addData("Comp", comp);
        telemetry.update();
    }
}
