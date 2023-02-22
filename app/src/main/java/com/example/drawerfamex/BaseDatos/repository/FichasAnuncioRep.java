package com.example.drawerfamex.BaseDatos.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.daos.fichasAnunciosDao;
import com.example.drawerfamex.BaseDatos.db.AppDatabase;
import com.example.drawerfamex.BaseDatos.entidades.fichaAnuncio;
import com.example.drawerfamex.adapters.BasicImageDownloader;

import java.util.List;

public class FichasAnuncioRep {

    fichasAnunciosDao fichasAnunciosDao;

    public interface OnFichaSaveListener {
        void onFichaSaved();
    }


    public FichasAnuncioRep(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        fichasAnunciosDao = db.fichasAnunciosDao();
    }

    public LiveData<List<fichaAnuncio>> getFichas(){
        return fichasAnunciosDao.getAll();
    }

    public void insertFicha(fichaAnuncio fichaAnuncio, @NonNull final OnFichaSaveListener listener){

        new FichasAnuncioRep.InsertAsyncTask(fichasAnunciosDao, listener).execute(fichaAnuncio);

    }


    private static class InsertAsyncTask extends AsyncTask<fichaAnuncio, Void ,Void> {
        private fichasAnunciosDao taskDao;
        private OnFichaSaveListener evento;

        InsertAsyncTask(fichasAnunciosDao fichasAnunciosDao, OnFichaSaveListener listener){
            BasicImageDownloader.FICHAS++;
            taskDao = fichasAnunciosDao;
            evento = listener;
        }

        @Override
        protected Void doInBackground(fichaAnuncio... fichaAnuncios) {

            taskDao.insertAll(fichaAnuncios[0]);

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
