package com.example.eva3_15_banner_asynktask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //TextView txtVwMostrar;

    ImageView imgVwBanner;

    int vel=1000;
    SeekBar skBrVel;

    int i, j=0;

    MiClaseAsincrona miCA = new MiClaseAsincrona();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //txtVwMostrar = findViewById(R.id.txtVwMostrar);
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
        //MiClaseAsincrona miCA = new MiClaseAsincrona();
        miCA.execute();
    }

    class MiClaseAsincrona extends AsyncTask<Void, String, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.wtf("1000", "\nINICIANDO LA TAREA ASINCRONA!!");
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            Log.wtf("1000", "\nFIN DE LA TAREA ASINCRONA!!");
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

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

            String s=values[0];
            Log.wtf("", s);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            //int limite = integers[0], time = integers[1];
            while(true){
                try {
                    Thread.sleep(vel);
                    publishProgress(" i = " + j);
                    j++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}