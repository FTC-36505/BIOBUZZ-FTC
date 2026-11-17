package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


@TeleOp(name = "TT-Rodão", group = "TESTE")
public class ttRodao extends OpMode {
    private DcMotor rodaFrenteDireita, rodaFrenteEsquerda, rodaTrasDireita, rodaTrasEsquerda;

    public void rodinha() {
        double comandoFrente = -gamepad1.left_stick_y;
        double comandoLateral = gamepad1.left_stick_x;
        double comandoGiro = gamepad1.right_stick_x;

        double potenciaFrenteEsquerda = comandoFrente + comandoLateral + comandoGiro;
        double potenciaFrenteDireita = comandoFrente - comandoLateral - comandoGiro;
        double potenciaTrasEsquerda = comandoFrente - comandoLateral + comandoGiro;
        double potenciaTrasDireita = comandoFrente + comandoLateral - comandoGiro;

        rodaFrenteDireita.setPower(potenciaFrenteDireita);
        rodaFrenteEsquerda.setPower(potenciaFrenteEsquerda);
        rodaTrasEsquerda.setPower(potenciaTrasEsquerda);
        rodaTrasDireita.setPower(potenciaTrasDireita);
    }

    @Override
    public void init() {
        rodaFrenteDireita = hardwareMap.get(DcMotor.class, "F-D");
        rodaFrenteEsquerda = hardwareMap.get(DcMotor.class, "F-E");
        rodaTrasDireita = hardwareMap.get(DcMotor.class, "T-D");
        rodaTrasEsquerda = hardwareMap.get(DcMotor.class, "T-E");

        rodaFrenteDireita.setDirection(DcMotorSimple.Direction.REVERSE);
        rodaTrasDireita.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    @Override
    public void loop() {

        rodinha();
    }
}
