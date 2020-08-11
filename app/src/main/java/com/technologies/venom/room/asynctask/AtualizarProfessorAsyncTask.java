package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Professor;
import com.technologies.venom.room.persistence.ProfessorDao;

public class AtualizarProfessorAsyncTask extends AsyncTask<Professor, Void, Void> {

    private ProfessorDao professorDao;

    public AtualizarProfessorAsyncTask(ProfessorDao professorDao) {
        this.professorDao = professorDao;
    }

    @Override
    protected Void doInBackground(Professor... professors) {

        professorDao.atualizar(professors[0]);

        return null;
    }
}
