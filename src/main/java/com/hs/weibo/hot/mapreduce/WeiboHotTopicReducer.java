package com.hs.weibo.hot.mapreduce;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import com.jcraft.jsch.MAC;
import com.sun.xml.bind.v2.model.core.ID;

public class WeiboHotTopicReducer extends Reducer<Text, Text, Text, Text>{
	
	int max = -1;
	HashMap<String, String> mostPopularWeibo = new HashMap<String, String>();
	@Override
	protected void reduce(Text key, Iterable<Text> values,
			Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
		long sum = 0;
		
		// 计算同一话题的活跃度 和 同一话题内的最活跃的微博
		for (Text value : values) {
			// 0:topic的计数  LongWriteable
			// 1:weibo  String
			// 2:微博的浏览次数    LongWriteable
			String[] content = value.toString().split("##");
			if(content.length>=3){
				sum+=Integer.parseInt(content[0]);
				if (Integer.parseInt(content[2])>=max) {
					max = Integer.parseInt(content[2]);
					mostPopularWeibo.put("mostPopularWeibo", content[1]);
				}
			}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
		}
//数据格式说明   topic count  mostPopularWeibo  readCount
		// 科技	812347	今天iphonex发布了	21421
		context.write(key, new Text(sum+"\t"+mostPopularWeibo.get("mostPopularWeibo")+"\t"+max));
		
		
		
	}
}
