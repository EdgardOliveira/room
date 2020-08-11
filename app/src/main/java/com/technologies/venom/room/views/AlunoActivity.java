package com.technologies.venom.room.views;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.technologies.venom.room.R;
import com.technologies.venom.room.models.Aluno;
import com.technologies.venom.room.models.Endereco;
import com.technologies.venom.room.persistence.AlunoRepository;

public class AlunoActivity extends AppCompatActivity {

    private Aluno aluno;
    private TextInputEditText txtId, txtNome, txtSobrenome, txtCPF, txtCEP, txtLogradouro, txtNumero, txtComplemento, txtBairro, txtCidade, txtUF;
    private TextInputLayout layId, layNome, laySobrenome, layCPF, layCEP, layLogradouro, layNumero, layComplemento, layBairro, layCidade, layUF;
    private Switch swtInativo;
    private boolean edicao = false;

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
}