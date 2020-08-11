package com.technologies.venom.room.views;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.technologies.venom.room.R;
import com.technologies.venom.room.api.RESTService;
import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.models.Endereco;
import com.technologies.venom.room.persistence.AlunoRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlunoActivity extends AppCompatActivity implements View.OnClickListener {

    private final String URL = "https://viacep.com.br/ws/";
    private Retrofit retrofit;
    private Aluno aluno;
    private TextInputEditText txtId, txtNome, txtSobrenome, txtCPF, txtCEP, txtLogradouro, txtNumero, txtComplemento, txtBairro, txtCidade, txtUF;
    private TextInputLayout layId, layNome, laySobrenome, layCPF, layCEP, layLogradouro, layNumero, layComplemento, layBairro, layCidade, layUF;
    private Switch swtInativo;
    private boolean edicao = false;
    private Button btnConsultarCEP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        /** CONFIGURAÇÕES DA ACTIONBAR **/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);              // Ligando a seta que volta para Activity pai (tem que fazer outra config no manisfest.xml)
        getSupportActionBar().setTitle(R.string.app_name);                  // Configurar o título
        getSupportActionBar().setSubtitle("Cadastro de aluno");             // Configurar o sub-título
        getSupportActionBar().setDisplayShowHomeEnabled(true);              // Configurar a Home
        getSupportActionBar().setLogo(R.drawable.ic_aluno);                 // Configurar o ícone que será exibido
        getSupportActionBar().setDisplayUseLogoEnabled(true);               // Habilitar a exibição do ícone

        txtId = findViewById(R.id.txtAlunoId);
        txtNome = findViewById(R.id.txtAlunoPrimeiroNome);
        txtSobrenome = findViewById(R.id.txtAlunoSobrenome);
        txtCPF = findViewById(R.id.txtAlunoCPF);
        txtCEP = findViewById(R.id.txtAlunoCEP);
        txtLogradouro = findViewById(R.id.txtAlunoLogradouro);
        txtNumero = findViewById(R.id.txtAlunoNumero);
        txtComplemento = findViewById(R.id.txtAlunoComplemento);
        txtBairro = findViewById(R.id.txtAlunoBairro);
        txtCidade = findViewById(R.id.txtAlunoCidade);
        txtUF = findViewById(R.id.txtAlunoUF);
        layId = findViewById(R.id.layAlunoId);
        layNome = findViewById(R.id.layAlunoPrimeiroNome);
        laySobrenome = findViewById(R.id.layAlunoSobrenome);
        layCPF = findViewById(R.id.layAlunoCPF);
        layCEP = findViewById(R.id.layAlunoCEP);
        layLogradouro = findViewById(R.id.layAlunoLogradouro);
        layNumero = findViewById(R.id.layAlunoNumero);
        layComplemento = findViewById(R.id.layAlunoComplemento);
        layBairro = findViewById(R.id.layAlunoBairro);
        layCidade = findViewById(R.id.laytAlunoCidade);
        layUF = findViewById(R.id.layAlunoUF);
        swtInativo = findViewById(R.id.swtInativo);
        btnConsultarCEP = findViewById(R.id.btnConsultarCEP);
        btnConsultarCEP.setOnClickListener(this);
        //configura os recursos do retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl(URL)                                       //endereço do webservice
                .addConverterFactory(GsonConverterFactory.create()) //conversor
                .build();

        aluno = (Aluno) getIntent().getSerializableExtra("aluno");

        if (aluno != null) {
            edicao = true;
            getSupportActionBar().setSubtitle("Atualização de Cadastro de aluno");             // Configurar o sub-título
            atualizarDadosTela();
        } else {
            edicao = false;
            getSupportActionBar().setSubtitle("Cadastro de aluno");                             // Configurar o sub-título
            layId.setVisibility(View.GONE);
            txtId.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Configurando o menu da tela
        getMenuInflater().inflate(R.menu.menu_aluno, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itemSalvar:
                salvar();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void consultarCEP() {
        String sCep = txtCEP.getText().toString().trim();

        //instanciando a interface
        RESTService restService = retrofit.create(RESTService.class);

        //passando os dados para consulta
        Call<Endereco> call = restService.consultarCEP(sCep);

        //colocando a requisição na fila para execução
        call.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if (response.isSuccessful()) {
                    Endereco endereco = response.body();
                    txtLogradouro.setText(endereco.getLogradouro());
                    txtComplemento.setText(endereco.getComplemento());
                    txtBairro.setText(endereco.getBairro());
                    txtUF.setText(endereco.getUf());
                    txtCidade.setText(endereco.getCidade());
                    txtUF.setText(endereco.getUf());
                    Toast.makeText(getApplicationContext(), "CEP consultado com sucesso", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Ocorreu um erro ao tentar consultar o CEP. Erro: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void atualizarDadosTela() {
        swtInativo.setChecked(aluno.isInativo());
        txtId.setText(String.valueOf(aluno.getAlunoId()));
        txtNome.setText(aluno.getPrimeiroNome());
        txtSobrenome.setText(aluno.getUltimoNome());
        txtCPF.setText(aluno.getCpf());
        txtCEP.setText(aluno.getEndereco().getCep());
        txtLogradouro.setText(aluno.getEndereco().getLogradouro());
        txtNumero.setText(aluno.getEndereco().getNumero());
        txtComplemento.setText(aluno.getEndereco().getComplemento());
        txtBairro.setText(aluno.getEndereco().getBairro());
        txtCidade.setText(aluno.getEndereco().getCidade());
        txtUF.setText(aluno.getEndereco().getUf());
    }

    private boolean validarDados() {
        boolean status = true;

        if (!edicao)
            aluno = new Aluno();
        aluno.setInativo(swtInativo.isChecked());
        aluno.setPrimeiroNome(txtNome.getText().toString());
        aluno.setUltimoNome(txtSobrenome.getText().toString());
        aluno.setCpf(txtCPF.getText().toString());
        Endereco endereco = new Endereco(
                txtCEP.getText().toString(),
                txtLogradouro.getText().toString(),
                txtNumero.getText().toString(),
                txtComplemento.getText().toString(),
                txtBairro.getText().toString(),
                txtCidade.getText().toString(),
                txtUF.getText().toString()
        );
        aluno.setEndereco(endereco);


        if (aluno.getPrimeiroNome().isEmpty()) {
            status = false;
            layNome.setError("Informe o nome.");
            txtNome.requestFocus();
        } else {
            layNome.setError(null);
        }
        if (aluno.getUltimoNome().isEmpty()) {
            status = false;
            laySobrenome.setError("Informe o sobrenome.");
            txtSobrenome.requestFocus();
        } else {
            laySobrenome.setError(null);
        }
        if (aluno.getCpf().isEmpty()) {
            status = false;
            layCPF.setError("Informe o cpf.");
            txtCPF.requestFocus();
        } else {
            layCPF.setError(null);
        }
        if (aluno.getEndereco().getCep().isEmpty()) {
            status = false;
            layCEP.setError("Informe o cep.");
            txtCEP.requestFocus();
        } else {
            layCEP.setError(null);
        }
        if (aluno.getEndereco().getLogradouro().isEmpty()) {
            status = false;
            layLogradouro.setError("Informe o logradouro.");
            txtLogradouro.requestFocus();
        } else {
            layLogradouro.setError(null);
        }
        if (aluno.getEndereco().getNumero().isEmpty()) {
            status = false;
            layNumero.setError("Informe o número.");
            txtNumero.requestFocus();
        } else {
            layNumero.setError(null);
        }
        if (aluno.getEndereco().getComplemento().isEmpty()) {
            status = false;
            layComplemento.setError("Informe o complemento.");
            txtComplemento.requestFocus();
        } else {
            layComplemento.setError(null);
        }
        if (aluno.getEndereco().getBairro().isEmpty()) {
            status = false;
            layBairro.setError("Informe o bairro.");
            txtBairro.requestFocus();
        } else {
            layBairro.setError(null);
        }
        if (aluno.getEndereco().getCidade().isEmpty()) {
            status = false;
            layCidade.setError("Informe a cidade.");
            txtCidade.requestFocus();
        } else {
            layCidade.setError(null);
        }
        if (aluno.getEndereco().getUf().isEmpty()) {
            status = false;
            layUF.setError("Informe a UF.");
            txtUF.requestFocus();
        } else {
            layUF.setError(null);
        }
        return status;
    }

    private void salvar() {
        if (validarDados()) {
            AlunoRepository repository = new AlunoRepository(this);
            if (edicao) {
                repository.atualizar(aluno);
            } else {
                repository.cadastrar(aluno);
            }
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Dados insuficientes para o registro!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnConsultarCEP:
                if(!txtCEP.getText().toString().isEmpty()){
                    consultarCEP();
                }else{
                    Toast.makeText(getApplicationContext(), "O CEP tem que ser fornecido!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}