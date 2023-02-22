package com.example.drawerfamex.BaseDatos.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.daos.fichasInicioDao;
import com.example.drawerfamex.BaseDatos.db.AppDatabase;
import com.example.drawerfamex.BaseDatos.entidades.fichaInicio;
import com.example.drawerfamex.adapters.BasicImageDownloader;

import java.util.List;

public class FichasInicioRep {

    fichasInicioDao fichasInicioDao;

    public interface OnFichaSaveListener {
        void onFichaSaved();
    }

    public FichasInicioRep(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        fichasInicioDao = db.fichasInicioDao();
    }

    public LiveData<List<fichaInicio>> getFichas(){
        return fichasInicioDao.getAll();
    }

    public void insertFicha(fichaInicio fichaInicio, @NonNull final FichasInicioRep.OnFichaSaveListener listener){

        new FichasInicioRep.InsertAsyncTask(fichasInicioDao, listener).execute(fichaInicio);

    }

    private static class InsertAsyncTask extends AsyncTask<fichaInicio, Void ,Void> {
        private fichasInicioDao taskDao;
        private OnFichaSaveListener evento;

        InsertAsyncTask(fichasInicioDao fichasInicioDao, OnFichaSaveListener listener){
            BasicImageDownloader.FICHAS++;
            taskDao = fichasInicioDao;
            evento = listener;

        }

        @Override
        protected Void doInBackground(fichaInicio... fichaInicios) {

            taskDao.insertAll(fichaInicios[0]);

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            BasicImageDownloader.FICHAS--;
            evento.onFichaSaved();
        }
    }

}
