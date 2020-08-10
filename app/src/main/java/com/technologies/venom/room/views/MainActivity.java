package com.technologies.venom.room.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.technologies.venom.room.R;
import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.models.AlunoComMatricula;
import com.technologies.venom.room.models.Disciplina;
import com.technologies.venom.room.models.Endereco;
import com.technologies.venom.room.models.Matricula;
import com.technologies.venom.room.models.Professor;
import com.technologies.venom.room.persistence.AlunoRepository;
import com.technologies.venom.room.persistence.DisciplinaRepository;
import com.technologies.venom.room.persistence.MatriculaRepository;
import com.technologies.venom.room.persistence.UniversidadeDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnListar, btnCadastrar;
    private ImageView imgvwAlunos;
    public static final String TAG = "Universidade>>>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnListar = findViewById(R.id.btnListar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        imgvwAlunos = findViewById(R.id.imgvwAlunos);
        btnListar.setOnClickListener(this);
        btnCadastrar.setOnClickListener(this);
        imgvwAlunos.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnListar:
                listar();
                break;
            case R.id.btnCadastrar:
                cadastrar();
                break;
            case R.id.imgvwAlunos:
                Intent intent = new Intent(getApplicationContext(), AlunosActivity.class);
                startActivity(intent);
        }
    }

    private void cadastrar() {
        AlunoRepository alunoRepository = new AlunoRepository(this);
        alunoRepository.cadastrar(new Aluno("Edgard", "Oliveira", "68936419234", false,
                new Endereco("69050001", "Av. Constantino Nery", "2525", "Em Frente a FAMETRO", "Chapada", "Manaus", "AM")));

//        ProfessorRepository professorRepository = ProfessorRepository(this);
//        professorRepository.cadastrar("Manfrine", "Santos", "1234567890",
//                new Endereco("69050002", "Av. Constantino Nery", "123", "FAMETRO", "Chapada", "Manaus", "AM"));
//
//        //Cadastro de professor com endereço
//        UniversidadeDatabase.getInstance(this).professorDao().inserir(new Professor("Manfrine", "Santos", "1234567890",
//                new Endereco("69050002", "Av. Constantino Nery", "123", "FAMETRO", "Chapada", "Manaus", "AM")));
//        UniversidadeDatabase.getInstance(this).professorDao().inserir(new Professor("Roney", "Ribeiro", "1234567891",
//                new Endereco("69050003", "Av. Constantino Nery", "123", "FAMETRO", "Chapada", "Manaus", "AM")));
//        UniversidadeDatabase.getInstance(this).professorDao().inserir(new Professor("Bruno", "Gonçalves", "1234567893",
//                new Endereco("69050004", "Av. Constantino Nery", "123", "FAMETRO", "Chapada", "Manaus", "AM")));
//        UniversidadeDatabase.getInstance(this).professorDao().inserir(new Professor("Aline", "Marin", "1234567894",
//                new Endereco("69050005", "Av. Constantino Nery", "123", "FAMETRO", "Chapada", "Manaus", "AM")));

        //cadastro de disciplinas
        DisciplinaRepository disciplinaRepository = new DisciplinaRepository(this);
        disciplinaRepository.cadastrar(new Disciplina("Programação Orientada à Objetos", Long.valueOf(3)));
        disciplinaRepository.cadastrar(new Disciplina("Estrutura de Dados II", Long.valueOf(1)));
        disciplinaRepository.cadastrar(new Disciplina("Manipulação  de Banco de Dados", Long.valueOf(2)));
        disciplinaRepository.cadastrar(new Disciplina("Tecnologia Web", Long.valueOf(1)));

        //cadastro de matrículas de aluno em uma disciplina
        MatriculaRepository matriculaRepository = new MatriculaRepository(this);
        matriculaRepository.cadastrar(new Matricula(Long.valueOf(1), Long.valueOf(1)));
        matriculaRepository.cadastrar(new Matricula(Long.valueOf(1), Long.valueOf(2)));
        matriculaRepository.cadastrar(new Matricula(Long.valueOf(1), Long.valueOf(3)));
        matriculaRepository.cadastrar(new Matricula(Long.valueOf(1), Long.valueOf(4)));
    }

    private void listar() {
        AlunoRepository alunoRepository = new AlunoRepository(this);
        List<AlunoComMatricula> alunoComMatriculaList = alunoRepository.listarComMatricula();
        for (AlunoComMatricula alunoComMatricula : alunoComMatriculaList) {
            Log.d(TAG,
                    "Id:" + alunoComMatricula.getAluno().getAlunoId() +
                            "\nNome:" + alunoComMatricula.getAluno().getPrimeiroNome() +
                            "\nSobrenome:" + alunoComMatricula.getAluno().getUltimoNome() +
                            "\nCEP:" + alunoComMatricula.getAluno().getEndereco().getCep() +
                            "\nLogradouro:" + alunoComMatricula.getAluno().getEndereco().getLogradouro() +
                            "\nNúmero:" + alunoComMatricula.getAluno().getEndereco().getNumero() +
                            "\nComplemento:" + alunoComMatricula.getAluno().getEndereco().getComplemento() +
                            "\nBairro:" + alunoComMatricula.getAluno().getEndereco().getBairro() +
                            "\nCidade:" + alunoComMatricula.getAluno().getEndereco().getCidade() +
                            "\nUF:" + alunoComMatricula.getAluno().getEndereco().getUf()
            );

            for (Matricula matricula : alunoComMatricula.getMatriculasList()) {
                Log.d(TAG, "Id Disciplina:" + matricula.getDisciplinaId());
                DisciplinaRepository disciplinaRepository = new DisciplinaRepository(this);
                Disciplina disciplina = disciplinaRepository.listarPorId(matricula.getDisciplinaId());
                Log.d(TAG, "Disciplina:" + disciplina.getNome());
            }
        }
    }
}