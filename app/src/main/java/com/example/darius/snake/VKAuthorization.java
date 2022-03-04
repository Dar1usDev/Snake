package com.example.darius.snake;


import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.darius.snake.MagicInterface;
import com.example.darius.snake.Settings;
import com.example.darius.snake.VKView;
/*import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKAccessTokenTracker;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError; */


public class VKAuthorization extends AppCompatActivity {

    public MagicInterface MagicInt;

    private static final String[] sMyScope = new String[]{
            //VKScope.FRIENDS,
            //VKScope.WALL,
            //VKScope.PHOTOS,
    };


    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new VKView(this));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        try {
            MagicInt = (MagicInterface) getApplicationContext();
        } catch (ClassCastException cce) {
            cce.printStackTrace();
        }
        // String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        // Log.v("LOOOOOOOOOOOL    =    "," "+fingerprints[0]);
        // VKSdk.login(this, sMyScope);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       /* if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                if (MagicInt != null) {
                     MagicInt.toSettings();
                }
            }
            @Override
            public void onError(VKError error) {
                Toast.makeText(getApplicationContext(),"(((((",Toast.LENGTH_LONG).show();
                if (MagicInt != null) {
                    MagicInt.toSettings();
                }
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    } */


    }
}
