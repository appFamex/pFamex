package com.example.drawerfamex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.drawerfamex.item.ItemAdapter;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class MapaLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_layout);
        PDFView pdfView = findViewById(R.id.pdfView);

        View backBTN = findViewById(R.id.hori_backBTN);

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        File pdfFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "recursos"+File.separator+"PDF6"+ "." + "pdf");

        if(pdfFile.exists()){
            pdfView.fromFile(pdfFile).load();
        }else{
            pdfView.fromAsset("mapalayout.pdf").load();
        }

        pdfView.setMinZoom(1);
        pdfView.setMidZoom(3);
        pdfView.setMaxZoom(5);


    }


}