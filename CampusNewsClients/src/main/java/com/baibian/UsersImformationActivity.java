package com.baibian;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.baibian.adapter.Tablayout_Adapter_Right;
import com.baibian.fragment.BaiBianImformationFragment;
import com.baibian.fragment.BaiBianStatementFragment;
import com.baibian.fragment.ClassificationFragment;
import com.baibian.fragment.FocusFragment;
import com.baibian.fragment.HotFragment;
import com.baibian.fragment.IntegrationFragment;
import com.baibian.fragment.RealTimeFragment;

import java.util.ArrayList;
import java.util.List;

public class UsersImformationActivity extends FragmentActivity {
    private Button choise_direction_back;
    private BaiBianStatementFragment baiBianStatementFragment;
    private BaiBianImformationFragment baiBianImformationFragment;
    private ViewPager users_imformation_pager;
    private TabLayout users_imformation_title;
    private FragmentPagerAdapter fAdapter;                               //定义adapter
    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.usersimformationlayout);
        initview();
        init_right_Tablayout();
        choise_direction_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    private void initview(){
        choise_direction_back=(Button) findViewById(R.id.choise_direction_back);
        users_imformation_pager=(ViewPager) findViewById(R.id.users_imformation_pager);
        users_imformation_title=(TabLayout) findViewById(R.id.users_imformation_title);
    }
    /**
     *  TabLayout的使用
     */
    private void init_right_Tablayout(){
        //初始化各fragment
        baiBianStatementFragment = new BaiBianStatementFragment();
        baiBianImformationFragment = new BaiBianImformationFragment();

        //将fragment装进列表中
        list_fragment = new ArrayList<Fragment>();
        list_fragment.add(baiBianStatementFragment);
        list_fragment.add(baiBianImformationFragment);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add(getString(R.string.BBstate));
        list_title.add(getString(R.string.BBimformation));
        //设置TabLayout的模式
        users_imformation_title.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        users_imformation_title.addTab(users_imformation_title.newTab().setText(list_title.get(0)));
        users_imformation_title.addTab(users_imformation_title.newTab().setText(list_title.get(1)));
        fAdapter = new Tablayout_Adapter_Right(getSupportFragmentManager(),list_fragment,list_title);

        //viewpager加载adapter
        users_imformation_pager.setAdapter(fAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        users_imformation_title.setupWithViewPager(users_imformation_pager);
        //tab_FindFragment_title.set
    }
}
