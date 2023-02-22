package com.example.drawerfamex.BaseDatos.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.entidades.fichaInicio;
import com.example.drawerfamex.BaseDatos.repository.FichasInicioRep;

import java.util.List;

public class FichasInicioVM extends AndroidViewModel {

    FichasInicioRep repository;
    LiveData<List<fichaInicio>> listaFichas;

    public interface OnFichaSaveListenerVM {
        void onFichaSavedVM();
    }

    public FichasInicioVM(Application application) {
        super(application);
        repository = new FichasInicioRep(application);
    }

    public void getFichas(){

        listaFichas = repository.getFichas();
    }

    public LiveData<List<fichaInicio>> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(LiveData<List<fichaInicio>> listaFichas) {
        this.listaFichas = listaFichas;
    }

    public void insertFicha(fichaInicio fichaInicio, @NonNull final OnFichaSaveListenerVM listenerVM){
        repository.insertFicha(fichaInicio, new FichasInicioRep.OnFichaSaveListener() {
            @Override
            public void onFichaSaved() {
                listenerVM.onFichaSavedVM();
            }
        });
    }
}
