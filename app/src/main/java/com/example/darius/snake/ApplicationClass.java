package com.example.darius.snake;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

//import com.vk.sdk.VKAccessToken;
//import com.vk.sdk.VKAccessTokenTracker;

public class ApplicationClass extends AppCompatActivity {

   /* VKAccessTokenTracker vkAccessTokenTracker = new VKAccessTokenTracker() {
        @Override
        public void onVKAccessTokenChanged(VKAccessToken oldToken, VKAccessToken newToken) {
            if (newToken == null) {
                Toast.makeText(ApplicationClass.this, "AccessToken invalidated", Toast.LENGTH_LONG).show();

            }
        }
    }; */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // vkAccessTokenTracker.startTracking();
       // VKSdk.initialize(this);
        Intent intent = new Intent(ApplicationClass.this, VKAuthorization.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
