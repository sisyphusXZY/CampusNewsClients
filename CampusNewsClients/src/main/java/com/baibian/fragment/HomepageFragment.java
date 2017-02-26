package com.baibian.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baibian.ChannelActivity;
import com.baibian.MainActivity;
import com.baibian.R;
import com.baibian.adapter.Homepage_ListAdapter;
import com.baibian.adapter.NewsFragmentPagerAdapter;
import com.baibian.app.AppApplication;
import com.baibian.bean.ChannelItem;
import com.baibian.bean.ChannelManage;
import com.baibian.load.refresh;
import com.baibian.view.ColumnHorizontalScrollView;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomepageFragment extends Fragment {

    private EditText mEditText;
    private ListView mListView;
    private List<Map<String, Object>> data;
    private Context context;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=activity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                                                             Bundle savedInstanceState) {
    View HomepageFragment = inflater.inflate(R.layout.homepage_layout, container, false);

        mListView = (ListView) HomepageFragment.findViewById(R.id.homepage_listview);
        getData();
        mListView.setAdapter(new Homepage_ListAdapter(context, data));

    return HomepageFragment;
}


    private void getData() {
        data = new ArrayList<Map<String, Object>>();
        for (int i = 1; i <= 20; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", getString(R.string.cancel) + i);
            data.add(map);
        }
    }

}
