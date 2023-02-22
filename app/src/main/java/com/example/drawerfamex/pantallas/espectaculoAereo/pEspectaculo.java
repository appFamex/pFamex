package com.example.drawerfamex.pantallas.espectaculoAereo;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.R;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;

public class pEspectaculo extends pMenu implements DialogInterface.OnDismissListener {

    private ConstraintLayout videoL;
    private ImageView btnPopup, btnBoletoEvA;

    private ImageView boletos;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        ActivityCompat.requestPermissions(pEspectaculo.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        ActivityCompat.requestPermissions(pEspectaculo.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);


        setContentView(R.layout.aal_12_espectaculo);
        super.onCreate(savedInstanceState);
       // setMenu();

        videoView = (VideoView) findViewById(R.id.vvwEspectaculo);
        videoL = findViewById(R.id.videoLayoutEvA);
        btnPopup = findViewById(R.id.btnPopEv);
        btnBoletoEvA = findViewById(R.id.imgBoletoEvA);

        //Even for the pop up
        popUpEspectaculoAereo popupEventoAereo = new popUpEspectaculoAereo();
        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //popupEventoAereo.show();
                popupEventoAereo.showNow(getSupportFragmentManager(),"DialogFrag");
                btnPopup.setVisibility(View.INVISIBLE);
            }
        });

        videoL.setVisibility(View.INVISIBLE);
        btnPopup.setVisibility(View.INVISIBLE);

        btnBoletoEvA.setVisibility(View.INVISIBLE);

        videoView.setVisibility(View.INVISIBLE);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.videofamex;
        Uri uri = Uri.parse(videoPath);
        videoView.setMediaController(new MediaController(videoView.getContext()));
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        videoL.postDelayed(new Runnable() {
            @Override
            public void run() {

                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
                videoView.setAnimation(animation);
                videoView.setVisibility(View.VISIBLE);
                videoL.setAnimation(animation);
                videoL.setVisibility(View.VISIBLE);
                btnBoletoEvA.setAnimation(animation);
                btnBoletoEvA.setVisibility(View.VISIBLE);
                btnPopup.setAnimation(animation);
                btnPopup.setVisibility(View.VISIBLE);
            }
        }, 1000 * 2);    // 2 seconds


    }


    protected void onResume() {
        super.onResume();
        //Pantalla tutorial


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_espectaculoaereo), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_espectaculoaereo), Boolean.TRUE);
            edit.commit();
            fragPantallaTutorial popup = new fragPantallaTutorial(15);
            popup.show(getSupportFragmentManager(),"DialogFrag");
        }


    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        //Fragment dialog had been dismissed
        btnPopup.setVisibility(View.VISIBLE);
    }

}