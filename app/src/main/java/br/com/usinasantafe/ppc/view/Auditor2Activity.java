package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class Auditor2Activity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditor2);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkAuditor2 = findViewById(R.id.buttonOkPadrao);
        Button buttonCancAuditor2 = findViewById(R.id.buttonCancPadrao);

        buttonOkAuditor2.setOnClickListener(v -> {

            if(!editTextPadrao.getText().toString().equals("")){

                Long auditor = Long.parseLong(editTextPadrao.getText().toString());

                if(ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().getMatricAuditor1Cabec() != auditor){

                    if(ppcContext.getPerdaCTR().verifAuditor(Long.parseLong(editTextPadrao.getText().toString()))) {

                        ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setMatricAuditor2Cabec(Long.parseLong(editTextPadrao.getText().toString()));
                        Intent it = new Intent(Auditor2Activity.this, Auditor3Activity.class);
                        startActivity(it);
                        finish();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(Auditor2Activity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("AUDITOR INEXISTENTE!");
                        alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                        alerta.show();

                    }

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(Auditor2Activity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("AUDITOR JÁ FOI INSERIDO ANTERIORMENTE. VERIFIQUE A MATRICULA DE AUDITOR.");

                    alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                    alerta.show();

                }

            } else {

                AlertDialog.Builder alerta = new AlertDialog.Builder(Auditor2Activity.this);
                alerta.setTitle("ATENÇÃO");
                alerta.setMessage("POR FAVOR, INSIRA A MATRICULA DO AUDITOR 2.");

                alerta.setPositiveButton("OK", (dialog, which) -> editTextPadrao.setText(""));
                alerta.show();

            }

        });

        buttonCancAuditor2.setOnClickListener(v -> {
            if(editTextPadrao.getText().toString().length() > 0){
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(Auditor2Activity.this, Auditor1Activity.class);
        startActivity(it);
        finish();
    }

}
