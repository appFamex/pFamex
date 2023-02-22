package com.example.drawerfamex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.drawerfamex.item.ItemAdapter;
import com.example.drawerfamex.pantallas.configuracion.principal.pConfiguracion;
import com.example.drawerfamex.pantallas.espectaculoAereo.pEspectaculo;
import com.example.drawerfamex.pantallas.evento.pEvento;
import com.example.drawerfamex.pantallas.famex.pFAMEX;
import com.example.drawerfamex.pantallas.francia.pFrancia2023;
import com.example.drawerfamex.pantallas.inicio.pInicio;
import com.example.drawerfamex.pantallas.itinerario.pItinerario;
import com.example.drawerfamex.pantallas.muma.pMUMA;
import com.example.drawerfamex.pantallas.perfil.pPerfil;
import com.example.drawerfamex.pantallas.prefamex.pPrefamex;
import com.example.drawerfamex.pantallas.restaurantes.pRestaurantes;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;

public class Mapa extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    //Initialize Variable
    DrawerLayout drawerLayout;
    int[] flagsIcons = {R.drawable.mx,R.drawable.france,R.drawable.usa};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        //Assign Variable
        drawerLayout = findViewById(R.id.drawer_Layout);

        //Spinner for Language Selection
        Spinner flagSpinner = findViewById(R.id.mySpinner);
        flagSpinner.setOnItemSelectedListener(this);
        ItemAdapter myAdapter = new ItemAdapter(this,flagsIcons);
        flagSpinner.setAdapter(myAdapter);
    }


    protected void onResume() {
        super.onResume();
        //Pantalla tutorial

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_mapa), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_rep), Boolean.TRUE);
            edit.commit();
            fragPantallaTutorial popup = new fragPantallaTutorial(17);
            popup.show(getSupportFragmentManager(),"DialogFrag");
        }
    }

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
    //Event for the LogoButton
    public void ClickLogo(View view){
        //CloseDrawer
        closeDrawer(drawerLayout);
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


    //Event for the pPerfil
    public void ClickPerfil(View view){
        //Redirect Activity to Mapa
        redirectActivity(this, pPerfil.class);
    }

    //Event for Home
    public void ClickHome(View view){
        //Redirect Activity to Home
        redirectActivity(this, pInicio.class);
    }

    //Event for pFAMEX
    public void ClickFAMEX(View view){
        //Redirect Activity to pFAMEX
        redirectActivity(this, pFAMEX.class);
    }

    //Event for Francia
    public void ClickFrancia(View view){
        //Redirect Activity to Francia
        redirectActivity(this, pFrancia2023.class);
    }

    //Event for the pEvento(Recreate the activity!!)
    public void ClickLayOut(View view){
        //Redirect Activity to pEvento
        redirectActivity(this, pEvento.class);
    }

    //Event for the pRestaurantes
    public void ClickFoodCourt(View view){
        //Redirect Activity to pRestaurantes
        redirectActivity(this, pRestaurantes.class);//Here was the last Step
    }


    //Event for pItinerario
    public void ClickItinerario(View view){
        //Redirect Activity to Francia
        redirectActivity(this, pItinerario.class);
    }


    public void ClickEspectaculo(View view){
        //Redirect Activity to Home
        redirectActivity(this, pEspectaculo.class);
    }

    //Event for pMUMA
    public void ClickMUMA(View view){
        //Redirect Activity to pMUMA
        redirectActivity(this, pMUMA.class);
    }

    //Event for Sanitizacion
    public void ClickSanitizacion(View view){
        //Redirect Activity to Sanitizacion
        redirectActivity(this,Sanitizacion.class);
    }

    //Event for Accesos
    public void ClickAccesos(View view){
        //Redirect Activity to Accesos
        redirectActivity(this,Accesos.class);
    }

    public void ClickGetReady(View view){
        redirectActivity(this, pPrefamex.class);
    }


    //Event for the Mapa
    public void ClickMapa(View view){
        //Redirect Activity to Mapa (Recreate Activity!!)
        recreate();
    }

    //Event for the Configuracion
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}