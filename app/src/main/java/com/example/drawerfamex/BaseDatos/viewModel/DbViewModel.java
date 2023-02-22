package com.example.drawerfamex.BaseDatos.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.entidades.Pantalla;
import com.example.drawerfamex.BaseDatos.repository.DbRepository;

import java.util.List;

public class DbViewModel extends AndroidViewModel {

    DbRepository repository;
    LiveData<List<Pantalla>> listaPantalla;
    LiveData<Pantalla> pantalla;


    public DbViewModel(Application application) {
        super(application);
        repository = new DbRepository(application);
    }
    public void initViewModel(String nombrePantalla){
        pantalla = repository.getPantalla(nombrePantalla);
    }

    public LiveData<List<Pantalla>> getListaPantalla(){
        return listaPantalla;
    }

    public LiveData<Pantalla> getPantallanombre(){
        return pantalla;
    }

    public void insertPantalla(Pantalla pantalla){

        repository.insertPantalla(pantalla);

    }

}
