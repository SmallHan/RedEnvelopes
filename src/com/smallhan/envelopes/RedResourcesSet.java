package com.smallhan.envelopes;

public class RedResourcesSet implements Runnable {
	public static int screenshotIntervar = Constants.SCREENSHOT_INTERVAL;
	private RedResources redResources;
	public RedResourcesSet(RedResources _redResources) {
		redResources=_redResources;
	}
	
	@Override
	public  void run() {
		while (true) {
			try {
				Thread.sleep(screenshotIntervar);
				redResources.set();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
