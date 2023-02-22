package com.example.drawerfamex.pantallas.famex;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.example.drawerfamex.BaseDatos.daos.pantallasDao;
import com.example.drawerfamex.BaseDatos.entidades.Pantalla;
import com.example.drawerfamex.BaseDatos.viewModel.DbViewModel;
import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.R;

public class pFAMEX extends pMenu {

    static final int nTextos = 15;
    String yetanotherlink = "https://www.f-airmexico.com.mx/galeriashow2019.html";
    ImageView boletos,ediciones;
    TextView textos[];
    Pantalla StringsTXT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aaj_10_famex);
        super.onCreate(savedInstanceState);
        //setMenu();
        //Assign Variable
        boletos = findViewById(R.id.Boletos);
        ediciones = findViewById(R.id.imageView8);

        textos = new TextView[nTextos];
        int it = 0;
        textos[it++] = findViewById(R.id.fmx_1_txt_scroll_titulo1);
        textos[it++] = findViewById(R.id.fmx_2_txt_scroll_titulo2);
        textos[it++] = findViewById(R.id.fmx_3_txt_scroll_titulo3);
        textos[it++] = findViewById(R.id.fmx_4_txt_subtitulo);
        textos[it++] = findViewById(R.id.fmx_5_txt_leyendaSubT);
        textos[it++] = findViewById(R.id.fmx_6_txt_tabla);
        textos[it++] = findViewById(R.id.fmx_7_txt_ediciones);
        textos[it++] = findViewById(R.id.fmx_8_txt_scroll_1txt);
        textos[it++] = findViewById(R.id.fmx_9_txt_scroll_2txt);
        textos[it++] = findViewById(R.id.fmx_10_txt_scroll_3txt);
        textos[it++] = findViewById(R.id.fmx_11_colm1);
        textos[it++] = findViewById(R.id.fmx_12_colm2);
        textos[it++] = findViewById(R.id.fmx_13_colm3);
        textos[it++] = findViewById(R.id.fmx_14_colm4);
        textos[it++] = findViewById(R.id.fmx_15_colm5);

        //Even for the Ediciones Butons
        ediciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri Link = Uri.parse(yetanotherlink);
                Intent i = new Intent(Intent.ACTION_VIEW,Link);
                startActivity(i);
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void setTextESP(){
        for(int i = 0; i < 10;i++) {
            textos[i].setText(StringsTXT.textosEsp.get(i));
        }
    }

    private void setTextEN(){
        for(int i = 0; i < 10;i++) {
            textos[i].setText(StringsTXT.textosEn.get(i));
        }
    }
    private void setTextFR(){
        for(int i = 0; i < 10;i++) {
            textos[i].setText(StringsTXT.textosFr.get(i));
        }
    }




}