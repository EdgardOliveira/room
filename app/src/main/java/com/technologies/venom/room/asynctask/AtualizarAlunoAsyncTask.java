package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.persistence.AlunoDao;

public class AtualizarAlunoAsyncTask extends AsyncTask<Aluno, Void, Void> {

    private AlunoDao alunoDao;

    public AtualizarAlunoAsyncTask(AlunoDao alunoDao) {
        this.alunoDao = alunoDao;
    }

    @Override
    protected Void doInBackground(Aluno... alunos) {
        alunoDao.atualizar(alunos[0]);
        return null;
    }
}
