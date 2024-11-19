package br.com.danielsilvalima.sorteador;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class Jogadores extends Fragment {

    private List<String> jogadoresList;
    private TextView txtJogadores;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.jogadores, container, false);

        jogadoresList = new ArrayList<>();
        TextInputEditText inputJogador = view.findViewById(R.id.inputJogador);
        Button btnAddJogador = view.findViewById(R.id.btnAddJogador);
        txtJogadores = view.findViewById(R.id.txtJogadores);

        btnAddJogador.setOnClickListener(v -> {
            String jogador = inputJogador.getText().toString().trim();
            if(!jogador.isEmpty()) {
                jogadoresList.add(jogador);
                inputJogador.setText("");
                atualizarLista();
            }
        });

        return view;
    }

    private void atualizarLista() {
        StringBuilder jogaresExibidos = new StringBuilder("Jogadores adicionados: \n");
        jogadoresList.forEach(jogador -> {
            jogaresExibidos.append(" -").append(jogador).append("\n");
        });
        txtJogadores.setText(jogaresExibidos.toString());
    }

}
