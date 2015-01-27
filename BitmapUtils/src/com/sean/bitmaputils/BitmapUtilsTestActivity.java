package com.sean.bitmaputils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BitmapUtilsTestActivity extends Activity {

	private ListView listview;
	private String[] listStr = { "图片上任意移动文字" };
	private final String TAG = "bu";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		listview = (ListView) this.findViewById(R.id.list);
		Log.d("bb", "---listview = " + listview);
		listview.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listStr));
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				// TODO Auto-generated method stub
				Log.d(TAG, "---position = " + position);

				Intent intent = new Intent(BitmapUtilsTestActivity.this,
						BitmapUtilsActivity.class);
				intent.putExtra("pos", position);
				startActivity(intent);

			}
		});
	}
}