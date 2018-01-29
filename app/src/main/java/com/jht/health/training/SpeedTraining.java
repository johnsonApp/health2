package  com.jht.health.training;


public class SpeedTraining extends BaseTraining {
    private int mTargetSpeed = 0;

    public SpeedTraining() {
    }

    public SpeedTraining(int speed) {
        setTargetSpeed(speed);
    }

    private void setTargetSpeed(int speed) {
        mTargetSpeed = speed;
    }
}