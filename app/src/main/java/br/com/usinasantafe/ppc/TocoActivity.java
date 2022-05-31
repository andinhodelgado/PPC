package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TocoActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toco);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkToco = findViewById(R.id.buttonOkPadrao);
        Button buttonCancToco = findViewById(R.id.buttonCancPadrao);

        buttonOkToco.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (!editTextPadrao.getText().toString().equals("")) {

                    String toco = editTextPadrao.getText().toString();
                    Double tocoNum = Double.valueOf(toco.replace(",", "."));

                    if (PPCContext.getAmostraVARTO().getTara() < tocoNum) {

                        PPCContext.getAmostraVARTO().setToco(tocoNum);
                        Intent it = new Intent(TocoActivity.this, PedacoActivity.class);
                        startActivity(it);
                        finish();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(TocoActivity.this);
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

                    PPCContext.getAmostraVARTO().setToco(0D);
                    Intent it = new Intent(TocoActivity.this, PedacoActivity.class);
                    startActivity(it);
                    finish();

                }
            }
        });

        buttonCancToco.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                } else {
                    Intent it = new Intent(TocoActivity.this, CanaInteiraActivity.class);
                    startActivity(it);
                    finish();
                }
            }
        });

    }
}
