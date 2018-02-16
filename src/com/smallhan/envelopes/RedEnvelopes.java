package com.smallhan.envelopes;

public class RedEnvelopes {
	public static void main(String[] args) {
		RedResources redResources=new RedResources();
		Thread th1=new Thread(new RedResourcesSet(redResources));
		Thread th2=new Thread(new RedResourcesGet(redResources));
		th1.start();
		th2.start();
	}
}

