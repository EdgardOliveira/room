package com.technologies.venom.room.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.technologies.venom.room.R;
import com.technologies.venom.room.models.Aluno;

public class AlunoActivity extends AppCompatActivity {

    private Aluno aluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);
        aluno = (Aluno) getIntent().getSerializableExtra("aluno");

        if (aluno != null)
            Toast.makeText(getApplicationContext(), "OK", Toast.LENGTH_SHORT).show();

    }
}