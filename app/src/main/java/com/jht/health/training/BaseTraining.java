package com.jht.health.training;

public class BaseTraining {

    private static final int SPEED_DEFAULT_VALUE = 0;
    private static final int CALORIE_DEFAULT_VALUE = 0;
    private static final int TIME_DEFAULT_VALUE = 0;
    private static final int INCLINE_DEFAULT_VALUE = 0;
    private static final int HEARTRATE_DEFAULT_VALUE = 0;
    private static final int DISTANCE_DEFAULT_VALUE = 0;

    //speed KPH
    private int mSpeed;
    //calorie
    private int mCalorie;
    //time second
    private long mTimes;
    //incline
    private int mIncline;
    //heartRate
    private int mHeartRate;
    //distance mile
    private long mDistance;

    protected BaseTraining () {
        setDefaultValue();
    }
    public void setSpeed(int speed) {
        mSpeed = speed;
    }

    public void setCalorie(int calorie) {
        mCalorie = calorie;
    }

    public void setTimes(long times) {
        mTimes = times;
    }

    public void setInclie (int incline) {
        mIncline = incline;
    }

    public void setHeartRate(int heartRate) {
        mHeartRate = heartRate;
    }

    public void setDistance(long distance) { mDistance = distance; }

    public int getSpeed() {
        return mSpeed;
    }

    public int getCalorie() {
        return mCalorie;
    }

    public long getTimes() {
        return mTimes;
    }

    public int getIncline() {
        return mIncline;
    }

    public int getHeartRate() {
        return mHeartRate;
    }

    public long getDistnce() {
        return mDistance;
    }

    public void setDefaultValue() {
        mSpeed = SPEED_DEFAULT_VALUE;
        mCalorie = CALORIE_DEFAULT_VALUE;
        mTimes = TIME_DEFAULT_VALUE;
        mIncline = INCLINE_DEFAULT_VALUE;
        mHeartRate = HEARTRATE_DEFAULT_VALUE;
        mDistance = DISTANCE_DEFAULT_VALUE;
    }
}
