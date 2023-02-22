package com.example.drawerfamex.pantallas.configuracion.reportarProblema;

import androidx.drawerlayout.widget.DrawerLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;
import com.example.drawerfamex.R;

public class pReportarProblema extends pMenu {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aas_19_reportar);
        super.onCreate(savedInstanceState);
        //setMenu();

    }
    @Override
    protected void onResume() {
        super.onResume();
        //Pantalla tutorial

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_rep), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_rep), Boolean.TRUE);
            edit.commit();
            fragPantallaTutorial popup = new fragPantallaTutorial(11);
            popup.show(getSupportFragmentManager(),"DialogFrag");
        }
    }


}