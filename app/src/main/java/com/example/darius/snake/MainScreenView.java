package com.example.darius.snake;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;


@SuppressWarnings("all")
public class MainScreenView extends View {
    public static int Width, Height;
    public MagicInterface togame;
    public int pause = 5000;

    public MainScreenView(Context context) {
        super(context);

        try {
            togame = (MagicInterface) context;
        } catch (ClassCastException cce) {
            cce.printStackTrace();
        }

        new CountDownTimer(pause, 1000) {
            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                if (togame != null) {
                    togame.toAuthorizate();
                }
            }
        }.start();
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Width = canvas.getWidth();
        Height = canvas.getHeight();
        int nameY = Width * 5 / 6 / 4;
        canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.logosm), Width * 3 / 4, Width / 4, true), Width / 2 - Width * 3 / 4 / 2, Width / 12, null);
        canvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.name), Width * 5 / 6, nameY, true), Width / 2 - Width * 5 / 6 / 2, Height / 2 - Width / 4 / 2, null);
    }
}