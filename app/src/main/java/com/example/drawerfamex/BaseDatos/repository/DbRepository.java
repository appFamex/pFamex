package com.example.drawerfamex.BaseDatos.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.daos.pantallasDao;
import com.example.drawerfamex.BaseDatos.db.AppDatabase;
import com.example.drawerfamex.BaseDatos.entidades.Pantalla;

import java.util.List;

public class DbRepository {

    pantallasDao pantallasDao;

    public DbRepository(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        pantallasDao = db.pantallasDao();
    }

    public LiveData<List<Pantalla>> getPantalla(){
        return pantallasDao.getAll();
    }

    public LiveData<Pantalla> getPantalla(String nombrePantalla){
        return pantallasDao.findByName(nombrePantalla);
    }

    public void insertPantalla(Pantalla pantalla){

        new InsertAsyncTask(pantallasDao).execute(pantalla);

    }

    private static class InsertAsyncTask extends AsyncTask<Pantalla, Void ,Void>{
        private pantallasDao taskDao;

        InsertAsyncTask(pantallasDao pantallasDao){

            taskDao = pantallasDao;
        }

        @Override
        protected Void doInBackground(Pantalla... pantallas) {

            taskDao.insertAll(pantallas[0]);

            return null;
        }
    }



}
