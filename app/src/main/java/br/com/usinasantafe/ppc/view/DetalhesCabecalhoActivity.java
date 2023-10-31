package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;
import br.com.usinasantafe.ppc.model.bean.variaveis.CabecalhoBean;

public class DetalhesCabecalhoActivity extends ActivityGeneric {

    private PPCContext ppcContext;
    private CabecalhoBean cabecalhoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_cabecalho);

        ppcContext = (PPCContext) getApplication();

        Button buttonInserirAmostra = findViewById(R.id.buttonInserirAmostra);
        Button buttonFecharCabecalho = findViewById(R.id.buttonFecharCabecalho);
        Button buttonExcluirCabecalho = findViewById(R.id.buttonExcluirCabecalho);
        Button buttonRetDetalheCabecalho = findViewById(R.id.buttonRetDetalheCabecalho);

        TextView textViewCabecalho = findViewById(R.id.textViewDetalheCabecalho);

        cabecalhoBean = ppcContext.getPerdaCTR().getCabecDAO().getCabecBean();

        String amostra;

        if(!ppcContext.getPerdaCTR().verifAmostraIdCabec(cabecalhoBean.getIdCabec())){
            amostra = "NÃO CONTÉM AMOSTRAS.";
        } else {
            amostra = "CONTÉM " + ppcContext.getPerdaCTR().qtdeAmostra(cabecalhoBean.getIdCabec()) + " AMOSTRA(S).";
        }

        String msgCabecalho = "TURNO: " + cabecalhoBean.getNroTurnoCabec() + " \n" +
                "FRENTE: " + cabecalhoBean.getCodFrenteCabec() + " \n" +
                "COLHEDORA: " + cabecalhoBean.getCodColhedoraCabec() + " \n" +
                "MOTORISTA:  " + cabecalhoBean.getMatricOperadorCabec() + " \n" +
                amostra;

        textViewCabecalho.setText(msgCabecalho);

        buttonInserirAmostra.setOnClickListener(v -> {
            Intent it = new Intent(DetalhesCabecalhoActivity.this, ListaAmostraActivity.class);
            startActivity(it);
            finish();
        });

        buttonFecharCabecalho.setOnClickListener(v -> {

            int countAmostra = ppcContext.getPerdaCTR().countAmostraList(cabecalhoBean.getIdCabec());

            AlertDialog.Builder alerta = new AlertDialog.Builder(DetalhesCabecalhoActivity.this);
            alerta.setTitle("ATENÇÃO");

            if(countAmostra >= 1){

                alerta.setMessage("DESEJA REALMENTE FINALIZAR A ANALISE?");

                alerta.setPositiveButton("SIM", (dialog, which) -> {

                    ppcContext.getPerdaCTR().fecharAnalise(cabecalhoBean.getIdCabec());
                    Intent it = new Intent(DetalhesCabecalhoActivity.this, ListaCabecalhoActivity.class);
                    startActivity(it);
                    finish();

                });

                alerta.setNegativeButton("NÃO", (dialog, which) -> {
                });

            } else {

                alerta.setMessage("POR FAVOR, INSIRA PELO MENOS 1 AMOSTRA NA ANALISE PARA QUE POSSA FINALIZAR A MESMA.");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });

            }
            alerta.show();

        });

        buttonExcluirCabecalho.setOnClickListener(v -> {
            AlertDialog.Builder alerta = new AlertDialog.Builder(DetalhesCabecalhoActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE EXCLUIR A ANALISE?");

            alerta.setPositiveButton("SIM", (dialog, which) -> {

                ppcContext.getPerdaCTR().deletarAnalise(cabecalhoBean.getIdCabec());
                Intent it = new Intent(DetalhesCabecalhoActivity.this, DetalhesCabecalhoActivity.class);
                startActivity(it);
                finish();

            });

            alerta.setNegativeButton("NÃO", (dialog, which) -> {
            });

            alerta.show();
        });

        buttonRetDetalheCabecalho.setOnClickListener(v -> {
            Intent it = new Intent(DetalhesCabecalhoActivity.this, ListaCabecalhoActivity.class);
            startActivity(it);
            finish();
        });


    }

    public void onBackPressed() {
    }
}
