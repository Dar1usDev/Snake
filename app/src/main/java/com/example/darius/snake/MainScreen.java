package com.example.darius.snake;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;


@TargetApi(18)
public class MainScreen extends AppCompatActivity implements MagicInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(new MainScreenView(this));

    }

    @Override
    public void toAuthorizate() {
        Intent ToSets = new Intent(this, Settings.class);
        startActivity(ToSets);
        this.finish();
    }


    @Override
    public void toGame() {

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
        this.finish();
    }

    @Override
    public void WinScreen() {

    }


}