package com.lucasbrick.whc_tetris.version2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class TetrisView extends View{

    TetrisManager tetrisManager;

    public TetrisView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        tetrisManager = new TetrisManager();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        tetrisManager.print(canvas, paint);
    }
}
