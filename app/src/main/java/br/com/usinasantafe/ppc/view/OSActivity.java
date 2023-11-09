package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;
import br.com.usinasantafe.ppc.util.ConexaoWeb;

public class OSActivity extends ActivityGeneric {

    private PPCContext ppcContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkOS = findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonOkOS.setOnClickListener(v -> {

            if (!editTextPadrao.getText().toString().equals("")) {

                if(ppcContext.getPerdaCTR().verifOS(Long.parseLong(editTextPadrao.getText().toString()))) {

                    ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setNroOSCabec(Long.parseLong(editTextPadrao.getText().toString()));
                    Intent it = new Intent(OSActivity.this, FrenteActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(OSActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("O.S. INEXISTENTE. POR FAVOR, VERIFIQUE A O.S. DIGITADA OU ATUALIZAR OS DADOS DE O.S.!");

                    alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                    alerta.show();

                }

            }

        });

        buttonCancOS.setOnClickListener(v -> {
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

        buttonAtualPadrao.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder( OSActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(OSActivity.this)) {

                    progressBar = new ProgressDialog(OSActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    ppcContext.getConfigCTR().atualDados(OSActivity.this, OSActivity.class, progressBar, "OS");

                } else {

                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( OSActivity.this);
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
        Intent it = new Intent(OSActivity.this, TalhaoActivity.class);
        startActivity(it);
        finish();
    }

}
