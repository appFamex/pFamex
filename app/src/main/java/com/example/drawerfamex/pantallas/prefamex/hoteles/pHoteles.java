package com.example.drawerfamex.pantallas.prefamex.hoteles;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Html;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.drawerfamex.BaseDatos.entidades.fichaHoteles;
import com.example.drawerfamex.BaseDatos.viewModel.FichasHotelesViewModel;
import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;
import com.example.drawerfamex.R;
import com.example.drawerfamex.TabAdapter3;

import java.util.ArrayList;
import java.util.List;

public class pHoteles extends pMenu {

    //FALTA QUE FUNCIONE DE MANERA AUTOMATICA

    //Variables Funcionamiento
    private ViewPager viewPager;
    private TabAdapter3 tabAdapter;
    private int nFragmentsHoteles;
    private LinearLayout linearLDots;
    private TextView[] dots;
    private ArrayList<fichaHoteles> listH;
    private int idioma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aao_15_hoteles);
        super.onCreate(savedInstanceState);
        FichasHotelesViewModel HotelViewModel = ViewModelProviders.of(this).get((FichasHotelesViewModel.class));
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        idioma = prefs.getInt(IDIOMA_POP,0);

       // setMenu();
        HotelViewModel.getFichas();
        HotelViewModel.getListaFichas().observe(this, lista -> {
                if (lista == null) {
                    return;
                }

                listH = (ArrayList<fichaHoteles>) lista;
                nFragmentsHoteles = listH.size();
                addDots(0);
                loadViewPager();
        });


        //Declaracion botones
        linearLDots = findViewById(R.id.LLH_dots);
        viewPager = findViewById(R.id.slideFagment);


    } //finish onCreate

    @Override
    protected void onResume() {
        super.onResume();
        //Pantalla tutorial
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_hoteles), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_hoteles), Boolean.TRUE);
            edit.commit();
            fragPantallaTutorial popup = new fragPantallaTutorial(8);
            popup.show(getSupportFragmentManager(),"DialogFrag");
        }
    }


    //Carga / Añade fragments
    private void loadViewPager(){
        tabAdapter = new TabAdapter3(getSupportFragmentManager());
        for(int i = 0; i<nFragmentsHoteles;i++){
            if(idioma == 0){
                tabAdapter.addFragments(new fragSlideInfoHotel(listH.get(i).imagenEsp,listH.get(i).linkES));
            }else if(idioma == 1){
                tabAdapter.addFragments(new fragSlideInfoHotel(listH.get(i).ImagenFr,listH.get(i).linkFR));
            }else{
                tabAdapter.addFragments(new fragSlideInfoHotel(listH.get(i).ImagenEn,listH.get(i).linkEN));
            }

        }
        viewPager.setAdapter(tabAdapter);
        viewPager.setOnPageChangeListener(pagerListener);
    }

    ViewPager.OnPageChangeListener pagerListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //Añade botones
    private void addDots(int currentPage){

        dots = new TextView[nFragmentsHoteles];

        linearLDots.removeAllViews();

        for(int i = 0;i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(40);
            if(i == currentPage){
                dots[i].setTextColor(Color.WHITE);
            } else{
                dots[i].setTextColor(Color.LTGRAY);
            }
            linearLDots.addView(dots[i]);
        }
    }


}

