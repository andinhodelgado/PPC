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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.usinasantafe.ppc.bo.ConexaoWeb;
import br.com.usinasantafe.ppc.bo.ManipDadosEnvio;
import br.com.usinasantafe.ppc.to.tb.variaveis.AmostraVARTO;
import br.com.usinasantafe.ppc.to.tb.variaveis.CabecalhoVARTO;

public class MsgFecharAnaliseActivity extends Activity {

    private PPCContext PPCContext;
    private ProgressDialog progressBar;
    private List listAmostra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_fechar_analise);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkMsgFecharAnalise = (Button) findViewById(R.id.buttonOKMsgFecharAnalise);
        Button buttonCancMsgFecharAnalise = (Button) findViewById(R.id.buttonCancMsgFecharAnalise);

        TextView textViewMsgFecharAnalise = (TextView) findViewById(R.id.textViewMsgFecharAnalise);

        AmostraVARTO amostraVARTO = new AmostraVARTO();
        listAmostra = amostraVARTO.get("idCabecalho", PPCContext.getAmostraVARTO().getIdCabecalho());

        String msg = "DESEJA FINALIZAR A ANALISE COM " + listAmostra.size() + " AMOSTRA(S).";

        textViewMsgFecharAnalise.setText(msg);

        buttonOkMsgFecharAnalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CabecalhoVARTO cabecalhoVARTO = new CabecalhoVARTO();

                List listaCabecalho = cabecalhoVARTO.get("id", PPCContext.getAmostraVARTO().getIdCabecalho());
                cabecalhoVARTO = (CabecalhoVARTO) listaCabecalho.get(0);
                cabecalhoVARTO.setStatus(2L);
                cabecalhoVARTO.setDhEnvio(data());
                cabecalhoVARTO.update();

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(MsgFecharAnaliseActivity.this)) {

                    progressBar = new ProgressDialog(v.getContext());
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ENVIANDO DADOS...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressBar.show();

                    ManipDadosEnvio.getInstance().setContext(MsgFecharAnaliseActivity.this, progressBar, 1);
                    ManipDadosEnvio.getInstance().enviarDados();

                } else {
                    AlertDialog.Builder alerta = new AlertDialog.Builder(MsgFecharAnaliseActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent it = new Intent(MsgFecharAnaliseActivity.this, ListaTipoApontActivity.class);
                            startActivity(it);
                        }
                    });

                    alerta.show();
                }

            }
        });

        buttonCancMsgFecharAnalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MsgFecharAnaliseActivity.this, ListaTipoApontActivity.class);
                startActivity(it);
            }
        });

    }

    public String data(){

        String dataCerta = "";

        Calendar cal = Calendar.getInstance();
        Date date = new Date();

        cal.setTime(date);

        int mes = cal.get(Calendar.MONTH);
        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int ano = cal.get(Calendar.YEAR);
        int horas = cal.get(Calendar.HOUR_OF_DAY);
        int minutos = cal.get(Calendar.MINUTE);

        dataCerta = ""+dia+"/"+(mes+1)+"/"+ano+" "+horas+":"+minutos;

        return dataCerta;

    }

    public ProgressDialog getProgressBar() {
        return progressBar;
    }

}
