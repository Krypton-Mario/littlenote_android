package com.littlenote.effect;

import android.graphics.Bitmap;

import com.littlenote.utils.LtBitmapUtils;
import com.littlenote.utils.LtFont;

public class LtTextMoveEffectBase {
	public static LtEffect getTextMoveHorizontal(int times, int frequency, String text, LtFont font) {
		LtEffect effect = new LtEffect();
		Bitmap bitmap = null, sourcebitmap = null;
		
		int textSpace = LtBitmapUtils.getTextSpace(text, LtBitmapUtils.getDefaultFont());
		
		int frames = times * frequency;
		float step = (float)textSpace / frames;
		int width = LtBitmapUtils.LED_WIDTH;
		int height = LtBitmapUtils.LED_HEIGHT;
		
		sourcebitmap = LtBitmapUtils.getBitmapByText(text + "   " + text, 0, height/2, font, textSpace * 3, height);
		int i;
		for(i = 0; i < frames && (step*i) < textSpace; i++) {
			//bitmap = LtBitmapUtils.getBitmapByText(text, -(), height/2, font, width, height);
			bitmap = Bitmap.createBitmap(sourcebitmap, (int)(i*step), 0, width, height);
			effect.effectBitmaps.add(bitmap);
		}
		
		effect.frames = i;
		effect.frequency = frequency;
		
		return effect;
	}
}
