package br.com.usinasantafe.ppc;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import br.com.usinasantafe.ppc.bo.ConexaoWeb;
import br.com.usinasantafe.ppc.bo.ManipDadosEnvio;
import br.com.usinasantafe.ppc.to.tb.variaveis.CabecalhoVARTO;

public class EnvioDadosActivity extends Activity {

    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_envio_dados);

        TextView textViewEnvioDados = (TextView) findViewById(R.id.textViewEnvioDados);
        Button buttonSimEnvioDados = (Button) findViewById(R.id.buttonSimEnvioDados);
        Button buttonNaoEnvioDados = (Button) findViewById(R.id.buttonNaoEnvioDados);

        CabecalhoVARTO cabecalhoVARTO = new CabecalhoVARTO();
        int qtde = cabecalhoVARTO.get("status", 2L).size();

        if(qtde == 0){
            textViewEnvioDados.setText("NÃO CONTÉM ANALISE(S) PARA SEREM(S) REENVIADA(S).");
        }
        else{
            textViewEnvioDados.setText("CONTÉM " + qtde + " ANALISE(S) PARA SEREM(S) REENVIADA(S).");
        }

        buttonSimEnvioDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(EnvioDadosActivity.this)) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ENVIANDO DADOS...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressBar.show();

                    ManipDadosEnvio.getInstance().setContext(EnvioDadosActivity.this, progressBar, 2);
                    ManipDadosEnvio.getInstance().enviarDados();

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(EnvioDadosActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    alerta.show();
                }


            }
        });

        buttonNaoEnvioDados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(EnvioDadosActivity.this, PrincipalActivity.class);
                startActivity(it);
            }
        });

    }
}
