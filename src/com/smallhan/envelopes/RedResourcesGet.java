package com.smallhan.envelopes;

public class RedResourcesGet implements Runnable {
	private RedResources redResources;

	public RedResourcesGet(RedResources _redResources) {
		redResources = _redResources;
	}

	public static int screenshotIntervar = Constants.SCREENSHOT_INTERVAL;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(screenshotIntervar);
				redResources.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
