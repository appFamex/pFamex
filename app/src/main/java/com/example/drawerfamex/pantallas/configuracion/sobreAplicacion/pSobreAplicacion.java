package com.example.drawerfamex.pantallas.configuracion.sobreAplicacion;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.R;

public class pSobreAplicacion extends pMenu {

    private ImageView btnDevs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.aav_21_acerca_de);
        super.onCreate(savedInstanceState);

       // setMenu();
        btnDevs = findViewById(R.id.btnDesarrolladores);


        fragPopupDevs mFragment = new fragPopupDevs();
        btnDevs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment.show(getSupportFragmentManager(),"DialogFrag");
            }
        });

    }

}