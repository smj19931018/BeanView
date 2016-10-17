package com.mycroft.beandemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mycroft.beandemo.view.BeanViewControl;

public class MainActivity extends AppCompatActivity {
    private BeanViewControl mBeanViewControl;
    private boolean isstart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBeanViewControl= (BeanViewControl) findViewById(R.id.animalview);


    }

    public void start(View view) {
        if (isstart){
            isstart=false;
            mBeanViewControl.stop();
        }else {
            isstart=true;
            mBeanViewControl.startAnmition();
            mBeanViewControl.setText("正在下载..");
        }
    }
}
