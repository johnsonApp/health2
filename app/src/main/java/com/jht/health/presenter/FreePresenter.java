package com.jht.health.presenter;


import com.jht.health.TrainingContract;
import com.jht.health.training.FreeTraining;
import com.jht.health.utils.Utils;


/**
 * Created by yangjing on 2018/1/25.
 */

public class FreePresenter extends BaseTypePresenter{

    private static final String TAG = Utils.APPLICATION_TAG + "TimePresenter";
    private static final int MILLISECOND = 1000;
    private FreeTraining mFreeTraining;
    private long mCurrentDistance = 0;
    private long mTarget = 0;
    private int mSpeed = 0;
    public FreePresenter(TrainingContract.View view, int speed) {
        mTrainingView = view;
        mFreeTraining = new FreeTraining();
        setTrainingType(mFreeTraining);
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


    public void setTrainingSpeed(int speed) {
        mFreeTraining.setSpeed(speed);
    }

    public long getCurrentDistance () {
        return mCurrentDistance;
    }
    @Override
    public void start() {
        setTrainingSpeed(mSpeed);
        stopTimer();
        initTimer();
    }

    @Override
    public void updateInformation() {
        long time = getCurrentTime();
        if(null == mFreeTraining) {
            return ;
        }
        mCurrentDistance += mFreeTraining.getSpeed();
        mTrainingView.updateInformation(time, mCurrentDistance);
    }

}
