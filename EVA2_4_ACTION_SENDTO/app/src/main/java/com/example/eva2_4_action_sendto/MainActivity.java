package com.example.eva2_4_action_sendto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
implements View.OnClickListener {

    EditText edtTextTel, edtTxtMensa;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTextTel = findViewById(R.id.edtTxtNoTel);
        edtTxtMensa = findViewById(R.id.edtTxtMensa);
        btnEnviar = findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String sTel, sMensa;
        sTel = "smsto:" +edtTextTel.getText().toString();
        sMensa = edtTxtMensa.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(sTel));
        intent.putExtra("sms_body", sMensa);

        startActivity(intent);
    }
}