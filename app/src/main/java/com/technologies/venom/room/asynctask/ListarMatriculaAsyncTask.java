package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Matricula;
import com.technologies.venom.room.persistence.MatriculaDao;

import java.util.List;

public class ListarMatriculaAsyncTask extends AsyncTask<Void, Void, List<Matricula>> {

    private MatriculaDao matriculaDao;


    public ListarMatriculaAsyncTask(MatriculaDao matriculaDao) {
        this.matriculaDao = matriculaDao;
    }

    @Override
    protected List<Matricula> doInBackground(Void... voids) {
        return (List<Matricula>) matriculaDao.listar();
    }
}
