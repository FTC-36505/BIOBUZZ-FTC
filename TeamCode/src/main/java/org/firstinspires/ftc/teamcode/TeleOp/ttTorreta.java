package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "TT-Torreta", group = "TESTE")
public class ttTorreta extends OpMode {
    DcMotor torreta;
    Limelight3A limelight;
    //------------------------
    ColorSensor sensor;

    static final int limiteAzul = 215;
    static final int limiteVermelho = 280;
    boolean maiorBlue;
    boolean maiorRed;
    //---------------------------
    Servo compressao;
    DcMotor caminhoShooter;
    //-----------------------------
    private DcMotor rodaFrenteDireita, rodaFrenteEsquerda, rodaTrasDireita, rodaTrasEsquerda;


    public void rodinhas() {
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


    public void giro() {

        LLResult result = limelight.getLatestResult();
        double power;

        if (result != null && result.isValid() && gamepad1.right_bumper) {
            double tx = result.getTx();

            if (Math.abs(tx) < 1) {
                power = 0;
            } else {
                power = -tx * 0.03;
            }

            telemetry.addData("TAG", "Visivel");
        } else {
            power = gamepad1.left_stick_x;

            telemetry.addData("TAG", "Nao visivel");
        }

        torreta.setPower(power);
    }

    public void color() {

        int red = sensor.red();
        int blue = sensor.blue();

        if (blue < limiteAzul) {
            blue = 0;
        }
        if (red < limiteVermelho) {
            red = 0;
        }

        maiorBlue = blue > red;
        maiorRed = red > blue;

        if (maiorBlue) {
            telemetry.addData("Detectado", "Nectar");
        } else if (maiorRed) {
            telemetry.addData("Detectado", "Polen");
        } else {
            telemetry.addData("Detectado", "Nada");
        }

        telemetry.addData("BLUE", blue);
        telemetry.addData("RED", red);

    }

    public void alterarCompressao() {
        if (maiorBlue) {
            compressao.setPosition(.7);
            if (compressao.getPosition() == .7) {
                caminhoShooter.setPower(1);
            }
            else {
                caminhoShooter.setPower(0);
            }
        }

        else if (maiorRed) {
            compressao.setPosition(.45);
            if (compressao.getPosition() == .45) {
                caminhoShooter.setPower(1);
            }
            else {
                caminhoShooter.setPower(0);
            }
        }
        else {
            compressao.setPosition(.45);
        }
    }

    public void lancar() {
        //AAAAAAAA
    }

    @Override
    public void init() {

        rodaFrenteDireita = hardwareMap.get(DcMotor.class, "F-D");
        rodaFrenteEsquerda = hardwareMap.get(DcMotor.class, "F-E");
        rodaTrasDireita = hardwareMap.get(DcMotor.class, "T-D");
        rodaTrasEsquerda = hardwareMap.get(DcMotor.class, "T-E");

        rodaFrenteDireita.setDirection(DcMotorSimple.Direction.REVERSE);
        rodaTrasDireita.setDirection(DcMotorSimple.Direction.REVERSE);

        torreta = hardwareMap.get(DcMotor.class, "motor");

        limelight = hardwareMap.get(Limelight3A.class, "limelight");

        limelight.pipelineSwitch(0);
        limelight.start();

        //sensor = hardwareMap.get(ColorSensor.class, "sensor-color");

        //caminhoShooter = hardwareMap.get(DcMotor.class, "caminhoShooter");
        //caminhoShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        compressao = hardwareMap.get(Servo.class, "compressao");
        compressao.setPosition(.45);
    }

    public void loop() {
        rodinhas();
        giro();
        color();
        alterarCompressao();
    }
}