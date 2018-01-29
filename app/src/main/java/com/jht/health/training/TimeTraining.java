package  com.jht.health.training;


public class TimeTraining extends BaseTraining {
    private int mTargetTime = 0;

    public TimeTraining() {
    }

    public TimeTraining(int time) {
        setTargetTime(time);
    }

    private void setTargetTime(int time) {
        mTargetTime = time;
    }
}