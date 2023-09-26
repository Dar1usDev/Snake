package com.example.darius.snake;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;


public class Music extends Service implements MagicInterface {
    public static MediaPlayer player;
    public static int poss;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        player = MediaPlayer.create(this, R.raw.ingamethesnowangel);
        player.setLooping(true);
        player.start();

    }

    @Override
    public boolean stopService(Intent name) {

        return super.stopService(name);
    }

    @Override
    public void onDestroy() {
        player.stop();
    }


    @Override
    public void Continuemusic() {

    }

    @Override
    public void Pausemusic() {
        poss = player.getCurrentPosition();
        player.stop();
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
    public void toGame() {

    }

    @Override
    public void toSettings() {

    }

    @Override
    public void Exit() {

    }

    @Override
    public void toAuthorizate() {

    }

    @Override
    public void WinScreen() {

    }


}
