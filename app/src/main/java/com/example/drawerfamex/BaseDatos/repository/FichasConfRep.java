package com.example.drawerfamex.BaseDatos.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.daos.fichasConfDao;
import com.example.drawerfamex.BaseDatos.db.AppDatabase;
import com.example.drawerfamex.BaseDatos.entidades.fichaConferencia;
import com.example.drawerfamex.adapters.BasicImageDownloader;

import java.util.List;

public class FichasConfRep {

    fichasConfDao fichasConfDao;
    public interface OnFichaSaveListener {
        void onFichaSaved();
    }

    public FichasConfRep(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        fichasConfDao = db.fichasConfDao();
    }

    public LiveData<List<fichaConferencia>> getFichas(int numeroBloque, int dia){
        return fichasConfDao.getFichas(numeroBloque, dia);
    }

    public LiveData<List<fichaConferencia>> getFichasIT(int dia,boolean it){
        return fichasConfDao.getFichasIT(dia,it);
    }

    public LiveData<List<fichaConferencia>> getFichasF(int numeroBloque,int dia, boolean francia){
        return fichasConfDao.getFichasF(numeroBloque,dia, francia);
    }

    public void insertFicha(fichaConferencia fichaConferencia, @NonNull final OnFichaSaveListener listener){

        new FichasConfRep.InsertAsyncTask(fichasConfDao, listener).execute(fichaConferencia);

    }

    private static class InsertAsyncTask extends AsyncTask<fichaConferencia, Void ,Void> {
        private fichasConfDao taskDao;

        private OnFichaSaveListener evento;

        InsertAsyncTask(fichasConfDao fichasConfDao, OnFichaSaveListener listener){
            BasicImageDownloader.FICHAS++;
            taskDao = fichasConfDao;
            evento = listener;
        }

        @Override
        protected Void doInBackground(fichaConferencia... fichaConferencias) {

            taskDao.insertAll(fichaConferencias[0]);

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
