package br.com.usinasantafe.ppc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class TurnoActivity extends ActivityGeneric {

    private ListView lista;
    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turno);

        PPCContext = (PPCContext) getApplication();

        Button buttonRetTurno = (Button) findViewById(R.id.buttonRetTurno);

        listarTurno();

        buttonRetTurno.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(TurnoActivity.this, DataActivity.class);
                startActivity(it);
            }
        });

    }

    public void listarTurno(){

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("TURNO 1");
        itens.add("TURNO 2");
        itens.add("TURNO 3");

        AdapterList adapterList = new AdapterList(this, itens);
        lista = (ListView) findViewById(R.id.listaTurno);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub
                if(position == 0){

                    PPCContext.getCabecalhoVARTO().setTurno(1L);
                    Intent it = new Intent(TurnoActivity.this, SecaoActivity.class);
                    startActivity(it);

                }
                else if(position == 1){

                    PPCContext.getCabecalhoVARTO().setTurno(2L);
                    Intent it = new Intent(TurnoActivity.this, SecaoActivity.class);
                    startActivity(it);

                }
                else if(position == 2){

                    PPCContext.getCabecalhoVARTO().setTurno(3L);
                    Intent it = new Intent(TurnoActivity.this, SecaoActivity.class);
                    startActivity(it);
                }
            }

        });

    }

}
