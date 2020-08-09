package com.technologies.venom.room.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.technologies.venom.room.models.Matricula;

import java.util.List;

@Dao
public interface MatriculaDao {
    @Insert
    long inserir(Matricula matricula);

    @Update
    void atualizar(Matricula matricula);

    @Delete
    void excluir(Matricula matricula);

    @Query("SELECT * FROM matriculas")
    List<Matricula> listar();
}
