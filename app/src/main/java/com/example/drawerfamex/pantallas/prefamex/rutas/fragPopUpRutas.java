package com.example.drawerfamex.pantallas.prefamex.rutas;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.drawerfamex.R;

public class fragPopUpRutas extends DialogFragment {

    private ImageView imagen, txtclose;
    String nom, txtBaseDest, txtH;
    int img;
    private TextView nombre;
    private TextView txtBaseDestino, txtHora;
    int valor;
    private TextView txtclose1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aap_16_popup, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        LinearLayout linearL;
        linearL = view.findViewById(R.id.lltfondopopexpoest);
        imagen = (ImageView) view.findViewById(R.id.imgTransp);
        nombre = (TextView) view.findViewById(R.id.txtTransport);
        txtBaseDestino = (TextView) view.findViewById(R.id.pfmx_rut_13_pop_2_txt_BaseDestino);
        txtHora = (TextView) view.findViewById(R.id.pfmx_rut_12_pop_1_txt_hora);
        //descripcion = (TextView) view.findViewById(R.id.txtdesc);
        txtclose =  (ImageView) view.findViewById(R.id.closeIc);
        txtclose1 = (TextView) view.findViewById(R.id.textclose2);

        if(valor == 1){
            //linearL.setBackgroundResource(R.drawable.ftexfondo1);
            imagen.setImageResource(img);
            txtBaseDestino.setText(txtBaseDest);
            txtHora.setText(txtH);
            nombre.setText(nom);
            txtclose.setVisibility(View.VISIBLE);
            txtclose1.setVisibility(View.INVISIBLE);
        }else if(valor == 2){
            //fondoluis
            imagen.setImageResource(img);
            txtBaseDestino.setText(txtBaseDest);
            txtHora.setText(txtH);
            nombre.setText(nom);
            txtclose.setVisibility(View.VISIBLE);
            txtclose1.setVisibility(View.INVISIBLE);

        } else if(valor == 3){
            imagen.setImageResource(img);
            txtBaseDestino.setText(txtBaseDest);
            txtHora.setText(txtH);
            nombre.setText(nom);
            txtclose.setVisibility(View.VISIBLE);
            txtclose1.setVisibility(View.INVISIBLE);
        }
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        txtclose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }

}