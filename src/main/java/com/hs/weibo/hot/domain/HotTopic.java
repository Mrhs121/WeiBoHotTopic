package com.hs.weibo.hot.domain;

//热门话题
public class HotTopic {
	private String topic;
	private String mostPopularWeibo;
	private int mostPopularWeiboHot;
	private long hot;
	
	
	
	/**
	 * 
	 * @param topic 话题
	 * @param hot 当前话题的热度
	 * @param mostPopularWeibo  当前最热的微博
	 * @param mostPopularWeiboHot 当前最热的微博的阅读量
	 *  
	 */
	public HotTopic(String topic, long hot ,String mostPopularWeibo, int mostPopularWeiboHot) {
		super();
		this.topic = topic;
		this.mostPopularWeibo = mostPopularWeibo;
		this.mostPopularWeiboHot = mostPopularWeiboHot;
		this.hot = hot;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public long getHot() {
		return hot;
	}
	public void setHot(long hot) {
		this.hot = hot;
	}
	@Override
	public String toString() {
		return "HotTopic [topic=" + topic +", hot=" + hot + ", mostPopularWeibo=" + mostPopularWeibo + ", mostPopularWeiboHot="
				+ mostPopularWeiboHot + "]";
	}

	
}
