package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ToleteActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tolete);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkTolete = findViewById(R.id.buttonOkPadrao);
        Button buttonCancTolete = findViewById(R.id.buttonCancPadrao);

        buttonOkTolete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    String tolete = editTextPadrao.getText().toString();
                    Double toleteNum = Double.valueOf(tolete.replace(",", "."));

                    if (PPCContext.getAmostraVARTO().getTara() < toleteNum) {

                        PPCContext.getAmostraVARTO().setTolete(toleteNum);
                        Intent it = new Intent(ToleteActivity.this, CanaInteiraActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ToleteActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("O PESO ESTA ABAIXO DO PESO TARA. POR FAVOR DIGITE NOVAMENTE O PESO.");

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editTextPadrao.setText("");
                            }
                        });
                        alerta.show();

                    }

                } else {

                    PPCContext.getAmostraVARTO().setTolete(0D);
                    Intent it = new Intent(ToleteActivity.this, CanaInteiraActivity.class);
                    startActivity(it);
                    finish();

                }
            }
        });

        buttonCancTolete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                } else {
                    Intent it = new Intent(ToleteActivity.this, TaraActivity.class);
                    startActivity(it);
                }
            }
        });

    }
}
