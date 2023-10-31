package br.com.usinasantafe.ppc.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class ListaTipoColheitaActivity extends Activity {

    private ListView tipoColheitaListaView;
    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_tipo_colheita);

        ppcContext = (PPCContext) getApplication();

        Button buttonRetListaTipoColheita = findViewById(R.id.buttonRetListaTipoColheita);

        ArrayList<String> itens = new ArrayList<>();

        itens.add("MECANIZADA");
        itens.add("MANUAL");

        AdapterList adapterList = new AdapterList(this, itens);
        tipoColheitaListaView = findViewById(R.id.tipoColheitaListaView);
        tipoColheitaListaView.setAdapter(adapterList);

        tipoColheitaListaView.setOnItemClickListener((l, v, position, id) -> {
            if(position == 0){
                ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setTipoColheitaCabec(1L);
            } else if(position == 1){
                ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setTipoColheitaCabec(2L);
            }

            Intent it = new Intent(ListaTipoColheitaActivity.this, Auditor1Activity.class);
            startActivity(it);
            finish();
        });

        buttonRetListaTipoColheita.setOnClickListener(v -> {
            Intent it = new Intent(ListaTipoColheitaActivity.this, ListaCabecalhoActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }
}
