package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TaraActivity extends ActivityGeneric {

    private PPCContext ppcContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tara);

        ppcContext = (PPCContext) getApplication();

        Button buttonOkTara = findViewById(R.id.buttonOkPadrao);
        Button buttonCancTara = findViewById(R.id.buttonCancPadrao);

        buttonOkTara.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {
                    String tara = editTextPadrao.getText().toString();
                    ppcContext.getAmostraVARTO().setTara(Double.valueOf(tara.replace(",", ".")));
                } else {
                    ppcContext.getAmostraVARTO().setTara(0D);
                }

                if (ppcContext.getTipoCabecalho() == 1L) {
                    Intent it = new Intent(TaraActivity.this, ToleteActivity.class);
                    startActivity(it);
                    finish();
                } else if (ppcContext.getTipoCabecalho() == 2L) {
                    ppcContext.getAmostraVARTO().setTolete(0D);
                    Intent it = new Intent(TaraActivity.this, CanaInteiraActivity.class);
                    startActivity(it);
                    finish();
                }

            }
        });

        buttonCancTara.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                } else {
                    Intent it = new Intent(TaraActivity.this, CabecalhoActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }
}
