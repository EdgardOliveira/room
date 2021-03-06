package com.technologies.venom.room.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


@Entity(tableName = "alunos")
public class Aluno implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public long alunoId;
    private String primeiroNome;
    private String ultimoNome;
    private String cpf;
    private boolean inativo;

    @Embedded(prefix = "end")
    private Endereco endereco;

    //construtor vazio

    @Ignore
    public Aluno() {

    }

    //construtor com parâmetros

    public Aluno(String primeiroNome, String ultimoNome, String cpf, boolean inativo, Endereco endereco) {
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.cpf = cpf;
        this.inativo = inativo;
        this.endereco = endereco;
    }

    //getters
    public long getAlunoId() {
        return alunoId;
    }

    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public String getUltimoNome() {
        return ultimoNome;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean isInativo() {
        return inativo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    //setters

    public void setAlunoId(long alunoId) {
        this.alunoId = alunoId;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setInativo(boolean inativo) {
        this.inativo = inativo;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
