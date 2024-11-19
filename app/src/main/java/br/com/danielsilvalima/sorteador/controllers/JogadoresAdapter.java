package br.com.danielsilvalima.sorteador.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import br.com.danielsilvalima.sorteador.R;
import br.com.danielsilvalima.sorteador.interfaces.OnActionListener;

public class JogadoresAdapter extends RecyclerView.Adapter<JogadoresAdapter.JogadorViewHolder> {

    private List<String> jogadores;
    private OnActionListener actionListener;

    public JogadoresAdapter(List<String> jogadores, OnActionListener actionListener) {
        this.jogadores = jogadores;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public JogadoresAdapter.JogadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jogador, parent, false);

        return new JogadorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JogadorViewHolder holder, int position) {
        String jogador = jogadores.get(position);
        holder.txtNomeJogador.setText(jogador);

        holder.btnRemover.setOnClickListener(v -> actionListener.onRemove(position));
    }

    @Override
    public int getItemCount() {
        return jogadores.size();
    }

    static class JogadorViewHolder extends RecyclerView.ViewHolder {

        TextView txtNomeJogador;
        Button btnRemover;

        public JogadorViewHolder(@NotNull View itemView) {
            super(itemView);
            txtNomeJogador = itemView.findViewById(R.id.txtNomeJogador);
            btnRemover = itemView.findViewById(R.id.btnRemoverJogador);
        }
    }
}
