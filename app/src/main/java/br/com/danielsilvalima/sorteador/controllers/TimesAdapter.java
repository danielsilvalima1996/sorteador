package br.com.danielsilvalima.sorteador.controllers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import br.com.danielsilvalima.sorteador.R;
import br.com.danielsilvalima.sorteador.interfaces.OnActionListener;

public class TimesAdapter extends RecyclerView.Adapter<TimesAdapter.TimesViewHolder> {

    private List<String> times = new ArrayList<>();
    private OnActionListener actionListener;

    public TimesAdapter(List<String> times, OnActionListener actionListener) {
        this.times = times;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public TimesAdapter.TimesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_time, parent, false);

        return new TimesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimesViewHolder holder, int position) {
        String time = times.get(position);
        holder.txtNomeTimes.setText(time);

        holder.btnRemover.setOnClickListener(v -> actionListener.onRemove(position));
    }

    @Override
    public int getItemCount() {
        return times.size();
    }

    static class TimesViewHolder extends RecyclerView.ViewHolder {

        TextView txtNomeTimes;
        Button btnRemover;

        public TimesViewHolder(@NotNull View itemView) {
            super(itemView);
            txtNomeTimes = itemView.findViewById(R.id.txtNomeTime);
            btnRemover = itemView.findViewById(R.id.btnRemoverTime);
        }
    }
}
