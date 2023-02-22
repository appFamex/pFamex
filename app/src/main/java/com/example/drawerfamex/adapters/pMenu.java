package com.example.drawerfamex.adapters;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextPaint;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.drawerfamex.Accesos;
import com.example.drawerfamex.Mapa;
import com.example.drawerfamex.R;
import com.example.drawerfamex.Sanitizacion;
import com.example.drawerfamex.item.ItemAdapter;
import com.example.drawerfamex.pantallas.configuracion.principal.pConfiguracion;
import com.example.drawerfamex.pantallas.espectaculoAereo.pEspectaculo;
import com.example.drawerfamex.pantallas.evento.pEvento;
import com.example.drawerfamex.pantallas.famex.pFAMEX;
import com.example.drawerfamex.pantallas.francia.pFrancia2023;
import com.example.drawerfamex.pantallas.inicio.pInicio;
import com.example.drawerfamex.pantallas.itinerario.pItinerario;
import com.example.drawerfamex.pantallas.muma.pMUMA;

import com.example.drawerfamex.pantallas.boletos.pBoletos;


import com.example.drawerfamex.pantallas.perfil.pPerfil;
import com.example.drawerfamex.pantallas.prefamex.pPrefamex;
import com.example.drawerfamex.pantallas.restaurantes.pRestaurantes;

import java.util.Locale;

public class pMenu extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //CLAVES DE ACCESO DEFAULT PREFERENCES POP UP / MENU HAMBURGESA
    protected String IDIOMA_POP = "POP";
    protected String IDIOMA_SPINNER = "SPINNER";
    protected String ICONO = "ICONO";
    protected String NOMBRE_USUARIO = "NOMBRE USUARIO";


    //Boletos
    //String link = "https://www.f-airmexico.com.mx/landingboletos.html";

    //MENU HAMBURGESA
    DrawerLayout drawerLayout;
    TextView nombreUsuario;
    ImageView iconoUsuario;
    ViewGroup home,perfil,famex,francia,evento,itinerario,restaurantes,espectaculo,
    muma,accessos,sanitizacion,prefamex,configuracion,boletos;
    protected int[] iconouser = {R.drawable.iconouser1,R.drawable.iconouser2,R.drawable.iconouser3, R.drawable.iconouser4,
            R.drawable.iconouser5};

    // BANDERAS SPINNER
    int[] flagsIcons = {R.drawable.mx,R.drawable.france,R.drawable.usa};

    //SPINNER
    Spinner flagSpinner;

    //OVERRIDES HEREDAN SU IMPLEMENTACION A SUS PANTALLAS HIJAS
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMenu();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //CONSERVA LA BANDERA SELECCIONADA CADA QUE SE RECARGA LA PANTALLA O SE CREA UNA NUEVA
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor edit = prefs.edit();
        flagSpinner.setSelection(prefs.getInt(IDIOMA_SPINNER,0));
        nombreUsuario.setText(prefs.getString(NOMBRE_USUARIO,"Usuario 1"));
        iconoUsuario.setImageResource(prefs.getInt(ICONO,iconouser[0]));
        listeners();

    }

    public void setMenu(){
        //Declaracion botones
        drawerLayout = findViewById(R.id.drawer_Layout);
        nombreUsuario = findViewById(R.id.MENU_txt_nombreUsuario);
        iconoUsuario = findViewById(R.id.MENU_img_perfil);

        //navegacion
        home = findViewById(R.id.MENU_LL_Inicio);
        perfil = findViewById(R.id.MENU_LL_perfil);
        famex = findViewById(R.id.MENU_LL_FAMEX);
        francia = findViewById(R.id.MENU_LL_francia);
        evento = findViewById(R.id.MENU_LL_evento);
        itinerario = findViewById(R.id.MENU_LL_itinerario);
        restaurantes = findViewById(R.id.MENU_LL_restaurantes);
        espectaculo = findViewById(R.id.MENU_LL_espectaculo);
        muma = findViewById(R.id.MENU_LL_MUMA);
        accessos = findViewById(R.id.MENU_LL_ACSS);
        sanitizacion = findViewById(R.id.MENU_LL_SANT);
        prefamex = findViewById(R.id.MENU_LL_prefamex);
        configuracion = findViewById(R.id.MENU_LL_settings);
        boletos = findViewById(R.id.MENU_LL_boletos);


        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor edit = prefs.edit();

        //Francia Color Text
        TextView FranciaText = (TextView) findViewById(R.id.franciatext);
        FranciaText.setText("Francia".toUpperCase());
        TextPaint paint = FranciaText.getPaint();
        float width = paint.measureText("Francia");

        Shader textShader = new LinearGradient(0, 0, width,FranciaText.getTextSize(),
                new int[]{
                        Color.parseColor("#002395"),
                        Color.parseColor("#FFFFFF"),
                        Color.parseColor("#ED2939"),
                }, null, Shader.TileMode.CLAMP);
        FranciaText.getPaint().setShader(textShader);



        //Spinner for Language Selection
        flagSpinner = findViewById(R.id.mySpinner);
        flagSpinner.setOnItemSelectedListener(this);
        ItemAdapter myAdapter = new ItemAdapter(this,flagsIcons);
        flagSpinner.setAdapter(myAdapter);

        //LISTENER SPINNER
        flagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //GUARDA EN DEFAULT PREFERENCES LA BANDERA SELECCIONADA
                if(prefs.getInt(IDIOMA_SPINNER,0) != position){
                    edit.putInt(IDIOMA_POP, position );
                    edit.putInt(IDIOMA_SPINNER, position);
                    edit.commit();

                    cambioIdioma(position);
                    finishAffinity();
                    redirectActivity(pMenu.this, pInicio.class);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // sometimes you need nothing here
            }
        });




    }

    public void listeners(){
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pMenu.this,pInicio.class);
                Log.e("ERROR","a" );
            }
        });

        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pMenu.this,pPerfil.class);
            }
        });

        famex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pMenu.this,pFAMEX.class);
            }
        });

        francia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pMenu.this,pFrancia2023.class);
            }
        });

        evento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pMenu.this,pEvento.class);
            }
        });

        itinerario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pMenu.this,pItinerario.class);
            }
        });

        restaurantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pMenu.this,pRestaurantes.class);
            }
        });

        espectaculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pMenu.this,pEspectaculo.class);
            }
        });

        muma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pMenu.this,pMUMA.class);
            }
        });

        accessos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pMenu.this,Accesos.class);
            }
        });

        sanitizacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pMenu.this,Sanitizacion.class);
            }
        });

        prefamex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pMenu.this,pPrefamex.class);
            }
        });

        configuracion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pMenu.this,pConfiguracion.class);
            }
        });

        //

        boletos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectActivity(pMenu.this,pBoletos.class);
            }
        });

        // BOLETOS
        /*
        boletos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri Link = Uri.parse(link);
                Intent i = new Intent(Intent.ACTION_VIEW,Link);
                startActivity(i);
            }

        });*/

    }





    //METODO CAMBIO IDIOMA
    public void cambioIdioma(int i){
        // Obtener la configuración actual
        Configuration config = getResources().getConfiguration();
        Locale locale = null;

        // Crear un nuevo objeto Locale con el idioma deseado
        if(i == 0){
            locale = new Locale("es");
        }else if(i == 1){
            locale = new Locale("fr");
        }else{
            locale = new Locale("en");
        }


        // Establecer el nuevo idioma en la configuración
        config.setLocale(locale);

        // Actualizar la configuración de la actividad
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }

    //METODOS MENU HAMBURGESA-----

    //Event for the Menu
    public void ClickMenu(View view){
        //Opening Drawer
        openDrawer(drawerLayout);
    }

    //Method for Opening the Drawer
    public static void openDrawer(DrawerLayout drawerLayout) {
        //OpenDrawerLayout
        drawerLayout.openDrawer(GravityCompat.START);
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

    //Event for the pEvento
    public void ClickLayOut(View view){
        //Redirect Activity to pEvento
        redirectActivity(this, pEvento.class);
    }

    //Event for pItinerario
    public void ClickItinerario(View view){
        //Redirect Activity to Francia
        redirectActivity(this, pItinerario.class);
    }

    //Event for pEspectaculo
    public void ClickEspectaculo(View view){
        //Redirect Activity to Home
        redirectActivity(this, pEspectaculo.class);
    }

    //Event for pMUMA
    public void ClickMUMA(View view){
        //Redirect Activity to pMUMA
        redirectActivity(this, pMUMA.class);
    }

    //Event for pBoletos
    public void ClickBoletos(View view){
        //Redirect Activity to pBoletos
        redirectActivity(this, pBoletos.class);
    }


    //Event for Sanitizacion
    public void ClickSanitizacion(View view){
        //Redirect Activity to Sanitizacion
        redirectActivity(this, Sanitizacion.class);
    }

    //Event for Accesos
    public void ClickAccesos(View view){
        redirectActivity(this, Accesos.class);
    }

    //Event for the Mapa
    public void ClickMapa(View view){
        //Redirect Activity to Mapa
        redirectActivity(this, Mapa.class);
    }

    //Event for the  PRE FAMEX
    public void ClickGetReady(View view){
        redirectActivity(this, pPrefamex.class);
    }

    public void ClickConfiguracion(View view){
        redirectActivity(this, pConfiguracion.class);
    }

    //Event for the Log-Out
    public void ClickLogOut(View view){
        logout(this);
    }

    //Method for AlertDialog(Further Coding Expected!!)
    public static void logout(final Activity activity) {
        //Initialize Alert Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        //Set Title
        builder.setTitle("Log-Out");
        //Set Message
        builder.setMessage("No hagas eso bro...");
        //Positive YES Button
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Finish Activity
                activity.finishAffinity();
                //Exit App
                System.exit(0);

            }
        });
        //Negative NO Button
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Dismiss Dialog
                dialog.dismiss();
            }
        });
        //Show dialog
        builder.show();

    }

    //Super Cool Method to Redirect Activities!!
    public static void redirectActivity(Activity activity,Class aClass) {
        //Initialize Intent
        Intent intent = new Intent(activity,aClass);
        //Start Activity
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        activity.startActivity(intent);

    }


    //CADA QUE SE SALE DE UNA PANTALLA SE CIERRA EL MENU HAMBURGESA
    @Override
    protected void onPause() {
        super.onPause();
        //Close Drawer
        closeDrawer(drawerLayout);
    }

    //SPINNER ADAPTER METODOS
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //GETTERS Y SETTERS

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public void setDrawerLayout(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }

    public int[] getFlagsIcons() {
        return flagsIcons;
    }

    public void setFlagsIcons(int[] flagsIcons) {
        this.flagsIcons = flagsIcons;
    }
}
