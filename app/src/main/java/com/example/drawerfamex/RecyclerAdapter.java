package com.example.drawerfamex;

import static com.github.barteksc.pdfviewer.util.FileUtils.copy;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawerfamex.item.Item;
import com.example.drawerfamex.item.Item1;
import com.example.drawerfamex.item.Item2;
import com.example.drawerfamex.item.Item3;
import com.example.drawerfamex.item.Item4;
import com.example.drawerfamex.item.Item5;
import com.example.drawerfamex.pantallas.evento.expoEst.fragPopUpExpoEstatica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter <RecyclerView.ViewHolder> {
    private final int ITEM1 = 1; //Restaurantes
    private final int ITEM2 = 2; //
    private final int ITEM3 = 3; //
    private final int ITEM4 = 4; //pItinerario
    private final int ITEM5 = 5;
    private AdapterView.OnItemClickListener listener;
    private List<Item> items = new ArrayList<>();
    private List<String> PDF;

    public int valor;

    public void setListener(AdapterView.OnItemClickListener listener){
        this.listener = listener;
    }

    public  Item getItemAt(int position){
        return items.get(position);
    }

    public RecyclerAdapter(List<Item> items) {
        this.items = items;
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType){
            case ITEM1:
                viewHolder = new Item1Holder(inflater.inflate(R.layout.itemlayout_foodcourt,parent,false), parent.getContext());
                break;
            case ITEM2:
                viewHolder = new Item2Holder(inflater.inflate(R.layout.itemlayout_conferencias,parent,false));
                break;
            case ITEM3:
                viewHolder = new Item3Holder(inflater.inflate(R.layout.aah_8_chalets_itemlayout,parent,false));
                break;
            case ITEM4:
                viewHolder = new Item4Holder(inflater.inflate(R.layout.itemlayout_itinerario,parent,false));
                break;
            case ITEM5:
                viewHolder = new Item5Holder(inflater.inflate(R.layout.itemlayout_expoestatica,parent,false));
                break;    
            default:
                viewHolder = new Item1Holder(inflater.inflate(R.layout.itemlayout_foodcourt,parent), parent.getContext());
        }
        return viewHolder;
    }


    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        switch (getItemViewType(position)){
            case ITEM1:
                Item1 item1 = (Item1) items.get(position);
                Item1Holder item1Holder = (Item1Holder)holder;
                item1Holder.nombre.setText(item1.getNombre());
                item1Holder.tipo.setImageResource(item1.getTipo());
                item1Holder.img.setImageResource(item1.getImg());
                item1Holder.pdf.setText(item1.getPdf());
                break;
            case ITEM2:
                Item2 item2 = (Item2) items.get(position);
                Item2Holder item2Holder = (Item2Holder)holder;
                item2Holder.nombre.setText(item2.getNombre());
                item2Holder.horario.setText(item2.getHorario());
                item2Holder.expositor.setText(item2.getExpositor());
                item2Holder.descripcion.setText(item2.getDescripcion());
                item2Holder.position = position;
                break;

            case ITEM3:
                Item3 item3 = (Item3) items.get(position);
                Item3Holder item3Holder = (Item3Holder)holder;
                item3Holder.nombre.setText(item3.getNombre());
                item3Holder.descripcion.setText(item3.getDescripcion());
                item3Holder.img.setImageResource(item3.getImg());
                item3Holder.position = position;
                break;

            case ITEM4:
                Item4 item4 = (Item4) items.get(position);
                Item4Holder item4Holder = (Item4Holder)holder;
                item4Holder.nombreevento.setText(item4.getNombreevento());
                item4Holder.nombreexpositor.setText(item4.getNombreexpositor());
                item4Holder.hora.setText(item4.getHora());
                item4Holder.descripcionevento.setText(item4.getDescripcion());
                item4Holder.lugarevento.setText(item4.getLugar());
                item4Holder.icono.setImageResource(item4.getImg());
                break;

            case ITEM5:
                Item5 item5 = (Item5) items.get(position);
                Item5Holder item5Holder = (Item5Holder)holder;
                item5Holder.nombre.setText(item5.getNombre());
                item5Holder.descripcion.setText(item5.getDescripcion());
                item5Holder.img.setImageResource(item5.getImg());
                item5Holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //fragPopUpExpoEstatica mFragment = new fragPopUpExpoEstatica();
                        //mFragment.setValor(valor);
                       //mFragment.valor = valor;
                        FragmentManager manager = ((AppCompatActivity)item5Holder.itemView.getContext()).getSupportFragmentManager();
                       // mFragment.show(manager,"dsand");
                        Bundle mBundle = new Bundle();
                        mBundle.putSerializable("item_selected_key", item5);
                       // mFragment.setArguments(mBundle);
                    }
                });
                break;    
        }

    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    //son para food
    class Item1Holder extends RecyclerView.ViewHolder{
        TextView nombre;
        ImageView tipo;
        ImageView img;
        TextView pdf;
        ImageView imgboton;
        public Item1Holder(View itemView,Context context) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.txtnombrefood);
            tipo = itemView.findViewById(R.id.icono1);
            img = itemView.findViewById(R.id.imageView_food);
            pdf = itemView.findViewById(R.id.txtPDFfootcourt);
            ImageView boton = itemView.findViewById(R.id.iconoPDFmenu);
            boton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    File file = new File(context.getFilesDir(), pdf.getText().toString());
                    Uri uri;
                    AssetManager assets = context.getAssets();
                    if (!file.exists()) {
                        try {
                            copy(assets.open(pdf.getText().toString()), file);
                        } catch (IOException e) {
                            Log.e("FileProvider", "No se copio el pdf", e);
                        }
                    }

                    Intent intent = new Intent(Intent.ACTION_VIEW);

                    try {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
                                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                                intent.setDataAndType(uri, "application/pdf");
                            } else {
                                uri = Uri.fromFile(file);
                                intent.setDataAndType(uri, "application/pdf");
                            }
                            if (uri != null) {
                                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                                try {
                                    context.startActivity(Intent.createChooser(intent, "Abrir con"));
                                } catch (Exception e) {
                                    Log.e("ERROR", "ERROR" + e);
                                    Toast.makeText(context, "La aplicación no esta disponible", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } catch (Exception e) {
                            Log.e("ERRORFIN", "ERROR" + e);
                            Toast.makeText(context, "No se pudo compartir", Toast.LENGTH_SHORT).show();
                        }
                }
            });
        }

    }



    class Item3Holder extends RecyclerView.ViewHolder{
        TextView nombre,descripcion;
        ImageView img;
        int position;
        public Item3Holder(View itemView) {
            super(itemView);
            nombre = (TextView)itemView.findViewById(R.id.txtnombrechalets);
            descripcion = (TextView)itemView.findViewById(R.id.txtdescripcionchalets);
            img = itemView.findViewById(R.id.imgvchalets);
            itemView.setOnClickListener((v) -> {
                Log.d("demo", "posicion:" + position);
            });

        }
    }


    class Item2Holder extends RecyclerView.ViewHolder{
        TextView expositor,nombre,horario,descripcion;
        int position; //igvFavConferenciacolor
        public Item2Holder(View itemView) {
            super(itemView);
            nombre = (TextView)itemView.findViewById(R.id.txtnombreconferencia);
            horario = (TextView)itemView.findViewById(R.id.txthorario);
            expositor = (TextView)itemView.findViewById(R.id.txtexpositor);
            descripcion = (TextView)itemView.findViewById(R.id.txtdescripcion);
            ImageView igvconfe = itemView.findViewById(R.id.igvFavConferencia);
            ImageView igvconfecolor = itemView.findViewById(R.id.igvFavConferenciacolor);
            igvconfe.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Log.d("demo", "FAV");
                    Toast.makeText(itemView.getContext(), "Se agrego a Mi pItinerario",Toast.LENGTH_SHORT).show();
                    igvconfe.setVisibility(View.INVISIBLE);
                    igvconfecolor.setVisibility(View.VISIBLE);
                }
            });
            igvconfecolor.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    igvconfe.setVisibility(View.VISIBLE);
                    igvconfecolor.setVisibility(View.INVISIBLE);
                }
            });

        }
    }


    class Item4Holder extends RecyclerView.ViewHolder{
        ImageView icono;
        TextView hora,nombreevento,nombreexpositor, descripcionevento,lugarevento;
        public Item4Holder(View itemView) {
            super(itemView);
            hora = (TextView)itemView.findViewById(R.id.txthorario);
            nombreevento = (TextView)itemView.findViewById(R.id.txtnombreevento);
            nombreexpositor = (TextView)itemView.findViewById(R.id.txtexpositorevento);
            lugarevento = (TextView)itemView.findViewById(R.id.txtlugarevento);
            descripcionevento = (TextView)itemView.findViewById(R.id.txtdescripcionevento);
            icono = itemView.findViewById(R.id.imgiconoquees);
        }
    }

    class Item5Holder extends RecyclerView.ViewHolder{
        TextView nombre,descripcion;
        ImageView img;
        Dialog myDialog;

        public Item5Holder(View itemView) {
            super(itemView);
            nombre = (TextView)itemView.findViewById(R.id.txtvnombre);
            descripcion = (TextView)itemView.findViewById(R.id.txtvdescripcion);
            img = itemView.findViewById(R.id.igvexpoestatica);
        }
    }
}
