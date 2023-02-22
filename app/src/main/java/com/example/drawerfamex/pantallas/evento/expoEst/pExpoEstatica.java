package com.example.drawerfamex.pantallas.evento.expoEst;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import com.example.drawerfamex.BaseDatos.entidades.fichaExpo;
import com.example.drawerfamex.BaseDatos.viewModel.FichasChalViewModel;
import com.example.drawerfamex.BaseDatos.viewModel.FichasExpoViewModel;
import com.example.drawerfamex.adapters.expoAdapter;
import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;
import com.example.drawerfamex.item.Item;
import com.example.drawerfamex.item.Item5;
import com.example.drawerfamex.R;
import com.example.drawerfamex.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

public class pExpoEstatica extends pMenu {

    private static final int BLOQUE_1 = 0;
    private static final int BLOQUE_2 = 1;
    private static final int BLOQUE_3 = 2;

    private final String claveFR = "FR";
    private boolean francia = false;

    //Initialize Variables
    private RecyclerView recyclerView,recyclerView2,recyclerView3;
    private expoAdapter adapter,adapter2,adapter3;
    private List<fichaExpo> items = new ArrayList<>();
    private List<fichaExpo> items2 = new ArrayList<>();
    private List<fichaExpo> items3 = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aai_9_expo_estatica);
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        francia = getIntent().getBooleanExtra(claveFR,false);


        //Inicilizar Recyclers
        recyclerView = (RecyclerView)findViewById(R.id.rcvexpoestatica_bloque1);
        recyclerView2 = (RecyclerView)findViewById(R.id.rcvexpoestatica_bloque2);
        recyclerView3 = (RecyclerView)findViewById(R.id.rcvexpoestatica_bloque3);

        //Adapters
        adapter = new expoAdapter(getBaseContext(),0,prefs.getInt(IDIOMA_POP,0),francia);
        adapter2 = new expoAdapter(getBaseContext(),1,prefs.getInt(IDIOMA_POP,0),francia);
        adapter3 = new expoAdapter(getBaseContext(),2,prefs.getInt(IDIOMA_POP,0),francia);

        //Recyclers
        recyclerView.setHasFixedSize(true);
        recyclerView2.setHasFixedSize(true);
        recyclerView3.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
        recyclerView3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        recyclerView.setAdapter(adapter);
        recyclerView2.setAdapter(adapter2);
        recyclerView3.setAdapter(adapter3);

        //Datos
        FichasExpoViewModel viewModel = ViewModelProviders.of(this).get(FichasExpoViewModel.class);

        if(francia)
            viewModel.getFichasExpoF(BLOQUE_1,francia);
        else
            viewModel.getFichasExpo(BLOQUE_1);

        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null){
                return;
            }
            adapter.adicionarListaFichas(lista);
        });


        if(francia)
            viewModel.getFichasExpoF(BLOQUE_2,francia);
        else
            viewModel.getFichasExpo(BLOQUE_2);

        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null){
                return;
            }
            adapter2.adicionarListaFichas(lista);
        });


        if(francia)
            viewModel.getFichasExpoF(BLOQUE_3,francia);
        else
            viewModel.getFichasExpo(BLOQUE_3);

        viewModel.getListaFichas().observe(this, lista -> {

            if(lista == null){
                return;
            }
            adapter3.adicionarListaFichas(lista);
        });


    }//End of OnCreateMethod


    @Override
    protected void onResume() {
        super.onResume();

        //Pantalla tutorial
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_expoEst), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_expoEst), Boolean.TRUE);
            edit.commit();
            fragPantallaTutorial popup = new fragPantallaTutorial(7);
            popup.show(getSupportFragmentManager(),"DialogFrag");
        }

    }
}