package br.com.usinasantafe.ppc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.usinasantafe.ppc.to.tb.estaticas.AuditorESTTO;

public class PrincipalActivity extends ActivityGeneric {

    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        listarMenuInicial();

    }

    public void listarMenuInicial(){

        ArrayList<String> itens = new ArrayList<String>();

        itens.add("APONTAMENTO");
        itens.add("CONFIGURAÇÃO");
        itens.add("REENVIAR DADOS");
        itens.add("SAIR");

        AdapterList adapterList = new AdapterList(this, itens);
        lista = findViewById(R.id.listaMenuInicial);
        lista.setAdapter(adapterList);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> l, View v, int position,
                                    long id) {
                // TODO Auto-generated method stub
                if(position == 0){

                    AuditorESTTO auditorESTTO = new AuditorESTTO();
                    if(auditorESTTO.hasElements()){
                        Intent it = new Intent(PrincipalActivity.this, ListaTipoApontActivity.class);
                        startActivity(it);
                    }

                }
                else if(position == 1){

                    Intent it = new Intent(PrincipalActivity.this, ConfiguracoesActivity.class);
                    startActivity(it);

                }
                else if(position == 2){

                    Intent it = new Intent(PrincipalActivity.this, EnvioDadosActivity.class);
                    startActivity(it);

                }
                else if(position == 3){

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
                else if(position == 4){

                    Intent it = new Intent(PrincipalActivity.this, CameraActivity.class);
                    startActivity(it);

                }
            }

        });

    }

}
