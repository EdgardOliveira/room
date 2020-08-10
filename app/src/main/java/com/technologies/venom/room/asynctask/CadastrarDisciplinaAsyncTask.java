package com.technologies.venom.room.asynctask;

import android.os.AsyncTask;

import com.technologies.venom.room.models.Disciplina;
import com.technologies.venom.room.persistence.AlunoDao;
import com.technologies.venom.room.persistence.DisciplinaDao;

public class CadastrarDisciplinaAsyncTask extends AsyncTask<Disciplina, Void, Long> {

    private DisciplinaDao disciplinaDao;

    public CadastrarDisciplinaAsyncTask(DisciplinaDao disciplinaDao) {
        this.disciplinaDao = disciplinaDao;
    }

    @Override
    protected Long doInBackground(Disciplina... disciplinas) {
        return disciplinaDao.inserir(disciplinas[0]);
    }

    @Override
    protected void onPostExecute(Long id) {
        super.onPostExecute(id);
    }
}
