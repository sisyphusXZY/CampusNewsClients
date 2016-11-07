package com.topnews.load;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.TextView;

import com.topnews.R;
import com.topnews.fragment.NewsFragment;
import com.topnews.view.HeadListView;

/**
 * Created by XZY on 2016/10/31.
 */

public class refresh{

    public static RotateAnimation refreshAnimation(){
        RotateAnimation rotateAnimation = new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setRepeatCount(1);
        return rotateAnimation;
     }

    public static void refreshHeadView(NewsFragment fragmentByTag){
        HeadListView headListView = fragmentByTag.getmHeadListView();
//                headListView.mCurrentState = STATE_REFRESHING;
//        ((TextView) headListView.findViewById(R.id.tv_title)).setText("æ­£åœ¨åˆ·æ–°...");
        ((TextView) headListView.findViewById(R.id.tv_title)).setText("ÕýÔÚË¢ÐÂ...");
        (headListView.findViewById(R.id.iv_arrow)).clearAnimation();
        (headListView.findViewById(R.id.iv_arrow)).setVisibility(View.INVISIBLE);
        headListView.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
        headListView.getHeaderView().setPadding(0, 0, 0, 0);

    }
}
