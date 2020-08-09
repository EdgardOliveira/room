package com.technologies.venom.room.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.models.AlunoComMatricula;

import java.util.List;

@Dao
public interface AlunoDao {
    @Insert
    long inserir(Aluno aluno);

    @Update
    void atualizar(Aluno aluno);

    @Delete
    void excluir(Aluno aluno);

    @Query("SELECT * FROM alunos")
    List<Aluno> listar();

    @Query("SELECT * FROM alunos WHERE alunoId = :id")
    List<Aluno> listarPorId(long id);

    @Transaction
    @Query("SELECT * FROM alunos")
    List<AlunoComMatricula> listarComMatricula();
}
