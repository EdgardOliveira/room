package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.persistence.AlunoDao;

import java.util.List;

public class ListarAlunoAsyncTask extends AsyncTask<Void, Void, List<Aluno>> {

    private AlunoDao alunoDao;

    public ListarAlunoAsyncTask(AlunoDao alunoDao) {
        this.alunoDao = alunoDao;
    }

    @Override
    protected List<Aluno> doInBackground(Void... voids) {
        return alunoDao.listar();
    }

    @Override
    protected void onPostExecute(List<Aluno> alunos) {
        super.onPostExecute(alunos);
    }
}
