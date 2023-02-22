package com.example.drawerfamex.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawerfamex.BaseDatos.entidades.fichaConferencia;
import com.example.drawerfamex.BaseDatos.viewModel.FichasConfViewModel;
import com.example.drawerfamex.R;
import com.example.drawerfamex.pantallas.iniciarSesion.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class confAdapter extends RecyclerView.Adapter<confAdapter.ViewHolder>{

    //Log.e("ERROR", "hola"); //para pruebas

    private ArrayList<fichaConferencia> dataset; //Fichas de conferencia
    private Context context;
    private Fragment activityOrigen;
    private int nAuditorio, idioma; //su valor equivale al pabellon correspondiente 0-5, A-F


    public confAdapter(Context context, int nAuditorio, Fragment activity, int idioma) {
        this.context = context;
        dataset = new ArrayList<>();
        this.nAuditorio = nAuditorio;
        this.activityOrigen = activity;
        this.idioma = idioma;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout_conferencias, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        fichaConferencia ficha = dataset.get(position);
        holder.select = ficha.it;
        holder.nombre.setText(ficha.nombre);
        holder.horario.setText(ficha.hInicio+ " - "+ficha.hFin);
        holder.expositor.setText(ficha.expositor);

        if(idioma == 0){
            holder.descripcion.setText(ficha.descEs);
        }else if(idioma == 1){
            holder.descripcion.setText(ficha.descFr);
        }else{
            holder.descripcion.setText(ficha.descEn);
        }

        if(holder.select == true){
            holder.boton.setImageResource(R.drawable.iconoseleccionado);
        }else {
            holder.boton.setImageResource(R.drawable.assent13fondo);
        }


        holder.boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getBindingAdapterPosition();
                Log.e("ERROR", "hola "+ position);
                // Si la posición es válida
                if (position != RecyclerView.NO_POSITION) {
                    fichaConferencia ficha = dataset.get(position);
                    if(holder.select == true){
                        ficha.it = false;
                    }else{
                        ficha.it = true;
                    }

                    FichasConfViewModel viewModel = ViewModelProviders.of(activityOrigen).get((FichasConfViewModel.class));
                    viewModel.insertFicha(ficha, new FichasConfViewModel.OnFichaSaveListenerVM() {
                        @Override
                        public void onFichaSavedVM() {
                            Toast.makeText(context, context.getString(R.string.CON_TOAST_Itinerario), Toast.LENGTH_SHORT).show();
                        }
                    });
                    notifyItemChanged(position);

                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return dataset.size();
    }



    public void adicionarListaFichas(List<fichaConferencia> fichaConferencias) {
        dataset.addAll(fichaConferencias);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView boton;
        private TextView nombre,horario,expositor, descripcion;
        private boolean select;
        public ViewHolder(View itemView) {

            super(itemView);
            boton = itemView.findViewById(R.id.igvFavConferencia);
            nombre = itemView.findViewById(R.id.txtnombreconferencia);
            horario = itemView.findViewById(R.id.txthorario);
            expositor = itemView.findViewById(R.id.txtexpositor);
            descripcion = itemView.findViewById(R.id.txtdescripcion);


        }

    }

    public void setDataset(ArrayList<fichaConferencia> dataset, int bloque) {
        this.dataset = dataset;
        this.nAuditorio = bloque;
        notifyDataSetChanged();
    }


}
