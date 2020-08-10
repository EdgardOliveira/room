package com.technologies.venom.room.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface GenericoDao<T> {

    /**
     * Função responsável por inserir um objeto na base de dados
     *
     * @param obj o objeto que será inserido
     */
    @Insert
    long inserir(T obj);

    /**
     * Função responsável por inserir um array de objetos na base de dados
     *
     * @param obj o objeto a ser inserido na base de dados
     */
    @Insert
    void inserir(T... obj);

    /**
     * Função responsável por atualizar um objeto na base de dados
     *
     * @param obj o objeto a ser atualizado
     */
    @Update
    void atualizar(T obj);

    /**
     * Função responsável por atualizar um array de objetos na base de dados
     *
     * @param obj o objeto a ser atualizado
     */
    @Update
    void atualizar(T... obj);

    /**
     * Função reponsável por excluir um objeto da base de dados
     *
     * @param obj objeto a ser ser excluído da base de dados
     */
    @Delete
    void excluir(T obj);
}
