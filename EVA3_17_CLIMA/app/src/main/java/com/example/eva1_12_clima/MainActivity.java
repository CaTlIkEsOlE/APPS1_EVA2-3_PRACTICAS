package com.example.eva1_12_clima;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*Clima[] aClimaCd = {
            new Clima(R.drawable.sunny, "Chihuahua", 28, "Despejado con viento"),
            new Clima(R.drawable.atmospher, "Delicias", 15, "Vientos huracanados"),
            new Clima(R.drawable.cloudy, "Camargo", 22.3, "Nublado con probabilidad de lluvia"),
            new Clima(R.drawable.light_rain, "Casas Grandes", 15, "LLuvia ligera"),
            new Clima(R.drawable.rainy, "Parral", 11, "LLuvioso con tormentas electricas"),
            new Clima(R.drawable.snow, "Cuauhtemoc",    -3, "Nieve"),
            new Clima(R.drawable.thunderstorm, "Madera", 24, "Tormentas fuertes"),
            new Clima(R.drawable.tornado, "Guerrero", 17, "Run like hell"),
            new Clima(R.drawable.sunny, "Creel", 12, "A todo dar"),
            new Clima(R.drawable.light_rain, "Ahumdada", 13, "Pal cafecito")
    };*/
    List<Clima> lstCiudades = new ArrayList<>();
    ListView lstVwClima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        lstVwClima=findViewById(R.id.lstViewClima);
        //lstVwClima.setAdapter(new ClimaAdaptador(this, R.layout.mi_lista_clima, aClimaCd));
        ConexionClima cc = new ConexionClima();
        cc.execute("http://api.openweathermap.org/data/2.5/find?lat=37.5&lon=126.9&cnt=30&units=metric&appid=76e68c28931eed49f5f32b3aa892a9fd");
    }
                                          //url, nada, json(string)
    class ConexionClima extends AsyncTask<String, Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            //aqui vamos a hacer la conexion()trabajo en segundo plano
            String sUrl = strings[0];
            String sResu = null;
            //HttpUrlConnection
            try {
                URL url = new URL(sUrl);
                //aqui va la conexion
                HttpURLConnection httpCon = (HttpURLConnection)url.openConnection();
                //aqui verificamos si la conexion fue exitosa
                if(httpCon.getResponseCode() == HttpURLConnection.HTTP_OK){
                    //aqui es como leer un archivo
                    InputStreamReader isReader = new InputStreamReader(httpCon.getInputStream());
                    BufferedReader brDatos = new BufferedReader(isReader);
                    sResu = brDatos.readLine();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sResu;
        }

        //aqui vamos  a llenar la lista con datoas
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.wtf("CONEXION", s);
            if(!(s.equals("") || s==null)){//verificar q tengamos una respuesta
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    //recuperar el arreglo de ciudades
                    JSONArray jsaCiudades = jsonObject.getJSONArray("list");
                    for(int i=0;i<jsaCiudades.length();i++){
                        JSONObject jsonCiudad = jsaCiudades.getJSONObject(i);//recuperar una ciudad particular
                        //leer cada ciudad
                        Clima climaCiudad = new Clima();
                        climaCiudad.setCiudad(jsonCiudad.getString("name"));
                        JSONObject jsonMain = jsonCiudad.getJSONObject("main");
                        climaCiudad.setTemp(jsonMain.getDouble("temp"));
                        JSONArray jsaWeather = jsonCiudad.getJSONArray("weather");
                        //tomamos el primer elemento
                        JSONObject jsonClimaActual = jsaWeather.getJSONObject(0);
                        climaCiudad.setDesc(jsonClimaActual.getString("description"));
                        int id = jsonClimaActual.getInt("id");
                        if(id < 300){
                            climaCiudad.setImagen(R.drawable.thunderstorm);
                        }else if(id < 400){//lluvia ligera
                            climaCiudad.setImagen(R.drawable.light_rain);
                        }else if(id < 600){//luvia
                            climaCiudad.setImagen(R.drawable.rainy);
                        }else if(id < 700){//nieve
                            climaCiudad.setImagen(R.drawable.snow);
                        }else if(id < 800){//fenomeno atmosferico
                            climaCiudad.setImagen(R.drawable.atmospher);
                        }else if(id < 801){//cielo despejado
                            climaCiudad.setImagen(R.drawable.sunny);
                        }else if(id < 900){//nublado
                            climaCiudad.setImagen(R.drawable.cloudy);
                        }else{
                            climaCiudad.setImagen(R.drawable.tornado);
                        }
                        lstCiudades.add(climaCiudad);
                    }
                    lstVwClima.setAdapter(new ClimaAdaptador(MainActivity.this,
                            R.layout.mi_lista_clima,
                            lstCiudades));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}