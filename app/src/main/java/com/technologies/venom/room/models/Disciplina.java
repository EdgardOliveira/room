package com.technologies.venom.room.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "disciplinas")
public class Disciplina {

    @PrimaryKey(autoGenerate = true)
    public long disciplinaId;
    private String nome;

    //construtor vazio
    @Ignore
    public Disciplina() {

    }

    //construtor com parâmetros
    public Disciplina(String nome) {
        this.nome = nome;
    }

    //getters
    public long getDisciplinaId() {
        return disciplinaId;
    }

    public String getNome() {
        return nome;
    }

    //setters
    public void setDisciplinaId(long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
