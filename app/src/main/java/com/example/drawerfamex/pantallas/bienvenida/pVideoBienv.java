package com.example.drawerfamex.pantallas.bienvenida;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.drawerfamex.Constants;
import com.example.drawerfamex.R;
import com.example.drawerfamex.pantallas.inicio.pInicio;


public class pVideoBienv extends Activity {
    //FALTA QUE SE MIESTRE VIDEO SUGUN IDIOMA
    private VideoView videoView;
    protected String IDIOMA_POP = "POP";
    protected String IDIOMA_SPINNER = "SPINNER";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_frag_p_bienvenida);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        int idioma = prefs.getInt(IDIOMA_POP, 0);


        initPage();
        initAnimation();
        //idioma = consulta SQL

        ConstraintLayout contenedor = (ConstraintLayout) findViewById(R.id.pbm);

        LayoutInflater inflater = LayoutInflater.from(this);

        View laViewInflada = inflater.inflate(R.layout.fragment_frag_p_bienvenida, contenedor, true);

        videoView = (VideoView) findViewById(R.id.video_view);
        videoView.setVisibility(View.VISIBLE);
        String videoPath;
        if(idioma == 0){//ESPAÑOL
            videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_es;
        }else if(idioma == 1){//FRNACES
            videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_fr;
        }else{//INGLES
            videoPath = "android.resource://" + getPackageName() + "/" + R.raw.video_en;
        }

        Uri uri = Uri.parse(videoPath);
        //videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                fadeTransitionJava(contenedor);
            }
        });

    }


    //Funcion que recupera info de transicion
    public void initPage(){

        // type = (Constants.TransitionType) getIntent().getSerializableExtra(Constants.KEY_ANIM_TYPE);
        // toolbarTitle = getIntent().getExtras().getString(Constants.KEY_TITLE);

    }

    //Funcion de animacion de entrada
    public void initAnimation(){

        Fade enterTransition = new Fade();
        enterTransition.setDuration(1000);
        getWindow().setEnterTransition(enterTransition);
    }

    //Intent Animacion salida
    public void fadeTransitionJava(View view){
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this);
        Intent i = new Intent(pVideoBienv.this, pInicio.class);
        i.putExtra(Constants.KEY_ANIM_TYPE,Constants.TransitionType.FadeJava);
        i.putExtra(Constants.KEY_TITLE,"Fade by Java");
        startActivity(i,options.toBundle());
    }

    //Super Cool Method to Redirect Activities!!
    public static void redirectActivity(Activity activity, Class aClass) {
        //Initialize Intent
        Intent intent = new Intent(activity,aClass);
        //SetFlag
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Start Activity
        activity.startActivity(intent);
    }

    @Override
    public void onBackPressed() {

    }
}