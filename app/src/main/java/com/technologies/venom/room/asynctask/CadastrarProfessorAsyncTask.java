package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Professor;
import com.technologies.venom.room.persistence.ProfessorDao;

public class CadastrarProfessorAsyncTask extends AsyncTask<Professor, Void, Long> {

    private ProfessorDao professorDao;

    public CadastrarProfessorAsyncTask(ProfessorDao professorDao) {
        this.professorDao = professorDao;
    }

    @Override
    protected Long doInBackground(Professor... professors) {
        return professorDao.inserir(professors[0]);
    }

    @Override
    protected void onPostExecute(Long id) {
        super.onPostExecute(id);
    }
}
