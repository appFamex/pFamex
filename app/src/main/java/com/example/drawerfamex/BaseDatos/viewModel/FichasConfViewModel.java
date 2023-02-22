package com.example.drawerfamex.BaseDatos.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.entidades.fichaConferencia;
import com.example.drawerfamex.BaseDatos.repository.FichasConfRep;

import java.util.List;

public class FichasConfViewModel extends AndroidViewModel {
    FichasConfRep repository;
    LiveData<List<fichaConferencia>> listaFichas;

    public interface OnFichaSaveListenerVM {
        void onFichaSavedVM();
    }

    public FichasConfViewModel(Application application) {
        super(application);
        repository = new FichasConfRep(application);
    }

    public void getFichasConf(int bloque, int dia){
        listaFichas = repository.getFichas(bloque, dia);
    }

    public void getFichasConfIT(int dia, boolean it){
        listaFichas = repository.getFichasIT(dia, it);
    }

    public void getFichasConfF(int bloque,int dia, boolean francia){
        listaFichas = repository.getFichasF(bloque, dia, francia);
    }

    public LiveData<List<fichaConferencia>> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(LiveData<List<fichaConferencia>> listaFichas) {
        this.listaFichas = listaFichas;
    }

    public void insertFicha(fichaConferencia fichaConferencia, @NonNull final FichasConfViewModel.OnFichaSaveListenerVM listener){
        repository.insertFicha(fichaConferencia, new FichasConfRep.OnFichaSaveListener() {
            @Override
            public void onFichaSaved() {
                listener.onFichaSavedVM();
            }
        });
    }
}
