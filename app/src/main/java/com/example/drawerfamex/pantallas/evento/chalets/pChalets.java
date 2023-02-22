package com.example.drawerfamex.pantallas.evento.chalets;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import com.example.drawerfamex.TabAdapter3;
import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.pantallas.evento.pabellones.FragPabs;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;
import com.example.drawerfamex.R;
import com.google.android.material.tabs.TabLayout;

public class pChalets extends pMenu {

    private boolean francia = false;
    private final String claveFR = "FR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aah_8_chalets);
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        if(getIntent().hasExtra(claveFR)){
            francia = getIntent().getBooleanExtra(claveFR,false);
        }

        FragChalets fragment = new FragChalets(francia,prefs.getInt(IDIOMA_POP,0));
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.framechalets, fragment, "ff");
        //fragment.setVa
        ft.commit();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Pantalla tutorial
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_chalets), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_chalets), Boolean.TRUE);
            edit.commit();
            fragPantallaTutorial popup = new fragPantallaTutorial(5);
            popup.show(getSupportFragmentManager(),"DialogFrag");
        }

    }

}