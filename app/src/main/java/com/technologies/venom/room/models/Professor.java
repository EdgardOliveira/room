package com.technologies.venom.room.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "professores")
public class Professor {

    @PrimaryKey(autoGenerate = true)
    private long professorId;
    private String primeiroNome;
    private String ultimoNome;
    private String matricula;
    @Embedded(prefix = "end")
    private Endereco endereco;

    //construtor vazio
    @Ignore

    public Professor() {
    }

    //construtor com parâmetros

    public Professor(String primeiroNome, String ultimoNome, String matricula, Endereco endereco) {
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.matricula = matricula;
        this.endereco = endereco;
    }

    //getters
    public long getProfessorId() {
        return professorId;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public String getMatricula() {
        return matricula;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    //setters

    public void setProfessorId(long professorId) {
        this.professorId = professorId;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
