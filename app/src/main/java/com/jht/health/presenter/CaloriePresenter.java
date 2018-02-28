package com.jht.health.presenter;


import android.util.Log;


import com.jht.health.TrainingContract;
import com.jht.health.training.CalorieTraining;
import com.jht.health.utils.Utils;


/**
 * Created by yangjing on 2018/1/25.
 */

public class CaloriePresenter extends BaseTypePresenter{

    private static final String TAG = Utils.APPLICATION_TAG + "TimePresenter";
    private static final int MILLISECOND = 1000;
    private CalorieTraining mCalorieTraining;
    private long mNeedTime = 0;
    private long mCurrentCalorie = 0;
    private long mTarget = 0;
    private int mSpeed = 0;
    private int mCaloriePerSec = 0;
    private int mWeight = 0;

    public CaloriePresenter(TrainingContract.View view, long targetCalorie, int speed) {
        mWeight = 50;
        initCaloriePresenter(view,targetCalorie,speed);
    }

    public CaloriePresenter(TrainingContract.View view, long targetCalorie, int speed, int weight) {
        mWeight = weight;
        initCaloriePresenter(view,targetCalorie,speed);
    }

    private void  initCaloriePresenter(TrainingContract.View view, long targetCalorie, int speed) {
        mTrainingView = view;
        mCalorieTraining = new CalorieTraining();
        setTrainingType(mCalorieTraining);
        if(0 != targetCalorie) {
            mTarget = targetCalorie;
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
        mCalorieTraining.setTargetCalorie(target);
    }

    public void setTrainingSpeed(int speed) {
        mCalorieTraining.setSpeed(speed);
    }

    public long getCurrentCalorie () {
        return mCurrentCalorie;
    }
    @Override
    public void start() {
        setTrainingTarget(mTarget);
        setTrainingSpeed(mSpeed);
        double calorie = mCalorieTraining.calculateCalorieValue(mWeight);
        mCaloriePerSec = (int)(calorie * 1000 / 60);
        mNeedTime = (int) (mTarget/mCaloriePerSec);
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
        if(null == mCalorieTraining) {
            return ;
        }
        mCurrentCalorie += mCaloriePerSec;
        if(mCurrentCalorie > mTarget) {
            mCurrentCalorie = 0;
            clearCurrentTime();
            stopTimer();
            return;
        }
        mTrainingView.updateInformation(getCurrentTime(), mCurrentCalorie);
    }

}
