package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class PonteiroActivity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ponteiro);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkPonteiro = findViewById(R.id.buttonOkPadrao);
        Button buttonCancPonteiro = findViewById(R.id.buttonCancPadrao);

        buttonOkPonteiro.setOnClickListener(v -> {

            Double valorDouble = 0D;
            if(!editTextPadrao.getText().toString().equals("")){

                String valor = editTextPadrao.getText().toString();
                valorDouble = Double.valueOf(valor.replace(",", "."));

                if(ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().getTaraAmostra() < valorDouble){

                    ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setPonteiroAmostra(valorDouble);

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(PonteiroActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("O PESO ESTA ABAIXO DO PESO TARA. POR FAVOR DIGITE NOVAMENTE O PESO.");

                    alerta.setPositiveButton("OK", (dialog, which) -> {
                        return;
                    });
                    alerta.show();

                }

            } else {

                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setLascasAmostra(0D);

            }

            if (ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().getTipoColheitaCabec() == 1L) {

                Intent it = new Intent(PonteiroActivity.this, LascasActivity.class);
                startActivity(it);
                finish();

            } else {

                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setLascasAmostra(valorDouble);

                AlertDialog.Builder alerta = new AlertDialog.Builder(PonteiroActivity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("DESEJA INSERIR OBSERVAÇÃO NA AMOSTRA?");

                alerta.setPositiveButton("SIM", (dialog, which) -> {

                    Intent it = new Intent(PonteiroActivity.this, ListaObservacaoActivity.class);
                    startActivity(it);
                    finish();

                });

                alerta.setNegativeButton("NÃO", (dialog, which) -> {

                    ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setPedraAmostra(0L);
                    ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setPlantaDaninhasAmostra(0L);
                    ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setTocoArvoreAmostra(0L);
                    ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setFormigueiroAmostra(0L);
                    ppcContext.getPerdaCTR().salvarAmostra();

                    Intent it = new Intent(PonteiroActivity.this, ListaAmostraActivity.class);
                    startActivity(it);
                    finish();

                });

                alerta.show();

            }

        });

        buttonCancPonteiro.setOnClickListener(v -> {
            if(editTextPadrao.getText().toString().length() > 0){
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        if (ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().getTipoColheitaCabec() == 1L) {
            Intent it = new Intent(PonteiroActivity.this, PedacoActivity.class);
            startActivity(it);
            finish();
        } else {
            Intent it = new Intent(PonteiroActivity.this, RepiqueActivity.class);
            startActivity(it);
            finish();
        }
    }
}
