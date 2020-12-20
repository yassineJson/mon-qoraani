package com.my.muquran.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class about extends AppCompatActivity {
    boolean isRunning;
    @Override
    protected void onStart(){
        super.onStart();
        isRunning=true;
    }
    protected void onStop(){
        super.onStop();
        isRunning=false;
    }

    InterstitialAd interstitialAd=new InterstitialAd(about.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        interstitialAd.setAdUnitId("ca-app-pub-8576484469053202/4866508248");
        interstitialAd.setAdListener(new AdListener(){
            public void onAdLoaded(){
                if (isRunning == true && interstitialAd.isLoaded()){
                    interstitialAd.show();
                }
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                RunAd();
            }
        },1000);
    }
    public void RunAd(){
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }
}