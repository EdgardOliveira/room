package com.technologies.venom.room.persistence;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.models.Disciplina;
import com.technologies.venom.room.models.Endereco;
import com.technologies.venom.room.models.Matricula;

@Database(
        entities = {Aluno.class, Disciplina.class, Endereco.class, Matricula.class},
        version = 2,
        exportSchema = true
)
public abstract class UniversidadeDatabase extends RoomDatabase {
    public static final String NOME_BD = "UniversidadeApp";
    public abstract AlunoDao alunosDao();
    public abstract DisciplinaDao disciplinaDao();
    public abstract EnderecoDao enderecoDao();
    public abstract MatriculaDao matriculaDao();

    public static UniversidadeDatabase universidadeDatabase;

    public static UniversidadeDatabase getInstance(Context context) {
        if(universidadeDatabase == null) {
            universidadeDatabase = Room.databaseBuilder(context.getApplicationContext(), UniversidadeDatabase.class, NOME_BD)
                    .allowMainThreadQueries()
                    .addCallback(callback)
                    .addMigrations(MIGRATION_1_2)
                    .build();
        }

        return universidadeDatabase;
    }

    public static RoomDatabase.Callback callback = new RoomDatabase.Callback() {

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            popularBD(universidadeDatabase);
        }
    };

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //incluímos o campo cpf na classe alunos
            database.execSQL("ALTER TABLE alunos "
                    + " ADD COLUMN cpf TEXT DEFAULT ''");
        }
    };

    private static void popularBD(UniversidadeDatabase db){
        //Endereco endereco = new Endereco("69050001", "Av. Constantino Nery", "2525", "Em Frente a FAMETRO", "Chapada", "Manaus", "AM");
        //Aluno aluno = new Aluno("Edgard", "Oliveira", endereco);
        //db.alunosDao().inserir(aluno);
    }
}
