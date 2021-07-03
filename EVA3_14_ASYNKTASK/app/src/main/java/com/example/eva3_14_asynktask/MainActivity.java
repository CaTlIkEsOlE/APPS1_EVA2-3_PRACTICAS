package com.example.eva3_14_asynktask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtVwMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVwMostrar = findViewById(R.id.txtVwMostrar);
        MiClaseAsincrona miCA = new MiClaseAsincrona();
        miCA.execute(10, 500);
    }

    class MiClaseAsincrona extends AsyncTask<Integer, String, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtVwMostrar.append("\n INICIANDO LA TAREA ASINCRONA!!\n");
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);
            txtVwMostrar.append("FIN DE LA TAREA ASINCRONA!!\n");
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            txtVwMostrar.append(values[0]);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            int limite = integers[0], time = integers[1];
            for(int i=0; i<limite; i++){
                try {
                    Thread.sleep(time);
                    publishProgress("i = " + i + "\n");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }
    }
}