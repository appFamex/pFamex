package com.example.drawerfamex.BaseDatos.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.drawerfamex.BaseDatos.entidades.fichaConferencia;

import java.util.List;

@Dao
public interface fichasConfDao {

    @Query("SELECT * FROM fichaConferencia")
    LiveData<List<fichaConferencia>> getAll();

    @Query("SELECT * FROM fichaConferencia WHERE nBlock = :numeroBloque AND nDia = :dia")
    LiveData<List<fichaConferencia>> getFichas(int numeroBloque, int dia);

    @Query("SELECT * FROM fichaConferencia WHERE it = :it AND nDia = :dia")
    LiveData<List<fichaConferencia>> getFichasIT(int dia, boolean it);

    @Query("SELECT * FROM fichaConferencia WHERE nBlock = :numeroBloque AND nDia = :dia AND francia = :francia")
    LiveData<List<fichaConferencia>> getFichasF(int numeroBloque,int dia, boolean francia);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(fichaConferencia... fichaConferencia);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOne(fichaConferencia fichaConferencia);

}
