package com.baibian;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.Button;
import android.view.animation.*;

public class ChoiceDirectionActivity extends Activity {
    private Button choise_direction_back;
    private FrameLayout frameLayout21;
    private FrameLayout frameLayout22;
    private FrameLayout frameLayout31;
    private FrameLayout frameLayout32;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choise_direction_layout);
        initview();
        ImageViewAnimation();
        choise_direction_back.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void initview(){
        choise_direction_back=(Button) findViewById(R.id.choise_direction_back);
        frameLayout21=(FrameLayout) findViewById(R.id.framelayoute21);
        frameLayout22=(FrameLayout) findViewById(R.id.framelayoute22);
        frameLayout31=(FrameLayout) findViewById(R.id.framelayoute31);
        frameLayout32=(FrameLayout) findViewById(R.id.framelayoute32);
    }
    protected void  ImageViewAnimation(){
        /**
         * 从左边进入的
         */
        TranslateAnimation translateAnimationleft=new TranslateAnimation(-300,0,0,0);
        translateAnimationleft.setDuration(700);
        frameLayout21.startAnimation(translateAnimationleft);//开始动画
        frameLayout31.startAnimation(translateAnimationleft);//开始动画

        /**
         * 从右边进入的
         */
        TranslateAnimation translateAnimationright=new TranslateAnimation(300,0,0,0);
        translateAnimationright.setDuration(700);
        frameLayout22.startAnimation(translateAnimationright);//开始动画
        frameLayout32.startAnimation(translateAnimationright);//开始动画
    }
}
