package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.Objects;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;
import br.com.usinasantafe.ppc.util.ConexaoWeb;

public class Auditor3Activity extends ActivityGeneric {

    private PPCContext ppcContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditor3);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkAuditor = findViewById(R.id.buttonOkPadrao);
        Button buttonCancAuditor = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonOkAuditor.setOnClickListener(v -> {

            if(!editTextPadrao.getText().toString().equals("")){

                Long auditor = Long.parseLong(editTextPadrao.getText().toString());

                if((!Objects.equals(ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().getMatricAuditor1Cabec(), auditor))
                        && (!Objects.equals(ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().getMatricAuditor2Cabec(), auditor))){

                    if(ppcContext.getPerdaCTR().verifAuditor(Long.parseLong(editTextPadrao.getText().toString()))) {

                        ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setMatricAuditor3Cabec(Long.parseLong(editTextPadrao.getText().toString()));
                        Intent it = new Intent(Auditor3Activity.this, DataActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(Auditor3Activity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("AUDITOR INEXISTENTE!");

                        alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                        alerta.show();

                    }

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(Auditor3Activity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("AUDITOR JÁ FOI INSERIDO ANTERIORMENTE. VERIFIQUE A MATRICULA DE AUDITOR.");

                    alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                    alerta.show();

                }

            } else {

                ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setMatricAuditor3Cabec(0L);
                Intent it = new Intent(Auditor3Activity.this, DataActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonCancAuditor.setOnClickListener(v -> {
            if(editTextPadrao.getText().toString().length() > 0){
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

        buttonAtualPadrao.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder( Auditor3Activity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(Auditor3Activity.this)) {

                    progressBar = new ProgressDialog(Auditor3Activity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    ppcContext.getConfigCTR().atualDados(Auditor3Activity.this, Auditor3Activity.class, progressBar, "Auditor");

                } else {

                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( Auditor3Activity.this);
                    alerta1.setTitle("ATENÇÃO");
                    alerta1.setMessage("FALHA NA CONEXÃO DE DADOS. O CELULAR ESTA SEM SINAL. POR FAVOR, TENTE NOVAMENTE QUANDO O CELULAR ESTIVE COM SINAL.");
                    alerta1.setPositiveButton("OK", (dialog1, which1) -> {
                    });
                    alerta1.show();

                }


            });

            alerta.setPositiveButton("NÃO", (dialog, which) -> {});
            alerta.show();

        });

    }

    public void onBackPressed() {
        Intent it = new Intent(Auditor3Activity.this, Auditor2Activity.class);
        startActivity(it);
        finish();
    }

}
