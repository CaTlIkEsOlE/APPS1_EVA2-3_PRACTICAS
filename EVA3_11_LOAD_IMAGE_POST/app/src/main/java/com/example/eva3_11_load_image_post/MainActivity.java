package com.example.eva3_11_load_image_post;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    int i=0;
    ImageView imgVwLoad;
    Bitmap bitmap;
    Handler handler = new Handler();

    //TRABAJO PESADO, EN SEGUNDO PLANO
    Runnable background = new Runnable() {
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(1000);
                    bitmap = descargarImagen("https://i.pinimg.com/originals/68/c4/8f/68c48fc341838437a440872481356355.jpg");
                    handler.post(foreground);
                    Log.wtf("", " i = " + i);
                    i++;
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
            //txtVwMostrar.append("Hola mundo!! \n");
            imgVwLoad.setImageBitmap(bitmap);
        }
    };

    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwLoad = findViewById(R.id.imgVwLoad);
        //txtVwMostrar = findViewById(R.id.txtVwMostrar);
        thread = new Thread(background);
        thread.start();
    }

    private Bitmap descargarImagen(String url){
        try {
            InputStream inputStream = (InputStream) new URL(url).getContent();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        thread.interrupt();
    }
}