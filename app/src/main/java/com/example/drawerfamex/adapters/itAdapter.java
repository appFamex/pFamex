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
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawerfamex.BaseDatos.entidades.fichaConferencia;
import com.example.drawerfamex.BaseDatos.viewModel.FichasConfViewModel;
import com.example.drawerfamex.R;

import java.util.ArrayList;
import java.util.List;

public class itAdapter extends RecyclerView.Adapter<itAdapter.ViewHolder> {
    private ArrayList<fichaConferencia> dataset; //Fichas de conferencia
    private Context context;
    private Activity activityOrigen;
    private String letraPab;
    private int nAuditorio; //su valor equivale al pabellon correspondiente 0-5, A-F


    public itAdapter(Context context, int nAuditorio, Activity activity) {
        this.context = context;
        dataset = new ArrayList<>();
        this.nAuditorio = nAuditorio;
        this.activityOrigen = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout_itinerario, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        fichaConferencia ficha = dataset.get(position);
        setLetraPab(ficha.nBlok);
        holder.select = ficha.it;
        holder.nombre.setText(ficha.nombre);
        holder.horario.setText(ficha.hInicio+ " - "+ficha.hFin);
        holder.expositor.setText(ficha.expositor);
        holder.descripcion.setText(ficha.descEs);
        holder.pabellon.setText("Pabellon "+letraPab);

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

                    FichasConfViewModel viewModel = ViewModelProviders.of((FragmentActivity) activityOrigen).get((FichasConfViewModel.class));
                    viewModel.insertFicha(ficha, new FichasConfViewModel.OnFichaSaveListenerVM() {
                        @Override
                        public void onFichaSavedVM() {
                            Toast.makeText(context, context.getString(R.string.IT_TOAST_Itinerario), Toast.LENGTH_SHORT).show();
                            //Agregar Mensaje Elemento removido??
                        }
                    });
                    dataset.remove(position);
                    notifyDataSetChanged();

                }
            }
        });

        /* CODIGO IMAGENES
        try {
            Glide.with(context)
                    .load(new File("/storage/emulated/0/Android/data/tech.alvarez.pokedex/files/Download" +
                            File.separator + "pokemones" + File.separator + (position + 1)+ ".png"))
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.fotoImageView);
        }catch (Exception e){
            e.getMessage();
        }

         */


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
        private TextView nombre,horario,expositor, descripcion, pabellon;
        private boolean select;
        public ViewHolder(View itemView) {

            super(itemView);
            boton = itemView.findViewById(R.id.imgeliminar);
            nombre = itemView.findViewById(R.id.txtnombreevento);
            horario = itemView.findViewById(R.id.txthorario);
            expositor = itemView.findViewById(R.id.txtexpositorevento);
            descripcion = itemView.findViewById(R.id.txtdescripcionevento);
            pabellon = itemView.findViewById(R.id.txtlugarevento);

        }

    }

    public void setDataset(ArrayList<fichaConferencia> dataset, int bloque) {
        this.dataset = dataset;
        this.nAuditorio = bloque;
        notifyDataSetChanged();
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

}
