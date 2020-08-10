package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.persistence.AlunoDao;

public class ExcluirAlunoAsyncTask extends AsyncTask<Aluno, Void, Void> {

    private AlunoDao alunoDao;

    public ExcluirAlunoAsyncTask(AlunoDao alunoDao) {
        this.alunoDao = alunoDao;
    }

    @Override
    protected Void doInBackground(Aluno... alunos) {

        alunoDao.excluir(alunos[0]);

        return null;
    }
}
