package com.jht.health.presenter;


import com.jht.health.TrainingContract;
import com.jht.health.training.HeartRateTraining;
import com.jht.health.utils.Utils;


/**
 * Created by yangjing on 2018/1/25.
 */

public class HeartRatePresenter extends BaseTypePresenter{

    private static final String TAG = Utils.APPLICATION_TAG + "HeartRatePresenter";
    private static final int MILLISECOND = 1000;
    private HeartRateTraining mHeartRateTraining;
    private long mCurrentDistance = 0;
    private long mCurrentHeartRate = 0;
    private long mTarget = 0;
    private int mSpeed = 0;
    public HeartRatePresenter(TrainingContract.View view, long targetTime, int speed) {
        mTrainingView = view;
        mHeartRateTraining = new HeartRateTraining();
        setTrainingType(mHeartRateTraining);
        if(0 != targetTime) {
            mTarget = targetTime;
        }
        if(0 != speed) {
            mSpeed = speed;
        }
    }

    @Override
    public void setSpeed (int speed) {
        mSpeed = speed;
    }

    @Override
    public void setTarget (long target) {
        mTarget = target;
    }

    public void setTrainingTarget(long target) {
        mHeartRateTraining.setTargetHeartRate(target);
    }

    public void setTrainingSpeed(int speed) {
        mHeartRateTraining.setSpeed(speed);
    }

    @Override
    public void start() {
        setTrainingTarget(mTarget);
        setTrainingSpeed(mSpeed);
        stopTimer();
        initTimer();

    }

    @Override
    public void updateInformation() {
        long time = getCurrentTime();
        if(null == mHeartRateTraining) {
            return ;
        }
        mCurrentDistance += mHeartRateTraining.getSpeed();
        mCurrentHeartRate = mHeartRateTraining.getHeartRate();
        if(mCurrentHeartRate >= mTarget) {
            mCurrentDistance = 0;
            clearCurrentTime();
            stopTimer();
            return;
        }
        mTrainingView.updateInformation(time, mCurrentDistance);
    }

}
