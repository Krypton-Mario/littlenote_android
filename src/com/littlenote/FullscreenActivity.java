package com.littlenote;

import java.util.ArrayList;

import com.littlenote.R;
import com.littlenote.utils.LtBitmapUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class FullscreenActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen);
		
		//Bitmap bmp = LtUtils.appendTextToPicture("", "my little girl");
		//LtUtils.saveBitmap(bmp, "bmp.bmp");
		
		//ArrayList<String> data = new ArrayList<String>();
		//data.add("my little girl");
		//LtBitmapUtils.writeImage("/sdcard/bmp1.bmp", data);
	}
}
