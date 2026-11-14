package org.firstinspires.ftc.teamcode;

import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.behaviors.EndCondition;
import com.qualcomm.robotcore.hardware.DcMotor;

public abstract class IntakeExternoIvy implements Command {
    private final DcMotor motorIntakeExterno;

    protected IntakeExternoIvy(DcMotor motorIntakeExterno) {
        this.motorIntakeExterno = motorIntakeExterno;
    }

    @Override
    public void start() {

    }

    @Override
    public void execute() {

    }

    @Override
    public boolean done() {
        return false;
    }

    @Override
    public void end(EndCondition endCondition) {

    }
}
