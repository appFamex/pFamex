package com.example.drawerfamex.pantallas.francia;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.pantallas.evento.chalets.pChalets;
import com.example.drawerfamex.pantallas.evento.conferencias.pConferencias;
import com.example.drawerfamex.pantallas.evento.expoEst.pExpoEstatica;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;
import com.example.drawerfamex.popups.base.fragPopupVentana;
import com.example.drawerfamex.item.Item;
import com.example.drawerfamex.item.Item5;
import com.example.drawerfamex.R;
import com.example.drawerfamex.RecyclerAdapter;

import java.util.ArrayList;

public class pFrancia2023 extends pMenu {

    String lnk1 = "https://mx.ambafrance.org/", link2 = "https://www.franciamexico.com/es.html",link3 = "https://www.safran-group.com/es",link4 = "https://www.airbus.com/en",link5 = "https://www.thalesgroup.com/es/americas/thales-mexico";
    ImageView boletos, imgLink1, imgLink2,imgLink3,imgLink4,imgLink5,conferencia, stands, aeronaves;

    RecyclerView recyclerAdapter;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter adapter;
    ArrayList<Item> items;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aak_11_francia);
        super.onCreate(savedInstanceState);
        //setMenu();

        //Assign Variable
        boletos = findViewById(R.id.Boletos);
        imgLink1 = findViewById(R.id.fr_link_img1);
        imgLink2 = findViewById(R.id.fr_link_img2);
        imgLink3 = findViewById(R.id.fr_link_img3);
        imgLink4 = findViewById(R.id.fr_link_img4);
        imgLink5 = findViewById(R.id.fr_link_img5);
        //conferencia = findViewById(R.id.imageConferencias);
        stands = findViewById(R.id.imageStands);
        //aeronaves = findViewById(R.id.imageAeronaves);

        //line
        TextView textView1 = (TextView) findViewById(R.id.fr_5_txt_pop1);
        TextView textView2 = (TextView) findViewById(R.id.fr_6_txt_pop2);
        TextView textView3 = (TextView) findViewById(R.id.fr_7_txt_pop3);
        TextView textView4 = (TextView) findViewById(R.id.fr_8_txt_pop4);

        SpannableString mitexto1 = new SpannableString(getString(R.string.FR_TXT_1));
        SpannableString mitexto2 = new SpannableString(getString(R.string.FR_TXT_2));
        SpannableString mitexto3 = new SpannableString(getString(R.string.FR_TXT_3));
        SpannableString mitexto4 = new SpannableString(getString(R.string.FR_TXT_4));


        /*mitexto1.setSpan(new UnderlineSpan(), 0, mitexto1.length(), 0);
        textView1.setText(mitexto1);
        mitexto2.setSpan(new UnderlineSpan(), 0, mitexto2.length(), 0);
        textView2.setText(mitexto2);
        mitexto3.setSpan(new UnderlineSpan(), 0, mitexto3.length(), 0);
        textView3.setText(mitexto3);
        mitexto4.setSpan(new UnderlineSpan(), 0, mitexto4.length(), 0);
        textView4.setText(mitexto4);*/

        //Even for the Butons
        imgLink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri Link = Uri.parse(lnk1);
                Intent i = new Intent(Intent.ACTION_VIEW,Link);
                startActivity(i);
            }
        });

        imgLink2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri Link = Uri.parse(link2);
                Intent i = new Intent(Intent.ACTION_VIEW,Link);
                startActivity(i);
            }
        });

        imgLink3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri Link = Uri.parse(link3);
                Intent i = new Intent(Intent.ACTION_VIEW,Link);
                startActivity(i);
            }
        });

        imgLink4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri Link = Uri.parse(link4);
                Intent i = new Intent(Intent.ACTION_VIEW,Link);
                startActivity(i);
            }
        });

        imgLink5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri Link = Uri.parse(link5);
                Intent i = new Intent(Intent.ACTION_VIEW,Link);
                startActivity(i);
            }
        });

        /*conferencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAnimDestino(conferencia, pConferencias.class);
            }
        });*/

        stands.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAnimDestino(stands, pChalets.class);
            }
        });

        /*aeronaves.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAnimDestino(aeronaves, pExpoEstatica.class);}
        });*/

        int valor = 3;
        TextView texto1,texto2,texto3,texto4;
        Item5 items;
        fragPopupVentana mFragment = new fragPopupVentana();
       // mFragment.valor = 1;
        mFragment.setValor(1);
        texto1 =  (TextView) findViewById(R.id.fr_5_txt_pop1);
        texto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment.setNom(getString(R.string.FR_TXT_1));
                mFragment.setDesc(getString(R.string.FR_POP_TXT_1));
                mFragment.setImg(R.drawable.fr_img_pop1);
                mFragment.show(getSupportFragmentManager(),"DialogFrag");
            }
        });


        texto2 =  (TextView) findViewById(R.id.fr_6_txt_pop2);
        texto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment.setNom(getString(R.string.FR_TXT_2));
                mFragment.setDesc(getString(R.string.FR_POP_TXT_2));
                mFragment.setImg(R.drawable.fr_img_pop2);
                mFragment.show(getSupportFragmentManager(),"DialogFrag");
            }
        });

        texto3 =  (TextView) findViewById(R.id.fr_7_txt_pop3);
        texto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment.setNom(getString(R.string.FR_TXT_3));
                mFragment.setDesc(getString(R.string.FR_POP_TXT_3));
                mFragment.setImg(R.drawable.fr_img_pop3);
                mFragment.show(getSupportFragmentManager(),"DialogFrag");
            }
        });


        texto4 =  (TextView) findViewById(R.id.fr_8_txt_pop4);
        texto4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment.setNom(getString(R.string.FR_TXT_4));
                mFragment.setDesc(getString(R.string.FR_POP_TXT_4));
                mFragment.setImg(R.drawable.fr_img_pop4);
                mFragment.show(getSupportFragmentManager(),"DialogFrag");
            }
        });
    }

    public void btnAnimDestino(ImageView view, Class destino) {
        Animation scaleDown = AnimationUtils.loadAnimation(this, R.anim.scale_down);
        view.setAnimation(scaleDown);
        view.startAnimation(scaleDown);
        scaleDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                Intent i = new Intent(getApplicationContext(), destino);
                i.putExtra("FR", true);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Pantalla tutorial
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_francia), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_francia), Boolean.TRUE);
            edit.commit();
            fragPantallaTutorial popup = new fragPantallaTutorial(3);
            popup.show(getSupportFragmentManager(),"DialogFrag");
        }
    }

}