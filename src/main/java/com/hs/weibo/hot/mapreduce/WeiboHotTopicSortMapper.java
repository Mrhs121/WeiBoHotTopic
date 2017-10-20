package com.hs.weibo.hot.mapreduce;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * 对清洗好的 微博数据 进行排序
 * 
 * @author 黄晟
 *
 */
public class WeiboHotTopicSortMapper extends Mapper<LongWritable, Text, HotTopicCompare, Text> {
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, HotTopicCompare, Text>.Context context)
			throws IOException, InterruptedException {
//数据格式说明   topic count  mostPopularWeibo  readCount
		//	0      1          2            3
		// 科技	812347	今天iphonex发布了	21421
		
		String values = value.toString();

		String[] key_value = values.split("\t");
		if (key_value.length >= 4) {
			// 利用mapreduce的天眼自带的排序 所以讲 KV倒过来输出  1代表的的这个话题的热度值  按照降序排序  0代表的是话题的名称以及最火的微博
			HotTopicCompare sortedKey = new HotTopicCompare(Integer.parseInt(key_value[1]));
			// key       value
			// 812347	 科技	今天iphonex发布了	21421
			context.write(sortedKey, new Text(key_value[0]+"\t"+key_value[2]+"\t"+key_value[3]));
		}
	}

}
