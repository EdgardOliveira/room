package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Disciplina;
import com.technologies.venom.room.persistence.DisciplinaDao;

import java.util.List;

public class ListarDisciplinaAsyncTask extends AsyncTask<Void, Void, List<Disciplina>> {

    private DisciplinaDao disciplinaDao;


    public ListarDisciplinaAsyncTask(DisciplinaDao disciplinaDao) {
        this.disciplinaDao = disciplinaDao;
    }

    @Override
    protected List<Disciplina> doInBackground(Void... voids) {
        return disciplinaDao.listar();
    }
}
