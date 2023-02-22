package com.example.drawerfamex.BaseDatos.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.drawerfamex.BaseDatos.entidades.fichaAnuncio;

import java.util.List;

@Dao
public interface fichasAnunciosDao {

    @Query("SELECT * FROM fichaAnuncio")
    LiveData<List<fichaAnuncio>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(fichaAnuncio... fichaAnuncioss);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(fichaAnuncio fichaAnuncio);

}
