package com.technologies.venom.room.persistence;

import androidx.room.Dao;
import androidx.room.Query;

import com.technologies.venom.room.models.Matricula;

import java.util.List;

@Dao
public abstract class MatriculaDao implements GenericoDao<Matricula> {

    @Query("SELECT * FROM matriculas")
    public abstract Matricula listar();

    @Query("SELECT * FROM matriculas WHERE disciplinaId = :disciplinaId AND alunoId = :alunoId")
    public abstract Matricula listarPorId(long alunoId, long disciplinaId);

    @Query("SELECT * FROM matriculas where alunoId = :alunoId")
    public abstract List<Matricula> listarPorAlunoId(long alunoId);
}
