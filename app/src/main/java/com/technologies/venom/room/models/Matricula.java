package com.technologies.venom.room.models;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;

@Entity(tableName = "matriculas",
        primaryKeys = {"alunoId", "disciplinaId"}
)
public class Matricula {
    public long alunoId;
    public long disciplinaId;

    //Construtor vazio
    @Ignore
    public Matricula() {

    }

    //construtor com parâmetros
    public Matricula(long alunoId, long disciplinaId) {
        this.alunoId = alunoId;
        this.disciplinaId = disciplinaId;
    }

    //getters
    public long getAlunoId() {
        return alunoId;
    }

    public long getDisciplinaId() {
        return disciplinaId;
    }

    //setters
    public void setAlunoId(long alunoId) {
        this.alunoId = alunoId;
    }

    public void setDisciplinaId(long disciplinaId) {
        this.disciplinaId = disciplinaId;
    }
}
