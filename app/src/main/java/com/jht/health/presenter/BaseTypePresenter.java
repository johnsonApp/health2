package com.jht.health.presenter;

import java.util.Timer;
import java.util.TimerTask;

import com.jht.health.TrainingContract;
import com.jht.health.training.BaseTraining;


/**
 * Created by yangjing on 2018/1/25.
 */

public class BaseTypePresenter implements TrainingContract.Presenter{

    protected TrainingContract.View mTrainingView;
    protected BaseTraining mBaseTraining;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private long mCurrentTime = 0;
    private boolean mPauseStatue = false;

    public BaseTypePresenter() {
        //initTimer();
    }

    public void updateInformation() {

    }
    @Override
    public void start() {

    }

    @Override
    public void end() {
        clearCurrentTime();
        stopTimer();
    }

    @Override
    public void setSpeed(int speed) {

    }

    public void setPause(){
        mPauseStatue = true;
    }

    public void setResume(){
        mPauseStatue = false;
    }

    public void setTrainingType (BaseTraining trainingType) {
        if(null != trainingType && trainingType instanceof BaseTraining) {
            mBaseTraining = trainingType;
        }
    }
    public long getCurrentTime() {
        return mCurrentTime;
    }

    @Override
    public void setTarget(long target) {

    }


    public void initTimer(){
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                if(mPauseStatue){
                    return;
                }
                mCurrentTime += 1;
                if(null != mBaseTraining) {
                    mBaseTraining.setTimes(mCurrentTime);
                }
                updateInformation();
            }
        };
        mTimer.schedule(mTimerTask,1000,1000);
    }

    public void stopTimer() {
        if(null != mTimer) {
            mTimer.cancel();
            mTimer = null;
        }
        if(null != mTimerTask) {
            mTimerTask.cancel();
            mTimerTask = null;
        }
    }

    public void clearCurrentTime() {
        mCurrentTime = 0;
    }
/*    class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
        }

        @Override
        public void onTick(long millisUntilFinished) {
        }
    }*/
}
