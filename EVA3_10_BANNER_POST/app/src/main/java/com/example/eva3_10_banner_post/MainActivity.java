package com.example.eva3_10_banner_post;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int vel=1000;
    SeekBar skBrVel;
    ImageView imgVwBanner;
    Handler handler = new Handler();
    int i=0;
    //TRABAJO PESADO, EN SEGUNDO PLANO
    Runnable background = new Runnable() {
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(vel);
                    handler.post(foreground);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        }
    };

    //TRABAJO CON LA UI
    Runnable foreground = new Runnable() {
        @Override
        public void run() {

            int image;
            if(i==0){
                image = R.drawable.f1;
                i++;
            }else if(i==1){
                image = R.drawable.f2;
                i++;
            }else{
                image = R.drawable.f3;
                i=0;
            }
            imgVwBanner.setImageResource(image);

            //txtVwMostrar.append("Hola mundo!! \n");
        }
    };

    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwBanner = findViewById(R.id.imgVwBanner);
        skBrVel = findViewById(R.id.skBrVel);
        skBrVel.setProgress(0);
        skBrVel.setMax(900);
        skBrVel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int valor = Integer.parseInt(String.valueOf(progress))+100;
                vel = 1100 - valor;
                Log.wtf("", "" + vel);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        thread = new Thread(background);
        thread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.interrupt();
    }

}