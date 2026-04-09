package com.example.mylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.MyViewHolder> {

    private List<String> listaProdutos;

    public ProdutoAdapter(List<String> lista) {
        this.listaProdutos = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_produto, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.nome.setText(listaProdutos.get(position));

        // Reinicia a quantidade para 0 ao carregar o item (evita erros de reciclagem visual)
        holder.txtQuantidade.setText("0");
        holder.status.setVisibility(View.GONE);

        // Lógica do botão MAIS (+)
        holder.btnMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qtd = Integer.parseInt(holder.txtQuantidade.getText().toString());
                qtd++;
                holder.txtQuantidade.setText(String.valueOf(qtd));

                // Se a quantidade for maior que 0, mostra "Adicionado"
                if (qtd > 0) {
                    holder.status.setVisibility(View.VISIBLE);
                }
            }
        });

        // Lógica do botão MENOS (-)
        holder.btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qtd = Integer.parseInt(holder.txtQuantidade.getText().toString());
                if (qtd > 0) {
                    qtd--;
                    holder.txtQuantidade.setText(String.valueOf(qtd));
                }

                // Se voltar a 0, esconde o "Adicionado"
                if (qtd == 0) {
                    holder.status.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaProdutos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome, status, txtQuantidade;
        Button btnMais, btnMenos;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txtNomeProduto);
            status = itemView.findViewById(R.id.txtStatus);
            txtQuantidade = itemView.findViewById(R.id.txtQuantidade);
            btnMais = itemView.findViewById(R.id.btnMais);
            btnMenos = itemView.findViewById(R.id.btnMenos);
        }
    }
}
