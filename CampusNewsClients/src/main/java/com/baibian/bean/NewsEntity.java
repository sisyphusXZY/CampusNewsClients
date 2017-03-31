package com.baibian.bean;

import java.io.Serializable;
import java.util.List;

public class NewsEntity implements Serializable {
	/** 新闻类别ID*/
	private Integer newsCategoryId;
	/** 新闻类型*/
	private String newsCategory;
	/** 标记状态，如推荐之类的 */
	private Integer mark;
	/** 评论数量*/
	private Integer commentNum;
	/** ID */
	private Integer id;
	/** 新闻ID*/
	private Integer newsId;
	/** 标题*/
	private String title;
	/** 新闻源*/
	private String source;
	/** 新闻源地址URl*/
	private String source_url;
	/** 发布时间*/
	private String publishTime;

	/** 刷新时间*/
	private long refreshTime;
	/** 总结 */
	private String summary;
	/** 摘要 */
	private String newsAbstract;
	/** 评论*/
	private String comment;
	/** 特殊标签，如广告推广之类的 ，可以为空 */
	private String local;
	/** 图片列表字符串 */
	private String picListString;
	/** 图片1 URL */
	private String picOne;
	/** 图片2 URL */
	private String picTwo;
	/** 图片3 URL */
	private String picThr;
	/** 图片列表 */
	private List<String> picList;
	/** 图片类型是否为大图*/
	private Boolean isLarge = false;
	/** 阅读状态 ，读过的话显示灰色背景 */
	private Boolean readStatus = false;
	/** 收藏状态̬ */
	private Boolean collectStatus = false;
	/**喜欢 状态״̬ */
	private Boolean likeStatus;
	/** 感兴趣状态 */
	private Boolean interestedStatus;


	public long getRefreshTime() {
		return refreshTime;
	}

	public void setRefreshTime(long refreshTime) {
		this.refreshTime = refreshTime;
	}


	public Integer getNewsCategoryId() {
		return newsCategoryId;
	}

	public void setNewsCategoryId(Integer newsCategoryId) {
		this.newsCategoryId = newsCategoryId;
	}

	public String getNewsCategory() {
		return newsCategory;
	}

	public void setNewsCategory(String newsCategory) {
		this.newsCategory = newsCategory;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getNewsId() {
		return newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getPicListString() {
		return picListString;
	}

	public void setPicListString(String picListString) {
		this.picListString = picListString;
	}

	public String getPicOne() {
		return picOne;
	}

	public void setPicOne(String picOne) {
		this.picOne = picOne;
	}

	public String getPicTwo() {
		return picTwo;
	}

	public void setPicTwo(String picTwo) {
		this.picTwo = picTwo;
	}

	public String getPicThr() {
		return picThr;
	}

	public void setPicThr(String picThr) {
		this.picThr = picThr;
	}

	public List<String> getPicList() {
		return picList;
	}

	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	public Boolean getReadStatus() {
		return readStatus;
	}

	public void setReadStatus(Boolean readStatus) {
		this.readStatus = readStatus;
	}

	public Boolean getCollectStatus() {
		return collectStatus;
	}

	public void setCollectStatus(Boolean collectStatus) {
		this.collectStatus = collectStatus;
	}

	public Boolean getLikeStatus() {
		return likeStatus;
	}

	public void setLikeStatus(Boolean likeStatus) {
		this.likeStatus = likeStatus;
	}

	public Boolean getInterestedStatus() {
		return interestedStatus;
	}

	public void setInterestedStatus(Boolean interestedStatus) {
		this.interestedStatus = interestedStatus;
	}

	public String getNewsAbstract() {
		return newsAbstract;
	}

	public void setNewsAbstract(String newsAbstract) {
		this.newsAbstract = newsAbstract;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Boolean getIsLarge() {
		return isLarge;
	}

	public void setIsLarge(Boolean isLarge) {
		this.isLarge = isLarge;
	}

	public String getSource_url() {
		return source_url;
	}

	public void setSource_url(String source_url) {
		this.source_url = source_url;
	}

}
