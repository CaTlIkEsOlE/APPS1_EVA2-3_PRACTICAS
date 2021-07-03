package com.example.eva1_12_clima;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ClimaAdaptador extends ArrayAdapter<Clima> {
    private Context context;
    private int layout;
    //private Clima[] cCiudades;
    private List<Clima> cCiudades;
    public ClimaAdaptador(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context=context;
        this.layout=resource;
        this.cCiudades=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //FILA: NO EXISTA
        if(convertView==null) { //PRIMERA VEZ QUE SE MUESTRE LA LISTA, NO EXISTE
            convertView = ((Activity)context).getLayoutInflater().inflate(layout, parent, false);
        }/*else{ //FILA SI EXISTE Y HAY QUE LLENAR

        }*/
        //FILA: EXISTA
        ImageView imgVwClima;
        TextView txtVwCiudad, txtVwTemp, txtVwDesc;
        //Vincularlas para poder usarlas
        imgVwClima = convertView.findViewById(R.id.imgVwClima);
        txtVwCiudad = convertView.findViewById(R.id.txtVwCiudad);
        txtVwTemp = convertView.findViewById(R.id.txtVwTemp);
        txtVwDesc = convertView.findViewById(R.id.txtVwDesc);
        //LLenar los widgets con datos (arreglo cCiudades)
        Clima climaCiudad = cCiudades.get(position);
        imgVwClima.setImageResource(climaCiudad.getImagen());
        txtVwCiudad.setText(climaCiudad.getCiudad());
        txtVwTemp.setText(climaCiudad.getTemp() + " C");
        txtVwDesc.setText(climaCiudad.getDesc());
        return convertView;
    }
}
