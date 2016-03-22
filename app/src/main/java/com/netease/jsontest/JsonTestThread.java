package com.netease.jsontest;

/**
 * desc:
 * Created by light on 2016/3/22.
 */
public class JsonTestThread extends Thread {
	@Override
	public void run() {
		super.run();
		setPriority(Thread.MAX_PRIORITY);
	}
}
