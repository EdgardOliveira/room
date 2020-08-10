package com.technologies.venom.room.persistence;

import androidx.room.Dao;
import androidx.room.Query;

import com.technologies.venom.room.models.Disciplina;

import java.util.List;

@Dao
public abstract class DisciplinaDao implements GenericoDao<Disciplina> {

    @Query("SELECT * FROM disciplinas")
    public abstract List<Disciplina> listar();

    @Query("SELECT * FROM disciplinas WHERE disciplinaId = :id")
    public abstract Disciplina listarPorId(long id);
}
