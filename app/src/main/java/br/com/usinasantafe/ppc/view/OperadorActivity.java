package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class OperadorActivity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operador);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkOperador= findViewById(R.id.buttonOkPadrao);
        Button buttonCancOperador = findViewById(R.id.buttonCancPadrao);

        buttonOkOperador.setOnClickListener(v -> {

            if (editTextPadrao.getText().toString().length() > 0) {

                if (ppcContext.getPerdaCTR().verifOperador(Long.parseLong(editTextPadrao.getText().toString()))) {

                    ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setMatricOperadorCabec(Long.parseLong(editTextPadrao.getText().toString()));
                    ppcContext.getPerdaCTR().getCabecDAO().salvarCabecAberto();

                    Intent it = new Intent(OperadorActivity.this, ListaCabecalhoActivity.class);
                    startActivity(it);
                    finish();

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(OperadorActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("ESSA OPERADOR INEXISTENTE!");

                    alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                    alerta.show();

                }


            }

        });


        buttonCancOperador.setOnClickListener(v -> {
            if (editTextPadrao.getText().toString().length() > 0) {
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(OperadorActivity.this, ColhedoraActivity.class);
        startActivity(it);
        finish();
    }
}
