package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class Auditor1Activity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditor1);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkAuditor1 = findViewById(R.id.buttonOkPadrao);
        Button buttonCancAuditor1 = findViewById(R.id.buttonCancPadrao);

        buttonOkAuditor1.setOnClickListener(v -> {

            if(!editTextPadrao.getText().toString().equals("")) {

                if(ppcContext.getPerdaCTR().verifAuditor(Long.parseLong(editTextPadrao.getText().toString()))) {

                    ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setMatricAuditor1Cabec(Long.parseLong(editTextPadrao.getText().toString()));
                    Intent it = new Intent(Auditor1Activity.this, Auditor2Activity.class);
                    startActivity(it);
                    finish();

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(Auditor1Activity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("AUDITOR INEXISTENTE!");
                    alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                    alerta.show();

                }

            } else {

                AlertDialog.Builder alerta = new AlertDialog.Builder(Auditor1Activity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("POR FAVOR, INSIRA A MATRICULA DO AUDITOR 1.");

                alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                alerta.show();

            }


        });

        buttonCancAuditor1.setOnClickListener(v -> {
            if(editTextPadrao.getText().toString().length() > 0){
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(Auditor1Activity.this, ListaTipoColheitaActivity.class);
        startActivity(it);
        finish();
    }

}
