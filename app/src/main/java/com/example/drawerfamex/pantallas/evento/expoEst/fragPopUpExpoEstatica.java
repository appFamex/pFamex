package com.example.drawerfamex.pantallas.evento.expoEst;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.drawerfamex.item.Item5;
import com.example.drawerfamex.R;

public class fragPopUpExpoEstatica extends DialogFragment {
    private ImageView imgAvion;
    private TextView nombre, descripcion;
    private String imagen, txtnombre,txtDescripcion;
    private boolean valor;
    //borrar version bien
    public int drawble;

    public fragPopUpExpoEstatica(String imagen, String txtnombre, String txtDescripcion, boolean valor) {
        this.imagen = imagen;
        this.txtnombre = txtnombre;
        this.txtDescripcion = txtDescripcion;
        this.valor = valor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_popup_expo_estatica, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        LinearLayout fondoPopup;
        fondoPopup = view.findViewById(R.id.lltfondopopexpoest);
        imgAvion = (ImageView) view.findViewById(R.id.igvavionpopoexpoestatica1);
        nombre = (TextView) view.findViewById(R.id.txtvnombrepopoexpoestatica1);
        descripcion = (TextView) view.findViewById(R.id.txtvdescripcionpopoexpoestatica1);

        //Valores inciales
        nombre.setText(txtnombre);
        descripcion.setText(txtDescripcion);


        fondoPopup.setBackgroundResource(R.drawable.fondopopupexpoestatica);

        if(!valor){
            nombre.setBackgroundResource(R.drawable.barrapopupexpoestatica);
        }else{
            nombre.setBackgroundResource(R.drawable.barrapopupexpoestaticafrancia);
        }

        try {
            Glide.with(getContext())
                    .load(imagen)
                    .centerCrop()
                    .centerInside()
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgAvion);
        }catch (Exception e){
            e.getMessage();
        }


        //controlador boton de cierre
        ImageView txtclose;
        txtclose =  (ImageView) view.findViewById(R.id.textclose);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }

    public ImageView getImgAvion() {
        return imgAvion;
    }

    public void setImgAvion(ImageView imgAvion) {
        this.imgAvion = imgAvion;
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

}