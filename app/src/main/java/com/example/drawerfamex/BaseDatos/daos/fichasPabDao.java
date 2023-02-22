package com.example.drawerfamex.BaseDatos.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.drawerfamex.BaseDatos.entidades.Pantalla;
import com.example.drawerfamex.BaseDatos.entidades.fichaPabellon;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface fichasPabDao {
    @Query("SELECT * FROM fichaPabellon")
    LiveData<List<fichaPabellon>> getAll();

    @Query("SELECT * FROM fichaPabellon WHERE nPab = :numeroPabellon")
    LiveData<List<fichaPabellon>> getFichas(int numeroPabellon);

    @Query("SELECT * FROM fichaPabellon WHERE nPab = :numeroPabellon AND francia = :francia")
    LiveData<List<fichaPabellon>> getFichasF(int numeroPabellon, boolean francia);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(fichaPabellon... fichaPabellons);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(fichaPabellon fichaPabellon);
}
