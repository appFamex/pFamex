package com.example.drawerfamex.BaseDatos.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.entidades.fichaAnuncio;
import com.example.drawerfamex.BaseDatos.repository.FichasAnuncioRep;

import java.util.List;

public class FichasAnuncioVM extends AndroidViewModel {

    FichasAnuncioRep repository;
    LiveData<List<fichaAnuncio>> listaFichas;

    public interface OnFichaSaveListenerVM {
        void onFichaSavedVM();
    }

    public FichasAnuncioVM(Application application) {
        super(application);
        repository = new FichasAnuncioRep(application);
    }

    public void getFichas(){

        listaFichas = repository.getFichas();
    }

    public LiveData<List<fichaAnuncio>> getListaFichas() {
        return listaFichas;
    }

    public void setListaFichas(LiveData<List<fichaAnuncio>> listaFichas) {
        this.listaFichas = listaFichas;
    }

    public void insertFicha(fichaAnuncio fichaAnuncio,  @NonNull final FichasAnuncioVM.OnFichaSaveListenerVM listener){
        repository.insertFicha(fichaAnuncio, new FichasAnuncioRep.OnFichaSaveListener() {
            @Override
            public void onFichaSaved() {
                listener.onFichaSavedVM();
            }
        });
    }
}
