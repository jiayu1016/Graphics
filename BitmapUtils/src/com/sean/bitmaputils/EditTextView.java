package com.sean.bitmaputils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;

public class EditTextView extends View {

	private Paint mPaint;
	private String mText = "";
	private Rect textBound;
	private AlphaAnimation mAnimation;

	public EditTextView(Context context, Rect bound, String text, Paint paint) {
		super(context);
		// TODO Auto-generated constructor stub
		textBound = bound;
		mText = text;
		mPaint = paint;
		mAnimation = new AlphaAnimation(1, 0);
	}

	public void setText(String text) {
		mText = text;
	}

	public void setPaint(Paint paint) {
		mPaint = paint;
	}

	public void setBound(Rect r) {
		textBound = r;
	}

	private boolean showBound = false;

	public void showBound(boolean show) {
		showBound = show;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Log.d("aa", "---showBound = " + showBound);
		if (null != mPaint && null != mText) {
			canvas.drawText(mText, textBound.left, textBound.bottom, mPaint);
		}
		
		if (showBound) {
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			// float textWidth = paint.measureText((String) getText());
			paint.setStrokeWidth(5);
			paint.setStyle(Style.FILL);
			paint.setColor(Color.RED);
			paint.setAlpha(120);

			if (null != textBound) {
				canvas.drawRect(textBound, paint);
			}
			paint.setAlpha(255);
			paint.setStyle(Style.STROKE);
			PathEffect effect = new DashPathEffect(new float[] { 8, 8 }, 1);
			paint.setPathEffect(effect);
			if (null != textBound) {
				mAnimation.setDuration(500);
				mAnimation.start();
				canvas.drawRect(textBound, paint);
			}
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}
}
