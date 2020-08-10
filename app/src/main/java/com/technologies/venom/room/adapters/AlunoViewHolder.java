package com.technologies.venom.room.adapters;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.technologies.venom.room.R;

public class AlunoViewHolder extends RecyclerView.ViewHolder {
    public CardView cardViewAluno;
    public TextView txtvwId, txtvwNome, txtvwTituloInativo, txtvwInativo;

    public AlunoViewHolder(@NonNull View itemView) {
        super(itemView);

        cardViewAluno = itemView.findViewById(R.id.cardviewAluno);
        txtvwId = itemView.findViewById(R.id.txtvwCVAlunoId);
        txtvwNome = itemView.findViewById(R.id.txtvwCVAlunoNome);
        txtvwTituloInativo = itemView.findViewById(R.id.txtvwCVAlunoTituloInativo);
        txtvwInativo = itemView.findViewById(R.id.txtvwCVAlunoInativo);
    }
}
