package com.example.eva2_5_extras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    Intent intent;
    TextView txtVwDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        intent = getIntent();
        String sNombre = intent.getStringExtra("NOMBRE");
        Double dSal = intent.getDoubleExtra("SALARIO", 0.0);
        boolean bInfo = intent.getBooleanExtra("INFO", false);
        int iEstado = intent.getIntExtra("ESTADO_CIVIL", 0);
        txtVwDatos = findViewById(R.id.txtVwDatos);
        txtVwDatos.append("Nombre: \n");
        txtVwDatos.append(sNombre + "\n");
        txtVwDatos.append("Salario: \n");
        txtVwDatos.append(dSal + "\n");
        txtVwDatos.append("Informacion: \n");
        if(bInfo)
            txtVwDatos.append("Con informacion \n");
        else
            txtVwDatos.append("Sin informacion \n");

        //RadioButton rdTemp = findViewById(iEstado);
        txtVwDatos.append("Estado civil: \n");
        txtVwDatos.append(iEstado + "\n");
    }

    public void onClick(View v){
        finish();;
    }

}