package com.example.drawerfamex.BaseDatos.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.drawerfamex.BaseDatos.daos.fichasHotelesDao;
import com.example.drawerfamex.BaseDatos.db.AppDatabase;
import com.example.drawerfamex.BaseDatos.entidades.fichaHoteles;
import com.example.drawerfamex.adapters.BasicImageDownloader;

import java.util.List;

public class FichasHotelesRep {

    fichasHotelesDao fichasHotelesDao;

    public interface OnFichaSaveListener {
        void onFichaSaved();
    }

    public FichasHotelesRep(Application application) {
        AppDatabase db = AppDatabase.getDataBase(application);
        fichasHotelesDao = db.fichasHotelesDao();
    }

    public LiveData<List<fichaHoteles>> getFichas(){
        return fichasHotelesDao.getAll();
    }

    public void insertFicha(fichaHoteles fichaHoteles, @NonNull final FichasHotelesRep.OnFichaSaveListener listener){

        new FichasHotelesRep.InsertAsyncTask(fichasHotelesDao, listener).execute(fichaHoteles);

    }

    private static class InsertAsyncTask extends AsyncTask<fichaHoteles, Void ,Void> {
        private fichasHotelesDao taskDao;
        private OnFichaSaveListener evento;

        InsertAsyncTask(fichasHotelesDao fichasHotelesDao, OnFichaSaveListener listener){
            BasicImageDownloader.FICHAS++;
            taskDao = fichasHotelesDao;
            evento = listener;
        }

        @Override
        protected Void doInBackground(fichaHoteles... fichaHoteless) {

            taskDao.insertAll(fichaHoteless[0]);

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
