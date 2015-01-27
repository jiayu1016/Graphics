package com.sean.bitmaputils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewStub;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

public class BitmapUtilsActivity extends Activity {

	private ImageView imageView;
	private EditTextView textView;
	private FrameLayout mainPanel;

	private Bitmap bm;
	private int mBitmapWidth;
	private int mBitmapHeight;

	int left;
	int right;
	int top;
	int bottom;

	private Handler mHandler;
	private static final int DRAW_TEXT_BOUND = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image);		

		mainPanel = (FrameLayout) this.findViewById(R.id.mainpanel);
		imageView = (ImageView) this.findViewById(R.id.imageview);
		// textView = (BoundTextView) this.findViewById(R.id.boundtext);

		BitmapFactory.Options opt = new BitmapFactory.Options();
		opt.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(getResources(), R.drawable.guitar, opt);
		mBitmapWidth = opt.outWidth;
		mBitmapHeight = opt.outHeight;

		bm = BitmapFactory.decodeResource(getResources(), R.drawable.guitar);

		mHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				switch (msg.what) {
				case DRAW_TEXT_BOUND:
					drawTextBound(false);
					break;
				}
			}
		};

		Intent intent = getIntent();
		Bundle bundle = intent.getExtras();
		int pos = bundle.getInt("position", 0);
		switch (pos) {
		case 0:
			imageView.setImageBitmap(bm);
			initText();
			initStatusView();
		}
		
	}

	//一个现实输入法输入文字的窗口
	private View mInputMethodView;
	private void initStatusView() {
		ViewStub stub = (ViewStub) findViewById(R.id.method_input);
		mInputMethodView = stub.inflate();
	}

	public View getStatusView() {
		if (mInputMethodView == null) {
			initStatusView();
		}

		return mInputMethodView;
	}

	private int textPosX;
	private int textPosY;
	private int boundWidth;
	private int textSize;
	//图片上初始化文字
	private void initText() {
		String defaultText = "HAPPY NEW YEAR";
		textPosX = 100;
		textPosY = 200;
		textSize = 50;

		Paint paint = new Paint();
		paint.setTextSize(textSize);
		float textWidth = paint.measureText(defaultText);
		boundWidth = (int) textWidth;
		Rect textBound = new Rect(textPosX, textPosY, textPosX + boundWidth,
				textPosY + textSize);
		textView = new EditTextView(this, textBound, defaultText, paint);
		textView.showBound(true);
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		textView.setLayoutParams(lp);
		mainPanel.addView(textView);
		mHandler.sendMessageDelayed(mHandler.obtainMessage(DRAW_TEXT_BOUND), 500);
		textView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showKeyboard();
			}
		});
	}

	private void drawTextBound(boolean drawBound) {
		if (null != textView) {
			textView.showBound(drawBound);
			textView.invalidate();
		}
	}

	public void showKeyboard() {
		InputMethodManager m = (InputMethodManager) this.getBaseContext()
				.getSystemService(INPUT_METHOD_SERVICE);
		m.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
	}
}
