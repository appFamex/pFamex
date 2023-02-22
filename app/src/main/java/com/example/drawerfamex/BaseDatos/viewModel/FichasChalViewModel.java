package com.example.drawerfamex.BaseDatos.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.entidades.fichaChalets;
import com.example.drawerfamex.BaseDatos.repository.FichasChalRep;

import java.util.List;

public class FichasChalViewModel extends AndroidViewModel {

    FichasChalRep repository;
    LiveData<List<fichaChalets>> listaFichas;

    public interface OnFichaSaveListenerVM {
        void onFichaSavedVM();
    }

    public FichasChalViewModel(Application application) {
        super(application);
        repository = new FichasChalRep(application);
    }

    public void getFichasChal(int bloque){

        listaFichas = repository.getFichasChal(bloque);
    }

    public void getFichasChalF(int bloque, boolean francia){

        listaFichas = repository.getFichasChalF(bloque, francia);
    }

    public LiveData<List<fichaChalets>> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(LiveData<List<fichaChalets>> listaFichas) {
        this.listaFichas = listaFichas;
    }

    public void insertFicha(fichaChalets fichaChalets, @NonNull final FichasChalViewModel.OnFichaSaveListenerVM listener){
        repository.insertFicha(fichaChalets, new FichasChalRep.OnFichaSaveListener() {
            @Override
            public void onFichaSaved() {
                listener.onFichaSavedVM();
            }
        });
    }

}
