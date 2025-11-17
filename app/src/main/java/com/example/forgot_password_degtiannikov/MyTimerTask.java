package com.example.myapplication;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
    public int Time = 30;
    public Activity Activity;
    public TextView tvText, tvSendMail;

    public MyTimerTask(Activity activity, TextView tvText, TextView tvSendMail) {
        this.Activity = activity;
        this.tvText = tvText;
        this.tvSendMail = tvSendMail;
    }

    @Override
    public void run() {
        Time--;
        if (Time == 0) this.cancel();

        Activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String seconds = Time >= 10 ? String.valueOf(Time) : "0" + String.valueOf(Time);
                tvText.setText("00:" + seconds);

                if (Time == 0) {
                    tvText.setVisibility(View.GONE);
                    tvSendMail.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}