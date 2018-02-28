package com.jht.health.presenter;

import android.util.Log;

import com.jht.health.TrainingContract;
import com.jht.health.training.TimeTraining;
import com.jht.health.utils.Utils;


/**
 * Created by yangjing on 2018/1/25.
 */

public class SpeedPresenter extends BaseTypePresenter{

    private static final String TAG = Utils.APPLICATION_TAG + "TimePresenter";
    private static final int MILLISECOND = 1000;
    private TimeTraining mTimeTraining;
    private long mNeedTime = 0;
    private long mCurrentDistance = 0;
    private long mTarget = 0;
    private int mSpeed = 0;
    public SpeedPresenter(TrainingContract.View view, long targetTime, int speed) {
        mTrainingView = view;
        mTimeTraining = new TimeTraining();
        setTrainingType(mTimeTraining);
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
        mTimeTraining.setTargetTime(target);
    }

    public void setTrainingSpeed(int speed) {
        mTimeTraining.setSpeed(speed);
    }

    public long getCurrentDistance () {
        return mCurrentDistance;
    }
    @Override
    public void start() {
        setTrainingTarget(mTarget);
        setTrainingSpeed(mSpeed);
        mNeedTime = mTarget;
        Log.d(TAG, "start mNeedTime " + mNeedTime);
        if(mNeedTime > 0) {
            stopTimer();
            initTimer();
        }else {
            mTrainingView.showErrorInfo();
        }
    }

    @Override
    public void updateInformation() {
        long time = getCurrentTime();
        if(null == mTimeTraining) {
            return ;
        }
        mCurrentDistance += mTimeTraining.getSpeed();
        if(time > mTarget) {
            mCurrentDistance = 0;
            clearCurrentTime();
            stopTimer();
            return;
        }
        mTrainingView.updateInformation(time, mCurrentDistance);
    }

}
