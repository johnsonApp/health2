package  com.jht.health.training;


public class HeartRateTraining extends BaseTraining {
    private int mTargetHeartRate = 0;

    public HeartRateTraining() {
    }

    public HeartRateTraining(int heartRate) {
        setTargetHeartRate(heartRate);
    }

    public void setTargetHeartRate(long heartRate) {
        mTargetHeartRate = (int)heartRate;
    }
}