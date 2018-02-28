package  com.jht.health.training;


public class TimeTraining extends BaseTraining {
    private long mTargetTime = 0;

    public TimeTraining() {
    }

    public TimeTraining(long time) {
        setTargetTime(time);
    }

    public void setTargetTime(long time) {
        mTargetTime = time;
    }
}