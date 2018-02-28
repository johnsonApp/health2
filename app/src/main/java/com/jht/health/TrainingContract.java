package com.jht.health;


/**
 * Created by yangjing on 2018/1/25.
 */

public interface TrainingContract {
    interface View extends BaseView<Presenter> {
        void showDetails();
        void showErrorInfo();
        void showTime(String  time);
        void showSpeed(int speed);
        void showDistance(String distance);
        void showHeartRate(int heartRate);
        void showIncline(int incline);
        void updateInformation(long time,long distance);
    }

    interface Presenter extends BasePresenter {
        void setTarget(long target);
        void setSpeed(int speed);
        void setPause();
        void setResume();
        // updateInformation(String s);
    }
}
