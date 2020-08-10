package com.technologies.venom.room.persistence;

import android.content.Context;

import com.technologies.venom.room.asynctask.AtualizarAlunoAsyncTask;
import com.technologies.venom.room.asynctask.CadastrarAlunoAsyncTask;
import com.technologies.venom.room.asynctask.ExcluirAlunoAsyncTask;
import com.technologies.venom.room.asynctask.ListarAlunoAsyncTask;
import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.models.AlunoComMatricula;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AlunoRepository {

    private UniversidadeDatabase universidadeDatabase;

    public AlunoRepository(Context context){
        universidadeDatabase = UniversidadeDatabase.getInstance(context);
    }

    public long cadastrar(Aluno aluno){
        CadastrarAlunoAsyncTask asyncTask = new CadastrarAlunoAsyncTask(universidadeDatabase.getAlunoDao());
        Long id = null;
        try {
            id = asyncTask.execute(aluno).get().longValue();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void atualizar(Aluno aluno){
        new AtualizarAlunoAsyncTask(universidadeDatabase.getAlunoDao()).execute(aluno);
    }

    public void excluir(Aluno aluno){
        new ExcluirAlunoAsyncTask(universidadeDatabase.getAlunoDao()).execute(aluno);
    }

    public List<Aluno> listar() {
        return universidadeDatabase.getAlunoDao().listar();//(List<Aluno>) new ListarAlunoAsyncTask(universidadeDatabase.getAlunoDao()).execute();
    }

    public List<AlunoComMatricula> listarComMatricula() {
        return universidadeDatabase.getAlunoDao().listarComMatricula();//(List<AlunoComMatricula>) universidadeDatabase.getAlunoDao().listarComMatricula();
    }

    public Aluno listarPorId(Long id) {
        return universidadeDatabase.getAlunoDao().listarPorId(id);
    }
}
