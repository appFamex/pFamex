package com.example.drawerfamex.pantallas.configuracion.contacto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.R;

public class pContacto extends pMenu {

    //Links
    private String linkFB ="https://www.facebook.com/feriaaeroespacialmexico";
    private String linkIG ="https://www.instagram.com/famex_oficial";
    private String linkWeb ="https://www.f-airmexico.com.mx/";
    private String linkYT ="https://www.youtube.com/c/FairmexicoMxGPlus";

    //Btns RS
    private ImageView btnFB,btnIG,btnWeb,btnYT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aat_20_contactos);
        super.onCreate(savedInstanceState);

     //  setMenu();
        btnFB = findViewById(R.id.btnFB);
        btnIG = findViewById(R.id.btnIG);
        btnWeb = findViewById(R.id.btnWeb);
        btnYT = findViewById(R.id.btnYT);




        btnFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkRS(linkFB);
            }
        });

        btnIG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkRS(linkIG);
            }
        });

        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkRS(linkWeb);
            }
        });

        btnYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinkRS(linkYT);
            }
        });

    }

    //Funcion Redirect RS

    public void LinkRS(String link){
        Uri Link = Uri.parse(link);
        Intent i = new Intent(Intent.ACTION_VIEW,Link);
        startActivity(i);
    }




}