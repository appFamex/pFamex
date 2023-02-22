package com.example.drawerfamex.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.drawerfamex.BaseDatos.entidades.fichaChalets;
import com.example.drawerfamex.BaseDatos.entidades.fichaExpo;
import com.example.drawerfamex.BaseDatos.entidades.fichaExpo;
import com.example.drawerfamex.R;
import com.example.drawerfamex.pantallas.evento.expoEst.fragPopUpExpoEstatica;

import java.util.ArrayList;
import java.util.List;

public class expoAdapter extends RecyclerView.Adapter<expoAdapter.ViewHolder> {

    private ArrayList<fichaExpo> dataset; //Fichas del pabellon
    private Context context;
    private int bloque, idioma;
    private SharedPreferences prefs;
    private  boolean francia;//su valor equivale al pabellon correspondiente 0-5, A-F

    public expoAdapter(Context context, int bloque, int idioma, boolean francia) {
        this.context = context;
        dataset = new ArrayList<>();
        this.bloque = bloque;
        this.idioma = idioma;
        this.francia = francia;
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout_expoestatica, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        fichaExpo ficha = dataset.get(position);

        if(francia){
            if(ficha.id == 3){
                try {
                    Glide.with(context)
                            .load(prefs.getString("FR0", ficha.imagen))
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.imagen);
                }catch (Exception e){
                    e.getMessage();
                }
            }else if(ficha.id == 4){
                try {
                    Glide.with(context)
                            .load(prefs.getString("FR1", ficha.imagen))
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.imagen);
                }catch (Exception e){
                    e.getMessage();
                }
            }else if(ficha.id == 5){
                try {
                    Glide.with(context)
                            .load(prefs.getString("FR2", ficha.imagen))
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.imagen);
                }catch (Exception e){
                    e.getMessage();
                }
            }else {
                try {
                    Glide.with(context)
                            .load(ficha.imagen)
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(holder.imagen);
                }catch (Exception e){
                    e.getMessage();
                }
            }
        }else{
            try {
                Glide.with(context)
                        .load(ficha.imagen)
                        .centerCrop()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.imagen);
            }catch (Exception e){
                e.getMessage();
            }
        }

        holder.imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragPopUpExpoEstatica popup;
                if(idioma == 0){
                    popup = new fragPopUpExpoEstatica(ficha.imagen,ficha.nombre,ficha.descEs,ficha.francia);
                }else if(idioma == 1){
                    popup = new fragPopUpExpoEstatica(ficha.imagen,ficha.nombre,ficha.descFr,ficha.francia);
                }else{
                    popup = new fragPopUpExpoEstatica(ficha.imagen,ficha.nombre,ficha.descEn,ficha.francia);
                }


                popup.show(((AppCompatActivity) view.getContext()).getSupportFragmentManager(), "dialog");
            }
        });

    }

    @Override
    public int getItemCount() {

        return dataset.size();
    }

    public void adicionarListaFichas(List<fichaExpo> fichaExpo) {
        dataset.addAll(fichaExpo);
        notifyDataSetChanged();
    }

    public void setDataset(ArrayList<fichaExpo> dataset, int nBloque) {
        this.dataset = dataset;
        bloque= nBloque;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagen;
        public ViewHolder(View itemView) {

            super(itemView);
            imagen = itemView.findViewById(R.id.igvexpoestatica);

        }
    }
}
