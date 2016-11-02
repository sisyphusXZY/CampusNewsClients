package com.topnews.tool;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.topnews.bean.CityEntity;
import com.topnews.bean.NewsEntity;

import java.util.ArrayList;
import java.util.List;

//import com.topnews.bean.NewsClassify;

public class Constants {
	/*
	 * 获取新闻列表
	 */
	public static ArrayList<NewsEntity> getNewsList(Activity activity) {
		ArrayList<NewsEntity> newsList = new ArrayList<NewsEntity>();
		for(int i =1 ; i <= 10 ; i++){
			NewsEntity news = new NewsEntity();
			news.setId(i);
			news.setNewsId(i);
			news.setCollectStatus(false);
			news.setCommentNum(i + 115);
			news.setInterestedStatus(true);
			news.setLikeStatus(true);
			news.setReadStatus(false);
			news.setNewsCategory("推荐");
			news.setNewsCategoryId(1);
//			news.setSource_url("http://news.ifeng.com/a/20161026/50157377_0.shtml?_zbs_baidu_news");
			List<String> url_list = new ArrayList<String>();
			if(i%4 == 1){
				news.setTitle("“彩虹”系列的12款产品将亮相中国航展");
				String url1 = "http://images.china.cn/attachement/jpg/site1000/20161027/b8aeed9906a4197b8e0d47.JPG";
				String url2 = "http://images.china.cn/attachement/jpg/site1000/20161027/b8aeed9906a4197b8e0746.jpg";
				String url3 = "http://images.china.cn/attachement/jpg/site1000/20161027/b8aeed9906a4197b8e0345.jpg";
				news.setPicOne(url1);
				news.setPicTwo(url2);
				news.setPicThr(url3);
				news.setSource_url("http://military.china.com.cn/2016-10/27/content_39577023.htm");
				url_list.add(url1);
				url_list.add(url2);
				url_list.add(url3);
				news.setSource("人民网");
			}else if(i%4 == 2){
				news.setSource("搜狐科技");
				news.setTitle("小米概念手机MIX发布3499起 91.3%屏占比");
				news.setSource_url("http://it.sohu.com/20161025/n471268409.shtml");
				String url = "http://img.mp.itc.cn/upload/20161025/3d4db678cffd41fcb2be4a7f094dfc98_th.jpg";
				news.setPicOne(url);
//				news.setReadStatus(false);
				url_list.add(url);
				news.setComment("我是评论！！这个APP做的真好！！");
			}else if(i%4 == 3){
				news.setSource("凤凰科技");
				news.setTitle("苹果发布新MacBook Pro：售价11488元起");
				news.setSource_url("http://tech.ifeng.com/a/20161028/44479615_0.shtml?_zbs_baidu_news");
				String url = "http://p1.ifengimg.com/a/2016_44/53cfb90128b1190_size109_w1280_h960.jpg";
				news.setPicOne(url);
				url_list.add(url);
			}else if(i%4 == 0){
				news.setTitle("西电导航");
				news.setLocal("推广");
				news.setIsLarge(true);
				String url = "http://123.xidian.edu.cn/img/logo.png";
				news.setSource_url("http://123.xidian.edu.cn/");
				news.setNewsAbstract("西安电子科技大学是以信息与电子学科为主，工、理、管、文多学科协调发展的全国重点大学，直属教育部 国家“优势学科创新平台”项目和“211工程”项目重点建设高校之一 首批35所示范性软件学院、首批9所示范性微电子学院和首批9所获批设立集成电路人才培养基地的高校之一。");
				news.setPicOne(url);
				url_list.clear();
				url_list.add(url);
			}
			news.setPicList(url_list);
//			news.setPublishTime(Long.valueOf(i));
			news.setReadStatus(false);

			news.setMark(i);
//			if(i == 4){
//				news.setTitle("西电导航");
//				news.setLocal("推广");
//				news.setIsLarge(true);
//				String url = "http://123.xidian.edu.cn/img/logo.png";
//				news.setSource_url("http://123.xidian.edu.cn/");
//				news.setPicOne(url);
//				url_list.clear();
//				url_list.add(url);
//			}else{
//				news.setIsLarge(false);
//			}
//			if(i == 2){
//				news.setComment("我是评论！！！！这个APP做的真好！！");
//			}
			//此处设置时间。应该记录上次刷新的时间，然后用本次时间-上次刷新时间。
//			getRefreshTime();
//			if(i <= 2){
//				news.setPublishTime((long) 3);
//			}else if(i >2 && i <= 5){
//				news.setPublishTime((long) 18);
//			}else{
//				news.setPublishTime((long) 22);
//			}
			long currentTimeMillis = System.currentTimeMillis();
			Log.d("time1", String.valueOf(currentTimeMillis));
			news.setPublishTime(currentTimeMillis);

			activity.getSharedPreferences("publishTime", Context.MODE_PRIVATE).edit().putLong("lastTime", currentTimeMillis).commit();
			newsList.add(news);
		}
		return newsList;
	}

//	private static void getRefreshTime() {
//
//	}

	/** mark=0 ：推荐 */
	public final static int mark_recom = 0;
	/** mark=1 ：热门 */
	public final static int mark_hot = 1;
	/** mark=2 ：首发 */
	public final static int mark_frist = 2;
	/** mark=3 ：独家 */
	public final static int mark_exclusive = 3;
	/** mark=4 ：收藏 */
	public final static int mark_favor = 4;
	
	/*
	 * 获取城市列表
	 */
	public static ArrayList<CityEntity> getCityList(){
		ArrayList<CityEntity> cityList =new ArrayList<CityEntity>();
		CityEntity city1 = new CityEntity(1, "安吉", 'A');
		CityEntity city2 = new CityEntity(2, "北京", 'B');
		CityEntity city3 = new CityEntity(3, "长春", 'C');
		CityEntity city4 = new CityEntity(4, "长沙", 'C');
		CityEntity city5 = new CityEntity(5, "大连", 'D');
		CityEntity city6 = new CityEntity(6, "哈尔滨", 'H');
		CityEntity city7 = new CityEntity(7, "杭州", 'H');
		CityEntity city8 = new CityEntity(8, "金沙江", 'J');
		CityEntity city9 = new CityEntity(9, "江门", 'J');
		CityEntity city10 = new CityEntity(10, "山东", 'S');
		CityEntity city11 = new CityEntity(11, "三亚", 'S');
		CityEntity city12 = new CityEntity(12, "义乌", 'Y');
		CityEntity city13 = new CityEntity(13, "舟山", 'Z');
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
	/* 频道中区域 如杭州 对应的栏目ID */
	public final static int CHANNEL_CITY = 3;
}
