package com.my.muquran.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    AdView adView;
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
    
    
    InterstitialAd interstitialAd=new InterstitialAd(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        },30000);

         MobileAds.initialize(this,"ca-app-pub-8576484469053202~2143114313");
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        webView =(WebView)findViewById(R.id.webview);
        webView.loadUrl("https://www.quraanfull.com/");
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setSupportZoom(true);


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()){
           webView.goBack();
           return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Uri uri=Uri.parse("https://play.google.com/store/apps/details?id=com.my.muquran.myapplication");
                Intent intent= new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;

            case R.id.item2:
                Intent sharingIntent=new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody="Your Body Here";
                String shareSubject="Your Subject here";
                sharingIntent.putExtra(Intent.EXTRA_TEXT,shareBody);
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,shareSubject);
                startActivity(Intent.createChooser(sharingIntent,"ShareVia"));
                break;
            case R.id.item3:
                Uri urri=Uri.parse("https://www.quraanfull.com/");
                Intent intentl =new Intent(Intent.ACTION_VIEW,urri);
                startActivity(intentl);
                break;
            case R.id.item4:
            Intent go  =new Intent(this,about.class);
            startActivity(go);
                break;
            case R.id.item5:
                Uri uro=Uri.parse("https://www.facebook.com/");
                Intent intentu =new Intent(Intent.ACTION_VIEW,uro);
                startActivity(intentu);
                break;
    }
        return super.onOptionsItemSelected(item);
    }

    public void RunAd(){
        interstitialAd.loadAd(new AdRequest.Builder().build());
    }
}