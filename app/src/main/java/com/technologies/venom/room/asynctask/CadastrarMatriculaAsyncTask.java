package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Matricula;
import com.technologies.venom.room.persistence.MatriculaDao;

public class CadastrarMatriculaAsyncTask extends AsyncTask<Matricula, Void, Long> {

    private MatriculaDao matriculaDao;

    public CadastrarMatriculaAsyncTask(MatriculaDao matriculaDao) {
        this.matriculaDao = matriculaDao;
    }

    @Override
    protected Long doInBackground(Matricula... matriculas) {
        return matriculaDao.inserir(matriculas[0]);
    }

    @Override
    protected void onPostExecute(Long id) {
        super.onPostExecute(id);
    }
}
