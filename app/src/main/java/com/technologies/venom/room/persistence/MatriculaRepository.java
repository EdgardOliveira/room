package com.technologies.venom.room.persistence;

import android.content.Context;

import com.technologies.venom.room.asynctask.AtualizarMatriculaAsyncTask;
import com.technologies.venom.room.asynctask.CadastrarMatriculaAsyncTask;
import com.technologies.venom.room.asynctask.ExcluirMatriculaAsyncTask;
import com.technologies.venom.room.asynctask.ListarMatriculaAsyncTask;
import com.technologies.venom.room.models.Matricula;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MatriculaRepository {
    private UniversidadeDatabase universidadeDatabase;

    public MatriculaRepository(Context context){
        universidadeDatabase = UniversidadeDatabase.getInstance(context);
    }

    public long cadastrar(Matricula matricula){
        CadastrarMatriculaAsyncTask asyncTask = new CadastrarMatriculaAsyncTask(universidadeDatabase.getMatriculaDao());
        Long id = null;
        try {
            id = asyncTask.execute(matricula).get().longValue();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void atualizar(Matricula matricula){
        new AtualizarMatriculaAsyncTask(universidadeDatabase.getMatriculaDao()).execute(matricula);
    }

    public void excluir(Matricula matricula){
        new ExcluirMatriculaAsyncTask(universidadeDatabase.getMatriculaDao()).execute(matricula);
    }

    public List<Matricula> listar() {
        return (List<Matricula>) new ListarMatriculaAsyncTask(universidadeDatabase.getMatriculaDao()).execute();
    }

    public Matricula listarPorId(Long alunoId, Long disciplinaId) {
        return universidadeDatabase.getMatriculaDao().listarPorId(alunoId, disciplinaId);
    }

    public List<Matricula> listarPorAlunoId(Long id) {
        return universidadeDatabase.getMatriculaDao().listarPorAlunoId(id);
    }
}
