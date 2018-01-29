package  com.jht.health.training;


public class HeartRateTraining extends BaseTraining {
    private int mTargetHeartRate = 0;

    public HeartRateTraining() {
    }

    public HeartRateTraining(int heartRate) {
        setTargetDistance(heartRate);
    }

    private void setTargetDistance(int heartRate) {
        mTargetHeartRate = heartRate;
    }
}