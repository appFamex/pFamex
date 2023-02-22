package com.example.drawerfamex.pantallas.evento.pabellones;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextPaint;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.drawerfamex.Accesos;
import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.pantallas.espectaculoAereo.pEspectaculo;
import com.example.drawerfamex.pantallas.evento.chalets.FragChalets;
import com.example.drawerfamex.pantallas.evento.pEvento;
import com.example.drawerfamex.pantallas.famex.pFAMEX;
import com.example.drawerfamex.pantallas.francia.pFrancia2023;
import com.example.drawerfamex.pantallas.itinerario.pItinerario;
import com.example.drawerfamex.pantallas.perfil.pPerfil;
import com.example.drawerfamex.pantallas.restaurantes.pRestaurantes;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;
import com.example.drawerfamex.item.ItemAdapter;
import com.example.drawerfamex.pantallas.muma.pMUMA;
import com.example.drawerfamex.R;
import com.example.drawerfamex.TabAdapter3;
import com.example.drawerfamex.pantallas.configuracion.principal.pConfiguracion;
import com.example.drawerfamex.pantallas.evento.conferencias.pConferencias;
import com.example.drawerfamex.pantallas.inicio.pInicio;
import com.example.drawerfamex.pantallas.prefamex.pPrefamex;
import com.google.android.material.tabs.TabLayout;

public class Pabellones extends pMenu {

    //Initialize Variable
    private DrawerLayout drawerLayout;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private boolean francia = false;
    private final String claveFR = "FR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aag_7_pabellones);
        super.onCreate(savedInstanceState);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        //Assign Variable
        drawerLayout = findViewById(R.id.drawer_Layout);
        tabLayout = findViewById(R.id.mytabLayout);
        viewPager = findViewById(R.id.myViewPager);

        //Code for the TabAdapter
        //Use to join TabLayout with ViewPager

        tabLayout.setupWithViewPager(viewPager);
        TabAdapter3 tabAdapter = new TabAdapter3(getSupportFragmentManager());
        tabAdapter.addFragments(new FragPabs(0,francia,prefs.getInt(IDIOMA_POP,0)),"A");
        tabAdapter.addFragments(new FragPabs(1,francia,prefs.getInt(IDIOMA_POP,0)),"B");
        tabAdapter.addFragments(new FragPabs(2,francia,prefs.getInt(IDIOMA_POP,0)),"C");
        tabAdapter.addFragments(new FragPabs(3,francia,prefs.getInt(IDIOMA_POP,0)),"D");
        tabAdapter.addFragments(new FragPabs(4,francia,prefs.getInt(IDIOMA_POP,0)),"E");
        tabAdapter.addFragments(new FragPabs(5,francia,prefs.getInt(IDIOMA_POP,0)),"F");
        viewPager.setAdapter(tabAdapter);






    }

    @Override
    protected void onResume() {
        super.onResume();
        //Pantalla tutorial
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_pabellones), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_pabellones), Boolean.TRUE);
            edit.commit();
            fragPantallaTutorial popup = new fragPantallaTutorial(6);
            popup.show(getSupportFragmentManager(),"DialogFrag");
        }
    }


    //Method to close the Drawer!!
    public static void closeDrawer(DrawerLayout drawerLayout) {
        //CloseDrawerLayout
        //Check for Condition
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            //When Drawer is Open
            //Close Drawer
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    //Super Cool Method to Redirect Activities!!
    public static void redirectActivity(Activity activity,Class aClass) {
        //Initialize Intent
        Intent intent = new Intent(activity,aClass);
        //SetFlag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start Activity
        activity.startActivity(intent);

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Close Drawer
        closeDrawer(drawerLayout);
    }

}