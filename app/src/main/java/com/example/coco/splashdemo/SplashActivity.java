package com.example.coco.splashdemo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {
    private static final int STARTCOUNT = 10;
    private TextView mTv;
    private int count = 0;
    private int time = 0;
    Timer timer ;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case STARTCOUNT:
                    handler.sendEmptyMessageDelayed(STARTCOUNT, 1000);
                    count += 1000;
                    if (count >= time) {
                        handler.removeMessages(STARTCOUNT);
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
//        initHandler(3000);
//        initTimer(3000);
        initWhile(3000);
    }

    private void initHandler(int time) {
        this.time=time;
        handler.sendEmptyMessage(STARTCOUNT);
    }

    private void initWhile(final int time) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count < time) {
                    SystemClock.sleep(1000);
                    count += 1000;
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (count!=4000){
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        }

                    }
                });
            }
        }).start();
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.mTv3);
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                timer.cancel();
//                timer.purge();
//                count=4000;
//                handler.removeMessages(STARTCOUNT);
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            }
        });
    }


    private void initTimer(final int time) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (count <= time) {
                            count += 1000;
                        } else {
                            timer.cancel();
                            timer.purge();
                            startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        }
                    }
                }, 0, 1000);
            }
        }).start();
    }
}
