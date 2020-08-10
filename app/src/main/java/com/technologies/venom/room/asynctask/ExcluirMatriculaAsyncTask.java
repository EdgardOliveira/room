package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Matricula;
import com.technologies.venom.room.persistence.MatriculaDao;

public class ExcluirMatriculaAsyncTask extends AsyncTask<Matricula, Void, Void> {

    private MatriculaDao matriculaDao;

    public ExcluirMatriculaAsyncTask(MatriculaDao matriculaDao) {
        this.matriculaDao = matriculaDao;
    }

    @Override
    protected Void doInBackground(Matricula... matriculas) {

        matriculaDao.excluir(matriculas[0]);

        return null;
    }
}
