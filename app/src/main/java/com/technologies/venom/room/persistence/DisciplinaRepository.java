package com.technologies.venom.room.persistence;

import android.content.Context;

import com.technologies.venom.room.asynctask.AtualizarAlunoAsyncTask;
import com.technologies.venom.room.asynctask.AtualizarDisciplinaAsyncTask;
import com.technologies.venom.room.asynctask.CadastrarAlunoAsyncTask;
import com.technologies.venom.room.asynctask.CadastrarDisciplinaAsyncTask;
import com.technologies.venom.room.asynctask.ExcluirAlunoAsyncTask;
import com.technologies.venom.room.asynctask.ExcluirDisciplinaAsyncTask;
import com.technologies.venom.room.asynctask.ListarAlunoAsyncTask;
import com.technologies.venom.room.asynctask.ListarDisciplinaAsyncTask;
import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.models.Disciplina;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class DisciplinaRepository {
    private UniversidadeDatabase universidadeDatabase;

    public DisciplinaRepository(Context context){
        universidadeDatabase = UniversidadeDatabase.getInstance(context);
    }

    public long cadastrar(Disciplina disciplina){
        CadastrarDisciplinaAsyncTask asyncTask = new CadastrarDisciplinaAsyncTask(universidadeDatabase.getDisciplinaDao());
        Long id = null;
        try {
            id = asyncTask.execute(disciplina).get().longValue();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return id;
    }

    public void atualizar(Disciplina disciplina){
        new AtualizarDisciplinaAsyncTask(universidadeDatabase.getDisciplinaDao()).execute(disciplina);
    }

    public void excluir(Disciplina disciplina){
        new ExcluirDisciplinaAsyncTask(universidadeDatabase.getDisciplinaDao()).execute(disciplina);
    }

    public List<Disciplina> listar() {
        return (List<Disciplina>) new ListarDisciplinaAsyncTask(universidadeDatabase.getDisciplinaDao()).execute();
    }

    public Disciplina listarPorId(Long id) {
        return universidadeDatabase.getDisciplinaDao().listarPorId(id);
    }
}
