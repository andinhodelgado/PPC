package br.com.usinasantafe.ppc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaTipoApontActivity extends Activity {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tipo_apont);

        Button buttonRetTipoApont = findViewById(R.id.buttonRetTipoApont);

        listarTipoApont();

        buttonRetTipoApont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaTipoApontActivity.this, PrincipalActivity.class);
                startActivity(it);
            }
        });

    }

    public void listarTipoApont(){

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("CABEÇALHO");
        itens.add("AMOSTRA");

        AdapterList adapterList = new AdapterList(this, itens);
        lista = findViewById(R.id.listaTipoApont);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                if(position == 0){
                    Intent it = new Intent(ListaTipoApontActivity.this, ListaCabecalhoActivity.class);
                    startActivity(it);
                    finish();
                }
                else if(position == 1){
                    Intent it = new Intent(ListaTipoApontActivity.this, ListaCabecAmostraActivity.class);
                    startActivity(it);
                    finish();
                }
            }

        });

    }

}
