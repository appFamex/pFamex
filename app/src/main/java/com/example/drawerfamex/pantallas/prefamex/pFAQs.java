package com.example.drawerfamex.pantallas.prefamex;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.R;

public class pFAQs extends pMenu {

    //Botones
    private ImageView btnGenerales, flechaG, flechaE, flechaR;
    private ImageView btnEvento;
    private ImageView btnRutas;

    //Textos desplegables
    private ConstraintLayout textoGenerales;
    private ConstraintLayout textoEvento;
    private ConstraintLayout textoRutas;


    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aaq_17_preguntas);
        super.onCreate(savedInstanceState);
        //setMenu();

        final ScrollView scrollview = ((ScrollView) findViewById(R.id.scrollFAQs));

        //Declaracion botones

        btnGenerales = findViewById(R.id.btnGenerales);
        btnEvento = findViewById(R.id.btnEvento);
        btnRutas = findViewById((R.id.btnRutas));
        flechaE = findViewById(R.id.flechaBtnEvento);
        flechaG =  findViewById(R.id.flechaBtnGenerales);
        flechaR = findViewById(R.id.flechaBtnRutas);

        //Declaracion de Textos
        textoGenerales = findViewById(R.id.txtsGenerales);
        textoEvento = findViewById(R.id.txtsEvento);
        textoRutas = findViewById(R.id.txtsRutas);

        btnGenerales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textoGenerales.getVisibility()== View.GONE){
                    textoGenerales.setVisibility(View.VISIBLE);
                    flechaG.setImageResource(R.drawable.faqs_ic_flecha_arriba);
                    scrollview.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    });
                }else{
                    textoGenerales.setVisibility(View.GONE);
                    flechaG.setImageResource(R.drawable.faqs_ic_flecha_abajo);
                }

            }
        });

        btnEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textoEvento.getVisibility()== View.GONE){
                    textoEvento.setVisibility(View.VISIBLE);
                    flechaE.setImageResource(R.drawable.faqs_ic_flecha_arriba);
                    scrollview.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollview.fullScroll(ScrollView.FOCUS_DOWN);
                        }
                    });
                }else{
                    textoEvento.setVisibility(View.GONE);
                    flechaE.setImageResource(R.drawable.faqs_ic_flecha_abajo);
                }

            }
        });

        btnRutas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textoRutas.getVisibility()== View.GONE){
                    textoRutas.setVisibility(View.VISIBLE);
                    flechaR.setImageResource(R.drawable.faqs_ic_flecha_arriba);
                    scrollview.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollview.fullScroll(ScrollView.FOCUS_DOWN);

                        }
                    });
                }else{
                    textoRutas.setVisibility(View.GONE);
                    flechaR.setImageResource(R.drawable.faqs_ic_flecha_abajo);
                }

            }
        });

    }

}
