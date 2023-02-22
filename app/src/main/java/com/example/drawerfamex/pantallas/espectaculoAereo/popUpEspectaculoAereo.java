package com.example.drawerfamex.pantallas.espectaculoAereo;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.drawerfamex.BaseDatos.daos.fichasPabDao;
import com.example.drawerfamex.BaseDatos.entidades.fichaPabellon;
import com.example.drawerfamex.R;
import com.example.drawerfamex.popups.tutorial.fragPantallaTutorial;

import java.io.File;
import java.io.FileOutputStream;

public class popUpEspectaculoAereo extends DialogFragment {

    //FALTA MENSAJE CUANDO YA SE DESCARGARON LOS FONDOS

    private ImageView btnClose, btnPOP, btnDownW, wall[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.aal_12_popup, container, false);
        wall = new ImageView[5];
        wall[0] = (ImageView) view.findViewById(R.id.imgWall1);
        wall[1] = (ImageView) view.findViewById(R.id.imgWall2);
        wall[2] = (ImageView) view.findViewById(R.id.imgWall3);
        wall[3] = (ImageView) view.findViewById(R.id.imgWall4);
        wall[4] = (ImageView) view.findViewById(R.id.imgWall5);
        btnClose =  (ImageView) view.findViewById(R.id.btnClosePopEva);
        btnPOP = (ImageView) view.findViewById(R.id.btnPopEv);
        btnDownW = (ImageView) view.findViewById(R.id.bntDownloadW);
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // btnPOP.setVisibility(View.VISIBLE);
                dismiss();
            }
        });

        btnDownW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // btnPOP.setVisibility(View.VISIBLE);
                btnAnimDestino(btnDownW);
                saveToGallery();


            }
        });
        return view;
    }

    public void onResume() {
        super.onResume();
        //Pantalla tutorial

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.p_espectaculoaereopop), false);

        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.p_espectaculoaereopop), Boolean.TRUE);
            edit.commit();
            fragPantallaTutorial popup = new fragPantallaTutorial(16);
            popup.show(getChildFragmentManager(),"DialogFrag");
        }
    }

    @Override
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        final Activity activity = getActivity();
        if (activity instanceof DialogInterface.OnDismissListener) {
            ((DialogInterface.OnDismissListener) activity).onDismiss(dialog);
        }
    }

    public void btnAnimDestino(ImageView view) {
        Animation scaleDown = AnimationUtils.loadAnimation(getContext(), R.anim.scale_down);
        view.setAnimation(scaleDown);
        view.startAnimation(scaleDown);
        scaleDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}
            @Override
            public void onAnimationRepeat(Animation animation) {}
            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(getContext(), "Se han descargado los fondos de Pantalla en tu galeria", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparencia);
    }

    private void saveToGallery(){
        new popUpEspectaculoAereo.InsertAsyncTask(wall).execute();

    }

    private static class InsertAsyncTask extends AsyncTask<Void, Void ,Void> {
        private ImageView wall[];

        InsertAsyncTask(ImageView imagenes[]){
            wall = imagenes;
        }
        @Override
        protected Void doInBackground(Void... Voids) {
            for(int i = 0; i < 5;i++){
                BitmapDrawable bitmapDrawable = (BitmapDrawable) wall[i].getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();

                FileOutputStream outputStream = null;
                String filename = String.format("%d.png",System.currentTimeMillis());

                File dir = new File("/storage/emulated/0/Pictures");


                File outFile = new File(dir,filename);
                try{
                    outputStream = new FileOutputStream(outFile);
                }catch (Exception e){
                    e.printStackTrace();
                }
                bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);
                try{
                    outputStream.flush();
                }catch (Exception e){
                    e.printStackTrace();
                }
                try{
                    outputStream.close();
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            return null;
        }
    }

}