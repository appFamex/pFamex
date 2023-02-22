package com.example.drawerfamex.BaseDatos.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.drawerfamex.BaseDatos.entidades.fichaInicio;

import java.util.List;

@Dao
public interface fichasInicioDao {

    @Query("SELECT * FROM fichaInicio")
    LiveData<List<fichaInicio>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(fichaInicio... fichaInicios);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(fichaInicio fichaInicio);

}
