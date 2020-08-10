package com.technologies.venom.room.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "disciplinas", foreignKeys = {@ForeignKey(entity = Professor.class, parentColumns = "professorId", childColumns = "professorId")})
public class Disciplina {

    @PrimaryKey(autoGenerate = true)
    public long disciplinaId;
    private String nome;
    private long professorId;

    //construtor vazio
    @Ignore
    public Disciplina() {

    }

    //construtor com parâmetros
    public Disciplina(String nome, long professorId) {
        this.nome = nome;
        this.professorId = professorId;
    }

    //getters
    public long getDisciplinaId() {
        return disciplinaId;
    }

    public String getNome() {
        return nome;
    }

    public long getProfessorId() {
        return professorId;
    }

    //setters
    public void setDisciplinaId(long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }
}
