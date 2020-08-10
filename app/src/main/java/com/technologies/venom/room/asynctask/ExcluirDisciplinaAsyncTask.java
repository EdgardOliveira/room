package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Disciplina;
import com.technologies.venom.room.persistence.DisciplinaDao;

public class ExcluirDisciplinaAsyncTask extends AsyncTask<Disciplina, Void, Void> {

    private DisciplinaDao disciplinaDao;

    public ExcluirDisciplinaAsyncTask(DisciplinaDao disciplinaDao) {
        this.disciplinaDao = disciplinaDao;
    }

    @Override
    protected Void doInBackground(Disciplina... disciplinas) {

        disciplinaDao.excluir(disciplinas[0]);

        return null;
    }
}
