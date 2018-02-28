package com.jht.health.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.FontRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.os.Message;

import com.jht.health.R;
import com.jht.health.TrainingContract;
import com.jht.health.presenter.*;
import com.jht.health.utils.Utils;

import com.jht.health.training.CalorieTraining;
import org.w3c.dom.Text;

import java.util.Set;

import javax.sql.StatementEvent;

public class DetailActivity extends AppCompatActivity implements TrainingContract.View{
    private static final String TAG = Utils.APPLICATION_TAG + "DetailActivity";

    private static final int UPDATE_TIME_DISTANCE = 1;

    private static final String TIME = "time";
    private static final String DISTANCE = "distance";
    private static final String CALORIE = "calorie";

    private TrainingContract.Presenter mPresenter;
    private int mTrainingMode;


    private EditText mTargetValue;
    private TextView mModeTrainingLable;
    private TextView mTimeTrainingLable;
    private EditText mSpeedTrainingLable;
    private TextView mDistanceTrainingLable;
    private TextView mCalorieTrainingLable;
    private TextView mWarningLable;

    private Button mStartButton;
    private Button mPauseButton;
    private Button mStopButton;

    private boolean mPauseStatue = false;
    private int mSpeed;
    private int mTime;
    private int mDistance;
    private double mCalorie;
    private int mTarget;
    //private TextView mTimeTrainingLable;
    //private TextView mModeTrainingLable;

    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        mTrainingMode = intent.getIntExtra(Utils.TRAINING_MODE,Utils.DEFAULT_MODE);
        mModeTrainingLable = findViewById(R.id.mode_information);
        mTargetValue = findViewById(R.id.target);

        mTimeTrainingLable = findViewById(R.id.time_lable);
        mSpeedTrainingLable = findViewById(R.id.speed_lable);
        mDistanceTrainingLable = findViewById(R.id.distance_lable);
        mCalorieTrainingLable = findViewById(R.id.calorie_lable);

        mWarningLable = findViewById(R.id.warning);
        mStartButton = findViewById(R.id.start_button);
        mStartButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"mStartButton clicked");
                boolean isSpeedInVaild = true;
                //boolean isTimeVaild = false;
                boolean isTargetInVaild = true;
                String targetInput = mTargetValue.getText().toString();
                if(Utils.FREE_MODE == mTrainingMode) {
                    isTargetInVaild = false;
                }else if(!(null == targetInput || "".equals(targetInput))) {
                    mTarget = Integer.parseInt(targetInput);
                    if(0 != mTarget) {
                        isTargetInVaild = false;
                    }
                }
                String speedInput =  mSpeedTrainingLable.getText().toString();
                if(!(null == speedInput ||  "".equals(speedInput))) {
                    mSpeed = Integer.parseInt(speedInput);
                    if(0 != mSpeed) {
                        isSpeedInVaild = false;
                    }
                }
                if(isSpeedInVaild || isTargetInVaild) {
                    mWarningLable.setText("please input vaild target and speed value");
                    return;
                }else {
                    mWarningLable.setText("");
                }

                setDetailPreenter(mTrainingMode);
                startRun();
                //mPresenter.start();
            }
        });
        mPauseButton = (Button) findViewById(R.id.pause_resume_button);
        mPauseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view){
                Log.d(TAG,"pause button click " + mPauseStatue);
                if(null == mPresenter) {
                    return;
                }
                String temp = "pause";
                if(mPauseStatue) {
                    mPresenter.setResume();
                }else {
                    mPresenter.setPause();
                    temp = "resume";
                }
                mPauseButton.setText(temp);
                mPauseStatue = !mPauseStatue;
            }
        });
        mStopButton = (Button) findViewById(R.id.stop_button);
        mStopButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"stop button clicked");
                if(null != mPresenter) {
                    mPresenter.end();
                }
                mTargetValue.setText("");
                mTimeTrainingLable.setText("");
                mSpeedTrainingLable.setText("");
                mDistanceTrainingLable.setText("");
                mCalorieTrainingLable.setText("");
                mWarningLable.setText("It has stoped");
            }
        });
        if(Utils.FREE_MODE == mTrainingMode) {
            mTargetValue.setEnabled(false);
        }
        mModeTrainingLable.setText("this mode is " + Utils.getTrainingModeString(mTrainingMode) + " please input your target number");
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what){
                    case UPDATE_TIME_DISTANCE:
                        String time = msg.getData().getString(TIME);
                        String distance = msg.getData().getString(DISTANCE);
                        String calorie = msg.getData().getString(CALORIE);
                        mTimeTrainingLable.setText(time);
                        mDistanceTrainingLable.setText(distance);
                        mCalorieTrainingLable.setText(calorie);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(null != mPresenter) {
            mPresenter.end();
            mPresenter = null;
        }
    }

    @Override
    public void setPresenter(@NonNull TrainingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void setDetailPreenter(int mode) {
        if(mPresenter != null) {
            mPresenter.setTarget(mTarget);
            mPresenter.setSpeed(mSpeed);
            return;
        }
        TrainingContract.Presenter presenter = null;
        switch (mode) {
            case Utils.TIME_MODE:
                presenter = new TimePresenter(this,mTarget,mSpeed);
            break;
            case Utils.CALORIE_MODE:
                presenter = new CaloriePresenter(this,mTarget,mSpeed);
            break;
            case Utils.HEARTRATE_MODE:
                presenter = new HeartRatePresenter(this,mTarget,mSpeed);
            break;
            case Utils.DISTANCE_MODE:
                presenter = new DistancePresenter(this,mTarget,mSpeed);
            break;
            case Utils.FREE_MODE:
                presenter = new FreePresenter(this, mSpeed);
                break;
            default:
                presenter = new DistancePresenter(this,mTarget,mSpeed);
                break;
        }
        if(null == presenter) {
            presenter = new DistancePresenter(this,mTarget,mSpeed);
        }
        setPresenter(presenter);
    }

    @Override
    public void updateInformation(long time, long distance) {
        sendUpdateTimeMessage(time, distance);
    }

    private void sendUpdateTimeMessage(long time, long distance) {
        Message msg = new Message();
        msg.what = UPDATE_TIME_DISTANCE;
        Bundle bundle = new Bundle();
        bundle.putString(TIME, String.valueOf(time));
        bundle.putString(DISTANCE, String.valueOf(distance));
        bundle.putString(CALORIE, String.valueOf((time * mCalorie * 1000)/60));
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    }
    @Override
    public void showErrorInfo() {
        if(null != mWarningLable) {
            mWarningLable.setText("args is wrong need vaild value");
        }
    }
    @Override
    public void showDetails() {

    }
    @Override
    public void showTime(String time) {
        mTimeTrainingLable.setText(time);
    }
    @Override
    public void showSpeed(int speed) {

    }
    @Override
    public void showDistance(String distance) {
        mDistanceTrainingLable.setText(distance);
    }
    @Override
    public void showHeartRate(int heartRate) {

    }
    @Override
    public void showIncline(int incline){

    }

    private void startRun() {
        if(null != mPresenter) {
            mPresenter.start();
            mCalorie = CalorieTraining.calculateCalorieValue(mSpeed, 1, 50);
            Log.d(TAG,"start Run mCalorie " + mCalorie);
            //mTargetValue.setEnabled(false);
            //mTimeTrainingLable.setEnabled(false);
            //mDistanceTrainingLable.setEnabled(false);
        }
    }
}
