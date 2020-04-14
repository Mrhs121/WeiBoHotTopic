
假设微博的数据格式如下所示
数据分为三个字段，字段之间用##分割开
--------------------------------------------------
topic(话题)    |   微博内容    |  这条微博的阅读量   |
--------------------------------------------------

## 数据格式样例:

十九大##习近平在参加党的十九大贵州省代表团讨论时强调：全党全国各族人民万众一心，开拓进取，把新时代中国特色社会主义推向前进##1000000  

* topic：十九大
* 微博内容：习近平在参加党的十九大贵州省代表团讨论时强调：全党全国各族人民万众一心，开拓进取，把新时代中国特色社会主义推向前进
* 阅读量：  1000000

## 实现思路：
	MapReduce二次排序
	
## 推广：
	类似在海量数据中求TopK的场景
