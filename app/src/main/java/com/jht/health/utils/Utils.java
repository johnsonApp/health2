package com.jht.health.utils;


/**
 * Created by yangjing on 2018/1/25.
 */

public final class Utils {

    public static final String  APPLICATION_TAG = "JIS_";
    public static final String  TRAINING_MODE = "training_mode";

    public static final int TIME_MODE = 0;
    public static final int CALORIE_MODE = 1;
    public static final int HEARTRATE_MODE = 2;
    public static final int DISTANCE_MODE = 3;
    public static final int FREE_MODE = 4;

    public static final int DEFAULT_MODE = FREE_MODE;

    private static final String TIME_MODE_STRING = "time mode";
    private static final String CALORIE_MODE_STRING = "calorie mode";
    private static final String HEARTRATE_MODE_STRING = "heartRate mode";
    private static final String DISTANCE_MODE_STRING = "distance mode";
    private static final String FREE_MODE_STRING = "free mode";

    public static String getTrainingModeString(int mode) {
        String temp = FREE_MODE_STRING;
        switch (mode) {
            case TIME_MODE:
                temp = TIME_MODE_STRING;
                break;
            case CALORIE_MODE:
                temp = CALORIE_MODE_STRING;
                break;
            case HEARTRATE_MODE:
                temp = HEARTRATE_MODE_STRING;
                break;
            case DISTANCE_MODE:
                temp = DISTANCE_MODE_STRING;
                break;
            case FREE_MODE:
                temp = FREE_MODE_STRING;
            default:
                break;
        }
        return temp;
    }
}
