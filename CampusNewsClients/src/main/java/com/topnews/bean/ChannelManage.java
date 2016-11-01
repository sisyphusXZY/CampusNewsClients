package com.topnews.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.topnews.dao.ChannelDao;
import com.topnews.db.SQLHelper;

import android.database.SQLException;
import android.util.Log;

public class ChannelManage {
	public static ChannelManage channelManage;
	/**
	 * Ä¬ï¿½Ïµï¿½ï¿½Ã»ï¿½Ñ¡ï¿½ï¿½Æµï¿½ï¿½ï¿½Ð±ï¿½
	 * */
	public static List<ChannelItem> defaultUserChannels;
	/**
	 * Ä¬ï¿½Ïµï¿½ï¿½ï¿½ï¿½ï¿½Æµï¿½ï¿½ï¿½Ð±ï¿½
	 * */
	public static List<ChannelItem> defaultOtherChannels;
	private ChannelDao channelDao;
	/** ï¿½Ð¶ï¿½ï¿½ï¿½ï¿½Ý¿ï¿½ï¿½ï¿½ï¿½Ç·ï¿½ï¿½ï¿½ï¿½ï¿½Ã»ï¿½ï¿½ï¿½ï¿½ï¿? */
	private boolean userExist = false;
	static {
		defaultUserChannels = new ArrayList<ChannelItem>();
		defaultOtherChannels = new ArrayList<ChannelItem>();
		defaultUserChannels.add(new ChannelItem(1, "ÍÆ¼ö", 1, 1));
		defaultUserChannels.add(new ChannelItem(2, "ÈÈÃÅ", 2, 1));
		defaultUserChannels.add(new ChannelItem(3, "Éç»á", 3, 1));
		defaultUserChannels.add(new ChannelItem(4, "Ö±²¥", 4, 1));
		defaultUserChannels.add(new ChannelItem(5, "ÓéÀÖ", 5, 1));
		defaultUserChannels.add(new ChannelItem(6, "¿Æ¼¼", 6, 1));
		defaultUserChannels.add(new ChannelItem(7, "¹ú¼Ê", 7, 1));
		defaultOtherChannels.add(new ChannelItem(8, "¾üÊÂ", 1, 0));
		defaultOtherChannels.add(new ChannelItem(9, "ÊÓÆµ", 2, 0));
		defaultOtherChannels.add(new ChannelItem(10, "ÌåÓý", 3, 0));
		defaultOtherChannels.add(new ChannelItem(11, "²Æ¾­", 4, 0));
		defaultOtherChannels.add(new ChannelItem(12, "ÂÃÓÎ", 5, 0));
		defaultOtherChannels.add(new ChannelItem(13, "ÓÎÏ·", 6, 0));
		defaultOtherChannels.add(new ChannelItem(14, "ÀúÊ·", 7, 0));
		defaultOtherChannels.add(new ChannelItem(15, "¹ÊÊÂ", 8, 0));
		defaultOtherChannels.add(new ChannelItem(16, "Çé¸Ð", 9, 0));
		defaultOtherChannels.add(new ChannelItem(17, "ÐÇ×ù", 10, 0));
		defaultOtherChannels.add(new ChannelItem(18, "½ÌÓý", 11, 0));
		defaultUserChannels.add(new ChannelItem(19, "ÎÄÑ§", 12, 0));
	}

	private ChannelManage(SQLHelper paramDBHelper) throws SQLException {
		if (channelDao == null)
			channelDao = new ChannelDao(paramDBHelper.getContext());
		// NavigateItemDao(paramDBHelper.getDao(NavigateItem.class));
		return;
	}

	/**
	 * ï¿½ï¿½Ê¼ï¿½ï¿½Æµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
	 * @param paramDBHelper
	 * @throws SQLException
	 */
	public static ChannelManage getManage(SQLHelper dbHelper)throws SQLException {
		if (channelManage == null)
			channelManage = new ChannelManage(dbHelper);
		return channelManage;
	}

	/**
	 * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ðµï¿½Æµï¿½ï¿?
	 */
	public void deleteAllChannel() {
		channelDao.clearFeedTable();
	}
	/**
	 * ï¿½ï¿½È¡ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Æµï¿½ï¿½
	 * @return ï¿½ï¿½ï¿½Ý¿ï¿½ï¿½ï¿½ï¿½ï¿½Ã»ï¿½ï¿½ï¿½ï¿½ï¿? ? ï¿½ï¿½ï¿½Ý¿ï¿½ï¿½Úµï¿½ï¿½Ã»ï¿½Ñ¡ï¿½ï¿½Æµï¿½ï¿½ : Ä¬ï¿½ï¿½ï¿½Ã»ï¿½Ñ¡ï¿½ï¿½Æµï¿½ï¿½ ;
	 */
	public List<ChannelItem> getUserChannel() {
		Object cacheList = channelDao.listCache(SQLHelper.SELECTED + "= ?",new String[] { "1" });
		if (cacheList != null && !((List) cacheList).isEmpty()) {
			userExist = true;
			List<Map<String, String>> maplist = (List) cacheList;
			int count = maplist.size();
			List<ChannelItem> list = new ArrayList<ChannelItem>();
			for (int i = 0; i < count; i++) {
				ChannelItem navigate = new ChannelItem();
				navigate.setId(Integer.valueOf(maplist.get(i).get(SQLHelper.ID)));
				navigate.setName(maplist.get(i).get(SQLHelper.NAME));
				navigate.setOrderId(Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID)));
				navigate.setSelected(Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED)));
				list.add(navigate);
			}
			return list;
		}
		initDefaultChannel();
		return defaultUserChannels;
	}
	
	/**
	 * ï¿½ï¿½È¡ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Æµï¿½ï¿½
	 * @return ï¿½ï¿½ï¿½Ý¿ï¿½ï¿½ï¿½ï¿½ï¿½Ã»ï¿½ï¿½ï¿½ï¿½ï¿? ? ï¿½ï¿½ï¿½Ý¿ï¿½ï¿½Úµï¿½ï¿½ï¿½ï¿½ï¿½Æµï¿½ï¿½ : Ä¬ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Æµï¿½ï¿½ ;
	 */
	public List<ChannelItem> getOtherChannel() {
		Object cacheList = channelDao.listCache(SQLHelper.SELECTED + "= ?" ,new String[] { "0" });
		List<ChannelItem> list = new ArrayList<ChannelItem>();
		if (cacheList != null && !((List) cacheList).isEmpty()){
			List<Map<String, String>> maplist = (List) cacheList;
			int count = maplist.size();
			for (int i = 0; i < count; i++) {
				ChannelItem navigate= new ChannelItem();
				navigate.setId(Integer.valueOf(maplist.get(i).get(SQLHelper.ID)));
				navigate.setName(maplist.get(i).get(SQLHelper.NAME));
				navigate.setOrderId(Integer.valueOf(maplist.get(i).get(SQLHelper.ORDERID)));
				navigate.setSelected(Integer.valueOf(maplist.get(i).get(SQLHelper.SELECTED)));
				list.add(navigate);
			}
			return list;
		}
		if(userExist){
			return list;
		}
		cacheList = defaultOtherChannels;
		return (List<ChannelItem>) cacheList;
	}
	
	/**
	 * ï¿½ï¿½ï¿½ï¿½ï¿½Ã»ï¿½Æµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ý¿ï¿½
	 * @param userList
	 */
	public void saveUserChannel(List<ChannelItem> userList) {
		for (int i = 0; i < userList.size(); i++) {
			ChannelItem channelItem = (ChannelItem) userList.get(i);
			channelItem.setOrderId(i);
			channelItem.setSelected(Integer.valueOf(1));
			channelDao.addCache(channelItem);
		}
	}
	
	/**
	 * ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Æµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½ï¿½Ý¿ï¿½
	 * @param otherList
	 */
	public void saveOtherChannel(List<ChannelItem> otherList) {
		for (int i = 0; i < otherList.size(); i++) {
			ChannelItem channelItem = (ChannelItem) otherList.get(i);
			channelItem.setOrderId(i);
			channelItem.setSelected(Integer.valueOf(0));
			channelDao.addCache(channelItem);
		}
	}
	
	/**
	 * ï¿½ï¿½Ê¼ï¿½ï¿½ï¿½ï¿½ï¿½Ý¿ï¿½ï¿½Úµï¿½Æµï¿½ï¿½ï¿½ï¿½ï¿½ï¿½
	 */
	private void initDefaultChannel(){
		Log.d("deleteAll", "deleteAll");
		deleteAllChannel();
		saveUserChannel(defaultUserChannels);
		saveOtherChannel(defaultOtherChannels);
	}
}
