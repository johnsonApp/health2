package com.jht.health.presenter;


import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jht.health.TrainingContract;


/**
 * Created by yangjing on 2018/1/25.
 */

public class BasePresenter implements TrainingContract.Presenter{

    protected TrainingContract.View mTrainingView;

    public BasePresenter() {

    }
    public void updateInformation(String s) {

    }
    @Override
    public void start() {

    }

    @Override
    public void setSpeed(int speed) {

    }

    @Override
    public void setTargetDistance(int distance) {

    }

    class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            updateInformation("done");
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.i("MainActivity", millisUntilFinished + "");
            long showTime = (millisUntilFinished + 500) / 1000;
            updateInformation("Still need(" + showTime + ")...");
        }
    }
}
