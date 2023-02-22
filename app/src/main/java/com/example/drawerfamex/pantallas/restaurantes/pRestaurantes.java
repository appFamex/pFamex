package com.example.drawerfamex.pantallas.restaurantes;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.item.Item;
import com.example.drawerfamex.item.Item1;
import com.example.drawerfamex.R;
import com.example.drawerfamex.RecyclerAdapter;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class pRestaurantes extends pMenu {

    //INGRESAR EL PDF PRUEBA

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_food_court);
        super.onCreate(savedInstanceState);
        setMenu();
        PDFView pdfView = findViewById(R.id.pdfView);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        int idioma = prefs.getInt(IDIOMA_POP, 0);

        /*
        File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF9"+ "." + "pdf");

        if(pdfFile.exists()){
            pdfView.fromFile(pdfFile).load();
        }else{
            pdfView.fromAsset("menuejem.pdf").load();
        }
        */
        File pdfFileES = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF9"+ "." + "pdf");
        File pdfFileFR = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF3"+ "." + "pdf");
        File pdfFileEN = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF4"+ "." + "pdf");


        if(idioma == 0){//ESPAÑOL
            if(pdfFileES.exists()){
                pdfView.fromFile(pdfFileES).load();
            }else{
                pdfView.fromAsset("menuejem.pdf").load();
            }
        }else if(idioma == 1){//FRNACES
            if(pdfFileFR.exists()){
                pdfView.fromFile(pdfFileFR).load();
            }else{
                pdfView.fromAsset("mapallenons4.pdf").load();
            }
        }else if(idioma == 2){
            if(pdfFileEN.exists()){
                pdfView.fromFile(pdfFileEN).load();
            }else{
                pdfView.fromAsset("mapallenons5.pdf").load();
            }
        }









        pdfView.setMinZoom(1);
        pdfView.setMidZoom(3);
        pdfView.setMaxZoom(5);



    }
}