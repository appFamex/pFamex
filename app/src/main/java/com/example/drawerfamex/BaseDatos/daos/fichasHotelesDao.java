package com.example.drawerfamex.BaseDatos.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.drawerfamex.BaseDatos.entidades.fichaHoteles;

import java.util.List;

@Dao
public interface fichasHotelesDao {

    @Query("SELECT * FROM fichaHoteles")
    LiveData<List<fichaHoteles>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(fichaHoteles... fichaHoteless);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(fichaHoteles fichaHoteles);

}
