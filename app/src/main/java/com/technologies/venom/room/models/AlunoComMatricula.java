package com.technologies.venom.room.models;


import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class AlunoComMatricula {
    @Embedded private Aluno aluno;                      //Entidade pai
    @Relation(
            parentColumn = "alunoId",                   //nome da coluna de chave primária da entidade pai
            entityColumn = "disciplinaId",              //nome da coluna da entidade filha que faz referência à chave primária da entidade pai
            associateBy = @Junction(Matricula.class)
    )
    public List<Matricula> matriculasList;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Matricula> getMatriculasList() {
        return matriculasList;
    }

    public void setMatriculasList(List<Matricula> matriculasList) {
        this.matriculasList = matriculasList;
    }
}
