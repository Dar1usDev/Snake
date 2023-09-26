package com.example.darius.snake;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.text.method.Touch;
import android.view.MotionEvent;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

@TargetApi(21)
public class GameView extends View {
    public int moveUp = 1, moveRight = 2, moveDown = 3, moveLeft = 4;
    public int cell_amount = 25;
    public int body_max_amount = cell_amount * cell_amount;
    public int body_start_amount = 5;
    private final int pause_time = 180;
    public int current_direction;
    public static int Points;
    public Bitmap snakehead, snakebody, fruit, updownborder, leftrightborder, caterpillar, butterfly;
    public Bitmap left_arrow, up_arrow, right_arrow, down_arrow, pause_button, play_button;
    public int HeadX, HeadY, FruitX, FruitY;
    public float touchX, touchY;
    public boolean fruittoPlace;
    public boolean placed_correctly;
    public int Width, Height;
    public int upper_border, left_border, bottom_border, right_border;
    public int plus1, plus2;
    public int body_current_amount;
    public boolean On;
    public boolean gameisOn;
    public boolean toFinish;
    public boolean toGrow;
    public boolean allowedtochange;
    public int cell_size;
    public int botton_size;
    public int pause_botton_size;
    public int[] BodyX = new int[body_max_amount];
    public int[] BodyY = new int[body_max_amount];
    public static int[] BGcolormas = new int[4];
    public Random rnd = new Random();
    public MagicInterface MagicInt;
    public Timer t = new Timer();
    public Paint paintpointsred = new Paint();
    public Paint paintpointsgreen = new Paint();
    public Paint paintpointsorange = new Paint();
    public Paint paintpowerfit = new Paint();
    public Paint paintpointsfit = new Paint();
    public Paint paintpoints = new Paint();
    public Paint painttime = new Paint();
    public Paint paintback = new Paint();
    public Paint paintline = new Paint();
    public static int MaxScore;
    public long timestart, timefinish;
    public String Backgroundcolor, Bodycolor;
    public boolean vibration;
    public Calendar cal;
    public SimpleDateFormat sdf;
    public String time;
    public float aim = 150;
    public int progressInt;
    public int shift;
    public float linelength, progress;
    public Rect field = new Rect();
    public int BatteryLevel;


    @SuppressWarnings("all")
    public GameView(Context context) {
        super(context);
        MaxScore = Game.Maxscore;
        BatteryLevel = Game.level;
        vibration = Settings.vibrationallowed;
        Backgroundcolor = Settings.bgcolor;
        Bodycolor = Settings.bodycolor;
        Height = MainScreenView.Height;
        Width = MainScreenView.Width;
        upper_border = Width * 2 / 9;
        plus1 = upper_border % cell_amount;


        bottom_border = Width;
        bottom_border += plus1 - (bottom_border % cell_amount);

        left_border = Width / 9;
        plus2 = left_border % cell_amount;
        right_border = Width * 8 / 9;
        right_border += plus2 - (right_border % cell_amount);

        botton_size = Width / 6;
        pause_botton_size = Width / 8;
        cell_size = (bottom_border - upper_border) / cell_amount;
        switch (Bodycolor) {
            case "green":
                snakebody = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.snake_body_lightgreen), cell_size, cell_size, true);
                break;
            case "blue":
                snakebody = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.snake_body_lightblue), cell_size, cell_size, true);
                break;
            case "orange":
                snakebody = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.snake_body_lightorange), cell_size, cell_size, true);
                break;
        }
        switch (Backgroundcolor) {
            case "white":
                BGcolormas[0] = 0;
                BGcolormas[1] = 0;
                BGcolormas[2] = 0;
                BGcolormas[3] = 0;
                paintback.setARGB(200, 244, 255, 255);
                break;
            case "blue":
                BGcolormas[0] = 150;
                BGcolormas[1] = 0;
                BGcolormas[2] = 191;
                BGcolormas[3] = 255;
                paintback.setARGB(BGcolormas[0], BGcolormas[1], BGcolormas[2] + 50, BGcolormas[3]);
                break;
            case "green":
                BGcolormas[0] = 180;
                BGcolormas[1] = 84;
                BGcolormas[2] = 255;
                BGcolormas[3] = 159;
                paintback.setARGB(BGcolormas[0], BGcolormas[1], BGcolormas[2], BGcolormas[3] + 41);
                break;
            default:
                break;
        }
        snakehead = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.snake_head), cell_size, cell_size, true);
        fruit = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.fruit), cell_size, cell_size, true);
        updownborder = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.border_cube), cell_size * 27, cell_size, true);
        leftrightborder = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.border_cube), cell_size, cell_size * 25, true);

        up_arrow = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.up_arrow), botton_size, botton_size, true);
        right_arrow = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.right_arrow), botton_size, botton_size, true);
        left_arrow = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.left_arrow), botton_size, botton_size, true);
        down_arrow = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.down_arrow), botton_size, botton_size, true);
        pause_button = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.pause_button), pause_botton_size, pause_botton_size, true);
        play_button = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.play_button), pause_botton_size, pause_botton_size, true);
        caterpillar = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.caterpillar), cell_size * 2, cell_size * 2, true);
        butterfly = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.butterfly), cell_size * 2, cell_size * 2, true);
        field.set(left_border, upper_border, right_border, bottom_border);

        current_direction = moveRight;
        Points = 0;
        gameisOn = true;
        toFinish = false;
        toGrow = false;
        allowedtochange = false;
        fruittoPlace = true;
        body_current_amount = body_start_amount;
        shift = cell_size * 2;
        sdf = new SimpleDateFormat("HH:mm");

        try {
            MagicInt = (MagicInterface) context;
        } catch (ClassCastException cce) {
            cce.printStackTrace();
        }
        paintline.setAntiAlias(true);
        paintline.setTextSize(90.0f);
        paintline.setStrokeWidth(20);
        paintline.setColor(Color.BLACK);

        paintpointsred.setAntiAlias(true);
        paintpointsred.setTextSize(65.0f);
        paintpointsred.setColor(Color.RED);

        paintpointsorange.setAntiAlias(true);
        paintpointsorange.setTextSize(65.0f);
        paintpointsorange.setColor(Color.argb(255, 255, 165, 0));

        paintpointsgreen.setAntiAlias(true);
        paintpointsgreen.setTextSize(65.0f);
        paintpointsgreen.setColor(Color.argb(255, 0, 205, 0));

        paintpoints.setAntiAlias(true);
        paintpoints.setTextSize(65.0f);
        paintpoints.setColor(Color.BLACK);

        painttime.setAntiAlias(true);
        painttime.setTextSize(85.0f);
        painttime.setColor(Color.BLACK);

        t.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        BatteryLevel = Game.level;
        if (Points == aim) {
            t.cancel();
            if (MagicInt != null) {
                MagicInt.WinScreen();
            }

        }
        cal = Calendar.getInstance();
        time = sdf.format(cal.getTime());

        canvas.drawARGB(BGcolormas[0], BGcolormas[1], BGcolormas[2], BGcolormas[3]);
        canvas.drawRect(field, paintback);
        allowedtochange = true;
        if (toGrow) {
            body_current_amount += 1;
            toGrow = false;
        }
        if (gameisOn) {
            movements();
        }
        if (fruittoPlace) {
            fruitplacing();
        }
        canvas.drawBitmap(fruit, FruitX, FruitY, null);
        for (int i = 0; i < body_current_amount; i++) {
            if (BodyX[i] != 0) {
                canvas.drawBitmap(snakebody, BodyX[i], BodyY[i], null);
            }
        }
        canvas.drawBitmap(snakehead, HeadX, HeadY, null);
        canvas.drawBitmap(updownborder, left_border - cell_size, upper_border - cell_size, null); //верх
        canvas.drawBitmap(updownborder, left_border - cell_size, bottom_border, null); //низ
        canvas.drawBitmap(leftrightborder, left_border - cell_size, upper_border, null); //левая
        canvas.drawBitmap(leftrightborder, right_border, upper_border, null); //правая

        canvas.drawBitmap(right_arrow, Width * 3 / 4 - botton_size / 2, Height * 3 / 4 - botton_size / 2 + shift, null);
        canvas.drawBitmap(down_arrow, Width / 2 - botton_size / 2, Height * 7 / 8 - botton_size / 2 + shift, null);
        canvas.drawBitmap(left_arrow, Width / 4 - botton_size / 2, Height * 3 / 4 - botton_size / 2 + shift, null);
        canvas.drawBitmap(up_arrow, Width / 2 - botton_size / 2, Height * 5 / 8 - botton_size / 2 + shift, null);
        if (gameisOn) {
            canvas.drawBitmap(pause_button, right_border - botton_size + cell_size, bottom_border + cell_size * 2, null);////!!!
        } else {
            canvas.drawBitmap(play_button, right_border - botton_size + cell_size, bottom_border + cell_size * 2, null);/////!!!
        }

        progress = (body_current_amount - 5) / aim * 100;
        progressInt = (int) progress;
        linelength = left_border + cell_size + ((right_border - cell_size * 3) - (left_border + cell_size)) * (progress / 100);
        canvas.drawBitmap(caterpillar, left_border - cell_size, upper_border - cell_size * 3, null);
        canvas.drawBitmap(butterfly, right_border - cell_size * 3, upper_border - cell_size * 3, null);
        canvas.drawLine(left_border + cell_size, upper_border - cell_size * 2, linelength, upper_border - cell_size * 2, paintline);
        canvas.drawText(progressInt + " %", right_border - cell_size + 5, upper_border - cell_size - 20, paintpoints);
        canvas.drawCircle(left_border + cell_size, upper_border - cell_size * 2, 10, paintline);

        if (Points < MaxScore / 2) {
            paintpointsfit = paintpointsred;
        } else {
            if (Points < MaxScore) {
                paintpointsfit = paintpointsorange;
            } else {
                paintpointsfit = paintpointsgreen;
            }
        }

        if (BatteryLevel > 20) {
            paintpowerfit = paintpointsgreen;
        } else {
            paintpowerfit = paintpointsred;
        }

        canvas.drawText("RECORD:" + MaxScore, Width / 12, upper_border / 3, paintpoints);
        canvas.drawText("SCORE:" + Points, Width / 2 - Width / 6, upper_border / 3, paintpointsfit);
        canvas.drawText("Power: " + BatteryLevel + "%", Width / 2 + Width / 18, upper_border / 3, paintpowerfit);
        canvas.drawText(time, Width * 9 / 11, upper_border / 3, painttime);


        if ((HeadX == FruitX) && (HeadY == FruitY)) {
            Points = Points + 1;
            toGrow = true;
            fruittoPlace = true;
            if (vibration) {
                if (MagicInt != null) {
                    MagicInt.Vibrate();
                }
            }
        }

        for (int i = 0; i < body_current_amount; i++) {
            if ((HeadX == BodyX[i]) && (HeadY == BodyY[i])) {
                t.cancel();
                if (MagicInt != null) {
                    MagicInt.Stopmusic();
                }
                if (Points > MaxScore) {
                    MaxScore = Points;
                }
                if (MagicInt != null) {
                    MagicInt.GameOver();
                }
            }
        }

    }


    public void movements() {
        if (On) {
            switch (current_direction) {
                case 1:
                    for (int i = body_current_amount - 1; i > -1; i--) {
                        if (i == 0) {
                            BodyX[i] = HeadX;
                            BodyY[i] = HeadY;
                        } else {
                            BodyX[i] = BodyX[i - 1];
                            BodyY[i] = BodyY[i - 1];
                        }
                    }
                    HeadY = HeadY - cell_size;
                    break;
                case 2:
                    for (int i = body_current_amount - 1; i > -1; i--) {
                        if (i == 0) {
                            BodyX[i] = HeadX;
                            BodyY[i] = HeadY;
                        } else {
                            BodyX[i] = BodyX[i - 1];
                            BodyY[i] = BodyY[i - 1];
                        }
                    }
                    HeadX = HeadX + cell_size;
                    break;
                case 3:
                    for (int i = body_current_amount - 1; i > -1; i--) {
                        if (i == 0) {
                            BodyX[i] = HeadX;
                            BodyY[i] = HeadY;
                        } else {
                            BodyX[i] = BodyX[i - 1];
                            BodyY[i] = BodyY[i - 1];
                        }
                    }
                    HeadY = HeadY + cell_size;
                    break;
                case 4:
                    for (int i = body_current_amount - 1; i > -1; i--) {
                        if (i == 0) {
                            BodyX[i] = HeadX;
                            BodyY[i] = HeadY;
                        } else {
                            BodyX[i] = BodyX[i - 1];
                            BodyY[i] = BodyY[i - 1];
                        }
                    }
                    HeadX = HeadX - cell_size;
                    break;
            }
        } else {
            HeadX = left_border + cell_size * (rnd.nextInt(5) + 10);
            HeadY = upper_border + cell_size * (rnd.nextInt(5) + 10);
            for (int i = 0; i < body_start_amount; i++) {
                if (i == 0) {
                    BodyX[i] = HeadX - cell_size;
                    BodyY[i] = HeadY;
                } else {
                    BodyX[i] = BodyX[i - 1] - cell_size;
                    BodyY[i] = BodyY[i - 1];
                }
            }
            On = true;
        }
        if (HeadX < left_border) {
            HeadX = right_border - cell_size;
        }
        if (HeadX > right_border - cell_size) {
            HeadX = left_border;
        }
        if (HeadY < upper_border) {
            HeadY = bottom_border - cell_size;
        }
        if (HeadY > bottom_border - cell_size) {
            HeadY = upper_border;
        }
    }


    public void fruitplacing() {
        while (fruittoPlace) {
            placed_correctly = true;
            FruitY = upper_border + cell_size * (rnd.nextInt(cell_amount - 2) + 1);
            FruitX = left_border + cell_size * (rnd.nextInt(cell_amount - 2) + 1);
            for (int i = 0; i < body_current_amount; i++) {
                if ((FruitX == BodyX[i]) && (FruitY == BodyY[i])) {
                    placed_correctly = false;
                }
            }
            if ((HeadX == FruitX) && (HeadY == FruitY)) {
                placed_correctly = false;
            }
            if (placed_correctly) {
                fruittoPlace = false;
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touched = event.getAction();
        if (touched == MotionEvent.ACTION_DOWN) {
            timestart = System.currentTimeMillis();
            touchX = event.getX();
            touchY = event.getY();
        }
        if (touched == MotionEvent.ACTION_UP) {
            timefinish = System.currentTimeMillis();
        }

        if (gameisOn) {
            direction_change();
        }


        if ((timefinish - timestart) / 1000.0 > 0.02) {
            if ((touchX >= right_border - pause_botton_size - cell_size) && (touchX < right_border + cell_size * 2) &&
                    (touchY >= bottom_border) && (touchY <= bottom_border + pause_botton_size + cell_size)) {
                if (gameisOn) {
                    t.cancel();
                    allowedtochange = false;
                    gameisOn = false;
                    invalidate();
                } else {
                    allowedtochange = true;
                    gameisOn = true;
                    t.start();
                }
            }
        }

        return true;
    }


    private class Timer extends CountDownTimer {

        private Timer() {
            super(Integer.MAX_VALUE, pause_time);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            invalidate();
        }

        @Override
        public void onFinish() {
            t.cancel();
        }

    }


    public void direction_change() {

        if (allowedtochange) {

            if ((touchX >= Width / 2 - botton_size / 2) && (touchX <= Width / 2 + botton_size / 2) &&
                    (touchY >= Height * 5 / 8 - botton_size / 2 + shift) && (touchY <= Height * 5 / 8 + botton_size / 2 + shift)) {
                if ((current_direction == moveRight) || (current_direction == moveLeft)) {
                    current_direction = moveUp;
                }
            }

            if ((touchX >= Width / 4 - botton_size / 2) && (touchX <= Width / 4 + botton_size / 2) &&
                    (touchY >= Height * 3 / 4 - botton_size / 2 + shift) && (touchY <= Height * 3 / 4 + botton_size / 2 + shift)) {
                if ((current_direction == moveUp) || (current_direction == moveDown)) {
                    current_direction = moveLeft;
                }
            }

            if ((touchX >= Width / 2 - botton_size / 2) && (touchX <= Width / 2 + botton_size / 2) &&
                    (touchY >= Height * 7 / 8 - botton_size / 2 + shift) && (touchY <= Height * 7 / 8 + botton_size / 2 + shift)) {
                if ((current_direction == moveRight) || (current_direction == moveLeft)) {
                    current_direction = moveDown;
                }
            }

            if ((touchX >= Width * 3 / 4 - botton_size / 2) && (touchX <= Width * 3 / 4 + botton_size / 2) &&
                    (touchY >= Height * 3 / 4 - botton_size / 2 + shift) && (touchY <= Height * 3 / 4 + botton_size / 2 + shift)) {
                if ((current_direction == moveUp) || (current_direction == moveDown)) {
                    current_direction = moveRight;
                }
            }
            allowedtochange = false;
        }
    }
}