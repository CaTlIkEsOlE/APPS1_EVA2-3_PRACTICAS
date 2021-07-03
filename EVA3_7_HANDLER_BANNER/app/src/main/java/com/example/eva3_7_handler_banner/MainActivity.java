package com.example.eva3_7_handler_banner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgVwBanner;
    Thread tBanner;
    int i = 0;
    //A TRAVES DE UN HANDLER (METODO HANDLERMESSAGE) INTERACTUAR CON LA UI
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //INTERACTUAR CON LA UI
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
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwBanner = findViewById(R.id.imgVwBanner);

        tBanner = new Thread(){
            @Override
            public void run() {
                super.run();
                while(true){
                    try {
                        Thread.sleep(1000);
                        //SOLICITAR UN MENSAJE
                        Message message = handler.obtainMessage();
                        //ENVIAR EL MENSAJE
                        handler.sendMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        break;
                    }
                }
            }
        };
        tBanner.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tBanner.interrupt();
    }
}