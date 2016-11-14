package com.topnews.crawler;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.topnews.bean.NewsEntity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by XZY on 2016/11/8.
 */

public class CrawlerChannel {

    private static final String BASE_URL = "http://blog.csdn.net/newarticle.html";
    private static final String BASE_URL1 = "http://blog.csdn.net";
    private static ArrayList<String> channelList = new ArrayList<String>();
    private Activity channelActivity;
    private static int refreshPage = 1;
    private static Elements item;
    private static String channelURL;

    public CrawlerChannel(final Activity channelActivity) {
        this.channelActivity = channelActivity;
        item = null;
    }

//    public void getChannel() {
//        final String[] htmlStr = new String[1];
//        new Thread() {
//            public void run() {
//
//            }
//        }.start();
//    }

    public interface OnRefreshListener {
       public void refreshItem(int i);
    }

    private OnRefreshListener mListener;

    public void setOnRefreshListener(OnRefreshListener listener) {
        mListener = listener;
    }


    public NewsEntity ConstantsAdapter(final int itemId, int channelId, final NewsEntity news) {
        final String[] htmlStr = new String[1];
        final int index = channelId - 1;

        new Thread() {
            public void run() {

                htmlStr[0] = HttpTool.doGet(BASE_URL);
//                System.out.println(htmlStr[0]);
                Document doc = Jsoup.parse(htmlStr[0]);

//        String titleStr = nav2.text();

                //        doc.getElementById("");
//        doc.getelem
//        Elements loaded = doc.getElementsByClass("hb-loaded");
//                Elements body = doc.getElementsByTag("body");
//                System.out.println(body);
//                Elements body1 = doc.getElementsByClass("blog_category");
//        System.out.println(body1);
                Elements li = doc.getElementsByTag("li");
                //                Elements nav2 = nav.get(0).getElementsByClass("nav2");
//                Elements ul = nav2.get(0).getElementsByTag("ul");
//                Elements li = ul.get(0).getElementsByTag("li");
                int num = 1;
                for (int i = 8; i < 13; i++) {
                    Element child = li.get(i).child(0);
                    Element child1 = li.get(i).child(1);
//                    System.out.println(child.attr("href"));
//                    String text2 = child.text();
                    String text = child.text();
                    String href = child.attr("href");
                    channelActivity.getSharedPreferences("channel", Context.MODE_PRIVATE).edit().putString(String.valueOf(num), text).commit();
//                    channelActivity.getSharedPreferences("channelSource", Context.MODE_PRIVATE).edit().putString(String.valueOf(num), BASE_URL1 + href).commit();
                    channelList.add(BASE_URL1 + href);
                    num++;
                    text = child1.text();
                    href = child1.attr("href");
                    channelActivity.getSharedPreferences("channel", Context.MODE_PRIVATE).edit().putString(String.valueOf(num), text).commit();
//                    channelActivity.getSharedPreferences("channelSource", Context.MODE_PRIVATE).edit().putString(String.valueOf(num), BASE_URL1 + href).commit();
                    channelList.add(BASE_URL1 + href);
                    num++;
//            String text = li.get(i).;
//                    System.out.println(BASE_URL1 + href);
//                    Log.d("url", BASE_URL1 + href);
                }
                System.out.println(item);
                if (item == null) {
                    Log.d("1111", String.valueOf(111111));
                    channelURL = channelList.get(index);
                    System.out.println(channelURL);
                    htmlStr[0] = HttpTool.doGet(channelURL + "?&page=" + refreshPage);
                    doc = Jsoup.parse(htmlStr[0]);
                    item = doc.getElementsByTag("dl");
                    Log.d("size", String.valueOf(item.size()));
                }

                Log.d("debug", String.valueOf(5));

                List<String> url_list = new ArrayList<String>();
                if (item.size() - 6 < itemId) {
                    Log.d("debug", String.valueOf(7));

                    refreshPage++;
//                    Log.d("url", channelURL + "?&page=" + refreshPage);
                    htmlStr[0] = HttpTool.doGet(channelURL + "?&page=" + refreshPage);
                    doc = Jsoup.parse(htmlStr[0]);
                    item.addAll(item.size() - 6, doc.getElementsByTag("dl"));
                }
                if (itemId == -1) {
                    Log.d("debug", String.valueOf(8));

                    htmlStr[0] = HttpTool.doGet(channelURL + "?&page=" + 1);
                    doc = Jsoup.parse(htmlStr[0]);
                    Elements refreshItem = doc.getElementsByTag("dl");
                    int indexOf = refreshItem.indexOf(item.get(0));
                    if (indexOf != -1) {
                        refreshItem.addAll(indexOf, item);
                        item = refreshItem;
                    } else {
                        mListener.refreshItem(0);
                    }
                } else {
                    Log.d("debug", String.valueOf(9));

//                以下if内用于加载下一个网页
                    Element element = item.get(itemId);
                    Elements a = element.getElementsByTag("a");

                    url_list.add(a.get(0).getElementsByTag("img").get(0).attr("src"));
//                Log.d("img", a.get(0).attr("src"));
//                news.setPicOne(a.get(0).attr("src"));
                    news.setPicList(url_list);


//                Log.d("img", a.get(1).text());

                    news.setSource_url(a.get(2).attr("href"));

                    news.setNewsAbstract(element.getElementsByClass("blog_list_c").text());
                    Elements label = element.getElementsByTag("label");
                    if (label.size() == 0) {
                        news.setTitle(a.get(1).text());
                        news.setPublishTime(" ");
                        news.setLocal("推广");
                        news.setIsLarge(true);
                        news.setComment("本书的内容，初稿自于笔者的CSDN博客——老罗的Android之旅，使用的源代码是Android 2.3，本书自2012年上市以来，获得很多热心读者的肯定，也有热心读者细心指出书中的不妥之处，作者结合各位读者的勘误，对 版做了修订版本。");
                    } else {
                        news.setSource(a.get(1).text());
                        news.setTitle(a.get(2).text());
                        news.setPublishTime(String.valueOf(label.get(0).text()));
                    }
                    Elements em = element.getElementsByTag("em");
                    if (em.size() == 0) {
                        news.setCommentNum(0);
                    } else {
                        news.setCommentNum(Integer.valueOf(element.getElementsByTag("em").get(0).text()));
                    }
                }
                mListener.refreshItem(1);
//                }
//                    System.out.println(item);
//                for (int i = 0; i < item.size(); i++) {
//                    System.out.println(item.get(i));
//                    System.out.println("-----------------------"+ i + "-----------------------------------");
//                }
            }
        }.start();
        return news;
    }

}
