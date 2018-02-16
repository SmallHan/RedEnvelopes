package com.smallhan.envelopes;

public class RedResources {
	private String fileName;
	private String filePath;
	private boolean flag;

	public synchronized void set() {
		// true��ʾδ����
		if (this.flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// ��ͼ
		RedResources redResources=AdbCaller.printScreenWithOld();
		this.fileName=redResources.getFileName();
		this.filePath=redResources.getFilePath();
		flag = true;
		// ����
		this.notify();
	}
	
	public synchronized void get() {
		// true��ʾδ����
		if (!this.flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ImageDistinguish.cutPic(this.filePath);
		// ��ͼ
		flag = false;
		// ����
		this.notify();
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public boolean isFlg() {
		return flag;
	}

	public void setFlag(boolean flg) {
		this.flag = flag;
	}

}
