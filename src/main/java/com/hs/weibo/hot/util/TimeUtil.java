package com.hs.weibo.hot.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class TimeUtil {
	private long startTime;
	private long endTime;
	private String applicationName;
	private boolean isRunning = false;

	public TimeUtil(String appName) {
		this.applicationName = appName;
	}

	public void start() {
		clean();
		isRunning = true;
		startTime = System.currentTimeMillis();
	}

	public void end() {
		if (!isRunning) {
			System.out.println("this timer is not running! can not stop");
			return;
		}
		isRunning = false;
		endTime = System.currentTimeMillis();

	}

	public long getUsedTime() {
		long usedTime = endTime - startTime;
		return usedTime;
	}

	public void printAppUsedTime() {		
	    if (isRunning) {
			end();
		}
		System.out.println("[ application " + this.applicationName + " ] : used " + getUsedTime() + " ms");

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
		System.out.println("[ start time : "+format.format(new Date(startTime))+" ]");
		System.out.println("[ end time   : "+format.format(new Date(endTime))+" ]");
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public void clean() {
		endTime = 0L;
		startTime = 0L;
	}
	
	public static void main(String[] args) throws InterruptedException {
		TimeUtil timeUtil = new TimeUtil("test");
		timeUtil.start();
		Thread.sleep(3000);
		timeUtil.end();
		timeUtil.printAppUsedTime();
		
		timeUtil.start();
		Thread.sleep(3000);
		timeUtil.end();
		timeUtil.printAppUsedTime();
		
	}
}
