package com.example.drawerfamex.pantallas.configuracion.principal;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.pantallas.configuracion.contacto.pContacto;
import com.example.drawerfamex.pantallas.configuracion.reportarProblema.pReportarProblema;
import com.example.drawerfamex.pantallas.configuracion.sobreAplicacion.pSobreAplicacion;
import com.example.drawerfamex.pantallas.configuracion.termCond.pTermCond;
import com.example.drawerfamex.R;
import com.example.drawerfamex.pantallas.inicio.pInicio;
import com.example.drawerfamex.pantallas.prefamex.pFAQs;

public class pConfiguracion extends pMenu implements DialogInterface.OnDismissListener{

   private Boolean notif = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        setContentView(R.layout.aar_18_configuracion);
        super.onCreate(savedInstanceState);
        //super.setMenu();

        LinearLayout btnIdioma,btnFaqs,btnReportProblem, btnContacto, btnAboutApp, btnTermCond, btnNotif;
        fragPopUpConfigIdioma mFragment = new fragPopUpConfigIdioma();
        btnIdioma =  (LinearLayout) findViewById(R.id.btnIdioma);
        btnIdioma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment.show(getSupportFragmentManager(),"DialogFrag");

            }
        });

        btnNotif =  (LinearLayout) findViewById(R.id.btnNotif);
        ImageView iCampana = (ImageView) findViewById(R.id.imgCampana);
        // Switch switchNotif = (Switch) findViewById(R.id.switchNotif);
        btnNotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(notif == true){
                    iCampana.setImageResource(R.drawable.config_ic_notif_desact);
                    notif = false;
                } else{
                    iCampana.setImageResource(R.drawable.config_ic_notif_activo);
                    notif = true;
                }

            }
        });



        btnFaqs =  (LinearLayout) findViewById(R.id.btnFaqs);
        btnFaqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(pConfiguracion.this, pFAQs.class);
                //SetFlag
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //Start Activity
                startActivity(intent);
            }
        });

        btnReportProblem =  (LinearLayout) findViewById(R.id.btnProblem);
        btnReportProblem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pConfiguracion.this, pReportarProblema.class);
            }
        });

        btnContacto =  (LinearLayout) findViewById(R.id.btnContacto);
        btnContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pConfiguracion.this, pContacto.class);
            }
        });

        btnAboutApp =  (LinearLayout) findViewById(R.id.btnAboutApp);
        btnAboutApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pConfiguracion.this, pSobreAplicacion.class);
            }
        });

        btnTermCond =  (LinearLayout) findViewById(R.id.btnTermCond);
        btnTermCond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pConfiguracion.this, pTermCond.class);
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        //GUARDA EN DEFAULT PREFERENCES LA BANDERA SELECCIONADA
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor edit = prefs.edit();
        if(prefs.getInt(IDIOMA_SPINNER,0) != prefs.getInt(IDIOMA_POP,0)){
            edit.putInt(IDIOMA_SPINNER, prefs.getInt(IDIOMA_POP,0));
            edit.commit();

            //cambioIdioma(position);
                    /*
                    Intent intent = getIntent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    finish();
                    overridePendingTransition(0, 0);

                    startActivity(intent);
                    overridePendingTransition(0, 0);

                     */
            cambioIdioma(prefs.getInt(IDIOMA_POP,0));
            finishAffinity();
            redirectActivity(this, pInicio.class);

        }
    }

    //Se sobreescribe el metodo para el menu
    public void ClickConfiguracion(View view){
        closeDrawer(super.getDrawerLayout());
    }



}