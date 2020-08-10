package com.technologies.venom.room.persistence;

import androidx.room.Dao;
import androidx.room.Query;

import com.technologies.venom.room.models.Professor;

import java.util.List;

@Dao
public abstract class ProfessorDao implements GenericoDao<Professor> {

    @Query("SELECT * FROM professores")
    public abstract List<Professor> listar();

    @Query("SELECT * FROM professores WHERE professorId = :id")
    public abstract Professor listarPorId(long id);
}
