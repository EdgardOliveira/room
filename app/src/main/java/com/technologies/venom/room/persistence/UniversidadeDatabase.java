package com.technologies.venom.room.persistence;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.models.Disciplina;
import com.technologies.venom.room.models.Endereco;
import com.technologies.venom.room.models.Matricula;
import com.technologies.venom.room.models.Professor;

@Database(
        entities = {Aluno.class, Disciplina.class, Endereco.class, Matricula.class, Professor.class},
        version = 6
)
public abstract class UniversidadeDatabase extends RoomDatabase {
    public static final String NOME_BD = "UniversidadeApp";

    public abstract AlunoDao getAlunoDao();
    public abstract DisciplinaDao getDisciplinaDao();
    public abstract EnderecoDao getEnderecoDao();
    public abstract MatriculaDao getMatriculaDao();
    public abstract ProfessorDao getProfessorDao();

    public static UniversidadeDatabase universidadeDatabase;

    public static UniversidadeDatabase getInstance(Context context) {
        if(universidadeDatabase == null) {
            universidadeDatabase = Room.databaseBuilder(context.getApplicationContext(), UniversidadeDatabase.class, NOME_BD)
                    .allowMainThreadQueries()
                    .addCallback(callback)
                    .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_4_5, MIGRATION_5_6)
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
            database.execSQL("ALTER TABLE alunos " +
                            "ADD COLUMN cpf TEXT DEFAULT ''");
        }
    };

    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //criação da entidade/tabela professores
            database.execSQL("CREATE TABLE `professores` (" +
                    "`professorId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`primeiroNome` TEXT, " +
                    "`ultimoNome` TEXT, " +
                    "`endenderecoId` INTEGER, " +
                    "`endcep` TEXT, " +
                    "`endlogradouro` TEXT, " +
                    "`endnumero` TEXT, " +
                    "`endcomplemento` TEXT, " +
                    "`endbairro` TEXT, " +
                    "`endcidade` TEXT, " +
                    "`enduf` TEXT, " +
                    "matricula TEXT DEFAULT '')");
        }
    };

    static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {

        }
    };

            static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // criando uma tabela de transição... necessário, pois o SQLite não implementou ainda a alteração para inclusão de FK
            database.execSQL("CREATE TABLE IF NOT EXISTS `disciplinasTemp` (" +
                    "`disciplinaId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    "`nome` TEXT, " +
                    "`professorId` INTEGER NOT NULL DEFAULT 1, " +
                    "FOREIGN KEY(`professorId`) REFERENCES `professores`(`professorId`))");

            // Copiando os dados
            database.execSQL("INSERT INTO `disciplinasTemp` (disciplinaId, nome, professorId) " +
                    "SELECT disciplinaId, nome, 1 FROM disciplinas");

            // Removendo a tabela antiga
            database.execSQL("DROP TABLE disciplinas");

            // Trocando o nome da tabela de transição para o nome correto
            database.execSQL("ALTER TABLE disciplinasTemp RENAME TO disciplinas");
        }
    };

    static final Migration MIGRATION_5_6 = new Migration(5, 6) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //incluímos o campo inativo na classe/entidade alunos
            database.execSQL("ALTER TABLE alunos "
                    + " ADD COLUMN inativo INTEGER NOT NULL DEFAULT 0");
        }
    };

    private static void popularBD(UniversidadeDatabase db){
        //Endereco endereco = new Endereco("69050001", "Av. Constantino Nery", "2525", "Em Frente a FAMETRO", "Chapada", "Manaus", "AM");
        //Aluno aluno = new Aluno("Edgard", "Oliveira", endereco);
        //db.alunosDao().inserir(aluno);
    }
}
