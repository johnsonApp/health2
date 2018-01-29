package com.jht.health.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.jht.health.R;
import com.jht.health.utils.Utils;

import java.io.UTFDataFormatException;

public class MainActivity extends AppCompatActivity implements OnClickListener{
    private TextView mTextMessage;
    private Button mTimeTrainingButton;
    private Button mCalorieTrainingButton;
    private Button mHeartRateTrainingButton;
    private Button mDistanceTrainingButton;
    private Button mFreeeTrainingButton;
    private int anInt;

    /*private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };*/

    @Override
    public void onClick(View v) {
        Log.d("YJ","onClick " + v.getId());
        int mode = Utils.DEFAULT_MODE;
        switch (v.getId()) {
            case R.id.time_training:
                mode = Utils.TIME_MODE;
                break;
            case R.id.calorie_training:
                mode = Utils.CALORIE_MODE;
                break;
            case R.id.heartrate_training:
                mode = Utils.HEARTRATE_MODE;
                break;
            case R.id.distance_training:
                mode = Utils.DISTANCE_MODE;
                break;
            case R.id.free_training:
                mode = Utils.FREE_MODE;
                break;
            default:
                break;
        }
        showDetailActivity(mode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        mTimeTrainingButton = (Button) findViewById(R.id.time_training);
        mTimeTrainingButton.setOnClickListener(this);
        mCalorieTrainingButton = (Button) findViewById(R.id.calorie_training);
        mCalorieTrainingButton.setOnClickListener(this);
        mHeartRateTrainingButton = (Button) findViewById(R.id.heartrate_training);
        mHeartRateTrainingButton.setOnClickListener(this);
        mDistanceTrainingButton = (Button) findViewById(R.id.distance_training);
        mDistanceTrainingButton.setOnClickListener(this);
        mFreeeTrainingButton = (Button) findViewById(R.id.free_training);
        mFreeeTrainingButton.setOnClickListener(this);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void showDetailActivity(int mode) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Utils.TRAINING_MODE,mode);
        startActivity(intent);
    }
}
