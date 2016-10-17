package com.mycroft.beandemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.mycroft.beandemo.R;

/**
 * Created by Mycroft on 2016/10/14.
 */

public class BeanView extends View {
    private float prograss;
    private float beanArcradius;
    private float viewWrith;
    private float viewheight;
    private float beanpointX;
    private float beanEyePonitY;
    private int color;
    private float beanArc=280;
    private Paint beanPaint;
    private float arcBegin=40;
    private Context mContext;
    private final float littlebeanradius=5;
    private final float littlebeanbetween=10;
    private boolean israise=true;
    private String text;
    private int textColor;
    private Paint mTextPaint;
    private int mAlpha;
    private int textWirth;
    private int textHeight;
    private boolean israise2;
    private float textSize;
    public BeanView(Context context) {
        super(context);
        mContext=context;
    }

    public BeanView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext=context;
        initView(context,attrs);
    }



    public BeanView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext=context;
        initView(context,attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewheight=h;
        viewWrith=w;
        beanArcradius=h/4;
        beanpointX=beanArcradius;
        beanEyePonitY=viewheight/8*3;
    }

    private void initView(Context context, AttributeSet attrs) {
        setBackground(null);
        color=Color.BLACK;
        textColor=Color.BLACK;
        mAlpha=255;
        if (attrs!=null){
            TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.BeanViewattr);
            color=typedArray.getColor(R.styleable.BeanViewattr_beancolor,Color.BLACK);
            textColor=typedArray.getColor(R.styleable.BeanViewattr_textcolor,Color.BLACK);
            text=typedArray.getString(R.styleable.BeanViewattr_text);
            textSize=typedArray.getDimensionPixelSize(R.styleable.BeanViewattr_textSize,15);
        }
        beanPaint=new Paint();
        beanPaint.setStyle(Paint.Style.FILL);
        beanPaint.setAntiAlias(true);
        beanPaint.setColor(color);
        mTextPaint=new Paint();
        mTextPaint.setStrokeCap(Paint.Cap.ROUND);
        mTextPaint.setTextSize(textSize);
        mTextPaint.setColor(textColor);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setAlpha(mAlpha);


    }

    private void drawbean(Canvas canvas) {
        int count=((int)(viewWrith-(beanpointX+beanArcradius)))/(dpToPx(mContext,littlebeanbetween)+dpToPx(mContext,littlebeanradius)*2);
        for(int i=1;i<=count+1;i++){
            canvas.drawCircle(viewWrith-(dpToPx(mContext,littlebeanradius)+(i-1)*(2*dpToPx(mContext,littlebeanradius)+dpToPx(mContext,littlebeanbetween)))/*i*(dpToPx(mContext,littlebeanbetween)+dpToPx(mContext,littlebeanradius))+(i-1)*dpToPx(mContext,littlebeanradius)+beanpointX+beanArcradius*/,viewheight/2-viewheight/4,littlebeanradius,beanPaint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBeanFace(canvas);
        drawbean(canvas);
        if (text!=null&&!TextUtils.isEmpty(text)){
            String temp=text+(int)(prograss*100)+"%";
            Rect rect=new Rect();
            mTextPaint.getTextBounds(temp,0,temp.length(),rect);
            textWirth=rect.width();
            textHeight=rect.height();
        }
      /*  if (israise){
            beanArc+=1;
            if (beanArc>359){
                israise=false;
            }
        }else {
            beanArc-=1;
            if (beanArc<320){
                israise=true;
            }
        }*/
        if (textHeight<viewheight/3&&text!=null&&!TextUtils.isEmpty(text)){
            Log.e("aaa","textsize="+textSize);
            canvas.drawText(text+(int)(prograss*100)+"%",0,(text+(int)(prograss*100)+"%").length(),(float) (viewWrith-textWirth)/2,viewheight/4*3,mTextPaint);
        }

        if (israise){
            arcBegin-=2;

            if (arcBegin<=0){
                israise=false;
            }
        }else {
            arcBegin+=2;
            if (arcBegin>40){
                israise=true;
            }
        }

        beanArc=360-2*arcBegin;

        if (israise2){
            mAlpha+=10;
            if (mAlpha>=250){
                israise2=false;
            }
        }else {
            mAlpha-=10;
            if (mAlpha<=100){
                israise2=true;
            }
        }
        mTextPaint.setAlpha(mAlpha);

        /*if (beanpointX<viewWrith-beanArcradius){*/
            beanpointX=beanArcradius+prograss*(viewWrith-beanArcradius);
       /* }else {
            beanpointX=beanArcradius;
            Log.e("bbbb","beanArc"+beanpointX);
        }*/

    }

    public void setPrograss(float prograss) {
        this.prograss = prograss;
        invalidate();
    }

    private void drawBeanFace(Canvas canvas) {
        RectF rectF=new RectF(beanpointX-beanArcradius,viewheight/4-viewheight/4,(beanpointX-beanArcradius)+2*beanArcradius,viewheight/4+2*beanArcradius-viewheight/4);

        canvas.drawArc(rectF,arcBegin,beanArc,true,beanPaint);
        beanPaint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(beanpointX,beanEyePonitY-viewheight/4,dpToPx(mContext,2f),beanPaint);
        beanPaint.setColor(color);
    }

    public void setText(String text){
        this.text=text;
        invalidate();
    }

    public int dpToPx(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, context.getResources().getDisplayMetrics());
    }
}
