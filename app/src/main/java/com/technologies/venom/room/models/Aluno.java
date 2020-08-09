package com.technologies.venom.room.models;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "alunos")
public class Aluno {

    @PrimaryKey(autoGenerate = true)
    public long alunoId;
    private String primeiroNome;
    private String ultimoNome;
    private String cpf;

    @Embedded(prefix = "end")
    private Endereco endereco;

    //construtor vazio

    @Ignore
    public Aluno() {

    }

    //construtor com parâmetros
    public Aluno(String primeiroNome, String ultimoNome, String cpf, Endereco endereco) {
        this.primeiroNome = primeiroNome;
        this.ultimoNome = ultimoNome;
        this.cpf = cpf;
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

    public Endereco getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
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

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
