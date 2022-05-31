package br.com.usinasantafe.ppc;

import android.app.Activity;
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

public class ListaCabecalhoActivity extends ActivityGeneric {

    private ListView lista;
    private List listaDados;
    private int qtdeCabecalho;
    private int posicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cabecalho);

        Button buttonInsListaCabecalho = (Button) findViewById(R.id.buttonInsListaCabecalho);
        Button buttonVoltaListaCabecalho = (Button) findViewById(R.id.buttonVoltaListaCabecalho);

        listarCabecalho();

        buttonInsListaCabecalho.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaCabecalhoActivity.this, ListaMenuActivity.class);
                startActivity(it);
                finish();
            }

        });

        buttonVoltaListaCabecalho.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent it = new Intent(ListaCabecalhoActivity.this, ListaTipoApontActivity.class);
                startActivity(it);
                finish();
            }
        });

    }

    public void listarCabecalho(){

        CabecalhoVARTO cabecalhoVARTO = new CabecalhoVARTO();

        ArrayList<String> itens = new ArrayList<String>();
        qtdeCabecalho = 0;

        if(!cabecalhoVARTO.hasElements()){
            itens.add("NÃO CONTÉM CABEÇALHO CADASTRADO.");
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
        lista = findViewById(R.id.listaCabecalho);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                CabecalhoVARTO cabecalhoVARTO = new CabecalhoVARTO();
                int qtde = cabecalhoVARTO.get("status", 1L).size();

                if(qtde > 0){

                    posicao = position;

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ListaCabecalhoActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("DESEJA EXCLUIR A ANALISE?");

                    alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            CabecalhoVARTO cabecalhoVARTO = (CabecalhoVARTO) listaDados.get(posicao);
                            AmostraVARTO amostraVARTO = new AmostraVARTO();
                            amostraVARTO.delete("idCabecalho", cabecalhoVARTO.getId());
                            cabecalhoVARTO.delete();

                            Intent it = new Intent(ListaCabecalhoActivity.this, ListaCabecalhoActivity.class);
                            startActivity(it);
                            finish();

                        }

                    });

                    alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alerta.show();

                }

            }

        });

    }

}
