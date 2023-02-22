package com.example.drawerfamex.pantallas.evento.conferencias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.drawerfamex.BaseDatos.entidades.fichaConferencia;
import com.example.drawerfamex.BaseDatos.viewModel.FichasConfViewModel;
import com.example.drawerfamex.R;
import com.example.drawerfamex.adapters.confAdapter;
import com.example.drawerfamex.item.Item;

import java.util.ArrayList;
import java.util.List;

public class fragAuditorio extends Fragment {

    private static final int BLOQUE_1 = 0;
    private static final int BLOQUE_2 = 1;
    private static final int BLOQUE_3 = 2;
    private static final int BLOQUE_4 = 3;
    private static final int BLOQUE_5 = 4;
    private static final int BLOQUE_6 = 5;
    private static final int BLOQUE_7 = 6;

    private RecyclerView recyclerAdapter;
    private confAdapter adapter;
    private ArrayList<Item> items;

    private int nAuditorio = 0;
    private List<fichaConferencia> bloque_1, bloque_2, bloque_3, bloque_4, bloque_5, bloque_6, bloque_7;
    private ImageView btnDerecha, btnIzquierda;
    private LinearLayout bannerFrancia;
    private TextView txtAuditorio, txtPabellon;
    private int dia, idioma;
    private boolean francia;


    public fragAuditorio(int dia, boolean francia, int idioma) {
        this.dia = dia;
        this.francia = francia;
        this.idioma = idioma;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Datos
        FichasConfViewModel viewModel = ViewModelProviders.of(this).get(FichasConfViewModel.class);

        if(francia)
            viewModel.getFichasConfF(BLOQUE_1,dia,francia);
        else
            viewModel.getFichasConf(BLOQUE_1,dia);

        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null || adapter.getItemCount() != 0){
                return;
            }

            adapter.adicionarListaFichas(lista);
            bloque_1 = lista;

        });


        if(francia)
            viewModel.getFichasConfF(BLOQUE_2,dia,francia);
        else
            viewModel.getFichasConf(BLOQUE_2,dia);

        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null){
                return;
            }

            bloque_2 = lista;

        });


        if(francia)
            viewModel.getFichasConfF(BLOQUE_3,dia,francia);
        else
            viewModel.getFichasConf(BLOQUE_3,dia);

        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null){
                return;
            }

            bloque_3 = lista;

        });

        if(francia)
            viewModel.getFichasConfF(BLOQUE_4,dia,francia);
        else
            viewModel.getFichasConf(BLOQUE_4,dia);

        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null){
                return;
            }

            bloque_4 = lista;

        });


        if(francia)
            viewModel.getFichasConfF(BLOQUE_5,dia,francia);
        else
            viewModel.getFichasConf(BLOQUE_5,dia);

        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null){
                return;
            }

            bloque_5 = lista;

        });


        if(francia)
            viewModel.getFichasConfF(BLOQUE_6,dia,francia);
        else
            viewModel.getFichasConf(BLOQUE_6,dia);

        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null){
                return;
            }

            bloque_6 = lista;

        });

        if(francia)
            viewModel.getFichasConfF(BLOQUE_7,dia,francia);
        else
            viewModel.getFichasConf(BLOQUE_7,dia);

        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null){
                return;
            }

            bloque_7 = lista;

        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aaf_6_conferencias_fragment, container, false);

        //Inicializar
        txtAuditorio = (TextView) view.findViewById(R.id.txtauditorios);
        txtPabellon = (TextView) view.findViewById(R.id.txtvnombrepabellon);
        bannerFrancia = (LinearLayout) view.findViewById(R.id.linearLayoutA1);
        btnDerecha = (ImageView) view.findViewById(R.id.btnsiguiente);
        btnIzquierda = (ImageView) view.findViewById(R.id.btnanterior);
        recyclerAdapter = view.findViewById(R.id.recycler_conferencias1);

     //instruccion cambia banner para francia
        if(francia)
            bannerFrancia.setBackgroundDrawable(ContextCompat.getDrawable(getContext(), R.drawable.bannerconfefrancia1));

        //Inicilizar Vision
        txtAuditorio.setText(getString(R.string.CON_TXT_Auditorio1));
        txtPabellon.setText(getString(R.string.CON_TXT_PabellonA));
        adapter = new confAdapter(getContext(),nAuditorio,fragAuditorio.this, idioma);

        //Adapter
        recyclerAdapter.setLayoutManager(new LinearLayoutManager((getContext())));
        recyclerAdapter.setHasFixedSize(true);
        recyclerAdapter.setAdapter(adapter);

        //control botones
        btnDerecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nAuditorio++;
                if(nAuditorio != BLOQUE_1){
                    btnIzquierda.setVisibility(View.VISIBLE);
                }else{

                }
                if(nAuditorio == BLOQUE_7){
                    btnDerecha.setVisibility(View.INVISIBLE);
                }
                //cambiar datos
                actualizarDatos();
            }
        });

        btnIzquierda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nAuditorio--;
                if(nAuditorio == BLOQUE_1){
                    btnIzquierda.setVisibility(View.INVISIBLE);
                }
                if(nAuditorio != BLOQUE_7){
                    btnDerecha.setVisibility(View.VISIBLE);
                }
                //cambiarDatos
                actualizarDatos();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter != null && bloque_1 != null){
            actualizarDatos();
        }
    }

    private  void actualizarDatos(){

        if(nAuditorio == BLOQUE_1){
            adapter.setDataset((ArrayList<fichaConferencia>) bloque_1,BLOQUE_1);
            txtAuditorio.setText(getString(R.string.CON_TXT_Auditorio1));
            txtPabellon.setText(getString(R.string.CON_TXT_PabellonA));
        }else if(nAuditorio == BLOQUE_2){
            adapter.setDataset((ArrayList<fichaConferencia>) bloque_2,BLOQUE_2);
            txtAuditorio.setText(getString(R.string.CON_TXT_Auditorio2));
            txtPabellon.setText(getString(R.string.CON_TXT_PabellonB));
            btnIzquierda.setVisibility(View.VISIBLE);
        }else if(nAuditorio == BLOQUE_3){
            adapter.setDataset((ArrayList<fichaConferencia>) bloque_3,BLOQUE_3);
            txtAuditorio.setText(getString(R.string.CON_TXT_Auditorio3));
            txtPabellon.setText(getString(R.string.CON_TXT_PabellonC));
            btnIzquierda.setVisibility(View.VISIBLE);
        }else if(nAuditorio == BLOQUE_4){
            adapter.setDataset((ArrayList<fichaConferencia>) bloque_4,BLOQUE_4);
            txtAuditorio.setText(getString(R.string.CON_TXT_Auditorio4));
            txtPabellon.setText(getString(R.string.CON_TXT_PabellonD));
            btnIzquierda.setVisibility(View.VISIBLE);
        }else if(nAuditorio == BLOQUE_5){
            adapter.setDataset((ArrayList<fichaConferencia>) bloque_5,BLOQUE_5);
            txtAuditorio.setText(getString(R.string.CON_TXT_Auditorio5));
            txtPabellon.setText(getString(R.string.CON_TXT_PabellonE));
            btnIzquierda.setVisibility(View.VISIBLE);
        }else if(nAuditorio == BLOQUE_6){
            adapter.setDataset((ArrayList<fichaConferencia>) bloque_6,BLOQUE_6);
            txtAuditorio.setText(getString(R.string.CON_TXT_Auditorio6));
            txtPabellon.setText(getString(R.string.CON_TXT_PabellonF));
            btnIzquierda.setVisibility(View.VISIBLE);
        }else if(nAuditorio == BLOQUE_7){
            adapter.setDataset((ArrayList<fichaConferencia>) bloque_7,BLOQUE_7);
            txtAuditorio.setText(getString(R.string.CON_TXT_Auditorio7));
            txtPabellon.setText(getString(R.string.CON_TXT_Bam1));
            btnIzquierda.setVisibility(View.VISIBLE);
        }

    }




}
