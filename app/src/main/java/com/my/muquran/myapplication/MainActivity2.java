package com.my.muquran.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Thread yassine = new Thread(){
            @Override
            public void run(){
                try {
                    sleep(1000);
                    Intent yaya =new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(yaya);
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        yassine.start();
    }
}