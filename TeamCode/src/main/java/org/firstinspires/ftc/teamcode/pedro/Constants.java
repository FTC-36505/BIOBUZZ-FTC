package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.drivetrain.Drivetrain;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.pedropathing.drivetrain.Drivetrain;
import com.pedropathing.localization.Localizer;
import com.pedropathing.algorithm.Foresight;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.pedro.procedures.PinpointTuner;

public class Constants {

    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("FrenteEsquerda");
        c.frontRightName.set("FrenteDireita");
        c.backLeftName.set("TrasEsquerda");
        c.backRightName.set("TrasDireita");
        c.frontLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.frontRightDirection.set(DcMotorSimple.Direction.REVERSE);
        c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backRightDirection.set(DcMotorSimple.Direction.REVERSE);
    });

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        c.xPodOffset.set(-0.4912257757712537);
        c.yPodOffset.set(0.4455679420411118);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.15440063022179487);
                Controller secondaryTranslationalForward = Controller.proportional(0.0570469072061303);
                Controller primaryTranslationalLateral = Controller.proportional(0.2243687628896536);
                Controller secondaryTranslationalLateral = Controller.proportional(0.08289826264396663);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.01899862137048946));
                c.brake.set(Controller.proportionalFeedforward(0.01614882816491604));

                c.headingFeedback.set(Controller.proportional(1.816700623060893));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.05452961103420479, 0.004935221302625899));

                c.linearBrakeCoefficients.set(Matrix.diag(0.08040370074080706, 0.09745067585437965));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.0010191594929760796, 8.997499566827323E-4));

                c.maxAchievableForwardVelocity.set(59.074426718158705);
                c.maxAchievableStrafeVelocity.set(53.36819996960598);
                c.naturalForwardDeceleration.set(76.56032118017431);
                c.naturalStrafeDeceleration.set(82.03463788006009);
            }
    );

            public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}