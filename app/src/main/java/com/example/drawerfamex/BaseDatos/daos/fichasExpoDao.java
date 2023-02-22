package com.example.drawerfamex.BaseDatos.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.drawerfamex.BaseDatos.entidades.fichaExpo;

import java.util.List;

@Dao
public interface fichasExpoDao {

    @Query("SELECT * FROM fichaExpo")
    LiveData<List<fichaExpo>> getAll();

    @Query("SELECT * FROM fichaExpo WHERE nBlock = :numeroBloque")
    LiveData<List<fichaExpo>> getFichas(int numeroBloque);

    @Query("SELECT * FROM fichaExpo WHERE nBlock = :numeroBloque AND francia = :francia")
    LiveData<List<fichaExpo>> getFichasF(int numeroBloque, boolean francia);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(fichaExpo... fichaExpo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(fichaExpo fichaExpo);

}
