package com.example.mylist;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class listaActivity extends AppCompatActivity {

    private EditText editPesquisar;
    private TextView btnFeito;
    private RecyclerView recyclerProdutos;
    private List<String> listaSugestoes = new ArrayList<>();
    private ProdutoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        editPesquisar = findViewById(R.id.editPesquisar);
        btnFeito = findViewById(R.id.btnFeito);
        recyclerProdutos = findViewById(R.id.recyclerProdutos);

        adapter = new ProdutoAdapter(listaSugestoes);
        recyclerProdutos.setLayoutManager(new LinearLayoutManager(this));
        recyclerProdutos.setHasFixedSize(true);
        recyclerProdutos.setAdapter(adapter);

        btnFeito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Volta para a tela principal
            }
        });

        editPesquisar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                String itemDigitado = editPesquisar.getText().toString();

                if (!itemDigitado.isEmpty()) {
                    listaSugestoes.add(0, itemDigitado);
                    adapter.notifyItemInserted(0); // Avisa o adapter para atualizar a tela
                    recyclerProdutos.scrollToPosition(0); // Rola para o topo para ver o item

                    Toast.makeText(listaActivity.this, "Adicionado: " + itemDigitado, Toast.LENGTH_SHORT).show();
                    editPesquisar.setText("");
                }
                return true;
            }
        });
    }
}
