package com.sean.bitmaputils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class BitmapUtils {

	/**
	 * 
	 * ���ܣ����ɻ�����ָ�����ֵ�Bitmap ����ֵ��Bitmap
	 * 
	 * @param oriBitmap
	 *            ԭʼBitmap
	 * @param text
	 *            ָ��������
	 * @param x
	 *            ���ֻ��Ƶ�Bitmap�ϵ�λ�õ�x����
	 * @param y
	 *            ���ֻ��Ƶ�Bitmap�ϵ�λ�õ�y����
	 * 
	 */
	public static Bitmap generateWordsOnBitmap(Bitmap oriBitmap, String text,
			float x, float y) {
		float oriWidth = oriBitmap.getWidth();
		float oriHeight = oriBitmap.getHeight();

		// ��ԭʼBitmap��Ϊ����
		Canvas canvas = new Canvas(oriBitmap);
		Paint paint = new Paint();
		// ��ȡ�ı��ĳ���(����)
		float textWidth = paint.measureText(text);
		if (x > oriWidth - textWidth) {
			x = oriWidth - textWidth;
		}
		canvas.drawText(text, x, y, paint);

		return oriBitmap;
	}

}