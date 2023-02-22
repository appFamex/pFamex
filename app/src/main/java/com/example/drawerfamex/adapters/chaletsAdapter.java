package com.example.drawerfamex.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.drawerfamex.BaseDatos.entidades.fichaChalets;
import com.example.drawerfamex.R;

import java.util.ArrayList;
import java.util.List;

public class chaletsAdapter extends RecyclerView.Adapter<chaletsAdapter.ViewHolder>{
    //Log.e("ERROR", "hola"); //para pruebas

    private ArrayList<fichaChalets> dataset; //Fichas del pabellon
    private Context context;
    private int bloque; //su valor equivale al pabellon correspondiente 0-5, A-F
    private int idioma;

    public chaletsAdapter(Context context, int bloque, int idioma) {
        this.context = context;
        dataset = new ArrayList<>();
        this.bloque = bloque;
        this.idioma = idioma;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.aah_8_chalets_itemlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        fichaChalets ficha = dataset.get(position);
        holder.nombre.setText(ficha.nombre);
        if(idioma == 0){
            holder.descripcion.setText(ficha.descEs);
        }else if(idioma == 1){
            holder.descripcion.setText(ficha.descFr);
        }else{
            holder.descripcion.setText(ficha.descEn);
        }


        try {
            Glide.with(context)
                    .load(ficha.imagen)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imagen);
        }catch (Exception e){
            e.getMessage();
        }

        /*holder.telefono.setText(ficha.telefono);
        holder.correo.setText(ficha.correo);
        holder.direccion.setText(ficha.direccion);*/






    }

    @Override
    public int getItemCount() {

        return dataset.size();
    }



    public void adicionarListaFichas(List<fichaChalets> fichaChalets) {
        dataset.addAll(fichaChalets);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagen;
        private TextView nombre, descripcion;// telefono, correo, direccion;

        public ViewHolder(View itemView) {

            super(itemView);
            imagen = itemView.findViewById(R.id.imgvchalets);
            nombre = itemView.findViewById(R.id.txtnombrechalets);
            descripcion = itemView.findViewById(R.id.txtdescripcionchalets);
            /*telefono= itemView.findViewById(R.id.txttelefono);
            telefono= itemView.findViewById(R.id.txttelefono);*/

        }
    }


    public ArrayList<fichaChalets> getDataset() {
        return dataset;
    }

    public void setDataset(ArrayList<fichaChalets> dataset, int nBloque) {
        this.dataset = dataset;
        bloque= nBloque;
        notifyDataSetChanged();
    }
}
