package com.littlenote.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.Log;

public class LtBitmapUtils {
	public static int LED_WIDTH = 320;
	public static int LED_HEIGHT = 160;
	
	public static Bitmap appendTextToPicture(final String picPath,
			final String msg) {
		final int TXT_SIZE = 24;
		// Bitmap bmp = BitmapFactory.decodeFile(picPath);
		final int y_offset = 5;
		// int heigth = bmp.getHeight() + y_offset + TXT_SIZE;
		// final int max_width = bmp.getWidth();
		int heigth = y_offset + TXT_SIZE;
		final int max_width = 100;
		List<String> buf = new ArrayList<String>();
		String lineStr = "";

		Paint p = new Paint();
		Typeface font = Typeface.create("宋体", Typeface.BOLD);
		p.setColor(Color.BLACK);
		p.setTypeface(font);
		p.setTextSize(TXT_SIZE);

		for (int i = 0; i < msg.length();) {

			if (Character.getType(msg.charAt(i)) == Character.OTHER_LETTER) {
				// 如果这个字符是一个汉字
				if ((i + 1) < msg.length()) {
					lineStr += msg.substring(i, i + 2);
				}

				i = i + 2;
			} else {
				lineStr += msg.substring(i, i + 1);
				i++;
			}

			float[] ws = new float[lineStr.length()];
			int wid = p.getTextWidths(lineStr, ws);

			if (wid > max_width) {
				buf.add(lineStr);
				lineStr = "";
				heigth += (TXT_SIZE + y_offset);
			}

			if (i >= msg.length()) {
				heigth += (TXT_SIZE + y_offset);
				break;
			}
		}

		Bitmap canvasBmp = Bitmap.createBitmap(max_width, heigth + TXT_SIZE,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(canvasBmp);
		canvas.drawColor(Color.WHITE);

		float y = y_offset + TXT_SIZE;
		for (String str : buf) {
			canvas.drawText(str, 0, y, p);
			y += (TXT_SIZE + y_offset);
		}

		// canvas.drawBitmap(bmp, 0, y, p);
		return canvasBmp;
	}

	@SuppressLint("SdCardPath")
	public static void saveBitmap(Bitmap bm, String picName) {
		Log.e("utils", "保存图片");
		File f = new File("/sdcard/", picName);
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bm.compress(Bitmap.CompressFormat.PNG, 90, out);
			out.flush();
			out.close();
			Log.i("utils", "已经保存");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param path
	 *            文件保存路径
	 * @param data
	 *            保存数据
	 * */
	public static void writeImage(String path, ArrayList<String> data) {
		try {
			int TXT_SIZE = 60;
			int height = data.size() * TXT_SIZE + 10; // 图片高
			Bitmap bitmap = Bitmap.createBitmap(270, height, Config.ARGB_8888);
			Canvas canvas = new Canvas(bitmap);
			canvas.drawColor(Color.WHITE); // 背景颜色

			/*
			 * Paint p = new Paint(); p.setColor(Color.BLACK); //画笔颜色
			 * p.setTextSize(15); //画笔粗细
			 */
			Paint p = new Paint();
			Typeface font = Typeface.create("宋体", Typeface.BOLD);
			p.setColor(Color.BLACK);
			p.setTypeface(font);
			p.setTextSize(TXT_SIZE);
			for (int i = 0; i < data.size(); i++) {
				canvas.drawText(data.get(i), -30, height / 2 + 10, p);
			}

			Log.e("path", path);
			// 将Bitmap保存为png图片
			FileOutputStream out = new FileOutputStream(path);
			bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
			Log.e("done", "done");

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	/**
	 * @param path
	 *            文件保存路径
	 * @param data
	 *            保存数据
	 * */
	public static Bitmap getBitmapByText(String text, int pos_x, int pos_y, LtFont font, int width, int height) {
		try {
			
			Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
			LtFont tmpFont = null;
			if(font == null) {
				tmpFont = new LtFont();
				tmpFont.bgColor = Color.BLACK;
				tmpFont.foreColor = Color.GREEN;
				tmpFont.textFont = Typeface.create("宋体", Typeface.BOLD);
				tmpFont.textSize = height / 2;
				tmpFont.textFont = Typeface.create("宋体", Typeface.BOLD);
			} else {
				tmpFont = font;
			}
			Canvas canvas = new Canvas(bitmap);
			canvas.drawColor(tmpFont.bgColor);
			Paint p = new Paint();
			p.setColor(tmpFont.foreColor);
			p.setTypeface(tmpFont.textFont);
			p.setTextSize(tmpFont.textSize);

			canvas.drawText(text, pos_x, pos_y, p);
			
			return bitmap;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	public static LtFont getDefaultFont() {
		LtFont tmpFont = null;
		tmpFont = new LtFont();
		tmpFont.bgColor = Color.BLACK;
		tmpFont.foreColor = Color.GREEN;
		tmpFont.textFont = Typeface.create("宋体", Typeface.BOLD);
		tmpFont.textSize = LED_HEIGHT - 10;
		
		return tmpFont;
	}
	
	public static int getTextSpace(String text, LtFont font) {
		Paint p = new Paint();
		p.setColor(font.foreColor);
		p.setTypeface(font.textFont);
		p.setTextSize(font.textSize);
		
		float textWidth = p.measureText(text); 
		return (int)textWidth;
	}
}
