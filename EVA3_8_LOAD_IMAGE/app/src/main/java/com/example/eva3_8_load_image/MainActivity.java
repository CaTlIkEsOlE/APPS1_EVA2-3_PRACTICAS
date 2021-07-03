package com.example.eva3_8_load_image;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView imgVwLoad;

    Bitmap bitmap;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //AQUI MOSTRAMOS LA IMAGEN
            imgVwLoad.setImageBitmap(bitmap);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgVwLoad = findViewById(R.id.imgVwLoad);
        //METER ESTA LINEA DENTRO DE UN HILO DE EJECUCION
        //Bitmap bitmap = descargarImagen("https://i.pinimg.com/originals/68/c4/8f/68c48fc341838437a440872481356355.jpg");
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                //aqui hacemos la conexion
                bitmap = descargarImagen("https://i.pinimg.com/originals/68/c4/8f/68c48fc341838437a440872481356355.jpg");
                Message msg = handler.obtainMessage();
                handler.sendMessage(msg);

            }
        };
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

}