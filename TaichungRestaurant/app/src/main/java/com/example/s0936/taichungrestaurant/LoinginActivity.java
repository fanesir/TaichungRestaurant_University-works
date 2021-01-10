package com.example.s0936.taichungrestaurant;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
public class LoinginActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;
    LoginButton logibButton;
    CallbackManager callbackManager;
    TextView textView2;
    ActionBar actionBar;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if (user == null) {
            setContentView(R.layout.activity_loingin);
            FacebookSdk.sdkInitialize(getApplicationContext());

            logibButton = (LoginButton) findViewById(R.id.login_button);
            callbackManager = CallbackManager.Factory.create();
            logibButton.setReadPermissions(Arrays.asList("email"));


        } else {
            Intent myIntent = new Intent(LoinginActivity.this, MainActivity.class);
            startActivity(myIntent);
        }



    }

    public void buttonclickLonginFb(View v) {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override

            public void onSuccess(LoginResult loginResult) {
                handleFacebookToken(loginResult.getAccessToken());
                Intent it2 = new Intent();
                it2.setClass(LoinginActivity.this, MainActivity.class);
                startActivity(it2);
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), "取消", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void handleFacebookToken(final AccessToken accessToken) {
        AuthCredential credential = FacebookAuthProvider.getCredential(accessToken.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                        }
                        else {
                            Toast.makeText(getApplicationContext(), "寄存器無法到firebase", Toast.LENGTH_LONG).show();
                        }

                    }
                });
    }
    // FirebaseUser myuserobj = auth.getCurrentUser();
//updateUI(myuserobj);
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUI(FirebaseUser myuserobj) {
        textView2.setText(myuserobj.getEmail());
    }


}





























































//keytool -exportcert -alias androiddebugkey -keystore "C:\Users\s0936\.android\debug.keystore" | "PATH_TO_OPENSSL_LIBRARY\bin\openssl" sha1 -binary | "PATH_TO_OPENSSL_LIBRARY\bin\openssl" base64

//DYd1mVprsgL1T3lcHqcPkj1LTr4=