package com.example.drawerfamex.pantallas.configuracion.sobreAplicacion;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.drawerfamex.R;

public class fragPopupDevs extends DialogFragment {

    private ImageView closeIc;

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aav_21_popup, container, false);

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        closeIc = (ImageView) view.findViewById(R.id.closeIc);
        closeIc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }

}