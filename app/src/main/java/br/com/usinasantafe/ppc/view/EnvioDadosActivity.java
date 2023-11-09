package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;
import br.com.usinasantafe.ppc.util.ConexaoWeb;
import br.com.usinasantafe.ppc.util.EnvioDadosServ;

public class EnvioDadosActivity extends ActivityGeneric {

    private ProgressDialog progressBar;
    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio_dados);

        TextView textViewEnvioDados = findViewById(R.id.textViewEnvioDados);
        Button buttonSimEnvioDados = findViewById(R.id.buttonSimEnvioDados);
        Button buttonNaoEnvioDados = findViewById(R.id.buttonNaoEnvioDados);

        ppcContext = (PPCContext) getApplication();

        if(!ppcContext.getPerdaCTR().verifCabecFechado()){
            textViewEnvioDados.setText("NÃO CONTÉM ANALISE(S) PARA SEREM(S) REENVIADA(S).");
        } else {
            textViewEnvioDados.setText("CONTÉM " +  ppcContext.getPerdaCTR().countCabecFechado() + " ANALISE(S) PARA SEREM(S) REENVIADA(S).");
        }

        buttonSimEnvioDados.setOnClickListener(v -> {

            if(ppcContext.getPerdaCTR().verifCabecFechado()){

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(EnvioDadosActivity.this)) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ENVIANDO DADOS...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressBar.show();

                    EnvioDadosServ.getInstance().envioDados(EnvioDadosActivity.this, EnvioDadosActivity.class, progressBar);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(EnvioDadosActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", (dialog, which) -> {
                    });

                    alerta.show();
                }

            } else {

                AlertDialog.Builder alerta = new AlertDialog.Builder(EnvioDadosActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("NÃO CONTÉM ANALISE(S) PARA SEREM(S) REENVIADA(S).");
                alerta.setPositiveButton("OK", (dialog, which) -> {
                });

                alerta.show();

            }

        });

        buttonNaoEnvioDados.setOnClickListener(v -> {
            Intent it = new Intent(EnvioDadosActivity.this, MenuInicialActivity.class);
            startActivity(it);
        });

    }
}
