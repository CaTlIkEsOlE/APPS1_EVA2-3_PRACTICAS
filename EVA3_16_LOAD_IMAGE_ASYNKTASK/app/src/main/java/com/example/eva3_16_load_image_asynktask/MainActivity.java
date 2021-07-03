package com.example.eva3_16_load_image_asynktask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    //TextView txtVwMostrar;

    ImageView imgVwLoad;
    Bitmap bitmap;
    int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //txtVwMostrar = findViewById(R.id.txtVwMostrar);
        imgVwLoad = findViewById(R.id.imgVwLoad);
        MiClaseAsincrona miCA = new MiClaseAsincrona();
        miCA.execute();
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

    class MiClaseAsincrona extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.wtf("", "\nINICIANDO LA TAREA ASINCRONA!!");
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            Log.wtf("", "\nFIN DE LA TAREA ASINCRONA!!");
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            imgVwLoad.setImageBitmap(bitmap);
            String s=values[0];
            Log.wtf("", s);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //int limite = integers[0], time = integers[1];
            while(true){
                try {
                    Thread.sleep(1000);
                    bitmap = descargarImagen("https://i.pinimg.com/originals/68/c4/8f/68c48fc341838437a440872481356355.jpg");
                    publishProgress(" i = " + i);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //return null;
        }
    }
}