package com.baibian.tool;


import com.baibian.bean.CityEntity;
import com.baibian.bean.NewsEntity;
import com.baibian.crawler.CrawlerChannel;

import java.util.ArrayList;
import java.util.List;

//import com.topnews.bean.NewsClassify;

public class Constants {
    /*
     * ��ȡ�����б�
     */
    public static ArrayList<NewsEntity> getNewsList(int position, int channelID, CrawlerChannel crawlerChannel) {
        ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
        for (int i = position; i < 10 + position; i++) {
            NewsEntity news = new NewsEntity();
            news.setId(position);
//			news.setNewsId(i);
            news.setCollectStatus(false);
//			news.setCommentNum(i + 115);
            news.setInterestedStatus(true);
            news.setLikeStatus(true);
            news.setReadStatus(false);
            news.setNewsCategory("�Ƽ�");
            news.setNewsCategoryId(1);
//			news.setSource_url("http://news.ifeng.com/a/20161026/50157377_0.shtml?_zbs_baidu_news");
//			List<String> url_list = new ArrayList<String>();
//
//
//
//			if(i%4 == 1){
//				news.setTitle("���ʺ硱ϵ�е�12���Ʒ�������й���չ");
//				String url1 = "http://images.china.cn/attachement/jpg/site1000/20161027/b8aeed9906a4197b8e0d47.JPG";
//				String url2 = "http://images.china.cn/attachement/jpg/site1000/20161027/b8aeed9906a4197b8e0746.jpg";
//				String url3 = "http://images.china.cn/attachement/jpg/site1000/20161027/b8aeed9906a4197b8e0345.jpg";
//				news.setPicOne(url1);
//				news.setPicTwo(url2);
//				news.setPicThr(url3);
//				news.setSource_url("http://military.china.com.cn/2016-10/27/content_39577023.htm");
//				url_list.add(url1);
//				url_list.add(url2);
//				url_list.add(url3);
//				news.setSource("������");
//			}else if(i%4 == 2){
//				news.setSource("�Ѻ��Ƽ�");
//				news.setTitle("С�׸����ֻ�MIX����3499�� 91.3%��ռ��");
//				news.setSource_url("http://it.sohu.com/20161025/n471268409.shtml");
//				String url = "http://img.mp.itc.cn/upload/20161025/3d4db678cffd41fcb2be4a7f094dfc98_th.jpg";
//				news.setPicOne(url);
////				news.setReadStatus(false);
//				url_list.add(url);
//				news.setComment("�������ۣ������APP������ã���");
//			}else if(i%4 == 3){
//				news.setSource("��˿Ƽ�");
//				news.setTitle("ƻ��������MacBook Pro���ۼ�11488Ԫ��");
//				news.setSource_url("http://tech.ifeng.com/a/20161028/44479615_0.shtml?_zbs_baidu_news");
//				String url = "http://p1.ifengimg.com/a/2016_44/53cfb90128b1190_size109_w1280_h960.jpg";
//				news.setPicOne(url);
//				url_list.add(url);
//			}else if(i%4 == 0){
//				news.setTitle("���絼��");
//				news.setSource("��˿Ƽ�");
//				news.setLocal("�ƹ�");
//				news.setIsLarge(true);
//				String url = "http://123.xidian.edu.cn/img/logo.png";
//				news.setSource_url("http://123.xidian.edu.cn/");
//				news.setNewsAbstract("�������ӿƼ���ѧ������Ϣ�����ѧ��Ϊ�����������ܡ��Ķ�ѧ��Э����չ��ȫ���ص��ѧ��ֱ�������� ���ҡ�����ѧ�ƴ���ƽ̨����Ŀ�͡�211���̡���Ŀ�ص㽨���У֮һ ����35��ʾ�������ѧԺ������9��ʾ����΢����ѧԺ������9�������������ɵ�·�˲��������صĸ�У֮һ��");
//				news.setPicOne(url);
//				url_list.clear();
//				url_list.add(url);
//			}
//			news.setPicList(url_list);
//			news.setPublishTime(Long.valueOf(i));
            news.setReadStatus(false);

            news.setMark(i);

            long currentTimeMillis = System.currentTimeMillis();
//			Log.d("time1", String.valueOf(currentTimeMillis));
            news.setRefreshTime(currentTimeMillis);

//			activity.getSharedPreferences("publishTime", Context.MODE_PRIVATE).edit().putLong("lastTime", currentTimeMillis).commit();
//			�����ǻ������ã��������������á�
            newsList.add(crawlerChannel.ConstantsAdapter(i, channelID, news));
        }
        NewsEntity adsItem = new NewsEntity();
        adsItem.setTitle("AndroidϵͳԴ�����龰����(�޶���)");
//        adsItem.setSource("����ѷ");
        List<String> url_list = new ArrayList<String>();
        String url = "https://images-cn-8.ssl-images-amazon.com/images/I/41f6IhXOn4L._SX373_BO1,204,203,200_.jpg";
        url_list.add(url);
        adsItem.setMark(0);
        adsItem.setPicList(url_list);
        adsItem.setSource_url("https://www.amazon.cn/Android%E7%B3%BB%E7%BB%9F%E6%BA%90%E4%BB%A3%E7%A0%81%E6%83%85%E6%99%AF%E5%88%86%E6%9E%90-%E7%BD%97%E5%8D%87%E9%98%B3/dp/B019FSLVUU");
        adsItem.setLocal("�ƹ�");
        adsItem.setIsLarge(true);
        adsItem.setComment("��������ݣ��������ڱ��ߵ�CSDN���͡������޵�Android֮�ã�ʹ�õ�Դ������Android 2.3��������2012��������������úܶ����Ķ��ߵĿ϶���Ҳ�����Ķ���ϸ��ָ�����еĲ���֮�������߽�ϸ�λ���ߵĿ��󣬶� �������޶��汾��");
        newsList.add(adsItem);
        return newsList;
    }

//	private static void getRefreshTime() {
//
//	}

    /**
     * mark=0 ���Ƽ�
     */
    public final static int mark_recom = 0;
    /**
     * mark=1 ������
     */
    public final static int mark_hot = 1;
    /**
     * mark=2 ���׷�
     */
    public final static int mark_frist = 2;
    /**
     * mark=3 ������
     */
    public final static int mark_exclusive = 3;
    /**
     * mark=4 ���ղ�
     */
    public final static int mark_favor = 4;

    /*
     * ��ȡ�����б�
     */
    public static ArrayList<CityEntity> getCityList() {
        ArrayList<CityEntity> cityList = new ArrayList<CityEntity>();
        CityEntity city1 = new CityEntity(1, "����", 'A');
        CityEntity city2 = new CityEntity(2, "����", 'B');
        CityEntity city3 = new CityEntity(3, "����", 'C');
        CityEntity city4 = new CityEntity(4, "��ɳ", 'C');
        CityEntity city5 = new CityEntity(5, "����", 'D');
        CityEntity city6 = new CityEntity(6, "������", 'H');
        CityEntity city7 = new CityEntity(7, "����", 'H');
        CityEntity city8 = new CityEntity(8, "��ɳ��", 'J');
        CityEntity city9 = new CityEntity(9, "����", 'J');
        CityEntity city10 = new CityEntity(10, "ɽ��", 'S');
        CityEntity city11 = new CityEntity(11, "����", 'S');
        CityEntity city12 = new CityEntity(12, "����", 'Y');
        CityEntity city13 = new CityEntity(13, "��ɽ", 'Z');
        cityList.add(city1);
        cityList.add(city2);
        cityList.add(city3);
        cityList.add(city4);
        cityList.add(city5);
        cityList.add(city6);
        cityList.add(city7);
        cityList.add(city8);
        cityList.add(city9);
        cityList.add(city10);
        cityList.add(city11);
        cityList.add(city12);
        cityList.add(city13);
        return cityList;
    }

    /* Ƶ�������� �纼�� ��Ӧ����ĿID */
    public final static int CHANNEL_CITY = 1;
}
