package br.com.usinasantafe.ppc.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import br.com.usinasantafe.ppc.PPCContext;
import br.com.usinasantafe.ppc.R;

public class OSActivity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkOS = findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = findViewById(R.id.buttonCancPadrao);

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

    }

    public void onBackPressed() {
        Intent it = new Intent(OSActivity.this, TalhaoActivity.class);
        startActivity(it);
        finish();
    }

}
