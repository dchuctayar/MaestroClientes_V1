package com.example.maestroclientes_v1.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class LineaHorizontal extends View {

    Paint pincel = new Paint();
    public LineaHorizontal(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        pincel.setColor(Color.BLACK);

        canvas.drawRect(0f,0f, getWidth(), 5f, pincel);
    }
}
