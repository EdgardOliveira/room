package com.technologies.venom.room.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.technologies.venom.room.R;
import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.models.AlunoComMatricula;
import com.technologies.venom.room.models.Disciplina;
import com.technologies.venom.room.models.Endereco;
import com.technologies.venom.room.models.Matricula;
import com.technologies.venom.room.persistence.UniversidadeDatabase;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnListar, btnCadastrar;
    public static final String TAG = "Universidade>>>";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnListar = findViewById(R.id.btnListar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnListar.setOnClickListener(this);
        btnCadastrar.setOnClickListener(this);
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
        }
    }

    private void cadastrar(){

        //Cadastro de aluno com endereço
        UniversidadeDatabase.getInstance(this).alunosDao().inserir(new Aluno("Edgard", "Oliveira", "68936419234",
                new Endereco("69050001", "Av. Constantino Nery", "2525", "Em Frente a FAMETRO", "Chapada", "Manaus", "AM")));

        //cadastro de disciplinas
        UniversidadeDatabase.getInstance(this).disciplinaDao().inserir(new Disciplina("Programação Orientada à Objetos"));
        UniversidadeDatabase.getInstance(this).disciplinaDao().inserir(new Disciplina("Estrutura de Dados II"));
        UniversidadeDatabase.getInstance(this).disciplinaDao().inserir(new Disciplina("Manipulação  de Banco de Dados"));
        UniversidadeDatabase.getInstance(this).disciplinaDao().inserir(new Disciplina("Tecnologia Web"));

        //cadastro de matrículas de aluno em uma disciplina
        UniversidadeDatabase.getInstance(this).matriculaDao().inserir(new Matricula(Long.valueOf(1), Long.valueOf(1)));
        UniversidadeDatabase.getInstance(this).matriculaDao().inserir(new Matricula(Long.valueOf(1), Long.valueOf(2)));
        UniversidadeDatabase.getInstance(this).matriculaDao().inserir(new Matricula(Long.valueOf(1), Long.valueOf(3)));
        UniversidadeDatabase.getInstance(this).matriculaDao().inserir(new Matricula(Long.valueOf(1), Long.valueOf(4)));
    }

    private void listar() {
        List<AlunoComMatricula> alunoComMatriculaList = UniversidadeDatabase.getInstance(this).alunosDao().listarComMatricula();
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
                Disciplina disciplina = UniversidadeDatabase.getInstance(this).disciplinaDao().listarPorId(matricula.getDisciplinaId());
                Log.d(TAG, "Disciplina:" + disciplina.getNome());
            }
        }
    }
}