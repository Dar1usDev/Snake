package com.example.darius.snake;


import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;


@TargetApi(18)
public class Settings extends AppCompatActivity implements MagicInterface {
    public static String FileName = "SavedData", Music = "Music", Vibration = "Vibration", Backgroundcolor = "BGcolor", Snakebodycolor = "SBcolor";
    public static int musicallowedInt, vibrationallowedInt;
    public static SharedPreferences SavedData;
    public static String bgcolor, bodycolor;

    public RadioGroup RGbgcolor, RGbodycolor;
    public static boolean vibrationallowed, musicallowed;
    public RadioButton RBmusicon, RBmusicoff, RBvibrationon, RBvibrationoff;
    public RadioButton RBbodygreen, RBbodyblue, RBbodyorange, RBbggreen, RBbgblue, RBbgwhite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SavedData = getSharedPreferences(FileName, Context.MODE_PRIVATE);
        vibrationallowedInt = SavedData.getInt(Vibration, 1);
        musicallowedInt = SavedData.getInt(Music, 1);
        bgcolor = SavedData.getString(Backgroundcolor, "white");
        bodycolor = SavedData.getString(Snakebodycolor, "green");

        RGbgcolor = (RadioGroup) findViewById(R.id.RGbgcolor);
        RGbodycolor = (RadioGroup) findViewById(R.id.RGbodycolor);

        RBbodygreen = (RadioButton) findViewById(R.id.radioButtonbdgreen);
        RBbodyblue = (RadioButton) findViewById(R.id.radioButtonbdblue);
        RBbodyorange = (RadioButton) findViewById(R.id.radioButtonbdorange);

        RBbgwhite = (RadioButton) findViewById(R.id.radioButtonbgwhite);
        RBbggreen = (RadioButton) findViewById(R.id.radioButtonbggreen);
        RBbgblue = (RadioButton) findViewById(R.id.radioButtonbgblue);

        RBvibrationon = (RadioButton) findViewById(R.id.RBvibrateON);
        RBvibrationoff = (RadioButton) findViewById(R.id.RBvibrateOFF);
        RBmusicon = (RadioButton) findViewById(R.id.RBmusicON);
        RBmusicoff = (RadioButton) findViewById(R.id.RBmusicOFF);

        switch (bgcolor) {
            case "white":
                RBbgwhite.setChecked(true);
                break;
            case "green":
                RBbggreen.setChecked(true);
                break;
            case "blue":
                RBbgblue.setChecked(true);
                break;
            default:
                break;
        }

        switch (bodycolor) {
            case "green":
                RBbodyorange.setChecked(true);
                break;
            case "blue":
                RBbodyblue.setChecked(true);
                break;
            case "orange":
                RBbodyorange.setChecked(true);
                break;
            default:
                break;
        }

        if (vibrationallowedInt == 1) {
            RBvibrationon.setChecked(true);
        } else {
            RBvibrationoff.setChecked(true);
        }
        if (musicallowedInt == 1) {
            RBmusicon.setChecked(true);
        } else {
            RBmusicoff.setChecked(true);
        }

    }


    public void startgame(View v) {
        checking();
        DataSaving();
        Intent inte = new Intent(this, Game.class);
        startActivity(inte);
        this.finish();
    }


    public void DataSaving() {
        SharedPreferences.Editor editor = SavedData.edit();
        editor.putInt(Music, musicallowedInt);
        editor.putInt(Vibration, vibrationallowedInt);
        editor.putString(Backgroundcolor, bgcolor);
        editor.putString(Snakebodycolor, bodycolor);
        editor.apply();
    }

    public void checking() {

        int checkedRadioButtonbody = RGbodycolor.getCheckedRadioButtonId();
        int checkedRadioButtonbg = RGbgcolor.getCheckedRadioButtonId();

        vibrationallowed = RBvibrationon.isChecked();
        musicallowed = RBmusicon.isChecked();

        switch (checkedRadioButtonbody) {
            case R.id.radioButtonbdgreen:
                bodycolor = "green";
                break;
            case R.id.radioButtonbdblue:
                bodycolor = "blue";
                break;
            case R.id.radioButtonbdorange:
                bodycolor = "orange";
                break;
            default:
                break;
        }

        switch (checkedRadioButtonbg) {
            case R.id.radioButtonbggreen:
                bgcolor = "green";
                break;
            case R.id.radioButtonbgblue:
                bgcolor = "blue";
                break;
            case R.id.radioButtonbgwhite:
                bgcolor = "white";
                break;
            default:
                break;
        }

        if (musicallowed) {
            musicallowedInt = 1;
        } else {
            musicallowedInt = 0;
        }

        if (vibrationallowed) {
            vibrationallowedInt = 1;
        } else {
            vibrationallowedInt = 0;
        }
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
    public void toAuthorizate() {

    }

    @Override
    public void WinScreen() {

    }

    @Override
    public void Exit() {
        this.finish();
    }

}