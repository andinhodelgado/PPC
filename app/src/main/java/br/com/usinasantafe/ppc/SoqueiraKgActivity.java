package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SoqueiraKgActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soqueira_kg);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkSoqueiraKg = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancSoqueiraKg = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkSoqueiraKg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(!editTextPadrao.getText().toString().equals("")){

                    String soqueiraKg = editTextPadrao.getText().toString();
                    Double soqueiraKgNum = Double.valueOf(soqueiraKg.replace(",", "."));

                    if(PPCContext.getAmostraVARTO().getTara() < soqueiraKgNum){

                        PPCContext.getAmostraVARTO().setSoqueiraKg(soqueiraKgNum);
                        Intent it = new Intent(SoqueiraKgActivity.this, SoqueiraNumActivity.class);
                        startActivity(it);

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(SoqueiraKgActivity.this);
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

                }
                else{

                    PPCContext.getAmostraVARTO().setSoqueiraKg(0D);
                    Intent it = new Intent(SoqueiraKgActivity.this, SoqueiraNumActivity.class);
                    startActivity(it);

                }

            }
        });

        buttonCancSoqueiraKg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else{

                    if(PPCContext.getTipoCabecalho() == 1L){
                        Intent it = new Intent(SoqueiraKgActivity.this, LascasActivity.class);
                        startActivity(it);
                    }
                    else if(PPCContext.getTipoCabecalho() == 2L){
                        Intent it = new Intent(SoqueiraKgActivity.this, PonteiroActivity.class);
                        startActivity(it);
                    }

                }
            }
        });

    }
}
