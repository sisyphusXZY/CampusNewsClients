package com.baibian.fragment;

import android.app.Activity;
import android.content.Context;
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

import com.baibian.CityListActivity;
import com.baibian.DetailsActivity;
import com.baibian.MainActivity;
import com.baibian.R;
import com.baibian.adapter.NewsAdapter;
import com.baibian.bean.NewsEntity;
import com.baibian.crawler.CrawlerChannel;
import com.baibian.tool.Constants;
import com.baibian.view.HeadListView;

import java.util.ArrayList;


public class NewsFragment extends Fragment {
    private final static String TAG = "NewsFragment";
    Activity activity;
    ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
    HeadListView mHeadListView;
    NewsAdapter mAdapter;
    String text;
    int channel_id;
    ImageView detail_loading;
    public final static int SET_NEWSLIST = 0;
    private static int NEWSITEM_ID = 0;
    //Toast��ʾ��
    private RelativeLayout notify_view;
    private TextView notify_view_text;
    private TextView footTitle;
    private int currentPosition;
    private TextView item_title;
    CrawlerChannel crawlerChannel;
    //    //��ʼ��itemʱ����id��ʼ��
//    private ArrayList<Integer> isReaded =new ArrayList<Integer>();
//    private ImageView popicon;
//

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        Bundle args = getArguments();
        text = args != null ? args.getString("text") : "";
        channel_id = args != null ? args.getInt("id", 0) : 0;
        Log.d("channel", String.valueOf(channel_id));
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
     * �˷�����˼Ϊfragment�Ƿ�ɼ� ,�ɼ�ʱ���������
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            //fragment�ɼ�ʱ��������
//            if (newsList != null && newsList.size() != 0) {
//                handler.obtainMessage(SET_NEWSLIST).sendToTarget();
//            } else {

//            ע�͵�����3�У�ʹ��ÿ�����������ʱ2�룬�Դ˵ȴ�listview��ˢ��
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    handler.obtainMessage(SET_NEWSLIST).sendToTarget();
                }
            }).start();
//            }
        } else {
            //fragment���ɼ�ʱ��ִ�в���
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
//
//    public HeadListView getListView(){
////        if (mHeadListView == null) {
////            Log.d("main!!!", String.valueOf(mHeadListView));
////        }
//        return mHeadListView;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.news_fragment, null);
//        Log.d("position2", String.valueOf(view));
        mHeadListView = (HeadListView) view.findViewById(R.id.mListView);
//        Log.d("position3", String.valueOf(mHeadListView));
        TextView item_textView = (TextView) view.findViewById(R.id.item_textview);
        detail_loading = (ImageView) view.findViewById(R.id.detail_loading);
        //Toast��ʾ��
        notify_view = (RelativeLayout) view.findViewById(R.id.notify_view);
        notify_view_text = (TextView) view.findViewById(R.id.notify_view_text);
        item_title = (TextView) view.findViewById(R.id.item_title);
        //pop
//        popicon = (ImageView) view.findViewById(R.id.popicon);
        item_textView.setText(text);
        return view;
    }

    public HeadListView getmHeadListView() {
        return mHeadListView;
    }

    private void initData() {
//        ���������䲻�ܵ���˳����Ҫ�ȳ�ʼ��������
        crawlerChannel = new CrawlerChannel(activity);
//        �ȳ�ʼ���ص��������������ָ��
        handler.obtainMessage(SET_NEWSLIST).sendToTarget();
        newsList = Constants.getNewsList(NEWSITEM_ID, channel_id, crawlerChannel);
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
                        //�ж��ǲ��ǳ��е�Ƶ��
                        if (channel_id == Constants.CHANNEL_CITY) {
                            //�ǳ���Ƶ��
                            mAdapter.setCityChannel(true);
                            initCityChannel();
                        }
                        mHeadListView.setAdapter(mAdapter);
                    } else {
                        mAdapter.notifyDataSetChanged();
                    }

                    ((MainActivity) activity).setOnRefreshListener(new MainActivity.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
//						getDataFromServer();
                            ((MainActivity) getActivity()).rotateTopRefresh();
                            crawlerChannel.pullToRefresh(channel_id);
                        }
                    });

//                  ���û���channel��Ƶ������
                    mHeadListView.setPinnedHeaderView(LayoutInflater.from(activity).inflate(R.layout.list_item_section, mHeadListView, false));

                    crawlerChannel.setOnRefreshListener(new CrawlerChannel.OnRefreshListener() {

                        @Override
                        public void refreshItem(int i) {
//                            0��������ˢ��û���µĶ�̬��
                            if (i == 0) {
                                Toast.makeText(activity, "û�и���������~", Toast.LENGTH_SHORT).show();
                            }

                            handler.obtainMessage(SET_NEWSLIST).sendToTarget();
                        }

                        @Override
                        public void pullRefreshItem(int i) {
                            newsList.clear();
                            NEWSITEM_ID = 0;
                            newsList.addAll(0, Constants.getNewsList(NEWSITEM_ID, channel_id, crawlerChannel));
                            initNotify(i);
                            mHeadListView.onRefreshComplete();
//                            handler.obtainMessage(SET_NEWSLIST).sendToTarget();
                        }
                    });

                    mHeadListView.setOnRefreshListener(new HeadListView.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
//						getDataFromServer();
                            ((MainActivity) getActivity()).rotateTopRefresh();
                            crawlerChannel.pullToRefresh(channel_id);
                        }

                        @Override
                        public void onLoadMore() {
//  -------------------------  loadTime��ʾ����2�Σ�����ĳ������ݾͼ���ˢ��
//                            if (loadTime++ <= 1) {
                            NEWSITEM_ID += 10;
                            newsList.addAll(Constants.getNewsList(NEWSITEM_ID, channel_id, crawlerChannel));
//                            } else {
//                                Toast.makeText(activity, "û��������~", Toast.LENGTH_SHORT).show();
//                            }
                            mHeadListView.onRefreshComplete();
                            handler.obtainMessage(SET_NEWSLIST).sendToTarget();
                        }

                    });

                    mHeadListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                int position, long id) {
//                            Log.d("position1111", String.valueOf(position));
                            currentPosition = position - 1;
//                            Log.d("position", String.valueOf(position));
//                            Log.d("position", String.valueOf(newsList.size()));
//                            �����һ�������һ��
                            if (currentPosition >= 0 && currentPosition != newsList.size()) {

//                                mHeadListView.findViewWithTag()
//                                mHeadListView.getChildAt(currentPosition).findViewById(R.id.popicon).setOnClickListener(new popAction(position));
//                                popicon.setOnClickListener(new popAction(position));
//                                isReaded.add(mAdapter.getItem(currentPosition).getId());

//                                mAdapter.getItem(currentPosition).setReadStatus(true);
//                                ��Ӧ����getSharedPreferences��¼�������񣬵�����Ϊ����������û��Ψһ��item��ʶ�����޷���֤���׼ȷ����ʱ�����ǡ�
//                                Log.d("itemId", String.valueOf(mAdapter.getItem(currentPosition).getId()));
                                activity.getSharedPreferences("publishTime", Context.MODE_PRIVATE).edit().putBoolean(String.valueOf(mAdapter.getItem(currentPosition).getId()), true).commit();
//                                item_title.setTextColor(Color.GRAY);
                                mAdapter.notifyDataSetChanged();
                                Intent intent = new Intent(activity, DetailsActivity.class);
                                if (channel_id == Constants.CHANNEL_CITY) {
                                    if (currentPosition != 0) {
                                        intent.putExtra("itemId", mAdapter.getItem(currentPosition - 1).getId());
                                        intent.putExtra("news", mAdapter.getItem(currentPosition - 1));
//                                        intent.putExtra("collectStatus", mAdapter.getItem(currentPosition - 1).getCollectStatus());
//                                        startActivity(intent);
                                        startActivityForResult(intent, 0);
                                        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                    }
                                } else {
                                    intent.putExtra("itemId", mAdapter.getItem(currentPosition).getId());
                                    intent.putExtra("news", mAdapter.getItem(currentPosition));
//                                    startActivity(intent);
                                    startActivityForResult(intent, 0);
                                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                }
                            }
                        }
                    });
//                    ������Ҫ�ģ�Ӧ������ˢ�µ�ʱ��ŵ���initNotify������
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

//    public void refreshData() {
////        ArrayList<NewsEntity> moreNewsList = Constants.getNewsList(-1, channel_id, crawlerChannel);
////        ArrayList<NewsEntity> moreNewsList = new ArrayList<NewsEntity>();
////                moreNewsList.add(crawlerChannel.ConstantsAdapter(-1, channel_id, new NewsEntity()));
////        this.newsList.addAll(0, moreNewsList);
//
//        if (moreNewsList.size() != 0) {
//            initNotify(moreNewsList.size());
//        } else {
//            initNotify(0);
//        }
//        mHeadListView.onRefreshComplete();
//    }

    /* ��ʼ��ѡ����е�header*/
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
        mHeadListView.addHeaderView(headView);
    }

    /* ��ʼ��֪ͨ��Ŀ������ˢ��֪ͨ*/
    private void initNotify(final int count) {
        new Handler(activity.getMainLooper()).postDelayed(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                if (count == 0) {
                    notify_view_text.setText("���޸���");
                } else {
                    notify_view_text.setText(String.format(getString(R.string.ss_pattern_update), count));
                }
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


    /* �ݻ���ͼ */
    @Override
    public void onDestroyView() {
        // TODO Auto-generated method stub
        super.onDestroyView();
        Log.d("onDestroyView", "channel_id = " + channel_id);
        mAdapter = null;
    }

    /* �ݻٸ�Fragment��һ����FragmentActivity ���ݻٵ�ʱ������Ŵݻ� */
    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d(TAG, "channel_id = " + channel_id);
    }
}
