package br.com.usinasantafe.ppc.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;
import br.com.usinasantafe.ppc.model.bean.variaveis.CabecalhoBean;

public class ListaCabecalhoActivity extends ActivityGeneric {

    private ListView cabecalhoListView;
    private List<CabecalhoBean> cabecList;
    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cabecalho);

        ppcContext = (PPCContext) getApplication();

        Button buttonInserirListaCabecalho = findViewById(R.id.buttonInserirCabecalho);
        Button buttonRetListaCabecalho = findViewById(R.id.buttonRetListaCabecalho);

        ArrayList<String> itens = new ArrayList<>();

        cabecList = ppcContext.getPerdaCTR().cabecAbertoList();

        if(cabecList.size() == 0) {

            itens.add("NÃO CONTÉM CABEÇALHO CADASTRADO.");

        } else {

            cabecList = ppcContext.getPerdaCTR().cabecAbertoList();

            for(CabecalhoBean cabecalhoBean : cabecList) {
                itens.add("COLHEDORA " + cabecalhoBean.getNroColhedoraCabec());
            }

        }

        AdapterList adapterList = new AdapterList(this, itens);
        cabecalhoListView = findViewById(R.id.cabecalhoListView);
        cabecalhoListView.setAdapter(adapterList);

        cabecalhoListView.setOnItemClickListener((l, v, position, id) -> {

            int pos = position;
            if(cabecList.size() > 0){

                ppcContext.getPerdaCTR().getCabecDAO().setCabecBean(cabecList.get(pos));
                cabecList.clear();

                Intent it = new Intent(ListaCabecalhoActivity.this, DetalhesCabecalhoActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonInserirListaCabecalho.setOnClickListener(v -> {
            Intent it = new Intent(ListaCabecalhoActivity.this, ListaTipoColheitaActivity.class);
            startActivity(it);
            finish();
        });

        buttonRetListaCabecalho.setOnClickListener(v -> {
            Intent it = new Intent(ListaCabecalhoActivity.this, MenuInicialActivity.class);
            startActivity(it);
            finish();
        });

    }

    public void onBackPressed() {
    }

}
