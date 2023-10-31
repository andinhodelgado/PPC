package br.com.usinasantafe.ppc.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.usinasantafe.ppc.BuildConfig;
import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class MenuInicialActivity extends ActivityGeneric {

    private ListView menuInicialListView;
    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicial);
        TextView textViewPrincipal = findViewById(R.id.textViewPrincipal);

        textViewPrincipal.setText("PRINCIPAL - V " + BuildConfig.VERSION_NAME);

        ppcContext = (PPCContext) getApplication();

        ArrayList<String> itens = new ArrayList<>();

        itens.add("APONTAMENTO");
        itens.add("CONFIGURAÇÃO");
        itens.add("REENVIAR DADOS");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        menuInicialListView = findViewById(R.id.menuInicialListView);
        menuInicialListView.setAdapter(adapterList);

        menuInicialListView.setOnItemClickListener((l, v, position, id) -> {

            TextView textView = v.findViewById(R.id.textViewItemList);
            String text = textView.getText().toString();

            Intent it;
            switch (text) {
                case "APONTAMENTO": {
                    if(ppcContext.getPerdaCTR().hasElemAuditor()){
                        it = new Intent(MenuInicialActivity.this, ListaCabecalhoActivity.class);
                        startActivity(it);
                        finish();
                    }
                    break;
                }
                case "CONFIGURAÇÃO": {
                    it = new Intent(MenuInicialActivity.this, ConfigActivity.class);
                    startActivity(it);
                    finish();
                    break;
                }
                case "REENVIAR DADOS": {
                    it = new Intent(MenuInicialActivity.this, EnvioDadosActivity.class);
                    startActivity(it);
                    finish();
                }
                case "SAIR": {
                    it = new Intent(Intent.ACTION_MAIN);
                    it.addCategory(Intent.CATEGORY_HOME);
                    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(it);
                    break;
                }
            }

        });

    }

    public void onBackPressed() {
    }

}
