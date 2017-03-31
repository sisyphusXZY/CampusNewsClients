package com.baibian.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * DrawerView�Ҳ�Tablayout��������
 */
public class Tablayout_Adapter_Right extends FragmentPagerAdapter {

    private List<Fragment> list_fragment;                         //fragment�б�
    private List<String> list_Title;                              //tab�����б�



    public Tablayout_Adapter_Right(FragmentManager fm, List<Fragment> list_fragment, List<String> list_Title) {
        super(fm);
        this.list_fragment = list_fragment;
        this.list_Title = list_Title;
    }

    @Override
    public Fragment getItem(int position) {
        return list_fragment.get(position);
    }

    @Override
    public int getCount() {
        return list_Title.size();
    }

    //�˷���������ʾtab�ϵ�����
    @Override
    public CharSequence getPageTitle(int position) {

        return list_Title.get(position % list_Title.size());
    }
}