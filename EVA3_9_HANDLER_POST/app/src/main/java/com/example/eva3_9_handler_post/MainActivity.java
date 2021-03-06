package com.example.eva3_9_handler_post;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtVwMostrar;

    Handler handler = new Handler();

    //TRABAJO PESADO, EN SEGUNDO PLANO
    Runnable background = new Runnable() {
        @Override
        public void run() {
            while(true){
                try {
                    Thread.sleep(1000);
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
            txtVwMostrar.append("Hola mundo!! \n");
        }
    };

    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtVwMostrar = findViewById(R.id.txtVwMostrar);
        thread = new Thread(background);
        thread.start();
    }
}