package com.smallhan.envelopes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageDistinguish {

	private static String adbPath = Constants.ADB_PATH;

	public static void cutPic(String filePath) {
		CutPicture o = new CutPicture(filePath, 0, 0, 400, 1000);
		String cutFilePath = filePath.substring(0, filePath.indexOf(".")) + "-1" + ".png";
		o.setSubpath(cutFilePath);
		System.out.println(cutFilePath);
		try {
			o.cut();
			compare(filePath, cutFilePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void compare(String filePath, String cutFilePath) {
		try {
			BufferedImage bufferedImage1 = ImageIO.read(new File(cutFilePath));
			BufferedImage bufferedImage2 = ImageIO.read(new File("red.png"));
			BufferedImage bufferedImage3 = ImageIO.read(new File("red1.png"));
			// 获得相似度
			float Similarity = CompareImageTool.CompareImage(bufferedImage1, bufferedImage2);
			float Similarity1 = CompareImageTool.CompareImage(bufferedImage1, bufferedImage2);
			// 在标签上显示
			System.out.println("路径:" + filePath + " 图片相似度:" + Similarity);
			if (Similarity <= 15 && Similarity1 <= 15) {
				deleteFile(filePath);
				deleteFile(cutFilePath);
			} else {
				for(int i=1100;i<=1400;i+=100) {
					String cmd=" shell input tap 200 "+i;
					Process p = Runtime.getRuntime().exec(adbPath+cmd);
				}
				System.out.println("符合");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Process p1 = Runtime.getRuntime().exec(adbPath + " shell input tap 395 1005");
				Process p2 = Runtime.getRuntime().exec(adbPath + " shell input tap 400 1010");
				Process p3 = Runtime.getRuntime().exec(adbPath + " shell input tap 405 1015");
//				try {
////					Thread.sleep(8000);
////					Process p4 = Runtime.getRuntime().exec(adbPath + " shell input tap 50 50");
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
				deleteFile(filePath);
				deleteFile(cutFilePath);
			}
		} catch (IOException e) {
			System.out.println("读取图片出错！");
			e.printStackTrace();
		}
	}
	private static void deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}
}
