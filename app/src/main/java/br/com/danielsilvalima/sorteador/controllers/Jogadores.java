package br.com.danielsilvalima.sorteador.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.danielsilvalima.sorteador.R;
import br.com.danielsilvalima.sorteador.interfaces.OnActionListener;

public class Jogadores extends Fragment {

    private static final String PREFS_NAME = "JogadoresPrefs";
    private static final String KEY_JOGADORES = "jogadores";

    private List<String> jogadoresList;
    private JogadoresAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.jogadores, container, false);

        jogadoresList = carregarJogadores();
        TextInputEditText inputJogador = view.findViewById(R.id.inputJogador);
        Button btnAddJogador = view.findViewById(R.id.btnAddJogador);
        RecyclerView recyclerJogadores = view.findViewById(R.id.recyclerJogadores);

        adapter = new JogadoresAdapter(jogadoresList, new OnActionListener() {

            @Override
            public void onRemove(int position) {
                jogadoresList.remove(position);
                adapter.notifyItemRemoved(position);
                salvarJogadores();
            }
        });

        recyclerJogadores.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerJogadores.setAdapter(adapter);

        btnAddJogador.setOnClickListener(v -> {
            String jogador = inputJogador.getText().toString().trim();
            if(!jogador.isEmpty()) {
                jogadoresList.add(jogador);
                inputJogador.setText("");
                salvarJogadores();
                adapter.notifyItemInserted(jogadoresList.size() - 1);
            }
        });

        return view;
    }

    private void salvarJogadores() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> jogadoresSet = new HashSet<>(jogadoresList);
        editor.putStringSet(KEY_JOGADORES, jogadoresSet);
        editor.apply();
    }

    private List<String> carregarJogadores() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> jogadoresSet = sharedPreferences.getStringSet(KEY_JOGADORES, new HashSet<>());
        return new ArrayList<>(jogadoresSet);
    }

}
