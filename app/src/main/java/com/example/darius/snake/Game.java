package com.example.darius.snake;


import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import java.io.File;
import java.io.FileNotFoundException;

//import static com.vk.sdk.VKUIHelper.getApplicationContext;


@TargetApi(18)
public class Game extends AppCompatActivity implements MagicInterface {
    public static String MaxScore;
    public static int Maxscore;
    public long vibr = 100;
    public Vibrator vibrator;
    public boolean music;
    public File file;
    public static int level;


    @Override
    public void Stopmusic() {
        stopService(new Intent(this,Music.class));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        file = new File(getApplicationContext().getFilesDir(), "MaxScoreFile.txt");
        MaxScore=" ";
        try {
               MaxScore = FileWorker.read(file);
        } catch(FileNotFoundException e ) {
               e.printStackTrace();
        }
        registerReceiver(batteryReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        Maxscore = 0;
        Maxscore = Integer.parseInt(MaxScore);
        setContentView(new GameView(this));
        music = Settings.musicallowed;
        if (music){
           startService(new Intent(this, Music.class));
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void Vibrate() {
    vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    vibrator.vibrate(vibr);
    }

    @Override
    public void GameOver() {
        unregisterReceiver(batteryReceiver);
        ScoreSaving();
        Intent Int = new Intent(this,GameOverScreen.class);
        startActivity(Int);
        this.finish();
    }



    public void ScoreSaving() {
        MaxScore = String.valueOf(GameView.MaxScore);
        try {
            FileWorker.update(MaxScore,file);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
    }

    public BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            level = intent.getIntExtra("level", 0);
            Log.e("TAG", " BATTERY: " + level );
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void Continuemusic() {
       Music.player.seekTo(Music.poss);
    }

    @Override
    public void Pausemusic() {

    }

    @Override
    public void Exit() {
           this.finish();
    }

    @Override
    public void toGame() {

    }

    @Override
    public void toSettings() {

    }

    @Override
    public void toAuthorizate() {

    }

    @Override
    public void WinScreen() {
        unregisterReceiver(batteryReceiver);
        Intent Int = new Intent(this,WinnerScreen.class);
        startActivity(Int);
        this.finish();
    }



}

