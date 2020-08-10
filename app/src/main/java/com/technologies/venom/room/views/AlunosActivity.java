package com.technologies.venom.room.views;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.technologies.venom.room.R;
import com.technologies.venom.room.adapters.AlunoAdapter;
import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.models.Disciplina;
import com.technologies.venom.room.models.Matricula;
import com.technologies.venom.room.persistence.AlunoRepository;
import com.technologies.venom.room.persistence.DisciplinaRepository;
import com.technologies.venom.room.persistence.MatriculaRepository;
import com.technologies.venom.room.utils.RecyclerItemClickListener;

import java.util.List;

public class AlunosActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private List<Aluno> list;
    private AlunoAdapter adapter;
    private AlunoRepository repository;
    private FloatingActionButton fabCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alunos);

        recyclerView = findViewById(R.id.recyclerViewAlunos);
        swipeRefreshLayout = findViewById(R.id.swipePerguntas);
        fabCadastrar = findViewById(R.id.fabCadastrarAluno);

        //configurando as cores
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        //configurando eventos de refresh
        swipeRefreshLayout.setOnRefreshListener(this);
        repository = new AlunoRepository(this);
        fabCadastrar.setOnClickListener(this);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Recuperando o  que o usuário clicou
                                final Aluno alunoSelecionado = list.get(position);

                                Intent intent = new Intent(getApplicationContext(), AlunoActivity.class);
                                //configurando o pacote de dados que será enviado a outra tela
                                intent.putExtra("aluno", alunoSelecionado);
                                //trocando de tela
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                //Recuperando o  que o usuário clicou
                                final Aluno alunoSelecionado= repository.listarPorId(list.get(position).getAlunoId());

                                //Configurando o Alert Dialog
                                AlertDialog.Builder dialog = new AlertDialog.Builder(AlunosActivity.this);
                                //Configurando o título e mensagem
                                dialog.setTitle("EXCLUIR");
                                dialog.setIcon(R.drawable.ic_aluno);
                                dialog.setMessage("Deseja excluir este aluno?");
                                //Configuração dos botões
                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        MatriculaRepository matriculaRepository = new MatriculaRepository(getApplicationContext());
                                        //antes de excluir... verifica se este aluno esta matriculado em alguma disciplina
                                        List<Matricula> lista = matriculaRepository.listarPorAlunoId(alunoSelecionado.getAlunoId());

                                        if (lista.size() > 0) {
                                            Toast.makeText(getApplicationContext(),
                                                    "Esse aluno está matriculado em uma disciplina.\nNão pode ser excluído.\nSerá somente desativado.",
                                                    Toast.LENGTH_SHORT).show();
                                            alunoSelecionado.setInativo(true);

                                            repository.atualizar(alunoSelecionado);

                                            //configura para aparecer a progressbar do swiperefresh
                                            swipeRefreshLayout.setRefreshing(true);

                                        } else {
                                            repository.excluir(alunoSelecionado);
                                        }

                                        //configura para aparecer a progressbar do swiperefresh
                                        swipeRefreshLayout.setRefreshing(true);

                                        consultarAlunos();

                                    }
                                });
                                dialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                dialog.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });
                                dialog.create();
                                dialog.show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
    }

    @Override
    protected void onStart() {
        //configura para aparecer a progressbar do swipeRefresh
        swipeRefreshLayout.setRefreshing(true);

        consultarAlunos();

        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    /***
     * Método chamado ao fazer o gesto de atualização no layout
     */
    public void onRefresh() {

        consultarAlunos();

        swipeRefreshLayout.setRefreshing(false);

    }

    private void consultarAlunos() {
        list = repository.listar();
        carregarListaAlunos();
    }

    //Método para consultar e listar no recyclerview
    private void carregarListaAlunos() {
        adapter = new AlunoAdapter(this, list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fabCadastrarAluno:
                Intent intent = new Intent(getApplicationContext(), AlunoActivity.class);
                //trocando de tela
                startActivity(intent);
                break;
        }
    }
}