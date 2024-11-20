package br.com.danielsilvalima.sorteador.controllers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import br.com.danielsilvalima.sorteador.R;
import br.com.danielsilvalima.sorteador.interfaces.OnActionListener;

public class Sorteio extends Fragment {

    private static final String PREFS_NAME = "TimesPrefs";
    private static final String KEY_TIMES = "times";

    private List<String> timesList = new ArrayList<>();
    private List<String> timesSorteadosList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.sorteio, container, false);

        Button btnSorteio = view.findViewById(R.id.btnSortear);

        TextView txtSortear = view.findViewById(R.id.txtSortear);

        timesList = carregarTimes();
        btnSorteio.setOnClickListener(v -> {
            if(timesList.isEmpty()) {
                txtSortear.setText(R.string.a_lista_zerou);
                timesSorteadosList = new ArrayList<>();
                timesList = carregarTimes();
            } else if(timesList.size() % 2 == 1) {
                txtSortear.setText(getString(R.string.quantidadeImpar, timesList.size()));
            } else {
                int indexTimeA = numeroAleatorio(timesList.size() - 1);
                String timeA = timesList.get(indexTimeA);
                timesSorteadosList.add(timeA);
                timesList.remove(indexTimeA);
                int indexTimeB = numeroAleatorio(timesList.size() - 1);
                String timeB = timesList.get(indexTimeB);
                timesSorteadosList.add(timeB);
                timesList.remove(indexTimeB);

                txtSortear.setText(getString(R.string.time_x_time, timeA, timeB));
            }

        });

        return view;
    }

    private List<String> carregarTimes() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> timesSet = sharedPreferences.getStringSet(KEY_TIMES, new HashSet<>());
        return new ArrayList<>(timesSet);
    }

    private int numeroAleatorio(int total) {
        Random random = new Random();
        int numeroAleatorio = 0;
        if(total != numeroAleatorio) {
            numeroAleatorio = random.nextInt(total);
        }
        return numeroAleatorio;
    }

}