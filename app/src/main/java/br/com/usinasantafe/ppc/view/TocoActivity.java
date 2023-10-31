package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class TocoActivity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toco);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkToco = findViewById(R.id.buttonOkPadrao);
        Button buttonCancToco = findViewById(R.id.buttonCancPadrao);

        buttonOkToco.setOnClickListener(v -> {

            if (!editTextPadrao.getText().toString().equals("")) {

                String valor = editTextPadrao.getText().toString();
                Double valorDouble = Double.valueOf(valor.replace(",", "."));

                if (ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().getTaraAmostra() < valorDouble) {

                    ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setTocoAmostra(valorDouble);
                    Intent it = new Intent(TocoActivity.this, PedacoActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(TocoActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("O PESO ESTA ABAIXO DO PESO TARA. POR FAVOR DIGITE NOVAMENTE O PESO.");

                    alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                    alerta.show();

                }

            } else {

                ppcContext.getPerdaCTR().getAmostraDAO().getAmostraBean().setTocoAmostra(0D);
                Intent it = new Intent(TocoActivity.this, PedacoActivity.class);
                startActivity(it);
                finish();

            }
        });

        buttonCancToco.setOnClickListener(v -> {
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(TocoActivity.this, CanaInteiraActivity.class);
        startActivity(it);
        finish();
    }
}
