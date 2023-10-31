package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class LascasActivity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lascas);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkLascas = findViewById(R.id.buttonOkPadrao);
        Button buttonCancLascas = findViewById(R.id.buttonCancPadrao);

        buttonOkLascas.setOnClickListener(v -> {

            if (!editTextPadrao.getText().toString().equals("")) {

                String valor = editTextPadrao.getText().toString();
                Double valorDouble = Double.valueOf(valor.replace(",", "."));

                if(ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().getTaraAmostra() < valorDouble){

                    ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setLascasAmostra(valorDouble);

                    AlertDialog.Builder alerta = new AlertDialog.Builder(LascasActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("DESEJA INSERIR OBSERVAÇÃO NA AMOSTRA?");

                    alerta.setPositiveButton("SIM", (dialog, which) -> {

                        Intent it = new Intent(LascasActivity.this, ListaObservacaoActivity.class);
                        startActivity(it);
                        finish();

                    });

                    alerta.setNegativeButton("NÃO", (dialog, which) -> {

                        ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setPedraAmostra(0L);
                        ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setPlantaDaninhasAmostra(0L);
                        ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setTocoArvoreAmostra(0L);
                        ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setFormigueiroAmostra(0L);
                        ppcContext.getPerdaCTR().salvarAmostra();

                        Intent it = new Intent(LascasActivity.this, ListaAmostraActivity.class);
                        startActivity(it);
                        finish();

                    });

                    alerta.show();

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(LascasActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("O PESO ESTA ABAIXO DO PESO TARA. POR FAVOR DIGITE NOVAMENTE O PESO.");

                    alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                    alerta.show();

                }

            } else {

                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setLascasAmostra(0D);

                AlertDialog.Builder alerta = new AlertDialog.Builder(LascasActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA INSERIR OBSERVAÇÃO NA AMOSTRA?");

                alerta.setPositiveButton("SIM", (dialog, which) -> {

                    Intent it = new Intent(LascasActivity.this, ListaObservacaoActivity.class);
                    startActivity(it);
                    finish();

                });

                alerta.setNegativeButton("NÃO", (dialog, which) -> {

                    ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setPedraAmostra(0L);
                    ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setPlantaDaninhasAmostra(0L);
                    ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setTocoArvoreAmostra(0L);
                    ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setFormigueiroAmostra(0L);
                    ppcContext.getPerdaCTR().salvarAmostra();

                    Intent it = new Intent(LascasActivity.this, ListaAmostraActivity.class);
                    startActivity(it);
                    finish();

                });

                alerta.show();

            }

        });


        buttonCancLascas.setOnClickListener(v -> {
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(LascasActivity.this, PonteiroActivity.class);
        startActivity(it);
        finish();
    }
}
