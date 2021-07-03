package com.example.eva2_6_on_activity_result;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1;
    final static int CODIGO_SECUN = 1000;
    final static int CODIGO_CONTACTOS = 2000;
    final static int CODIGO_IMAGENES = 3000;

    Intent intent, intentCont, intentImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, Secundaria.class);
        intentCont = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        intentImg = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

    }

    public void onClickCont(View v){
        startActivityForResult(intentCont, CODIGO_CONTACTOS);
    }

    public void onClickImg(View v){
        startActivityForResult(intentImg, CODIGO_IMAGENES);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("DATOS", "Informacion enviada desde principal!!");
                startActivityForResult(intent, CODIGO_SECUN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //AQUI PROCESAMOS LA RESPUESTA DE LA ACTIVIDAD
        //IDENTIFICAR LA ACTIVIDAD QUE DEVOLVIO EL RESULTADO
        //IDENTIFICAR SI SE DEVOLVIO UN VALOR O NO
        //LEER LOS DATOS(INTENT)
        switch (requestCode){
            case CODIGO_SECUN:
                if(resultCode == Activity.RESULT_OK){//SI ME DEVOLVIO UN VALOR
                    //LEER LOS DATOS
                    Toast.makeText(this, data.getStringExtra("VALOR"), Toast.LENGTH_LONG).show();
                }
                break;
                case CODIGO_CONTACTOS://un case para cada actividad que devuelve un valor
                    if(resultCode == Activity.RESULT_OK){
                        String returnedData = data.getDataString();
                        Toast.makeText(this, returnedData, Toast.LENGTH_LONG).show();
                    }
                    break;
            case CODIGO_IMAGENES:
                if(resultCode == Activity.RESULT_OK){
                    String returnedData = data.getDataString();
                    Toast.makeText(this, returnedData, Toast.LENGTH_SHORT).show();
                }
            default:
        }
    }

}