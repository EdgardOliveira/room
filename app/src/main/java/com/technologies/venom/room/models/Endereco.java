package com.technologies.venom.room.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "enderecos")
public class Endereco implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long enderecoId;
    @SerializedName("cep")
    @Expose
    private String cep;
    @Expose
    @SerializedName("logradouro")
    private String logradouro;
    @Expose
    @SerializedName("numero")
    private String numero;
    @Expose
    @SerializedName("complemento")
    private String complemento;
    @Expose
    @SerializedName("bairro")
    private String bairro;
    @Expose
    @SerializedName("localidade")
    private String cidade;
    @Expose
    @SerializedName("uf")
    private String uf;

    //construtor vazio
    @Ignore
    public Endereco() {

    }

    //construtor com parâmetros
    public Endereco(String cep, String logradouro, String numero, String complemento, String bairro, String cidade, String uf) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    //getters
    public long getEnderecoId() {
        return enderecoId;
    }

    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }


    //setters
    public void setEnderecoId(long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}
