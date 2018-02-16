package com.smallhan.envelopes;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AdbCaller {
	private static String adbPath = Constants.ADB_PATH;

	private static String screenshotLocation = Constants.SCREENSHOT_LOCATION;

	public static void setAdbPath(String adbPath) {
		AdbCaller.adbPath = adbPath;
	}

	public static void setScreenshotLocation(String screenshotLocation) {
		AdbCaller.screenshotLocation = screenshotLocation;
	}

	public static RedResources printScreenWithOld() {
		RedResources red=null;
		try {
		     red=new RedResources();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmssms");
			String date=sdf.format(new Date());
			String cutPic=adbPath + " shell screencap -p /sdcard/"+date+".png";
			Process p1 = Runtime.getRuntime().exec(cutPic);
			p1.waitFor();
			String savePic=adbPath + " pull /sdcard/"+date+".png "+screenshotLocation+date+".png";
			Process p2 = Runtime.getRuntime().exec(savePic);
			p2.waitFor();
			red.setFileName(date+".png");
			red.setFilePath(screenshotLocation+date+".png");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return red;
	}
}
