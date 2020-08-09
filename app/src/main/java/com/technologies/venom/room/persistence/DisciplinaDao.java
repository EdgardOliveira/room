package com.technologies.venom.room.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.models.Disciplina;

import java.util.List;

@Dao
public interface DisciplinaDao {
    @Insert
    long inserir(Disciplina disciplina);

    @Update
    void atualizar(Disciplina disciplina);

    @Delete
    void excluir(Disciplina disciplina);

    @Query("SELECT * FROM disciplinas")
    List<Disciplina> listar();

    @Query("SELECT * FROM disciplinas WHERE disciplinaId = :id")
    Disciplina listarPorId(long id);
}
