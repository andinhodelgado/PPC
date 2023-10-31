package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class ColhedoraActivity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colhedora);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkColhedora = findViewById(R.id.buttonOkPadrao);
        Button buttonCancColhedora = findViewById(R.id.buttonCancPadrao);

        buttonOkColhedora.setOnClickListener(v -> {

            if (!editTextPadrao.getText().toString().equals("")) {

                if(ppcContext.getPerdaCTR().verifColhedora(Long.parseLong(editTextPadrao.getText().toString()))){

                    if(!ppcContext.getPerdaCTR().verifColhedoraRepetido(Long.parseLong(editTextPadrao.getText().toString()))){

                        ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setCodColhedoraCabec(Long.parseLong(editTextPadrao.getText().toString()));
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

    }

    public void onBackPressed() {
        Intent it = new Intent(ColhedoraActivity.this, FrenteActivity.class);
        startActivity(it);
        finish();
    }
}
