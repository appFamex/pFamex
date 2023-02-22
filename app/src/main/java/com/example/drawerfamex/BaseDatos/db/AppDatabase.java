package com.example.drawerfamex.BaseDatos.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.drawerfamex.BaseDatos.converters.Converters;
import com.example.drawerfamex.BaseDatos.daos.fichasAnunciosDao;
import com.example.drawerfamex.BaseDatos.daos.fichasHotelesDao;
import com.example.drawerfamex.BaseDatos.daos.fichasInicioDao;
import com.example.drawerfamex.BaseDatos.daos.fichasChaletsDao;
import com.example.drawerfamex.BaseDatos.daos.fichasConfDao;
import com.example.drawerfamex.BaseDatos.daos.fichasExpoDao;
import com.example.drawerfamex.BaseDatos.daos.fichasPabDao;
import com.example.drawerfamex.BaseDatos.daos.pantallasDao;
import com.example.drawerfamex.BaseDatos.entidades.Pantalla;
import com.example.drawerfamex.BaseDatos.entidades.fichaAnuncio;
import com.example.drawerfamex.BaseDatos.entidades.fichaChalets;
import com.example.drawerfamex.BaseDatos.entidades.fichaConferencia;
import com.example.drawerfamex.BaseDatos.entidades.fichaExpo;
import com.example.drawerfamex.BaseDatos.entidades.fichaHoteles;
import com.example.drawerfamex.BaseDatos.entidades.fichaInicio;
import com.example.drawerfamex.BaseDatos.entidades.fichaPabellon;

@Database(entities = {Pantalla.class, fichaPabellon.class, fichaChalets.class, fichaExpo.class, fichaConferencia.class, fichaHoteles.class, fichaInicio.class, fichaAnuncio.class}, version = 6)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract pantallasDao pantallasDao();

    public abstract fichasPabDao fichasPabDao();

    public abstract fichasChaletsDao fichasChaletsDao();

    public abstract fichasExpoDao fichasExpoDao();

    public abstract fichasConfDao fichasConfDao();

    public abstract fichasHotelesDao fichasHotelesDao();

    public abstract fichasInicioDao fichasInicioDao();

    public abstract fichasAnunciosDao fichasAnunciosDao();

    public static AppDatabase INSTANCE;

    public static AppDatabase getDataBase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class, "pantallas-database").fallbackToDestructiveMigration().build();
                }
            }
        }
        return INSTANCE;
    }

}
