package com.hs.weibo.hot.mapreduce;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.WritableComparable;

/**
 * 将 微博 热门话题 按照降序排序送往 reduce端 适用于 sortMap
 * 
 * @author 黄晟
 *
 */
public class HotTopicCompare implements WritableComparable<HotTopicCompare> {

	public long topic_hot;

	public HotTopicCompare() {
		
	}

	public HotTopicCompare(long topic_hot) {
		this.topic_hot = topic_hot;
	}

	public void write(DataOutput out) throws IOException {
		out.writeLong(topic_hot);

	}

	public void readFields(DataInput in) throws IOException {
		this.topic_hot = in.readLong();

	}

	public int compareTo(HotTopicCompare anotherTopic) {
		long res = this.topic_hot - anotherTopic.topic_hot;
		if (res >= 0) {
			return -1;
		}
		return 1;
	}

}
