package com.jht.health;

import com.jht.health.training.BaseTraining;

/**
 * Created by yangjing on 2018/1/25.
 */

public interface TrainingContract {
    interface View extends BaseView<Presenter> {
        void showDetails();
        void showErrorInfo();
        void showTime(String  time);
        void showSpeed(int speed);
        void showDistance(int distance);
        void showHeartRate(int heartRate);
        void showIncline(int incline);
    }

    interface Presenter extends BasePresenter {
        void setTargetDistance(int distance);
        void setSpeed(int speed);
        // updateInformation(String s);
    }
}
