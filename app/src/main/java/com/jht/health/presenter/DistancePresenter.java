package com.jht.health.presenter;

import android.util.Log;


import com.jht.health.TrainingContract;
import com.jht.health.training.DistanceTraining;
import com.jht.health.utils.Utils;


/**
 * Created by yangjing on 2018/1/25.
 */

public class DistancePresenter extends BaseTypePresenter{

    private static final String TAG = Utils.APPLICATION_TAG + "DistancePresenter";
    private static final int MILLISECOND = 1000;
    private DistanceTraining mDistanceTraining;
    private long mNeedTime = 0;
    private long mCurrentDistance = 0;
    private long mTarget = 0;
    private int mSpeed = 0;
    public DistancePresenter(TrainingContract.View view, long targetDistance, int speed) {
        mTrainingView = view;
        mDistanceTraining = new DistanceTraining();
        setTrainingType(mDistanceTraining);
        if(0 != targetDistance) {
            mTarget = targetDistance;
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
        long data = target;
        if(target > mCurrentDistance) {
            data -= mCurrentDistance;
        }
        mDistanceTraining.setTargetDistance(data);
    }

    public void setTrainingSpeed(int speed) {
        mDistanceTraining.setSpeed(speed);
    }

    public long getCurrentDistance () {
        return mCurrentDistance;
    }
    @Override
    public void start() {
        setTrainingTarget(mTarget);
        setTrainingSpeed(mSpeed);
        mNeedTime = mDistanceTraining.calculateTime();
        Log.d(TAG, "start mNeedTime " + mNeedTime);
        if(mNeedTime > 0) {
            stopTimer();
            initTimer();
            /*if(null != mMyCountDownTimer) {
                mMyCountDownTimer.cancel();
                mMyCountDownTimer = null;
            }
            mMyCountDownTimer = new MyCountDownTimer(mNeedTime, MILLISECOND);
            mMyCountDownTimer.start();*/
        }else {
            mTrainingView.showErrorInfo();
        }
    }

    @Override
    public void updateInformation() {
        long time = getCurrentTime();
        if(null == mDistanceTraining) {
            return ;
        }
        mCurrentDistance += mDistanceTraining.getSpeed();
        if(mCurrentDistance > mTarget) {
            mCurrentDistance = 0;
            clearCurrentTime();
            stopTimer();
            return;
        }
        mTrainingView.updateInformation(time, mCurrentDistance);
        //mTrainingView.showTime(String.valueOf(getCurrentTime()));
        //mTrainingView.showDistance(String.valueOf(distance));
    }

}
