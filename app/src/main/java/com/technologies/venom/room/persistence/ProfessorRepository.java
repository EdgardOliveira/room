package com.technologies.venom.room.persistence;

import android.content.Context;

import com.technologies.venom.room.asynctask.AtualizarProfessorAsyncTask;
import com.technologies.venom.room.asynctask.CadastrarProfessorAsyncTask;
import com.technologies.venom.room.asynctask.ExcluirProfessorAsyncTask;
import com.technologies.venom.room.asynctask.ListarProfessorAsyncTask;
import com.technologies.venom.room.models.Professor;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProfessorRepository {
    private UniversidadeDatabase universidadeDatabase;

    public ProfessorRepository(Context context){
        universidadeDatabase = UniversidadeDatabase.getInstance(context);
    }

    public long cadastrar(Professor professor){
        CadastrarProfessorAsyncTask asyncTask = new CadastrarProfessorAsyncTask(universidadeDatabase.getProfessorDao());
        Long id = null;
        try {
            id = asyncTask.execute(professor).get().longValue();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void atualizar(Professor professor){
        new AtualizarProfessorAsyncTask(universidadeDatabase.getProfessorDao()).execute(professor);
    }

    public void excluir(Professor professor){
        new ExcluirProfessorAsyncTask(universidadeDatabase.getProfessorDao()).execute(professor);
    }

    public List<Professor> listar() {
        return (List<Professor>) new ListarProfessorAsyncTask(universidadeDatabase.getProfessorDao()).execute();
    }

    public Professor listarPorId(Long professorId) {
        return universidadeDatabase.getProfessorDao().listarPorId(professorId);
    }
}
