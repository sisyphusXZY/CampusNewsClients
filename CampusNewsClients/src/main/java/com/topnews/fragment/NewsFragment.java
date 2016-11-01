package com.topnews.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.topnews.CityListActivity;
import com.topnews.DetailsActivity;
import com.topnews.MainActivity;
import com.topnews.R;
import com.topnews.adapter.NewsAdapter;
import com.topnews.bean.NewsEntity;
import com.topnews.tool.Constants;
import com.topnews.view.HeadListView;

import java.util.ArrayList;

public class NewsFragment extends Fragment {
    private final static String TAG = "NewsFragment";
    Activity activity;
    ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
    HeadListView mListView;
    NewsAdapter mAdapter;
    String text;
    int channel_id;
    ImageView detail_loading;
    public final static int SET_NEWSLIST = 0;
    //Toast提示框
    private RelativeLayout notify_view;
    private TextView notify_view_text;
    private TextView footTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Bundle args = getArguments();
        text = args != null ? args.getString("text") : "";
        channel_id = args != null ? args.getInt("id", 0) : 0;
        initData();
//        getFragmentManager().beginTransaction()
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        this.activity = activity;
        super.onAttach(activity);
    }

    /**
     * 此方法意思为fragment是否可见 ,可见时候加载数据
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            //fragment可见时加载数据
            if (newsList != null && newsList.size() != 0) {
                handler.obtainMessage(SET_NEWSLIST).sendToTarget();
            } else {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        try {
                            Thread.sleep(2);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        handler.obtainMessage(SET_NEWSLIST).sendToTarget();
                    }
                }).start();
            }
        } else {
            //fragment不可见时不执行操作
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
//
//    public HeadListView getListView(){
////        if (mListView == null) {
////            Log.d("main!!!", String.valueOf(mListView));
////        }
//        return mListView;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.news_fragment, null);
//        Log.d("position2", String.valueOf(view));
        mListView = (HeadListView) view.findViewById(R.id.mListView);
//        Log.d("position3", String.valueOf(mListView));
        TextView item_textView = (TextView) view.findViewById(R.id.item_textview);
        detail_loading = (ImageView) view.findViewById(R.id.detail_loading);
        //Toast提示框
        notify_view = (RelativeLayout) view.findViewById(R.id.notify_view);
        notify_view_text = (TextView) view.findViewById(R.id.notify_view_text);
        item_textView.setText(text);
        return view;
    }

    public HeadListView getmListView(){
        return mListView;
    }

    private void initData() {
        newsList = Constants.getNewsList(activity);
    }

    private int loadTime = 0;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            switch (msg.what) {
                case SET_NEWSLIST:
                    detail_loading.setVisibility(View.GONE);
                    if (mAdapter == null) {
                        mAdapter = new NewsAdapter(activity, newsList);
                        //判断是不是城市的频道
                        if (channel_id == Constants.CHANNEL_CITY) {
                            //是城市频道
                            mAdapter.setCityChannel(true);
                            initCityChannel();
                        }
                    }
                    mListView.setAdapter(mAdapter);
//				mListView.setOnScrollListener(mAdapter);
//                  设置滑动channel（频道）条
                    mListView.setPinnedHeaderView(LayoutInflater.from(activity).inflate(R.layout.list_item_section, mListView, false));


                    mListView.setOnRefreshListener(new HeadListView.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
//						getDataFromServer();
                            ((MainActivity)getActivity()).rotateTopRefresh();
                            refreshData();
                        }

                        @Override
                        public void onLoadMore() {
                            if (loadTime++ <= 1) {
//                                new Thread(new Runnable() {
//                                    public void run() {
//                                        try {
//                                            Thread.sleep(5000);
//                                        } catch (InterruptedException e) {
//                                            e.printStackTrace();
//                                        }
//                                        handler.sendMessage(handler.obtainMessage(SET_NEWSLIST));
//                                    }
//                                });
                                newsList.addAll(Constants.getNewsList(activity));
                            } else {
                                Toast.makeText(activity, "没有新闻啦~", Toast.LENGTH_SHORT).show();

                            }
//                            mAdapter.notifyDataSetChanged();
                            mListView.onRefreshComplete();
                        }

                    });
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
//                            Log.d("position1111", String.valueOf(position));
                            position--;
//                            Log.d("position", String.valueOf(position));
//                            Log.d("position", String.valueOf(newsList.size()));
//                            禁点第一个和最后一个
                            if (position >= 0 && position != newsList.size()) {
                                Intent intent = new Intent(activity, DetailsActivity.class);
                                if (channel_id == Constants.CHANNEL_CITY) {
                                    if (position != 0) {
                                        intent.putExtra("news", mAdapter.getItem(position - 1));
                                        startActivity(intent);
                                        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                    }
                                } else {
                                    intent.putExtra("news", mAdapter.getItem(position));
                                    startActivity(intent);
                                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                            }
                        }
                    });
//                    这句后面要改，应该是有刷新的时候才调用initNotify（），
//                    if (channel_id == 1) {
//                    initNotify();
//                    }
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public void refreshData() {
        ArrayList<NewsEntity> moreNewsList = Constants.getNewsList(activity);
        this.newsList.addAll(0, moreNewsList);
        initNotify(moreNewsList.size());
        mListView.onRefreshComplete();
    }

    /* 初始化选择城市的header*/
    public void initCityChannel() {
        View headView = LayoutInflater.from(activity).inflate(R.layout.city_category_list_tip, null);
        TextView chose_city_tip = (TextView) headView.findViewById(R.id.chose_city_tip);
        chose_city_tip.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(activity, CityListActivity.class);
                startActivity(intent);
            }
        });
        mListView.addHeaderView(headView);
    }

    /* 初始化通知栏目，发送刷新通知*/
    private void initNotify(final int count) {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                notify_view_text.setText(String.format(getString(R.string.ss_pattern_update), count));
                notify_view.setVisibility(View.VISIBLE);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        notify_view.setVisibility(View.GONE);
                    }
                }, 2000);
            }
        }, 1000);
    }
//
//    private void initNotifyNoMoreNews() {
//        Log.d("initNotifyNoMoreNews", "here");
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                footTitle.setText("没有更多新闻");
//            }
//        }, 1000);
//    }


    /* 摧毁视图 */
    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        Log.d("onDestroyView", "channel_id = " + channel_id);
        mAdapter = null;
    }

    /* 摧毁该Fragment，一般是FragmentActivity 被摧毁的时候伴随着摧毁 */
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d(TAG, "channel_id = " + channel_id);
    }
}
