package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.persistence.AlunoDao;

public class CadastrarAlunoAsyncTask extends AsyncTask<Aluno, Void, Long> {

    private AlunoDao alunoDao;

    public CadastrarAlunoAsyncTask(AlunoDao alunoDao) {
        this.alunoDao = alunoDao;
    }

    @Override
    protected Long doInBackground(Aluno... alunos) {
        return alunoDao.inserir(alunos[0]);
    }

    @Override
    protected void onPostExecute(Long id) {
        super.onPostExecute(id);
    }
}
