package br.com.danielsilvalima.sorteador;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import br.com.danielsilvalima.sorteador.controllers.TabAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);

        // Configurar o adapter
        TabAdapter adapter = new TabAdapter(this);
        viewPager.setAdapter(adapter);

        // Sincronizar o TabLayout com o ViewPager2
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText(R.string.jogadores);
                    break;
                case 1:
                    tab.setText(R.string.times);
                    break;
                default:
                    tab.setText("Sortear");
                    break;
            }
        }).attach();
    }
}