package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Professor;
import com.technologies.venom.room.persistence.ProfessorDao;

public class ExcluirProfessorAsyncTask extends AsyncTask<Professor, Void, Void> {

    private ProfessorDao professorDao;

    public ExcluirProfessorAsyncTask(ProfessorDao professorDao) {
        this.professorDao = professorDao;
    }

    @Override
    protected Void doInBackground(Professor... professors) {

        professorDao.excluir(professors[0]);

        return null;
    }
}
