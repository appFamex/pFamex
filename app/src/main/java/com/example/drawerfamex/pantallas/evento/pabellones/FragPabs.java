package com.example.drawerfamex.pantallas.evento.pabellones;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.drawerfamex.BaseDatos.entidades.fichaChalets;
import com.example.drawerfamex.BaseDatos.entidades.fichaPabellon;
import com.example.drawerfamex.BaseDatos.viewModel.FichasPabVieModel;
import com.example.drawerfamex.R;
import com.example.drawerfamex.adapters.pabellonAdapter;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.util.List;

public class FragPabs extends Fragment {

    private int numero, idioma;
    private RecyclerView recyclerView;
    private pabellonAdapter adapter;
    private List<fichaPabellon> fichas;
    private ImageView imgLogo;
    private TextView fichatxt, txtId;
    private boolean francia = false;

    public FragPabs(int numero,boolean francia, int idioma){
        this.numero = numero;
        this.francia = francia;
        this.idioma = idioma;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FichasPabVieModel viewModel = ViewModelProviders.of(this).get(FichasPabVieModel.class);
        viewModel.getFichasPab(numero);
        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null){
                return;
            }

            adapter.adicionarListaFichas(lista);
            fichas = lista;

        });



    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter != null && fichas != null){
            adapter.adicionarListaFichas(fichas);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.aag_7_pabellones_fragment, container, false);

        //Controladores
        final ImageView imgFicha = view.findViewById(R.id.imgFRM1);
        //final  View scroll = view.findViewById(R.id.constraintLayout3);
        final  View constrain1 = view.findViewById(R.id.constraintMapa1A);
        final  View constrainframe1 = view.findViewById(R.id.constraintFrame1A);
        final PDFView pdfView = view.findViewById(R.id.imageView1a);
        final ImageView logo = view.findViewById(R.id.PAB_imgFicha);
        final TextView ID = view.findViewById(R.id.PAB_txt_itemSel);

        //Inicializar
        //scroll.setVisibility(View.VISIBLE);
        constrain1.setVisibility(View.VISIBLE);
        constrainframe1.setVisibility(View.VISIBLE);

        //Code for the pdf Map
        pdfView.setMinZoom(1);
        pdfView.setMidZoom(2);
        pdfView.setMaxZoom(3);
        //pdfView.setMaxZoom(100);
        if(numero == 0){
            File pdfFile = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF0"+ "." + "pdf");
            if(pdfFile.exists()){
                pdfView.fromFile(pdfFile).load();
            }else{
                pdfView.fromAsset("mapallenons1.pdf").load();
            }
        }else if(numero == 1){
            File pdfFile = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF1"+ "." + "pdf");
            if(pdfFile.exists()){
                pdfView.fromFile(pdfFile).load();
            }else{
                pdfView.fromAsset("mapallenons2.pdf").load();
            }
        }else if(numero == 2){
            File pdfFile = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF2"+ "." + "pdf");
            if(pdfFile.exists()){
                pdfView.fromFile(pdfFile).load();
            }else{
                pdfView.fromAsset("mapallenons3.pdf").load();
            }
        }else if(numero == 3){
            File pdfFile = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF3"+ "." + "pdf");
            if(pdfFile.exists()){
                pdfView.fromFile(pdfFile).load();
            }else{
                pdfView.fromAsset("mapallenons4.pdf").load();
            }
        }else if(numero == 4){
            File pdfFile = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF4"+ "." + "pdf");
            if(pdfFile.exists()){
                pdfView.fromFile(pdfFile).load();
            }else{
                pdfView.fromAsset("mapallenons5.pdf").load();
            }
        }else if(numero == 5){
            File pdfFile = new File(getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF5"+ "." + "pdf");
            if(pdfFile.exists()){
                pdfView.fromFile(pdfFile).load();
            }else{
                pdfView.fromAsset("mapallenons6.pdf").load();
            }
        }



        //Inicilizar
        final TextView txtFicha =view.findViewById(R.id.texto_pabellones);



        adapter = new pabellonAdapter(getContext(), numero, txtFicha, logo,ID,idioma);
        recyclerView = view.findViewById(R.id.recycler_pabellones);

        //Recycler View
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        return view;

    }

    private void getReference(){
    }

}
