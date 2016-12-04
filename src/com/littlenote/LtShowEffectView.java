package com.littlenote;

import com.littlenote.effect.LtEffect;
import com.littlenote.effect.LtTextMoveEffectBase;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class LtShowEffectView extends View {
	private static String TAG = "LtShowEffectView";
    private boolean isRun;
    private LtEffect mEffect = null;
    private int index = 0;
    public LtShowEffectView(Context context, AttributeSet atrrs){
        super(context);
        String str = "我们是中国人";
        mEffect = LtTextMoveEffectBase.getTextMoveHorizontal(5, 24, str, null);
        moveView();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d(TAG, "onDraw");
        canvas.drawBitmap(mEffect.effectBitmaps.get(index), 100, 100, null);
    }

    public void moveView(){  
        if(isRun == false ){  
            isRun  = true;  
        }  
        else if(isRun==true){  
            isRun = false;  
        }  
        new Thread(moveThread).start();  
    }
    private final Runnable moveThread = new Runnable() {  
          
        @Override  
        public void run() {  
            while(isRun = true){  
            	index = (index + 1) % (mEffect.frames);
            	Log.d(TAG, "index = " + index);
                try {  
                    Thread.sleep(1000/mEffect.frequency);  
                } catch (InterruptedException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
                postInvalidate();  
            }  
        }  
    };  
      
}  