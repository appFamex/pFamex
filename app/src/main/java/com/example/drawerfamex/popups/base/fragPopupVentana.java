package com.example.drawerfamex.popups.base;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.drawerfamex.R;

public class fragPopupVentana extends DialogFragment {
    private ImageView imagen, txtclose;
    String nom,desc;
    int img;
    private TextView nombre;
    private TextView descripcion;
    int valor;
    private TextView txtclose1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_popup_ventana, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        LinearLayout lltfondopopexpoest;
        lltfondopopexpoest = view.findViewById(R.id.lltfondopopexpoest);
        imagen = (ImageView) view.findViewById(R.id.igvventana);
        nombre = (TextView) view.findViewById(R.id.txtvnombreventana);
        descripcion = (TextView) view.findViewById(R.id.txtdescripcionventana);
        txtclose =  (ImageView) view.findViewById(R.id.textclose);
        txtclose1 = (TextView) view.findViewById(R.id.textclose2);

        if(valor == 1){
            lltfondopopexpoest.setBackgroundResource(R.drawable.ftexfondo1);
            imagen.setImageResource(img);
            nombre.setText(nom);
            descripcion.setText(desc);
            txtclose.setVisibility(View.VISIBLE);
            txtclose1.setVisibility(View.INVISIBLE);
        }else{
            if (valor == 2 ){
                //fondoluis
                imagen.setImageResource(img);
                nombre.setText(nom);
                descripcion.setText(desc);
                lltfondopopexpoest.setBackgroundResource(R.drawable.gr_rutas_ppp_fondo);
                txtclose1.setVisibility(View.INVISIBLE);
                txtclose.setVisibility(View.VISIBLE);
            }
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

    public ImageView getImagen() {
        return imagen;
    }

    public void setImagen(ImageView imagen) {
        this.imagen = imagen;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ImageView getTxtclose() {
        return txtclose;
    }

    public void setTxtclose(ImageView txtclose) {
        this.txtclose = txtclose;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(TextView descripcion) {
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public TextView getTxtclose1() {
        return txtclose1;
    }

    public void setTxtclose1(TextView txtclose1) {
        this.txtclose1 = txtclose1;
    }
}