package com.example.eva2_5_extras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    EditText edtNom, edtTxtSal;
    CheckBox chkBxInfo;
    RadioGroup rdGrpEstadoCivil;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(this, MainActivity2.class);

    }

    @Override
    protected void onStart() {
        super.onStart();

        edtNom = findViewById(R.id.edtTxtNombre);
        edtTxtSal =  findViewById(R.id.edtTxtSalario);
        chkBxInfo = findViewById(R.id.chkBxInfo);
        rdGrpEstadoCivil = findViewById(R.id.rdGrpEstadoCivil);

    }

    public void onClick(View v){
        intent.putExtra("NOMBRE", edtNom.getText().toString());
        Double dSalario = 0.0;
        dSalario = Double.parseDouble(edtTxtSal.getText().toString());
        intent.putExtra("SALARIO", dSalario);
        intent.putExtra("INFO", chkBxInfo.isChecked());
        intent.putExtra("ESTADO_CIVIL", rdGrpEstadoCivil.getCheckedRadioButtonId());
        startActivity(intent);
    }

}