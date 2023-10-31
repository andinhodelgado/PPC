package br.com.usinasantafe.ppc.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class ListaTurnoActivity extends ActivityGeneric {

    private ListView turnoListView;
    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_turno);

        ppcContext = (PPCContext) getApplication();

        Button buttonRetTurno = findViewById(R.id.buttonRetTurno);

        ArrayList<String> itens = new ArrayList<>();

        itens.add("TURNO 1");
        itens.add("TURNO 2");
        itens.add("TURNO 3");

        AdapterList adapterList = new AdapterList(this, itens);
        turnoListView = findViewById(R.id.turnoListView);
        turnoListView.setAdapter(adapterList);

        turnoListView.setOnItemClickListener((l, v, position, id) -> {

            if(position == 0){
                ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setNroTurnoCabec(1L);
            } else if(position == 1){
                ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setNroTurnoCabec(2L);
            } else if(position == 2){
                ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setNroTurnoCabec(3L);
            }

            Intent it = new Intent(ListaTurnoActivity.this, SecaoActivity.class);
            startActivity(it);
            finish();

        });

        buttonRetTurno.setOnClickListener(v -> {
            Intent it = new Intent(ListaTurnoActivity.this, DataActivity.class);
            startActivity(it);
            finish();
        });

    }

}
