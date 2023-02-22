package com.example.drawerfamex.pantallas.evento.conferencias;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;

import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;
import com.example.drawerfamex.R;
import com.example.drawerfamex.TabAdapter3;
import com.google.android.material.tabs.TabLayout;

public class pConferencias extends pMenu {
    public static final String idactivity ="IDD";
    private final String claveFR = "FR";
    //Initialize Variable
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private boolean francia= false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aaf_6_conferencias);
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
       //setMenu();

        if(getIntent().hasExtra(claveFR)){
            francia = getIntent().getBooleanExtra(claveFR,false);
        }

        //Assign Variable
        tabLayout = findViewById(R.id.mytabLayout);
        viewPager = findViewById(R.id.myViewPager);
        ImageView igvFavConferencia = findViewById(R.id.igvFavConferencia);

        //Code for the TabAdapter
        tabLayout.setupWithViewPager(viewPager);
        TabAdapter3 tabAdapter = new TabAdapter3(getSupportFragmentManager());
        tabAdapter.addFragments(new fragAuditorio(0,francia,prefs.getInt(IDIOMA_POP,0)), getString(R.string.CON_TAB_Dia1));
        tabAdapter.addFragments(new fragAuditorio(1,francia,prefs.getInt(IDIOMA_POP,0)),getString(R.string.CON_TAB_Dia2));
        tabAdapter.addFragments(new fragAuditorio(2,francia,prefs.getInt(IDIOMA_POP,0)),getString(R.string.CON_TAB_Dia3));
        viewPager.setAdapter(tabAdapter);

    }
    @Override
    protected void onResume() {
        super.onResume();

        //Pantalla tutorial
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_conferencias), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_conferencias), Boolean.TRUE);
            edit.commit();
            fragPantallaTutorial popup = new fragPantallaTutorial(4);
            popup.show(getSupportFragmentManager(),"DialogFrag");
        }

    }

    public void onClick(View view){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch(view.getId()){
            //case R.id.btnderecha:
        }
    }


}





