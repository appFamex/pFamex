package com.example.drawerfamex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.item.ItemAdapter;
import com.example.drawerfamex.pantallas.espectaculoAereo.pEspectaculo;
import com.example.drawerfamex.pantallas.evento.pEvento;
import com.example.drawerfamex.pantallas.famex.pFAMEX;
import com.example.drawerfamex.pantallas.francia.pFrancia2023;
import com.example.drawerfamex.pantallas.inicio.pInicio;
import com.example.drawerfamex.pantallas.itinerario.pItinerario;
import com.example.drawerfamex.pantallas.muma.pMUMA;
import com.example.drawerfamex.pantallas.perfil.pPerfil;
import com.example.drawerfamex.pantallas.prefamex.pPrefamex;
import com.example.drawerfamex.pantallas.restaurantes.pRestaurantes;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class Accesos extends AppCompatActivity {

    //Creacion de variable
    protected String IDIOMA_POP = "POP";

    //Pantalla Accesos Prioritarios PDF
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_accesos);
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        int idioma = prefs.getInt(IDIOMA_POP, 0);

        View backBTN = findViewById(R.id.hori_backBTN);


        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        PDFView pdfView = findViewById(R.id.pdfView);


        /*
        File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF7"+ "." + "pdf");

        if(pdfFile.exists()){
            pdfView.fromFile(pdfFile).load();
        }else{
            pdfView.fromAsset("acceosprioritarios.pdf").load();
        }
        */

        File pdfFileES = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF7"+ "." + "pdf");
        File pdfFileFR = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF5"+ "." + "pdf");
        File pdfFileEN = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF6"+ "." + "pdf");


        if(idioma == 0){//ESPAÑOL
            if(pdfFileES.exists()){
                pdfView.fromFile(pdfFileES).load();
            }else{
                pdfView.fromAsset("acceosprioritarios.pdf").load();
            }
        }else if(idioma == 1){//FRNACES
            if(pdfFileFR.exists()){
                pdfView.fromFile(pdfFileFR).load();
            }else{
                pdfView.fromAsset("mapallenons6.pdf").load();
            }
        }else if(idioma == 2){
            if(pdfFileEN.exists()){
                pdfView.fromFile(pdfFileEN).load();
            }else{
                pdfView.fromAsset("mapallenons7.pdf").load();
            }
        }

        pdfView.setMinZoom(1);
        pdfView.setMidZoom(3);
        pdfView.setMaxZoom(5);


    }

}