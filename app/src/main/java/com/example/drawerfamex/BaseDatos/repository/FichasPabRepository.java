package com.example.drawerfamex.BaseDatos.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.daos.fichasPabDao;
import com.example.drawerfamex.BaseDatos.daos.pantallasDao;
import com.example.drawerfamex.BaseDatos.db.AppDatabase;
import com.example.drawerfamex.BaseDatos.entidades.Pantalla;
import com.example.drawerfamex.BaseDatos.entidades.fichaPabellon;
import com.example.drawerfamex.adapters.BasicImageDownloader;

import java.util.List;

public class FichasPabRepository {

    fichasPabDao fichasPabDao;

    public interface OnFichaSaveListener {
        void onFichaSaved();
    }

    public FichasPabRepository(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        fichasPabDao = db.fichasPabDao();
    }

    public LiveData<List<fichaPabellon>> getFichasPab(int numero){
        return fichasPabDao.getFichas(numero);
    }

    public LiveData<List<fichaPabellon>> getFichasPabF(int numero, boolean francia){
        return fichasPabDao.getFichasF(numero, francia);
    }

    public void insertFicha(fichaPabellon fichaPabellon, @NonNull final FichasPabRepository.OnFichaSaveListener listener){

        new FichasPabRepository.InsertAsyncTask(fichasPabDao, listener).execute(fichaPabellon);

    }

    private static class InsertAsyncTask extends AsyncTask<fichaPabellon, Void ,Void> {
        private fichasPabDao taskDao;
        private OnFichaSaveListener evento;

        InsertAsyncTask(fichasPabDao fichasPabDao, OnFichaSaveListener listener){
            BasicImageDownloader.FICHAS++;
            taskDao = fichasPabDao;
            evento = listener;
        }

        @Override
        protected Void doInBackground(fichaPabellon... fichaPabellons) {

            taskDao.insertAll(fichaPabellons[0]);

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
