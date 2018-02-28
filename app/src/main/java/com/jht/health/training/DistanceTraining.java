package  com.jht.health.training;

import android.util.Log;

import com.jht.health.utils.Utils;

public class DistanceTraining extends BaseTraining {
    private static final String TAG = Utils.APPLICATION_TAG + "DistanceTraining";
    private long mTargetDistance = 0;
    private long mNeedTime = 0;

    public DistanceTraining() {
    }

    public DistanceTraining(int distance) {
        setTargetDistance(distance);
    }

    public void setTargetDistance(long distance) {
        mTargetDistance = distance;
    }

/*    public void setSpeed(int speed) {
        mSpeed = speed;
    }*/

    public long calculateTime() {
        int speed = getSpeed();
        //int time = 0;
        Log.d(TAG, "calculateTime distance " + mTargetDistance + " speed " + speed);
        if(0 == speed) {
            return -1;
        }
        mNeedTime = mTargetDistance/speed;
        return mNeedTime;
    }
}