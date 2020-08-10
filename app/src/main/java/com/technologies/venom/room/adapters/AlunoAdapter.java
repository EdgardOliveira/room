package com.technologies.venom.room.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.technologies.venom.room.R;
import com.technologies.venom.room.models.Aluno;

import java.util.List;

public class AlunoAdapter extends RecyclerView.Adapter<AlunoViewHolder> {

    private Context context;
    private List<Aluno> alunosList;

    public AlunoAdapter(Context context, List<Aluno> alunosList) {
        this.context = context;
        this.alunosList = alunosList;
    }

    @NonNull
    @Override
    public AlunoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_aluno, parent, false);
        return new AlunoViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull AlunoViewHolder holder, int position) {
        //Pegando a posição do item
        final Aluno alunoSelecionado = alunosList.get(position);

        //Configurando os objetos no CardView de acordo com o que foi passado
        holder.txtvwId.setText(String.valueOf(alunoSelecionado.getAlunoId()));
        holder.txtvwNome.setText(alunoSelecionado.getPrimeiroNome() + " " + alunoSelecionado.getUltimoNome());

        if (alunoSelecionado.isInativo()) {
            holder.txtvwTituloInativo.setVisibility(View.VISIBLE);
            holder.txtvwInativo.setVisibility(View.VISIBLE);
            holder.txtvwInativo.setText("INATIVO");
        } else {
            holder.txtvwTituloInativo.setVisibility(View.GONE);
            holder.txtvwInativo.setVisibility(View.GONE);
            holder.txtvwInativo.setText("ATIVO");
        }

        //Configurar o evento de click normal
        holder.cardViewAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //Configurar o evento de click longo
        holder.cardViewAluno.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return alunosList.size();
    }
}
