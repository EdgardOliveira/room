package com.technologies.venom.room.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.technologies.venom.room.R;
import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.models.Disciplina;
import com.technologies.venom.room.models.Endereco;
import com.technologies.venom.room.models.Matricula;
import com.technologies.venom.room.models.Professor;
import com.technologies.venom.room.persistence.AlunoRepository;
import com.technologies.venom.room.persistence.DisciplinaRepository;
import com.technologies.venom.room.persistence.MatriculaRepository;
import com.technologies.venom.room.persistence.ProfessorRepository;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnCadastrar;
    private ImageView imgvwAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        imgvwAlunos = findViewById(R.id.imgvwAlunos);
        btnCadastrar.setOnClickListener(this);
        imgvwAlunos.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnCadastrar:
                cadastrar();
                break;
            case R.id.imgvwAlunos:
                Intent intent = new Intent(getApplicationContext(), AlunosActivity.class);
                startActivity(intent);
        }
    }

    private void cadastrar() {
        //cadastro de aluno
        AlunoRepository alunoRepository = new AlunoRepository(this);
        alunoRepository.cadastrar(new Aluno("Edgard", "Oliveira", "12345678934", false,
                new Endereco("69050001", "Av. Constantino Nery", "5555", "Em Frente a FAMETRO", "Chapada", "Manaus", "AM")));

        //cadastro de professores
        ProfessorRepository professorRepository = new ProfessorRepository(this);
        professorRepository.cadastrar(new Professor("Manfrine", "Santos", "1234567890",
                new Endereco("69050002", "Av. Constantino Nery", "123", "FAMETRO", "Chapada", "Manaus", "AM")));
        professorRepository.cadastrar(new Professor("Manfrine", "Santos", "1234567890",
                new Endereco("69050002", "Av. Constantino Nery", "123", "FAMETRO", "Chapada", "Manaus", "AM")));
        professorRepository.cadastrar(new Professor("Roney", "Ribeiro", "1234567891",
                new Endereco("69050003", "Av. Constantino Nery", "123", "FAMETRO", "Chapada", "Manaus", "AM")));
        professorRepository.cadastrar(new Professor("Bruno", "Gonçalves", "1234567893",
                new Endereco("69050004", "Av. Constantino Nery", "123", "FAMETRO", "Chapada", "Manaus", "AM")));
        professorRepository.cadastrar(new Professor("Aline", "Mary", "1234567894",
                new Endereco("69050005", "Av. Constantino Nery", "123", "FAMETRO", "Chapada", "Manaus", "AM")));

        //cadastro de disciplinas
        DisciplinaRepository disciplinaRepository = new DisciplinaRepository(this);
        disciplinaRepository.cadastrar(new Disciplina("Tecnologia Web", Long.valueOf(1)));
        disciplinaRepository.cadastrar(new Disciplina("Manipulação de Banco de Dados", Long.valueOf(2)));
        disciplinaRepository.cadastrar(new Disciplina("Programação Orientada à Objetos", Long.valueOf(3)));
        disciplinaRepository.cadastrar(new Disciplina("Estrutura de Dados II", Long.valueOf(1)));

        //cadastro de matrículas de aluno em uma disciplina
        MatriculaRepository matriculaRepository = new MatriculaRepository(this);
        matriculaRepository.cadastrar(new Matricula(Long.valueOf(1), Long.valueOf(1)));
        matriculaRepository.cadastrar(new Matricula(Long.valueOf(1), Long.valueOf(2)));
        matriculaRepository.cadastrar(new Matricula(Long.valueOf(1), Long.valueOf(3)));
        matriculaRepository.cadastrar(new Matricula(Long.valueOf(1), Long.valueOf(4)));
    }
}