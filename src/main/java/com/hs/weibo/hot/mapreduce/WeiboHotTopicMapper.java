package com.hs.weibo.hot.mapreduce;

import java.io.IOException;
import java.util.HashMap;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.sun.tools.javac.comp.Lower;



public class WeiboHotTopicMapper extends Mapper<LongWritable, Text, Text, Text>{
	

	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		Text Key_topic = new Text();
		Text weibo = new Text();
		String lines = value.toString();
		String[] content = lines.split("##");
		if (content.length>=3) {
			Key_topic.set(content[0]);
			weibo.set(new LongWritable(1)+"##"+content[1]+"##"+content[2]);
			// data : {"科技","1##今天没有吃饭##12321"}
			context.write(Key_topic,weibo);
		}
		
	}
}
