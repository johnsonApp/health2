package  com.jht.health.training;

import android.util.Log;

import com.jht.health.utils.Utils;

public class DistanceTraining extends BaseTraining {
    private static final String TAG = Utils.APPLICATION_TAG + "DistanceTraining";
    private int mTargetDistance = 0;

    public DistanceTraining() {
    }

    public DistanceTraining(int distance) {
        setTargetDistance(distance);
    }

    public void setTargetDistance(int distance) {
        mTargetDistance = distance;
    }

/*    public void setSpeed(int speed) {
        mSpeed = speed;
    }*/

    public int calculateTime() {
        int speed = getSpeed();
        int time = 0;
        Log.d(TAG, "calculateTime speed" + speed + " time " + time);
        if(0 == speed) {
            return -1;
        }
        time = (int) (mTargetDistance/speed);
        return time;
    }
}