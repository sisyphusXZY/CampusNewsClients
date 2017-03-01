package com.baibian.fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.baibian.R;
import com.baibian.adapter.Homepage_ListAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HomepageFragment extends Fragment  {
    private Button homepage_search_button;
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
        homepage_search_button=(Button) HomepageFragment.findViewById(R.id.homepage_search_button);
        getData();
        mListView.setAdapter(new Homepage_ListAdapter(context, data));
        homepage_search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getActivity(),"123", Toast.LENGTH_SHORT).show();
            }
        });

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
