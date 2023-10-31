package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class Auditor3Activity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditor3);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkAuditor3 = findViewById(R.id.buttonOkPadrao);
        Button buttonCancAuditor3 = findViewById(R.id.buttonCancPadrao);

        buttonOkAuditor3.setOnClickListener(v -> {

            if(!editTextPadrao.getText().toString().equals("")){

                Long auditor = Long.parseLong(editTextPadrao.getText().toString());

                if((ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().getMatricAuditor1Cabec() != auditor)
                        && (ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().getMatricAuditor2Cabec() != auditor)){

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

                ppcContext.getPerdaCTR().getCabecDAO().getCabecBean().setMatricAuditor3Cabec(Long.parseLong(editTextPadrao.getText().toString()));
                Intent it = new Intent(Auditor3Activity.this, DataActivity.class);
                startActivity(it);
                finish();

            }

        });

        buttonCancAuditor3.setOnClickListener(v -> {
            if(editTextPadrao.getText().toString().length() > 0){
                editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
            }
        });

    }

    public void onBackPressed() {
        Intent it = new Intent(Auditor3Activity.this, Auditor2Activity.class);
        startActivity(it);
        finish();
    }

}
