package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RepiqueActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repique);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkRepique = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancRepique = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkRepique.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {

                    String repique = editTextPadrao.getText().toString();
                    Double repiqueNum = Double.valueOf(repique.replace(",", "."));

                    if (PPCContext.getAmostraVARTO().getTara() < repiqueNum) {

                        PPCContext.getAmostraVARTO().setRepique(repiqueNum);
                        Intent it = new Intent(RepiqueActivity.this, PonteiroActivity.class);
                        startActivity(it);

                    }
                    else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(RepiqueActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("O PESO ESTA ABAIXO DO PESO TARA. POR FAVOR DIGITE NOVAMENTE O PESO.");

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub
                                editTextPadrao.setText("");
                            }
                        });
                        alerta.show();

                    }

                } else {

                    PPCContext.getAmostraVARTO().setRepique(0D);
                    Intent it = new Intent(RepiqueActivity.this, PonteiroActivity.class);
                    startActivity(it);

                }

            }
        });


        buttonCancRepique.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                } else {
                    Intent it = new Intent(RepiqueActivity.this, PedacoActivity.class);
                    startActivity(it);
                }
            }
        });

    }
}
