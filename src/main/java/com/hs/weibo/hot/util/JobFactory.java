package com.hs.weibo.hot.util;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import com.hs.weibo.hot.Test;
import com.hs.weibo.hot.mapreduce.HotTopicCompare;
import com.hs.weibo.hot.mapreduce.WeiboHotTopicMapper;
import com.hs.weibo.hot.mapreduce.WeiboHotTopicReducer;
import com.hs.weibo.hot.mapreduce.WeiboHotTopicSortMapper;
import com.hs.weibo.hot.mapreduce.WeiboHotTopicSortReducer;

/**
 * job工厂 生成不同的作业
 * 
 * @author 黄晟
 *
 */
public class JobFactory {

	public static Job createJob(String jobType,Configuration conf) throws IOException {
		Job job = Job.getInstance(conf);

		if (jobType.equals(GlobalContent.COUNT_JOB)) {
			System.out.println("create "+GlobalContent.COUNT_JOB);
			job.setJobName("count weibo topic ");
			job.setJarByClass(Test.class);
			
			job.setMapperClass(WeiboHotTopicMapper.class);
			job.setReducerClass(WeiboHotTopicReducer.class);
			//指定map阶段数据输出的格式
			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(Text.class);
			// 指定reduce 阶段 数据输出的格式
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);

		} else if (jobType.equals(GlobalContent.SORT_JOB)) {
			System.out.println("create "+GlobalContent.SORT_JOB);
			job.setJobName("count weibo topic");
			job.setJarByClass(Test.class);
			
			job.setMapperClass(WeiboHotTopicSortMapper.class);
			job.setReducerClass(WeiboHotTopicSortReducer.class);	
			
			// map阶段输出数据的格式
			job.setMapOutputKeyClass(HotTopicCompare.class);
			job.setMapOutputValueClass(Text.class);		
			// reduce 阶段数据输出的格式
			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(Text.class);
		}
		return job;
	}
}
