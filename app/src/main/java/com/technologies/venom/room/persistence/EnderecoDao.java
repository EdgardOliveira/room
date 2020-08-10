package com.technologies.venom.room.persistence;

import androidx.room.Dao;
import androidx.room.Query;

import com.technologies.venom.room.models.Endereco;

import java.util.List;

@Dao
public abstract class EnderecoDao implements GenericoDao<Endereco> {

    @Query("SELECT * FROM enderecos")
    public abstract List<Endereco> listar();

    @Query("SELECT * FROM enderecos WHERE enderecoId = :id")
    public abstract Endereco listarPorId(long id);
}
