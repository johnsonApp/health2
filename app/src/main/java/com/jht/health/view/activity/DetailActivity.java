package com.jht.health.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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

import com.jht.health.R;
import com.jht.health.TrainingContract;
import com.jht.health.presenter.DistancePresenter;
import com.jht.health.utils.Utils;

import org.w3c.dom.Text;

import java.util.Set;

public class DetailActivity extends AppCompatActivity implements TrainingContract.View{
    private static final String TAG = Utils.APPLICATION_TAG + "DetailActivity";

    private TrainingContract.Presenter mPresenter;
    private int mTrainingMode;


    private EditText mTargetValue;
    private TextView mModeTrainingLable;
    private EditText mTimeTrainingLable;
    private EditText mSpeedTrainingLable;
    private EditText mDistanceTrainingLable;
    private TextView mWarningLable;

    private Button mStartButton;
    private int mSpeed;
    private int mTime;
    private int mDistance;
    private int mTarget;
    //private TextView mTimeTrainingLable;
    //private TextView mModeTrainingLable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        mTrainingMode = intent.getIntExtra(Utils.TRAINING_MODE,Utils.DEFAULT_MODE);
        mModeTrainingLable = (TextView) findViewById(R.id.mode_information);
        mTargetValue = (EditText) findViewById(R.id.target);

        mTimeTrainingLable = (EditText) findViewById(R.id.time_lable);
        mSpeedTrainingLable = (EditText) findViewById(R.id.speed_lable);
        mDistanceTrainingLable = (EditText) findViewById(R.id.distance_lable);
        mWarningLable = (TextView) findViewById(R.id.warning);
        mStartButton = (Button) findViewById(R.id.start_button);
        mStartButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSpeedInVaild = true;
                boolean isTimeVaild = false;
                boolean isDistanceInVaild = true;
                String targetInput = mTargetValue.getText().toString();
                if(!(null == targetInput ||  "".equals(targetInput))) {
                    mTarget = Integer.parseInt(targetInput);
                    if(0 != mTarget) {
                        isDistanceInVaild = false;
                    }
                }
                if(mTrainingMode == Utils.DISTANCE_MODE) {
                    String speedInput =  mSpeedTrainingLable.getText().toString();
                    if(!(null == speedInput ||  "".equals(speedInput))) {
                        mSpeed = Integer.parseInt(speedInput);
                        if(0 != mSpeed) {
                            isSpeedInVaild = false;
                        }
                    }
                    if(isSpeedInVaild || isDistanceInVaild) {
                        mWarningLable.setText("please input vaild distance target and speed value");
                        return;
                    }else {
                        mWarningLable.setText("");
                    }
                }
                setDetailPreenter(mTrainingMode);
                mPresenter.start();
            }
        });
        mModeTrainingLable.setText("this mode is " + Utils.getTrainingModeString(mTrainingMode) + " please input your target number");
    }

    @Override
    public void setPresenter(@NonNull TrainingContract.Presenter presenter) {
        mPresenter = presenter;
    }

    private void setDetailPreenter(int mode) {
        if(mPresenter != null) {
            mPresenter.setTargetDistance(mDistance);
            mPresenter.setSpeed(mSpeed);
            return;
        }
        TrainingContract.Presenter presenter = null;
        switch (mode) {
            case Utils.TIME_MODE:
            break;
            case Utils.CALORIE_MODE:
            break;
            case Utils.HEARTRATE_MODE:
            break;
            case Utils.DISTANCE_MODE:
            break;
            case Utils.DEFAULT_MODE:
                presenter = new DistancePresenter(this,mDistance,mSpeed);
                break;
            default:
                presenter = new DistancePresenter(this,mDistance,mSpeed);
                break;
        }
        if(null == presenter) {
            presenter = new DistancePresenter(this,mDistance,mSpeed);
        }
        setPresenter(presenter);
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
    public void showDistance(int distance) {

    }
    @Override
    public void showHeartRate(int heartRate) {

    }
    @Override
    public void showIncline(int incline){

    }

}
