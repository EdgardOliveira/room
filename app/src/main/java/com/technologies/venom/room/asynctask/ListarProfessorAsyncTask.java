package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Professor;
import com.technologies.venom.room.persistence.ProfessorDao;

import java.util.List;

public class ListarProfessorAsyncTask extends AsyncTask <Void, Void, List<Professor>> {

    private ProfessorDao professorDao;

    public ListarProfessorAsyncTask(ProfessorDao professorDao) {
        this.professorDao = professorDao;
    }

    @Override
    protected List<Professor> doInBackground(Void... voids) {
        return professorDao.listar();
    }

    @Override
    protected void onPostExecute(List<Professor> professors) {
        super.onPostExecute(professors);
    }
}
