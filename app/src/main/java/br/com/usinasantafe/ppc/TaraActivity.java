package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TaraActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tara);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkTara = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancTara = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkTara.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {

                    String tara = editTextPadrao.getText().toString();
                    PPCContext.getAmostraVARTO().setTara(Double.valueOf(tara.replace(",", ".")));

                    if (PPCContext.getTipoCabecalho() == 1L) {
                        Intent it = new Intent(TaraActivity.this, ToleteActivity.class);
                        startActivity(it);
                    } else if (PPCContext.getTipoCabecalho() == 2L) {
                        PPCContext.getAmostraVARTO().setTolete(0D);
                        Intent it = new Intent(TaraActivity.this, CanaInteiraActivity.class);
                        startActivity(it);
                    }

                } else {

                    AlertDialog.Builder alerta = new AlertDialog.Builder(TaraActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR, INSIRA O PESO TARA.");

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            editTextPadrao.setText("");
                        }
                    });
                    alerta.show();

                }

            }
        });

        buttonCancTara.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                } else {
                    Intent it = new Intent(TaraActivity.this, CabecalhoActivity.class);
                    startActivity(it);
                }
            }
        });

    }
}
