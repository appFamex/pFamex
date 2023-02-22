package com.example.drawerfamex.BaseDatos.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.daos.fichasChaletsDao;
import com.example.drawerfamex.BaseDatos.db.AppDatabase;
import com.example.drawerfamex.BaseDatos.entidades.fichaChalets;
import com.example.drawerfamex.adapters.BasicImageDownloader;

import java.util.List;

public class FichasChalRep {

    fichasChaletsDao fichasChaletsDao;

    public interface OnFichaSaveListener {
        void onFichaSaved();
    }

    public FichasChalRep(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        fichasChaletsDao = db.fichasChaletsDao();
    }

    public LiveData<List<fichaChalets>> getFichasChal(int numero){
        return fichasChaletsDao.getFichas(numero);
    }

    public LiveData<List<fichaChalets>> getFichasChalF(int numero, boolean francia){
        return fichasChaletsDao.getFichasF(numero,francia);
    }

    public void insertFicha(fichaChalets fichaChalets, @NonNull final OnFichaSaveListener listener){

        new FichasChalRep.InsertAsyncTask(fichasChaletsDao, listener).execute(fichaChalets);

    }

    private static class InsertAsyncTask extends AsyncTask<fichaChalets, Void ,Void> {
        private fichasChaletsDao taskDao;
        private OnFichaSaveListener evento;

        InsertAsyncTask(fichasChaletsDao fichasChaletsDao, OnFichaSaveListener listener){
            BasicImageDownloader.FICHAS++;
            taskDao = fichasChaletsDao;
            evento = listener;
        }

        @Override
        protected Void doInBackground(fichaChalets... fichaChaletss) {

            taskDao.insertAll(fichaChaletss[0]);

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
