package com.example.drawerfamex.pantallas.prefamex;

import androidx.constraintlayout.widget.ConstraintLayout;

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
import com.example.drawerfamex.R;
import com.example.drawerfamex.pantallas.prefamex.hoteles.pHoteles;
import com.example.drawerfamex.pantallas.prefamex.rutas.pRutas;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;


public class pPrefamex extends pMenu {

    private ConstraintLayout btnHoteles;
    private ConstraintLayout btnRutas;
    private ConstraintLayout btnCmLlegar;
    private ImageView btnPregFrec;
    private String linkComollegar = "https://goo.gl/maps/GwWNcHE8FHrVQdgH9";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aan_14_prefamex);
        super.onCreate(savedInstanceState);
        //setMenu();

        //Declaracion botones
        btnHoteles = findViewById(R.id.ConstrainBTNhoteles);
        btnCmLlegar = findViewById(R.id.ConstrainBTNcmLlegar);
        btnRutas = findViewById(R.id.ConstrainBTNrutas);
        btnPregFrec = findViewById(R.id.gr_btn_pregFrec);

        //Uso Botones
        btnHoteles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAnimDestino(btnHoteles,pHoteles.class);
            }
        });

        //Uso Botones
        btnCmLlegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButtonAnimation1(btnCmLlegar);
            }
        });

        btnRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAnimDestino(btnRutas,pRutas.class);
            }
        });

        btnPregFrec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAnimDestino(btnPregFrec,pFAQs.class);
            }
        });

    }

    protected void onResume() {
        super.onResume();
        //Pantalla tutorial

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_prefamex), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_prefamex), Boolean.TRUE);
            edit.commit();
            fragPantallaTutorial popup = new fragPantallaTutorial(18);
            popup.show(getSupportFragmentManager(),"DialogFrag");
        }
    }

    //Animation BTN como llegar
    public void startButtonAnimation1(ConstraintLayout view) {
        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        view.setAnimation(scaleDown);
        view.startAnimation(scaleDown);

        scaleDown.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Uri Link2 = Uri.parse(linkComollegar);
                Intent i = new Intent(Intent.ACTION_VIEW,Link2);
                startActivity(i);
            }
        });
    }

    public void btnAnimDestino(View view, Class destino) {
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
