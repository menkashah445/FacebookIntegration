package com.menkashah.facebookintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    Button loginButton, googleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView (R.layout.activity_main);
        callbackManager = CallbackManager.Factory.create();
         loginButton= findViewById (R.id.login_button);
         googleButton = findViewById (R.id.google_button);

//        AccessToken accessToken = AccessToken.getCurrentAccessToken();
//        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();


        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult> () {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        Intent i = new Intent (MainActivity.this,second.class);
                        startActivity (i);
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
        loginButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {


                LoginManager.getInstance().logInWithReadPermissions(MainActivity.this, Arrays.asList("public_profile"));
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}