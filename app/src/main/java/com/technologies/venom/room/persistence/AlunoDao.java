package com.technologies.venom.room.persistence;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.models.AlunoComMatricula;

import java.util.List;

@Dao
public abstract class AlunoDao implements GenericoDao<Aluno> {

    @Query("SELECT * FROM alunos")
    public abstract List<Aluno> listar();

    @Query("SELECT * FROM alunos WHERE alunoId = :id")
    public abstract Aluno listarPorId(long id);

    @Transaction
    @Query("SELECT * FROM alunos")
    public abstract List<AlunoComMatricula> listarComMatricula();
}
