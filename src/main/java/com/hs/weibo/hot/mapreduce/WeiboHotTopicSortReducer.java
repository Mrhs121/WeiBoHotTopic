package com.hs.weibo.hot.mapreduce;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.hs.weibo.hot.domain.HotTopic;
import com.hs.weibo.hot.util.GlobalContent;

public class WeiboHotTopicSortReducer extends Reducer<HotTopicCompare, Text, Text, Text> {

	/**
	 * @param topic_key
	 *            一个或者多个话题的热度
	 * @param values
	 *            相同热度的话题
	 */
	@Override
	protected void reduce(HotTopicCompare topic_key, Iterable<Text> values,
			Reducer<HotTopicCompare, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// key    value
		// hot    topic       weibo   weibohot
		//          0          1          2
		// 812347     科技      今天iphonex发布了      21421
		// 这里的topic没有重复的值
		for (Text text : values) {
			String[] content = text.toString().split("\t");
			if (content.length >= 3) {
				HotTopic hotTopic = new HotTopic(content[0], topic_key.topic_hot, content[1], Integer.parseInt(content[2]));
				GlobalContent.HOT_TOPIC_TOPTEN_LIST.add(hotTopic);
				context.write(new Text(content[0]), new Text(topic_key.topic_hot+"\t"+content[1]+"\t"+content[2]));
			}	
			
		}
	}
}
