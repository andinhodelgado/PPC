package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import br.com.usinasantafe.ppc.to.tb.estaticas.OSESTTO;

public class OSActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_os);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkOS = findViewById(R.id.buttonOkPadrao);
        Button buttonCancOS = findViewById(R.id.buttonCancPadrao);

        buttonOkOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OSESTTO osTO = new OSESTTO();

                if (!editTextPadrao.getText().toString().equals("")) {

                    if(osTO.exists("nroOS", Long.parseLong(editTextPadrao.getText().toString()))) {

                        PPCContext.getCabecalhoVARTO().setOs(Long.parseLong(editTextPadrao.getText().toString()));
                        Intent it = new Intent(OSActivity.this, FrenteActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(OSActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("O.S. INEXISTENTE. POR FAVOR, VERIFIQUE A O.S. DIGITADA.");

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editTextPadrao.setText("");
                            }
                        });
                        alerta.show();

                    }

                }

            }
        });

        buttonCancOS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                } else {
                    Intent it = new Intent(OSActivity.this, TalhaoActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

    }
}
