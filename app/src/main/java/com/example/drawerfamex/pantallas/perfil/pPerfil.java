package com.example.drawerfamex.pantallas.perfil;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.drawerfamex.R;
import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.pantallas.inicio.pInicio;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;

public class pPerfil extends pMenu {

    //FALTA QUE FUNCIONE ICONO Y NOMBRE USUARIO

    private EditText editUserName;
    private TextView txtUserName;
    private ImageView icono1,icono2,icono3,icono4,icono5,imgiconouser, edit , save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aax_23_perfil);
        super.onCreate(savedInstanceState);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //setMenu();

        //Assign Variable
        icono1 = findViewById(R.id.imgicono1);
        icono2 = findViewById(R.id.imgicono2);
        icono3 = findViewById(R.id.imgicono3);
        icono4 = findViewById(R.id.imgicono4);
        icono5 = findViewById(R.id.imgicono5);
        edit = findViewById(R.id.PERF_img_edit);
        save = findViewById(R.id.PERF_img_save);
        imgiconouser = findViewById(R.id.imgiconouser);
        txtUserName = findViewById(R.id.txtnombreusuarioS);
        editUserName = findViewById(R.id.txtnombreusuario);

        //Even for the Tickets Butons
        icono1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Animationicon(icono1, 0);
            }
        });

        icono2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Animationicon(icono2, 1);
            }
        });

        icono3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Animationicon(icono3,2);
            }
        });


        icono4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Animationicon(icono4,3);
            }
        });

        icono5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Animationicon(icono5,4);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.setVisibility(View.INVISIBLE);
                save.setVisibility(View.VISIBLE);
                editUserName.setVisibility(View.VISIBLE);
                txtUserName.setVisibility(View.INVISIBLE);
                editUserName.requestFocus();
                imm.showSoftInput(editUserName, InputMethodManager.SHOW_IMPLICIT);
                editUserName.setSelection(editUserName.getText().length());
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guardar();
            }
        });

        editUserName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE ||
                        actionId == EditorInfo.IME_ACTION_GO ||
                        actionId == EditorInfo.IME_ACTION_NEXT ||
                        actionId == EditorInfo.IME_ACTION_SEND) {
                    // Ocultar teclado
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    guardar();
                    return true;
                }
                return false;
            }
        });

    }

    public void guardar(){
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor editor = prefs.edit();
        edit.setVisibility(View.VISIBLE);
        save.setVisibility(View.INVISIBLE);
        editUserName.setVisibility(View.INVISIBLE);
        txtUserName.setVisibility(View.VISIBLE);
        txtUserName.setText(editUserName.getText().toString());
        editor.putString(NOMBRE_USUARIO, editUserName.getText().toString());
        editor.apply();
        finishAffinity();
        redirectActivity(pPerfil.this, pInicio.class);

        Toast.makeText(getBaseContext(), "Se cambio el nombre de usuario ", Toast.LENGTH_SHORT).show();
    }

    protected void onResume() {
        super.onResume();

        //Pantalla tutorial
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        imgiconouser.setImageResource(prefs.getInt(ICONO,iconouser[0]));
        editUserName.setText(prefs.getString(NOMBRE_USUARIO,"Usuario 1"));
        txtUserName.setText(prefs.getString(NOMBRE_USUARIO,"Usuario 1"));
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_perfil), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_perfil), Boolean.TRUE);
            edit.apply();
            fragPantallaTutorial popup = new fragPantallaTutorial(14);
            popup.show(getSupportFragmentManager(),"DialogFrag");
        }

    }

    public void Animationicon(ImageView view, int pos) {
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

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
                SharedPreferences.Editor edit = prefs.edit();
                edit.putInt(ICONO,iconouser[pos]);
                edit.apply();
                imgiconouser.setImageResource(iconouser[pos]);
                finishAffinity();
                redirectActivity(pPerfil.this, pInicio.class);

                Toast.makeText(getBaseContext(), "Se cambio la foto de portada. ", Toast.LENGTH_SHORT).show();

            }
        });
    }

}