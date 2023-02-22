package com.example.drawerfamex.BaseDatos.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.daos.fichasExpoDao;
import com.example.drawerfamex.BaseDatos.db.AppDatabase;
import com.example.drawerfamex.BaseDatos.entidades.fichaExpo;
import com.example.drawerfamex.adapters.BasicImageDownloader;

import java.util.List;

public class FichasExpoRep {

    fichasExpoDao fichasExpoDao;

    public interface OnFichaSaveListener {
        void onFichaSaved();
    }

    public FichasExpoRep(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        fichasExpoDao = db.fichasExpoDao();
    }

    public LiveData<List<fichaExpo>> getFichas(int numero){
        return fichasExpoDao.getFichas(numero);
    }

    public LiveData<List<fichaExpo>> getFichasF(int numero, boolean francia){
        return fichasExpoDao.getFichasF(numero, francia);
    }

    public void insertFicha(fichaExpo fichaExpo, @NonNull final OnFichaSaveListener listener){

        new FichasExpoRep.InsertAsyncTask(fichasExpoDao, listener).execute(fichaExpo);

    }

    private static class InsertAsyncTask extends AsyncTask<fichaExpo, Void ,Void> {
        private fichasExpoDao taskDao;
        private OnFichaSaveListener evento;

        InsertAsyncTask(fichasExpoDao fichasExpoDao, OnFichaSaveListener listener){
            BasicImageDownloader.FICHAS++;
            taskDao = fichasExpoDao;
            evento = listener;
        }

        @Override
        protected Void doInBackground(fichaExpo... fichaExpos) {

            taskDao.insertAll(fichaExpos[0]);

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
