package com.example.tiendalistoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    private boolean isChatOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabChat = findViewById(R.id.fabChat);
        LinearLayout chatBotLayout = findViewById(R.id.chatBotLayout);
        TextView chatBotResponse = findViewById(R.id.chatBotResponse);
        Button btnPregunta1 = findViewById(R.id.btnPregunta1);
        Button btnPregunta2 = findViewById(R.id.btnPregunta2);
        Button btnPregunta3 = findViewById(R.id.btnPregunta3);

        // Botón flotante para abrir/cerrar el chatbot
        fabChat.setOnClickListener(v -> {
            if (!isChatOpen) {
                chatBotLayout.setVisibility(View.VISIBLE);
                fabChat.setImageResource(android.R.drawable.ic_menu_close_clear_cancel);
                isChatOpen = true;
            } else {
                chatBotLayout.setVisibility(View.GONE);
                fabChat.setImageResource(android.R.drawable.ic_dialog_email);
                isChatOpen = false;
            }
        });

        btnPregunta1.setOnClickListener(v -> {
            chatBotResponse.setText("Puedes encontrar promociones en la pantalla principal o sección de catálogo. ¡Se actualizan cada semana!");
        });

        btnPregunta2.setOnClickListener(v -> {
            chatBotResponse.setText("Nuestro horario es de lunes a sábado de 8:00 a. m. a 9:00 p. m.");
        });

        btnPregunta3.setOnClickListener(v -> {
            chatBotResponse.setText("Aceptamos pagos en efectivo, Yape, Plin y tarjetas.");
        });

        // Manejo de la barra de navegación inferior
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectedFragment = new TiendaFragment();
                    break;
                case R.id.nav_categorias:
                    selectedFragment = new CategoriasFragment();
                    break;
                case R.id.nav_promos:
                    selectedFragment = new PromosFragment();
                    break;
                case R.id.nav_pedidos:
                    selectedFragment = new PedidosFragment();
                    break;
                case R.id.nav_cuenta:
                    selectedFragment = new CuentaFragment();
                    break;
            }

            if (selectedFragment != null) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                        .commit();
                return true;
            }
            return false;
        });

        // Mostrar TiendaFragment por defecto al iniciar
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, new TiendaFragment())
                    .commit();
        }
    }
}
