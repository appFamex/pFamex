package com.example.drawerfamex.pantallas.inicio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.lifecycle.ViewModelProviders;

import com.example.drawerfamex.BaseDatos.viewModel.FichasAnuncioVM;
import com.example.drawerfamex.BaseDatos.viewModel.FichasInicioVM;
import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;
import com.example.drawerfamex.pantallas.itinerario.pItinerario;
import com.example.drawerfamex.pantallas.evento.pEvento;
import com.example.drawerfamex.R;

import org.imaginativeworld.whynotimagecarousel.ImageCarousel;
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;

import java.util.ArrayList;
import java.util.List;

public class pInicio extends pMenu {

    //FALTA QUE FUNCIONEN LAS IMAGENES

    private List<CarouselItem> list = new ArrayList<>();//Array for the items in the Slide
    private List<CarouselItem> list2 = new ArrayList<>();//Array for the items in the Second Slide
    private ImageView Itinerario,Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aac_3_principal);
        super.onCreate(savedInstanceState);

        FichasInicioVM InitViewModel = ViewModelProviders.of(this).get((FichasInicioVM.class));
        FichasAnuncioVM AnunViewModel = ViewModelProviders.of(this).get((FichasAnuncioVM.class));

        ImageCarousel carousel = findViewById(R.id.Slidecarousel);
        ImageCarousel carousel1 = findViewById(R.id.Slidecarousel1);
        Layout = findViewById(R.id.imageViewLayout);
        Itinerario = findViewById(R.id.imageViewItinerario);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

        InitViewModel.getFichas();
        InitViewModel.getListaFichas().observe(this, lista -> {

            if(lista == null){
                return;
            }
            if(prefs.getInt(IDIOMA_POP,0) == 0){
                for(int i = 0; i < 3;i++){
                    list.add(new CarouselItem(lista.get(i).imagen1));
                }
            }else if(prefs.getInt(IDIOMA_POP,0) == 1){
                for(int i = 0; i < 3;i++){
                    list.add(new CarouselItem(lista.get(i).Imagen2));
                }
            }else if(prefs.getInt(IDIOMA_POP,0) == 2){
                for(int i = 0; i < 3;i++){
                    list.add(new CarouselItem(lista.get(i).Imagen3));
                }
            }



            //Adding the data to the List
            carousel.setData(list);

        });

        AnunViewModel.getFichas();
        AnunViewModel.getListaFichas().observe(this, lista ->{
            if(lista == null){
                return;
            }


            list2.add(new CarouselItem(lista.get(prefs.getInt(IDIOMA_POP,0)).imagen1));
            list2.add(new CarouselItem(lista.get(prefs.getInt(IDIOMA_POP,0)).Imagen2));
            list2.add(new CarouselItem(lista.get(prefs.getInt(IDIOMA_POP,0)).Imagen3));

            //Adding the data to the List
            carousel1.setData(list2);
        });



        //Event for the animation of Layout Button
        Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButtonAnimation(Layout);
            }
        });

        //Event for the animation of Layout Button
        Itinerario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startButtonAnimation1(Itinerario);
            }
        });

    }//Here ends Oncreate Method!!

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Pantalla tutorial
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_principal), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_principal), Boolean.TRUE);
            edit.commit();
            fragPantallaTutorial popup = new fragPantallaTutorial(1);
            popup.show(getSupportFragmentManager(),"DialogFrag");
        }
    }

    //Animation Shit
    public void startButtonAnimation(ImageView view) {
        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        view.setAnimation(scaleDown);
        view.startAnimation(scaleDown);

        scaleDown.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(getApplicationContext(), pEvento.class));
            }
        });
    }

    public void startButtonAnimation1(ImageView view) {
        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        view.setAnimation(scaleDown);
        view.startAnimation(scaleDown);

        scaleDown.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(getApplicationContext(), pItinerario.class));
            }
        });
    }

}
