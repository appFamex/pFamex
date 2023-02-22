package com.example.drawerfamex.BaseDatos.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.entidades.fichaExpo;
import com.example.drawerfamex.BaseDatos.repository.FichasExpoRep;

import java.util.List;

public class FichasExpoViewModel extends AndroidViewModel {
    FichasExpoRep repository;
    LiveData<List<fichaExpo>> listaFichas;

    public interface OnFichaSaveListenerVM {
        void onFichaSavedVM();
    }

    public FichasExpoViewModel(Application application) {
        super(application);
        repository = new FichasExpoRep(application);
    }

    public void getFichasExpo(int bloque){

        listaFichas = repository.getFichas(bloque);
    }

    public void getFichasExpoF(int bloque, boolean francia){

        listaFichas = repository.getFichasF(bloque,francia);
    }

    public LiveData<List<fichaExpo>> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(LiveData<List<fichaExpo>> listaFichas) {
        this.listaFichas = listaFichas;
    }

    public void insertFicha(fichaExpo fichaExpo, @NonNull final FichasExpoViewModel.OnFichaSaveListenerVM listener){
        repository.insertFicha(fichaExpo, new FichasExpoRep.OnFichaSaveListener() {
            @Override
            public void onFichaSaved() {
                listener.onFichaSavedVM();
            }
        });
    }
}
