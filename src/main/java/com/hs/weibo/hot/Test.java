package com.hs.weibo.hot;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.jobcontrol.JobControl;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.hs.weibo.hot.util.GlobalContent;
import com.hs.weibo.hot.util.JobFactory;
import com.hs.weibo.hot.util.TimeUtil;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.jobcontrol.ControlledJob;

/**
 * 微博 热门 话题 排序 从三百万行的数据中找出 top10
 * 
 * @author 黄晟
 *
 */
public class Test {

	private static final String inputPath = "/input/weibo.txt";
	private static final String job1_outputPath = "/output/weibojob1/";
	private static final String job2_outputPath = "/output/weibojob2/";
	private static TimeUtil timeUtil = new TimeUtil("Test");

	public static void main(String[] args) throws IOException {
		Configuration conf = new Configuration();
		Job countJob = JobFactory.createJob(GlobalContent.COUNT_JOB, conf);
		ControlledJob job1 = new ControlledJob(conf);
		job1.setJob(countJob);
		Path outputPath1 = new Path(job1_outputPath);
		outputPath1.getFileSystem(conf).delete(outputPath1, true);
		FileInputFormat.addInputPath(countJob, new Path(inputPath));
		FileOutputFormat.setOutputPath(countJob, outputPath1);

		Job sortJob = JobFactory.createJob(GlobalContent.SORT_JOB, conf);
		ControlledJob job2 = new ControlledJob(conf);
		job2.setJob(sortJob);
		// 排序作业的输入依赖于前面的清洗作业
		job2.addDependingJob(job1);
		Path outputPath2 = new Path(job2_outputPath);
		outputPath2.getFileSystem(conf).delete(outputPath2, true);
		FileInputFormat.addInputPath(sortJob, outputPath1);
		FileOutputFormat.setOutputPath(sortJob, outputPath2);
		
		
		JobControl masterCtrl = new JobControl("weibo hot topic");
		masterCtrl.addJob(job1);
		masterCtrl.addJob(job2);
		timeUtil.start();
		Thread thread = new Thread(masterCtrl);
		thread.start();
		while (true) {
			if (masterCtrl.allFinished()) {
				System.out.println("all job finish!");
				masterCtrl.stop();
				timeUtil.end();
				timeUtil.printAppUsedTime();
				for (int i = 0; i < 10; i++) {
					System.out.println(GlobalContent.HOT_TOPIC_TOPTEN_LIST.get(i).toString());
				}
				break;
			}
		}
	}
}
