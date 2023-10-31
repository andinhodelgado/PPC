package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;
import br.com.usinasantafe.ppc.model.bean.variaveis.AmostraBean;
import br.com.usinasantafe.ppc.model.bean.variaveis.CabecalhoBean;

public class ListaAmostraActivity extends ActivityGeneric {

    private ListView amostraListView;
    private PPCContext ppcContext;
    private List<AmostraBean> amostraList;
    private CabecalhoBean cabecalhoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_amostra);

        ppcContext = (PPCContext) getApplication();

        Button buttonRetListaAmostra = findViewById(R.id.buttonRetListaAmostra);
        Button buttonInserirAmostra = findViewById(R.id.buttonInserirAmostra);

        ArrayList<String> itens = new ArrayList<>();

        cabecalhoBean = ppcContext.getPerdaCTR().getCabecDAO().getCabecBean();
        amostraList = ppcContext.getPerdaCTR().getAmostraList(cabecalhoBean.getIdCabec());

        if(amostraList.size() > 0) {
            itens.add("NÃO CONTÉM AMOSTRA(S) NESSA ANALISE.");
        } else {
            for(int i = 0; i < amostraList.size(); i++){
                itens.add("Amostra " + (i + 1));
            }
        }

        AdapterList adapterList = new AdapterList(this, itens);
        amostraListView = findViewById(R.id.amostraListView);
        amostraListView.setAdapter(adapterList);

        amostraListView.setOnItemClickListener((l, v, position, id) -> {

            if(amostraList.size() > 0){

                AlertDialog.Builder alerta = new AlertDialog.Builder(ListaAmostraActivity.this);
                alerta.setTitle("ATENÇÃO");

                alerta.setMessage("DESEJA REALMENTE EXCLUIR A AMOSTRA?");

                alerta.setPositiveButton("SIM", (dialog, which) -> {

                    AmostraBean amostraBean = amostraList.get(position);
                    ppcContext.getPerdaCTR().delAmostraId(amostraBean.getIdAmostra());

                    Intent it = new Intent(ListaAmostraActivity.this, ListaAmostraActivity.class);
                    startActivity(it);
                    finish();

                });

                alerta.setNegativeButton("NÃO", (dialog, which) -> {
                });

            }

        });

        buttonInserirAmostra.setOnClickListener(v -> {
            Intent it = new Intent(ListaAmostraActivity.this, TaraActivity.class);
            startActivity(it);
            finish();
        });

        buttonRetListaAmostra.setOnClickListener(v -> {
            Intent it = new Intent(ListaAmostraActivity.this, DetalhesCabecalhoActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }

}
