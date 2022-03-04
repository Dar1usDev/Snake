package com.example.darius.snake;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import java.io.File;
import java.io.FileNotFoundException;

public class WinnerScreen extends AppCompatActivity implements MagicInterface {
         public MagicInterface MagInt;
         public Bitmap win,play,exit;
         public int Xx,winY,playY,exitY;
         public float Width,Height,TouchY;
         public Paint paint = new Paint();
         public boolean allowtotouch;
         public int delay = 500;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new WinView(this));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }


    public class WinView extends View{


        public WinView (Context context) {
            super(context);
            Width = MainScreenView.Width;
            Height = MainScreenView.Height;
            File file =new File(getApplicationContext().getFilesDir(), "MaxScoreFile.txt");
            try {
                FileWorker.update("0", file);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
            try {
                MagInt = (MagicInterface) context;
            } catch (ClassCastException cce) {
                cce.printStackTrace();
            }
            paint.setAntiAlias(true);
            paint.setTextSize(90.0f);
            paint.setColor(Color.BLACK);
            Xx = (int) Width * 3 / 4;
            winY = Xx / 3;
            playY = Xx / 3;
            exitY = Xx / 3;
            win = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.youwin), Xx, winY, true);
            play = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.playagain), Xx, playY, true);
            exit = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.exit), Xx, exitY, true);
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
            canvas.drawBitmap(win,Width/2 - (Xx/2),Height/6-winY/2,null);
            canvas.drawBitmap(play,Width/2 - (Xx/2),Height/2-playY/2,null);
            canvas.drawBitmap(exit,Width/2 - (Xx/2),Height*5/6-exitY/2,null);
            canvas.drawLine(0,Height*2/3,Width,Height*2/3,paint);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {

            int touched = event.getAction();
            if (touched == MotionEvent.ACTION_DOWN) {
                TouchY = event.getY();
            }

            if (allowtotouch) {
                if ((TouchY >= Height / 3) && (TouchY <= Height * 2 / 3)) {
                    if (MagInt != null) {
                        MagInt.toGame();
                    }
                } else {
                    if (TouchY >= Height * 2 / 3) {
                        if (MagInt != null) {
                            MagInt.Exit();
                        }
                    }
                }
            }
            return true;
        }
    }


    @Override
    public void toGame() {
        Intent Int = new Intent(this,Game.class);
        startActivity(Int);
        this.finish();
    }

    @Override
    public void toSettings() {

    }

    @Override
    public void GameOver() {

    }

    @Override
    public void Vibrate() {

    }

    @Override
    public void toAuthorizate() {

    }

    @Override
    public void Stopmusic() {

    }

    @Override
    public void Pausemusic() {

    }

    @Override
    public void Continuemusic() {

    }

    @Override
    public void Exit() {
        stopService(new Intent(this,Music.class));
        finishAffinity();
    }

    @Override
    public void WinScreen() {

    }


}
