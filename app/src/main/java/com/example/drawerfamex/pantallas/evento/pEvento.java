package com.example.drawerfamex.pantallas.evento;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.pantallas.evento.chalets.pChalets;
import com.example.drawerfamex.pantallas.evento.conferencias.pConferencias;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;
import com.example.drawerfamex.MapaLayout;
import com.example.drawerfamex.R;
import com.example.drawerfamex.pantallas.evento.expoEst.pExpoEstatica;
import com.example.drawerfamex.pantallas.evento.pabellones.Pabellones;


public class pEvento extends pMenu {

    //Initialize Variable
    private ImageView mapa,conferencias,Chalets,pabellones,expoestatica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aae_5_evento);
        super.onCreate(savedInstanceState);
        //setMenu();

        //Assign Variable
        mapa = findViewById(R.id.MapaGeneral);
        conferencias = findViewById(R.id.Conferencias);
        Chalets = findViewById(R.id.imageView12);
        pabellones = findViewById(R.id.imageView10);
        expoestatica = findViewById(R.id.imageView13);


        //Event for the animation of the General Layout View
        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAnimDestino(mapa,MapaLayout.class);
            }
        });

        Chalets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAnimDestino(Chalets, pChalets.class);
            }
        });

        //Event for the animation of the General Layout View
        conferencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAnimDestino(conferencias,pConferencias.class);
            }
        });

        //Event for the animation of the Pabellones Layout View
        pabellones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAnimDestino(pabellones, Pabellones.class);
            }
        });

        //Event for the animation of the pExpoEstatica Layout View
        expoestatica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAnimDestino(expoestatica,pExpoEstatica.class);
            }
        });

    }//Here ends Oncreate Method!!


    @Override
    protected void onResume() {
        super.onResume();
        //Pantalla tutorial
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_evento), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_evento), Boolean.TRUE);
            edit.commit();
            fragPantallaTutorial popup = new fragPantallaTutorial(2);
            popup.show(getSupportFragmentManager(),"DialogFrag");
        }
    }

    public void btnAnimDestino(ImageView view, Class destino) {
        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        view.setAnimation(scaleDown);
        view.startAnimation(scaleDown);
        scaleDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent i = new Intent(getApplicationContext(), destino);
                startActivity(i);
            }
        });
    }

}