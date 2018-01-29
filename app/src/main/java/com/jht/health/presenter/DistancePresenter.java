package com.jht.health.presenter;


import android.os.CountDownTimer;
import android.util.Log;


import com.jht.health.TrainingContract;
import com.jht.health.training.DistanceTraining;
import com.jht.health.utils.Utils;


/**
 * Created by yangjing on 2018/1/25.
 */

public class DistancePresenter extends BasePresenter{

    private static final String TAG = Utils.APPLICATION_TAG + "DistancePresenter";
    private static final int MILLISECOND = 1000;
    private DistanceTraining distanceTraining;
    private MyCountDownTimer mMyCountDownTimer;
    private long mNeedTime = 0;
    public DistancePresenter(TrainingContract.View view, int targetDistance, int speed) {
        mTrainingView = view;
        distanceTraining = new DistanceTraining();
        if(0 != targetDistance) {
            setTargetDistance(targetDistance);
        }
        if(0 != speed) {
            setSpeed(speed);
        }
    }

    @Override
    public void setTargetDistance(int target) {
        distanceTraining.setTargetDistance(target);
    }
    @Override
    public void setSpeed(int speed) {
        distanceTraining.setSpeed(speed);
    }

    @Override
    public void start() {
        mNeedTime = distanceTraining.calculateTime() * MILLISECOND;
        Log.d(TAG, "start mNeedTime " + mNeedTime);
        if(mNeedTime > 0) {
            if(null != mMyCountDownTimer) {
                mMyCountDownTimer.cancel();
                mMyCountDownTimer = null;
            }
            mMyCountDownTimer = new MyCountDownTimer(mNeedTime, MILLISECOND);
            mMyCountDownTimer.start();
        }else {
            mTrainingView.showErrorInfo();
        }
    }

    @Override
    public void updateInformation(String time) {
        mTrainingView.showTime(time);
    }

}
