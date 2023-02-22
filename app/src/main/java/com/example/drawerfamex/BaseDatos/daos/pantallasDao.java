package com.example.drawerfamex.BaseDatos.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.drawerfamex.BaseDatos.entidades.Pantalla;

import java.util.List;

@Dao
public interface pantallasDao {

    @Query("SELECT * FROM Pantalla")
    LiveData<List<Pantalla>> getAll();

    @Query("SELECT * FROM Pantalla WHERE id IN (:userIds)")
    List<Pantalla> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM Pantalla WHERE nombre LIKE :name LIMIT 1")
    LiveData<Pantalla> findByName(String name);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Pantalla... Pantallas);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(Pantalla pantalla);

    @Delete
    void delete(Pantalla pantalla);

    @Update
    void update(Pantalla pantalla);

}
