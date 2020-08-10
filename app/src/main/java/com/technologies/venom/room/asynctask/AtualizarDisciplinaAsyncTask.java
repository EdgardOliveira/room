package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Disciplina;
import com.technologies.venom.room.persistence.DisciplinaDao;

public class AtualizarDisciplinaAsyncTask extends AsyncTask<Disciplina, Void, Void> {

    private DisciplinaDao disciplinaDao;

    public AtualizarDisciplinaAsyncTask(DisciplinaDao disciplinaDao) {
        this.disciplinaDao = disciplinaDao;
    }

    @Override
    protected Void doInBackground(Disciplina... disciplinas) {

        disciplinaDao.atualizar(disciplinas[0]);

        return null;
    }
}
