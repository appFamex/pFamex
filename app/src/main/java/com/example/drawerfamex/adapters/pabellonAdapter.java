package com.example.drawerfamex.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.drawerfamex.BaseDatos.entidades.fichaPabellon;
import com.example.drawerfamex.R;

import java.util.ArrayList;
import java.util.List;

public class pabellonAdapter extends RecyclerView.Adapter<pabellonAdapter.ViewHolder> {

    //Log.e("ERROR", "hola"); //para pruebas

    private ArrayList<fichaPabellon> dataset; //Fichas del pabellon
    private Context context;
    private int nPabellon, idioma; //su valor equivale al pabellon correspondiente 0-5, A-F
    private String letraPab;// Se utiliza para crear los botones con su letra correspondiente
    private TextView textoFicha, id; // Se guarda la referencia al TextView de la ficha desde el controlador de pabellones, a traves del constructor
    private ImageView logo;


    public pabellonAdapter(Context context, int numero, TextView txtFicha, ImageView imgLogo, TextView txtID, int idioma) {
        this.context = context;
        dataset = new ArrayList<>();
        this.nPabellon = numero;
        setLetraPab(numero);
        textoFicha = txtFicha;
        logo = imgLogo;
        id = txtID;
        this.idioma = idioma;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aag_pabellones_boton, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        fichaPabellon ficha = dataset.get(position);
        holder.boton.setText(letraPab+(ficha.nPos));

        if(position == 0){
            if(idioma == 0){
                textoFicha.setText(ficha.textoEsp);
            }else if(idioma == 1){
                textoFicha.setText(ficha.textoFr);
            }else{
                textoFicha.setText(ficha.textoEn);
            }
            id.setText(letraPab+1);
            try {
                Glide.with(context)
                        .load(ficha.imagen)
                        .centerCrop()
                        .centerInside()
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(logo);
            }catch (Exception e){
                e.getMessage();
            }
        }

        holder.boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes poner el código que quieres ejecutar al hacer clic en el botón
                if(idioma == 0){
                    textoFicha.setText(ficha.textoEsp);
                }else if(idioma == 1){
                    textoFicha.setText(ficha.textoFr);
                }else{
                    textoFicha.setText(ficha.textoEn);
                }
                id.setText(letraPab+(ficha.nPos));
                try {
                    Glide.with(context)
                            .load(ficha.imagen)
                            .centerCrop()
                            .centerInside()
                            .fitCenter()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(logo);
                }catch (Exception e){
                    e.getMessage();
                }
            }
        });


    }

    @Override
    public int getItemCount() {

        return dataset.size();
    }



    public void adicionarListaFichas(List<fichaPabellon> fichaPabellons) {
        dataset.addAll(fichaPabellons);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private Button boton;

        public ViewHolder(View itemView) {

            super(itemView);
            boton = itemView.findViewById(R.id.boton_pab);

        }
    }

    public void setLetraPab(int nLetra) {
        if(nLetra == 0){
            letraPab = "A";
        }else if(nLetra == 1){
            letraPab = "B";
        }else if(nLetra == 2){
            letraPab = "C";
        }else if(nLetra == 3){
            letraPab = "D";
        }else if(nLetra == 4){
            letraPab = "E";
        }else if(nLetra == 5){
            letraPab = "F";
        }
    }

    /*

    public ArrayList<Pokemon> getDataset() {
        return dataset;
    }

    public void setDataset(ArrayList<Pokemon> dataset) {
        this.dataset = dataset;
    }

     */
}
