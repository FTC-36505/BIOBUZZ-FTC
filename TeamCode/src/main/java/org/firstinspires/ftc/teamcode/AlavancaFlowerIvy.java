package org.firstinspires.ftc.teamcode;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.behaviors.EndCondition;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.IntakeExternoIvy;

public abstract class AlavancaFlowerIvy implements Command {
    private final Servo servoFlower1;
    private final Servo servoFlower2;

    private IntakeExternoIvy intakeExternoIvy;

    protected AlavancaFlowerIvy(Servo servoFlower1, Servo servoFlower2, IntakeExternoIvy intakeExternoIvy){
        this.servoFlower1 = servoFlower1;
        this.servoFlower2 = servoFlower2;
        this.intakeExternoIvy = intakeExternoIvy;
    }

    @Override
    public void start() {
        servoFlower1.setDirection(Servo.Direction.FORWARD);
        servoFlower2.setDirection(Servo.Direction.FORWARD);
        servoFlower1.setPosition(.20);
        servoFlower2.setPosition(.20);
    }

    @Override
    public void execute() {
        servoFlower1.setPosition(.85);
        servoFlower2.setPosition(.85);
    }

    @Override
    public boolean done() {
        return intakeExternoIvy.done();
    }

    @Override
    public void end(EndCondition endCondition) {
        servoFlower1.setPosition(.2);
        servoFlower1.setPosition(.2);
    }
}
