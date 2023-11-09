package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;
import br.com.usinasantafe.ppc.util.ConexaoWeb;

public class ColhedoraActivity extends ActivityGeneric {

    private PPCContext ppcContext;
    private ProgressDialog progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colhedora);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkColhedora = findViewById(R.id.buttonOkPadrao);
        Button buttonCancColhedora = findViewById(R.id.buttonCancPadrao);
        Button buttonAtualPadrao = findViewById(R.id.buttonAtualPadrao);

        buttonOkColhedora.setOnClickListener(v -> {

            if (!editTextPadrao.getText().toString().equals("")) {

                if(ppcContext.getPerdaCTR().verifColhedora(Long.parseLong(editTextPadrao.getText().toString()))){

                    if(!ppcContext.getPerdaCTR().verifColhedoraRepetido(Long.parseLong(editTextPadrao.getText().toString()))){

                        ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setNroColhedoraCabec(Long.parseLong(editTextPadrao.getText().toString()));
                        Intent it = new Intent(ColhedoraActivity.this, OperadorActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ColhedoraActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("ESSA COLHEDORA JÁ FOI INSERIDA EM OUTRO CABECALHO.");

                        alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                        alerta.show();

                    }

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(ColhedoraActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("ESSA COLHEDORA INEXISTENTE!");

                    alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                    alerta.show();

                }

            }

        });

        buttonCancColhedora.setOnClickListener(v -> {
            if(editTextPadrao.getText().toString().length() > 0){
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

        buttonAtualPadrao.setOnClickListener(v -> {

            AlertDialog.Builder alerta = new AlertDialog.Builder( ColhedoraActivity.this);
            alerta.setTitle("ATENÇÃO");
            alerta.setMessage("DESEJA REALMENTE ATUALIZAR BASE DE DADOS?");
            alerta.setNegativeButton("SIM", (dialog, which) -> {

                ConexaoWeb conexaoWeb = new ConexaoWeb();

                if (conexaoWeb.verificaConexao(ColhedoraActivity.this)) {

                    progressBar = new ProgressDialog(ColhedoraActivity.this);
                    progressBar.setCancelable(true);
                    progressBar.setMessage("ATUALIZANDO ...");
                    progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressBar.setProgress(0);
                    progressBar.setMax(100);
                    progressBar.show();

                    ppcContext.getConfigCTR().atualDados(ColhedoraActivity.this, ColhedoraActivity.class, progressBar, "Colhedora");

                } else {

                    AlertDialog.Builder alerta1 = new AlertDialog.Builder( ColhedoraActivity.this);
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
        Intent it = new Intent(ColhedoraActivity.this, FrenteActivity.class);
        startActivity(it);
        finish();
    }
}
