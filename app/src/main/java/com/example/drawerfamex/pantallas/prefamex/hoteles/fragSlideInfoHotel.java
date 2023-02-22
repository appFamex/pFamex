package com.example.drawerfamex.pantallas.prefamex.hoteles;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.drawerfamex.R;

public class fragSlideInfoHotel extends Fragment {

    String linkShera, imagen;

    public fragSlideInfoHotel(String imagen,String link ) {
        this.imagen = imagen;
        linkShera = link;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.aao_15_hoteles_imghotel, container, false);
        final ImageView btnReserva = view.findViewById(R.id.imgSlideHotel);

        try {
            Glide.with(getContext())
                    .load(imagen)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(btnReserva);
        }catch (Exception e){
            e.getMessage();
        }

        btnReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri Link = Uri.parse(linkShera);
                Intent i = new Intent(Intent.ACTION_VIEW,Link);
                startActivity(i);
            }
        });

        return view;
    }
}
