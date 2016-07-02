package com.huawei.ottvideo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        OttVideoView ovv = (OttVideoView)findViewById(R.id.ovv);
        ovv.setPath("/sdcard/11.mp4","ww");
    }
}
