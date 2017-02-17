package com.baibian.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baibian.R;


public class ForumsFragment extends Fragment{
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View ForumsFragment = inflater.inflate(R.layout.forums_layout, container, false);
        return ForumsFragment;
    }
}
