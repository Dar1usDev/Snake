package com.example.darius.snake;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

@TargetApi(18)
public class GameOverScreen extends AppCompatActivity implements MagicInterface {
    public boolean music;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameOverView(this));
        music = Settings.musicallowed;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    @Override
    public void toGame() {
        Intent ToGame = new Intent(this, Game.class);
        startActivity(ToGame);
        this.finish();
    }

    @Override
    public void toSettings() {
        Intent ToSets = new Intent(this, Settings.class);
        startActivity(ToSets);
        this.finish();
    }

    @Override
    public void Exit() {
        stopService(new Intent(this, Music.class));
        finishAffinity();
    }

    @Override
    public void GameOver() {

    }

    @Override
    public void Vibrate() {

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
    public void toAuthorizate() {

    }

    @Override
    public void WinScreen() {

    }

}
