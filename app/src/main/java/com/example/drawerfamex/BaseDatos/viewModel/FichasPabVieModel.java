package com.example.drawerfamex.BaseDatos.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.entidades.fichaPabellon;
import com.example.drawerfamex.BaseDatos.repository.FichasPabRepository;

import java.util.List;

public class FichasPabVieModel extends AndroidViewModel {
    FichasPabRepository repository;
    LiveData<List<fichaPabellon>> listaFichas;

    public interface OnFichaSaveListenerVM {
        void onFichaSavedVM();
    }

    public FichasPabVieModel(Application application) {
        super(application);
        repository = new FichasPabRepository(application);
    }

    public void getFichasPab(int numero){

        listaFichas = repository.getFichasPab(numero);
    }

    public void getFichasPabF(int numero, boolean francia){

        listaFichas = repository.getFichasPabF(numero, francia);
    }

    public LiveData<List<fichaPabellon>> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(LiveData<List<fichaPabellon>> listaFichas) {
        this.listaFichas = listaFichas;
    }

    public void insertFicha(fichaPabellon fichaPabellon, @NonNull final OnFichaSaveListenerVM listenerVM){
        repository.insertFicha(fichaPabellon, new FichasPabRepository.OnFichaSaveListener() {
            @Override
            public void onFichaSaved() {
                listenerVM.onFichaSavedVM();
            }
        });
    }

}
