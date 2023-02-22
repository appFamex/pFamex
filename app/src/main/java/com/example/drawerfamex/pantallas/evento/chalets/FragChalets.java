package com.example.drawerfamex.pantallas.evento.chalets;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawerfamex.BaseDatos.entidades.fichaChalets;
import com.example.drawerfamex.BaseDatos.viewModel.FichasChalViewModel;
import com.example.drawerfamex.R;
import com.example.drawerfamex.adapters.chaletsAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragChalets extends Fragment {
    //Constantes
    private static final int BLOQUE_1 = 0;
    private static final int BLOQUE_2 = 1;
    //private static final int BLOQUE_3 = 2;
    int[] spinner={R.layout.aag_7_pabellones};

    //Controlador
    private int bloque = 0;
    private boolean francia= false;
    private ImageView btnDerecha, btnIzquierda;
    private TextView txtBloque;
    private LinearLayout banderaFondo;
    private int idioma;

    //Recycler View
    private RecyclerView recyclerView;
    private List<fichaChalets> bloque_1, bloque_2;// bloque_3;
    private chaletsAdapter adapter;

    public FragChalets(){

    }

    public  FragChalets(boolean francia, int idioma) {
        this.francia = francia;
        this.idioma = idioma;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Datos
        FichasChalViewModel viewModel = ViewModelProviders.of(this).get(FichasChalViewModel.class);
        if(!francia){
            viewModel.getFichasChal(bloque);
        }else{
            viewModel.getFichasChalF(bloque, true);
        }

        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null){
                return;
            }

            adapter.adicionarListaFichas(lista);
            bloque_1 = lista;

        });
        if(!francia){
            viewModel.getFichasChal(BLOQUE_2);
        }else{
            viewModel.getFichasChalF(BLOQUE_2,true);
        }
        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null){
                return;
            }
            bloque_2 = lista;

        });


        /*if(!francia){
            viewModel.getFichasChal(BLOQUE_3);
        }else{
            viewModel.getFichasChalF(BLOQUE_3,true);
        }
        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null){
                return;
            }
            bloque_3 = lista;

        });*/


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.aah_8_chalets_fragment, container, false);
        banderaFondo = (LinearLayout) view.findViewById(R.id.MENU_LL_evento);
        btnDerecha = (ImageView) view.findViewById(R.id.btnsiguiente1);
        btnIzquierda = (ImageView) view.findViewById(R.id.btnanterior1);
        txtBloque = (TextView) view.findViewById(R.id.txtvnombrepabellon);

        //Layout
        if(francia)
            banderaFondo.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.bannerconfefrancia1));

        //control botones
        btnDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bloque++;
                if(bloque != 0){
                    btnIzquierda.setVisibility(View.VISIBLE);
                }
                if(bloque == 1){
                    btnDerecha.setVisibility(View.INVISIBLE);
                    txtBloque.setText(getString(R.string.CHA_TXT_Bloque)+(bloque+1));
                }
                /*if(bloque == 1){
                    txtBloque.setText(getString(R.string.CHA_TXT_Bloque)+(bloque+1));
                }*/
                //cambiar datos
                actualizarDatos();
            }
        });

        btnIzquierda.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                bloque--;
                if(bloque == 0){
                    btnIzquierda.setVisibility(View.INVISIBLE);
                    txtBloque.setText(getString(R.string.CHA_TXT_Bloque)+(bloque+1));
                }
                if(bloque != 1){
                    btnDerecha.setVisibility(View.VISIBLE);
                    txtBloque.setText(getString(R.string.CHA_TXT_Bloque)+(bloque+1));
                }
                //cambiarDatos
                actualizarDatos();
            }
        });

        //Adapter inicilizarlo
        adapter = new chaletsAdapter(getContext(),bloque, idioma);


        //RecyclerView
        recyclerView = view.findViewById(R.id.recycler_conferencias1);
        recyclerView.setLayoutManager(new LinearLayoutManager((getContext())));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);




        return view;
    }

    private  void actualizarDatos(){

        if(bloque == BLOQUE_1){
            adapter.setDataset((ArrayList<fichaChalets>) bloque_1,BLOQUE_1);
        }else if(bloque == BLOQUE_2){
            adapter.setDataset((ArrayList<fichaChalets>) bloque_2,BLOQUE_2);
        }/*else if(bloque == BLOQUE_3){
            adapter.setDataset((ArrayList<fichaChalets>) bloque_3,BLOQUE_3);
        }*/

    }



}
