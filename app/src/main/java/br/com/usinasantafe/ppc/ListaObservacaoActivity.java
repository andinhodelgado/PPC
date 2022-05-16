package br.com.usinasantafe.ppc;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.usinasantafe.ppc.to.tb.estaticas.ObservacaoESTTO;
import br.com.usinasantafe.ppc.to.tb.variaveis.AmostraVARTO;

public class ListaObservacaoActivity extends ActivityGeneric {

    private PPCContext PPCContext;
    private ListView lista;
    private List listaDados;
    private ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_observacao);

        PPCContext = (PPCContext) getApplication();

        Button buttonSalvarListObsv = (Button) findViewById(R.id.buttonSalvarListaObservacao);
        Button buttonCancListObsv = (Button) findViewById(R.id.buttonCancListaObservacao);

        listarObservacoes();

        buttonSalvarListObsv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String observacao = "";
                SparseBooleanArray checked = lista.getCheckedItemPositions();

                if(checked.size() == 0){
                    observacao = "null";
                }
                else{
                    for (int i = 0; i < checked.size(); i++) {
                        int position = checked.keyAt(i);
                        if (checked.valueAt(i)){
                            observacao = observacao + ". " + adaptador.getItem(position).toString() +  " .";
                        }
                    }
                }

                AmostraVARTO amostraVARTO = new AmostraVARTO();

                if(!amostraVARTO.hasElements()){
                    PPCContext.getAmostraVARTO().setId((long) 1);
                    PPCContext.getAmostraVARTO().setNum((long) 1);
                }
                else{
                    List listAmostra = amostraVARTO.orderBy("id", false);
                    if(listAmostra.size() == 0){
                        PPCContext.getAmostraVARTO().setId((long) 1);
                        PPCContext.getAmostraVARTO().setNum((long) 1);
                    }
                    else{
                        amostraVARTO = (AmostraVARTO) listAmostra.get(0);
                        Long codigo = amostraVARTO.getId() + 1;
                        PPCContext.getAmostraVARTO().setId(codigo);

                        listAmostra = amostraVARTO.get("idCabecalho", PPCContext.getAmostraVARTO().getIdCabecalho());
                        Long num = listAmostra.size() + 1L;
                        PPCContext.getAmostraVARTO().setNum(num);

                    }

                }

                PPCContext.getAmostraVARTO().setObsv(observacao);
                PPCContext.getAmostraVARTO().insert();

                Intent it = new Intent(ListaObservacaoActivity.this, MsgFecharAnaliseActivity.class);
                startActivity(it);
            }
        });

        buttonCancListObsv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
//                Intent it = new Intent(ListaObservacaoActivity.this, SoqueiraNumActivity.class);
//                startActivity(it);

                if(PPCContext.getTipoCabecalho() == 1L){
                    Intent it = new Intent(ListaObservacaoActivity.this, LascasActivity.class);
                    startActivity(it);
                }
                else if(PPCContext.getTipoCabecalho() == 2L){
                    Intent it = new Intent(ListaObservacaoActivity.this, PonteiroActivity.class);
                    startActivity(it);
                }

            }
        });

    }

    public void listarObservacoes(){

        ObservacaoESTTO observacaoESTTO = new ObservacaoESTTO();

        String[] itens;

        listaDados = observacaoESTTO.all();
        itens = new String[listaDados.size()];

        for(int i = 0; i < listaDados.size(); i++){
            observacaoESTTO = (ObservacaoESTTO) listaDados.get(i);
            itens[i] = String.valueOf(observacaoESTTO.getDescObservacao());
        }

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, itens);

        lista = (ListView) findViewById(R.id.listaObservacao);
        lista.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lista.setAdapter(adaptador);

    }

}
