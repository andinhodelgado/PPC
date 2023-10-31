package br.com.usinasantafe.ppc.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class ListaObservacaoActivity extends ActivityGeneric {

    private PPCContext ppcContext;
    private ListView observListView;
    private AdapterListChoice adapterListChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_observacao);

        ppcContext = (PPCContext) getApplication();

        Button buttonSalvarListObsv = findViewById(R.id.buttonSalvarListaObservacao);
        Button buttonCancListObsv = findViewById(R.id.buttonCancListaObservacao);

        ArrayList<ViewHolderChoice> itens = new ArrayList<>();
        ViewHolderChoice viewHolderChoice = new ViewHolderChoice();
        viewHolderChoice.setSelected(false);
        viewHolderChoice.setDescrCheckBox("PEDRA");
        itens.add(viewHolderChoice);

        viewHolderChoice = new ViewHolderChoice();
        viewHolderChoice.setSelected(false);
        viewHolderChoice.setDescrCheckBox("TOCO DE ARVORE");
        itens.add(viewHolderChoice);

        viewHolderChoice = new ViewHolderChoice();
        viewHolderChoice.setSelected(false);
        viewHolderChoice.setDescrCheckBox("PLANTAS DANINHAS");
        itens.add(viewHolderChoice);

        viewHolderChoice = new ViewHolderChoice();
        viewHolderChoice.setSelected(false);
        viewHolderChoice.setDescrCheckBox("FORMIGUEIROS");
        itens.add(viewHolderChoice);

        adapterListChoice = new AdapterListChoice(this, itens);
        observListView = (ListView) findViewById(R.id.observListView);
        observListView.setAdapter(adapterListChoice);

        buttonSalvarListObsv.setOnClickListener(v -> {

            ViewHolderChoice view = itens.get(0);
            if(view.isSelected()){
                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setPedraAmostra(1L);
            } else {
                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setPedraAmostra(0L);
            }

            view = itens.get(1);
            if(view.isSelected()){
                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setTocoArvoreAmostra(1L);
            } else {
                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setTocoArvoreAmostra(0L);
            }

            view = itens.get(2);
            if(view.isSelected()){
                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setPlantaDaninhasAmostra(1L);
            } else {
                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setPlantaDaninhasAmostra(0L);
            }

            view = itens.get(3);
            if(view.isSelected()){
                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setFormigueiroAmostra(1L);
            } else {
                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setFormigueiroAmostra(0L);
            }

            ppcContext.getPerdaCTR().salvarAmostra();

            Intent it = new Intent(ListaObservacaoActivity.this, ListaAmostraActivity.class);
            startActivity(it);
            finish();

        });

        buttonCancListObsv.setOnClickListener(v -> {

            if (ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().getTipoColheitaCabec() == 1L) {
                Intent it = new Intent(ListaObservacaoActivity.this, LascasActivity.class);
                startActivity(it);
                finish();
            } else {
                Intent it = new Intent(ListaObservacaoActivity.this, PonteiroActivity.class);
                startActivity(it);
                finish();
            }

        });

    }

    public void onBackPressed() {
    }
}
