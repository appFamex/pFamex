package com.example.drawerfamex.popups.tutorial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import com.example.drawerfamex.R;

public class fragPantallaTutorial extends DialogFragment {

    //VARIABLES POP UP TUTORIALES
    private ImageView btnClose, imgTut;
    private int val;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //VISTA INFLADA
        View view = inflater.inflate(R.layout.fragment_frag_pantalla_tutorial, container, false);

        //ASIGNACION DE IDS
        btnClose =  (ImageView) view.findViewById(R.id.bntClosePopPtuto);
        imgTut = view.findViewById(R.id.imgTuto);

        //LISTENER BOTON DE CIERRE
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        //ASIGNACION DE IMAGEN
        setImg(val);

        return view;
    }

    public void setImg(int tipo){

        switch (tipo){

            case 1: {
                imgTut.setImageResource(R.drawable.p_tutorial_inicio);
                break;
            }

            case 2: {
                imgTut.setImageResource(R.drawable.p_tutorial_evento);
                break;

            }

            case 3:{
                imgTut.setImageResource(R.drawable.p_tutorial_mfrancia);
                break;
            }

            case 4: {
                imgTut.setImageResource(R.drawable.p_tutorial_conferencia);
                break;

            }

            case 5: {
                imgTut.setImageResource(R.drawable.p_tutorial_chalets);
                break;
            }

            case 6: {
                imgTut.setImageResource(R.drawable.p_tutorial_pabellones);
                break;

            }

            case 7: {
                imgTut.setImageResource(R.drawable.p_tutorial_expoest);
                break;

            }

            case 8: {
                imgTut.setImageResource(R.drawable.p_tutorial_hoteles);
                break;

            }

            case 9: {
                imgTut.setImageResource(R.drawable.p_tutorial_rutas);
                break;
            }

            case 10: {
                imgTut.setImageResource(R.drawable.p_tutorial_itinerario);
                break;
            }

            case 11: {
                imgTut.setImageResource(R.drawable.p_tutorial_rep);
                break;
            }

            case 12: {
                imgTut.setImageResource(R.drawable.p_tutorial_chaletsfr);
                break;
            }

            case 13: {
                imgTut.setImageResource(R.drawable.p_tutorial_expoestfr);
                break;

            }

            case 14: {
                imgTut.setImageResource(R.drawable.p_tutorial_perfil);
                break;
            }

            case 15: {
                imgTut.setImageResource(R.drawable.p_tutorial_espeaereo);
                break;
            }

            case 16: {
                imgTut.setImageResource(R.drawable.p_tutorial_espeaereopop);
                break;
            }

            case 17: {
                imgTut.setImageResource(R.drawable.p_tutorial_mapas);
                break;
            }

            case 18: {
                imgTut.setImageResource(R.drawable.p_tutorial_prefamex);
                break;
            }
            default:
                System.out.println("No hay imagen");
                break;
        }


    }


    @Override
    public void onStart() {
        super.onStart();
        setImg(val);

        //PONE EL FONDO DEL FRAGMENT TRANSPARENTE
        getDialog().getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.transparencia);
    }

    public fragPantallaTutorial(int valor){
        setVal(valor);
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }


}
