package com.mycroft.beanlibrary.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

/**
 * Created by Mycroft on 2016/10/14.
 */

public class BeanViewControl extends BeanView{
    private ValueAnimator mValueAnimator;

    public BeanViewControl(Context context) {
        super(context);
        initanimal();
    }



    public BeanViewControl(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initanimal();
    }

    public BeanViewControl(Context context, AttributeSet attrs) {
        super(context, attrs);
        initanimal();
    }

    private void initanimal() {
        mValueAnimator=ValueAnimator.ofFloat(0,1).setDuration(4000);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setPrograss((float)animation.getAnimatedValue());
            }
        });
    }

    public void startAnmition(){
        mValueAnimator.start();
    }


    public void pauseAnition(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mValueAnimator.pause();
        }
    }

    public void stop(){
        mValueAnimator.end();
        mValueAnimator.cancel();
    }
}
