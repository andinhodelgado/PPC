package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppc.to.tb.variaveis.AmostraVARTO;
import br.com.usinasantafe.ppc.to.tb.variaveis.CabecalhoVARTO;

public class ListaCabecAmostraActivity extends ActivityGeneric {

    private ListView lista;
    private List listaDados;
    private int qtdeCabecalho;
    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cabec_amostra);

        PPCContext = (PPCContext) getApplication();

        Button buttonRetListaCabecAmostra = (Button) findViewById(R.id.buttonRetListaCabecAmostra);

        listarCabecAmostra();

        buttonRetListaCabecAmostra.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent it = new Intent(ListaCabecAmostraActivity.this, ListaTipoApontActivity.class);
                startActivity(it);
                finish();

            }

        });

    }

    public void listarCabecAmostra(){

        CabecalhoVARTO cabecalhoVARTO = new CabecalhoVARTO();

        ArrayList<String> itens = new ArrayList<String>();

        if(!cabecalhoVARTO.hasElements()){
            itens.add("NÃO CONTÉM CABEÇALHO(S) CADASTRADO.");
        }
        else{

            listaDados = cabecalhoVARTO.get("status", 1L);
            qtdeCabecalho = listaDados.size();

            if(qtdeCabecalho == 0){
                itens.add("NÃO CONTÉM CABEÇALHO(S) CADASTRADO.");
            }
            else{
                for(int i = 0; i < listaDados.size(); i++){
                    cabecalhoVARTO = (CabecalhoVARTO) listaDados.get(i);
                    itens.add(String.valueOf(cabecalhoVARTO.getColhedora()));
                }
            }
        }

        AdapterList adapterList = new AdapterList(this, itens);
        lista = findViewById(R.id.listaCabecAmostra);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {

                if(qtdeCabecalho > 0){

                    CabecalhoVARTO cabecalhoVARTO = (CabecalhoVARTO) listaDados.get(position);
                    PPCContext.getAmostraVARTO().setIdCabecalho(cabecalhoVARTO.getId());
                    PPCContext.setTipoCabecalho(cabecalhoVARTO.getTipo());

                    AmostraVARTO amostraVARTO = new AmostraVARTO();
                    int qtde = amostraVARTO.get("idCabecalho", cabecalhoVARTO.getId()).size();

                    if(qtde < 2){
                        Intent it = new Intent(ListaCabecAmostraActivity.this, CabecalhoActivity.class);
                        startActivity(it);
                    }
                    else if(qtde == 2){

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ListaCabecAmostraActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("DESEJA INSERIR OUTRA AMOSTRA OU FINALIZAR A ANALISE?");

                        alerta.setPositiveButton("INSERIR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent it = new Intent(ListaCabecAmostraActivity.this, CabecalhoActivity.class);
                                startActivity(it);
                                finish();

                            }
                        });

                        alerta.setNegativeButton("FINALIZAR", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent it = new Intent(ListaCabecAmostraActivity.this, MsgFecharAnaliseActivity.class);
                                startActivity(it);
                                finish();

                            }
                        });

                        alerta.show();


                    }
                    else if(qtde == 3){

                        Intent it = new Intent(ListaCabecAmostraActivity.this, MsgFecharAnaliseActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }

        });

    }

}
