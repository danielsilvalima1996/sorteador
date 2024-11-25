package br.com.danielsilvalima.sorteador.controllers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import br.com.danielsilvalima.sorteador.R;
import br.com.danielsilvalima.sorteador.interfaces.OnActionListener;

public class Times extends Fragment {

    private static final String PREFS_NAME = "TimesPrefs";
    private static final String KEY_TIMES = "times";

    private List<String> timesList;
    private TimesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedIntanceState) {
        View view = inflater.inflate(R.layout.times, container, false);

        timesList = carregarTimes();
        TextInputEditText inputTime = view.findViewById(R.id.inputTime);
        Button btnAddTime = view.findViewById(R.id.btnAddTime);
        RecyclerView recyclerTimes = view.findViewById(R.id.recyclerTimes);
        TextView txtQuantidade = view.findViewById(R.id.quantidadeTimes);

        adapter = new TimesAdapter(timesList, new OnActionListener() {

            @Override
            public void onRemove(int position) {
                timesList.remove(position);
                adapter.notifyItemRemoved(position);
                salvarTimes();
                txtQuantidade.setText(getString(R.string.quantidade_de_times, timesList.size()));
            }
        });

        recyclerTimes.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerTimes.setAdapter(adapter);

        btnAddTime.setOnClickListener(v -> {
            String time = inputTime.getText().toString().trim();
            if(!time.isEmpty()) {
                timesList.add(time);
                inputTime.setText("");
                salvarTimes();
                adapter.notifyItemInserted(timesList.size() - 1);
            }

            txtQuantidade.setText(getString(R.string.quantidade_de_times, timesList.size()));

        });

        txtQuantidade.setText(getString(R.string.quantidade_de_times, timesList.size()));

        return view;
    }

    private void salvarTimes() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> timesSet = new HashSet<>(timesList);
        editor.putStringSet(KEY_TIMES, timesSet);
        editor.apply();
    }

    private List<String> carregarTimes() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Set<String> timesSet = sharedPreferences.getStringSet(KEY_TIMES, new HashSet<>());
        return new ArrayList<>(timesSet);
    }

}