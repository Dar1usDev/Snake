package com.example.darius.snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;


public class GameOverView extends View {
    public Paint paint = new Paint();
    public MagicInterface inter;
    public float TouchY, Width, Height;
    public int[] mas;
    public Bitmap sets, play, exit;
    public int X, setsY, playY, exitY;
    public boolean allowtotouch;
    public int delay = 500;


    public GameOverView(Context context) {
        super(context);
        Width = MainScreenView.Width;
        Height = MainScreenView.Height;
        mas = GameView.BGcolormas;
        try {
            inter = (MagicInterface) context;
        } catch (ClassCastException cce) {
            cce.printStackTrace();
        }
        X = (int) Width * 3 / 4;
        setsY = X / 4;
        playY = X / 3;
        exitY = X / 3;
        sets = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.settings), X, setsY, true);
        play = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.playagain), X, playY, true);
        exit = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.exit), X, exitY, true);
        allowtotouch = false;
        new CountDownTimer(delay, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                allowtotouch = true;
            }
        }.start();

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(mas[0], mas[1], mas[2], mas[3]);
        paint.setAntiAlias(true);
        paint.setTextSize(90.0f);
        paint.setColor(Color.BLACK);
        canvas.drawLine(0, Height / 3, Width, Height / 3, paint);
        canvas.drawLine(0, Height * 2 / 3, Width, Height * 2 / 3, paint);
        canvas.drawBitmap(sets, Width / 2 - (X / 2), Height * 5 / 6 - setsY / 2, null);
        canvas.drawBitmap(play, Width / 2 - (X / 2), Height / 2 - playY / 2, null);
        canvas.drawBitmap(exit, Width / 2 - (X / 2), Height / 6 - playY / 2, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int touched = event.getAction();
        if (touched == MotionEvent.ACTION_DOWN) {
            TouchY = event.getY();
        }


        if (allowtotouch) {
            if (TouchY < Height / 3) {
                if (inter != null) {
                    inter.Exit();
                }
            } else {
                if ((TouchY >= Height / 3) && (TouchY <= Height * 2 / 3)) {
                    if (inter != null) {
                        inter.toGame();
                    }
                } else {
                    if (inter != null) {
                        inter.toSettings();
                    }
                }
            }
        }
        return true;
    }
}
