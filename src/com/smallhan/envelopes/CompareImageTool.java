package com.smallhan.envelopes;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class CompareImageTool {
	private static HashMap GetRGBMap(BufferedImage bufferedImage) {
		HashMap map = new HashMap();

		for (int x = 0; x < bufferedImage.getWidth(); x++) {
			for (int y = 0; y < bufferedImage.getHeight(); y++) {
				double RGBValue = bufferedImage.getRGB(x, y);
				// ���û�б����ɫֵ,����
				if (map.get(RGBValue) == null) {
					map.put(RGBValue, 1);
				}
				// ����ɫֵ���ִ�������һ��
				else {
					int Times = (int) map.get(RGBValue);
					Times += 1;
					map.put(RGBValue, Times);
				}

			}
		}
		return map;
	}

	private static HashMap VectorNormalizing(HashMap RGBMap) {
		// ��ͼƬ��������1��ģ��
		double ModulaLength = 0;
		for (Object i : RGBMap.keySet()) { // �Ƚ�����ƽ�����
			ModulaLength += Math.pow((double) (int) RGBMap.get(i), 2);
		}
		// ��ƽ����
		ModulaLength = Math.sqrt(ModulaLength);
		// ��ͼƬ��������1��׼��
		for (Object i : RGBMap.keySet()) {
			double a = (double) (int) RGBMap.get(i);
			a = a / ModulaLength;
			RGBMap.put(i, a);
		}

		return RGBMap;
	}

	public static float CompareImage(BufferedImage bufferedImage1, BufferedImage bufferedImage2) {

		HashMap RGBMap1 = GetRGBMap(bufferedImage1);
		HashMap RGBMap2 = GetRGBMap(bufferedImage2);
		// ���ƶ�
		float Similarity = 0;

		// ������HashMap���������������й�һ��
		RGBMap1 = VectorNormalizing(RGBMap1);
		RGBMap2 = VectorNormalizing(RGBMap2);

		// ��������õ����ƶ�
		for (Object i : RGBMap1.keySet()) {
			double Value2;
			if (RGBMap2.get(i) == null) {
				Value2 = 0;
			} else {
				Value2 = (double) RGBMap2.get(i);
			}
			double Value1 = (double) RGBMap1.get(i);

			Similarity += Value1 * Value2;

		}

		return Similarity * 100;
	}
}
