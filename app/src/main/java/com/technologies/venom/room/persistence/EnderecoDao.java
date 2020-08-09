package com.technologies.venom.room.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.technologies.venom.room.models.Disciplina;
import com.technologies.venom.room.models.Endereco;

import java.util.List;

@Dao
public interface EnderecoDao {

    @Insert
    long inserir(Endereco endereco);

    @Update
    void atualizar(Endereco endereco);

    @Delete
    void excluir(Endereco endereco);

    @Query("SELECT * FROM enderecos")
    List<Endereco> listar();

    @Query("SELECT * FROM enderecos WHERE enderecoId = :id")
    List<Endereco> listarPorId(long id);

}
