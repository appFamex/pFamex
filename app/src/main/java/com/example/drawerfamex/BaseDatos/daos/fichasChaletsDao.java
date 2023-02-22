package com.example.drawerfamex.BaseDatos.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.drawerfamex.BaseDatos.entidades.fichaChalets;

import java.util.List;

@Dao
public interface fichasChaletsDao {
    @Query("SELECT * FROM fichaChalets")
    LiveData<List<fichaChalets>> getAll();

    @Query("SELECT * FROM fichaChalets WHERE nBlock = :numeroBloque")
    LiveData<List<fichaChalets>> getFichas(int numeroBloque);

    @Query("SELECT * FROM fichaChalets WHERE nBlock = :numeroBloque AND francia = :francia")
    LiveData<List<fichaChalets>> getFichasF(int numeroBloque, boolean francia);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(fichaChalets... fichaChalets);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(fichaChalets fichaChalets);
}
