package com.sean.bitmaputils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class BitmapUtils {

	/**
	 * 
	 * 功能：生成绘制了指定文字的Bitmap 返回值：Bitmap
	 * 
	 * @param oriBitmap
	 *            原始Bitmap
	 * @param text
	 *            指定的文字
	 * @param x
	 *            文字绘制到Bitmap上的位置的x坐标
	 * @param y
	 *            文字绘制到Bitmap上的位置的y坐标
	 * 
	 */
	public static Bitmap generateWordsOnBitmap(Bitmap oriBitmap, String text,
			float x, float y) {
		float oriWidth = oriBitmap.getWidth();
		float oriHeight = oriBitmap.getHeight();

		// 把原始Bitmap作为画布
		Canvas canvas = new Canvas(oriBitmap);
		Paint paint = new Paint();
		// 获取文本的长度(像素)
		float textWidth = paint.measureText(text);
		if (x > oriWidth - textWidth) {
			x = oriWidth - textWidth;
		}
		canvas.drawText(text, x, y, paint);

		return oriBitmap;
	}

}