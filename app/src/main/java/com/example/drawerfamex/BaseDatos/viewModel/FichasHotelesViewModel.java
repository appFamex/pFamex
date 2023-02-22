package com.example.drawerfamex.BaseDatos.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.entidades.fichaHoteles;
import com.example.drawerfamex.BaseDatos.repository.FichasHotelesRep;

import java.util.List;

public class FichasHotelesViewModel extends AndroidViewModel {

    FichasHotelesRep repository;
    LiveData<List<fichaHoteles>> listaFichas;

    public interface OnFichaSaveListenerVM {
        void onFichaSavedVM();
    }

    public FichasHotelesViewModel(Application application) {
        super(application);
        repository = new FichasHotelesRep(application);
    }

    public void getFichas(){

        listaFichas = repository.getFichas();
    }

    public LiveData<List<fichaHoteles>> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(LiveData<List<fichaHoteles>> listaFichas) {
        this.listaFichas = listaFichas;
    }

    public void insertFicha(fichaHoteles fichaHoteles, @NonNull final OnFichaSaveListenerVM listenerVM){
        repository.insertFicha(fichaHoteles, new FichasHotelesRep.OnFichaSaveListener() {
            @Override
            public void onFichaSaved() {
                listenerVM.onFichaSavedVM();
            }
        });
    }
}
