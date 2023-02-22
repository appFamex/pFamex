package com.example.drawerfamex.pantallas.configuracion.principal;

import androidx.fragment.app.DialogFragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.drawerfamex.R;
import com.example.drawerfamex.adapters.pMenu;
import com.example.drawerfamex.pantallas.inicio.pInicio;

import java.util.Locale;

public class fragPopUpConfigIdioma extends DialogFragment {
    private static final String IDIOMA_POP = "POP"; // String pop up idioma
    private static final String IDIOMA_SPINNER = "SPINNER"; // String pop up idioma
    private int flag;
    private LinearLayout btnUKi,btnMXi,btnFRi;
    private ImageView closeIc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aar_18_popup, container, false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        btnUKi = (LinearLayout) view.findViewById(R.id.btnUKi);
        btnMXi = (LinearLayout) view.findViewById(R.id.btnMXi);
        btnFRi = (LinearLayout) view.findViewById(R.id.btnFRi);
        closeIc = (ImageView) view.findViewById(R.id.closeIc);

        btnUKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selUKi();
                dismiss();
            }
        });
        btnMXi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selMXi();
                dismiss();
            }
        });
        btnFRi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selFRi();
                dismiss();
            }
        });
        closeIc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;

    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        setFlag(prefs.getInt(IDIOMA_POP,0));
        setIdiom(getFlag());
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    private void setIdiom(int flag){
        if(flag == 2){
            selUKi();
        }else if(flag == 1){
            selFRi();
        }else{
            selMXi();

        }
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor edit = prefs.edit();
        if(prefs.getInt(IDIOMA_POP,0) != flag) {
            edit.putInt(IDIOMA_POP, flag);
            edit.apply();
        }
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }

    private void selUKi(){
        btnUKi.setBackgroundResource(R.drawable.rectanglebackground);
        btnFRi.setBackgroundResource(0);
        btnMXi.setBackgroundResource(0);
        setFlag(2);
    }
    private void selMXi(){
        btnUKi.setBackgroundResource(0);
        btnFRi.setBackgroundResource(0);
        btnMXi.setBackgroundResource(R.drawable.rectanglebackground);
        setFlag(0);
    }
    private void selFRi(){
        btnUKi.setBackgroundResource(0);
        btnFRi.setBackgroundResource(R.drawable.rectanglebackground);
        btnMXi.setBackgroundResource(0);
        setFlag(1);
    }


    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public LinearLayout getBtnUKi() {
        return btnUKi;
    }

    public void setBtnUKi(LinearLayout btnUKi) {
        this.btnUKi = btnUKi;
    }

    public LinearLayout getBtnMXi() {
        return btnMXi;
    }

    public void setBtnMXi(LinearLayout btnMXi) {
        this.btnMXi = btnMXi;
    }

    public LinearLayout getBtnFRi() {
        return btnFRi;
    }

    public void setBtnFRi(LinearLayout btnFRi) {
        this.btnFRi = btnFRi;
    }

    public ImageView getCloseIc() {
        return closeIc;
    }

    public void setCloseIc(ImageView closeIc) {
        this.closeIc = closeIc;
    }

}