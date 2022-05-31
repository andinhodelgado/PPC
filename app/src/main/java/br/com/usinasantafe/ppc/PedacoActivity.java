package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PedacoActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedaco);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkPedaco = findViewById(R.id.buttonOkPadrao);
        Button buttonCancPedaco = findViewById(R.id.buttonCancPadrao);

        buttonOkPedaco.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!editTextPadrao.getText().toString().equals("")){

                    String pedaco = editTextPadrao.getText().toString();
                    Double pedacoNum = Double.valueOf(pedaco.replace(",", "."));

                    if(PPCContext.getAmostraVARTO().getTara() < pedacoNum){

                        PPCContext.getAmostraVARTO().setPedaco(pedacoNum);

                        if(PPCContext.getTipoCabecalho() == 1L){

                            PPCContext.getAmostraVARTO().setRepique(0D);
                            Intent it = new Intent(PedacoActivity.this, PonteiroActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else if(PPCContext.getTipoCabecalho() == 2L){

                            Intent it = new Intent(PedacoActivity.this, RepiqueActivity.class);
                            startActivity(it);
                            finish();

                        }

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(PedacoActivity.this);
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

                }
                else{


                    if(PPCContext.getTipoCabecalho() == 1L){

                        PPCContext.getAmostraVARTO().setRepique(0D);
                        PPCContext.getAmostraVARTO().setPedaco(0D);
                        Intent it = new Intent(PedacoActivity.this, PonteiroActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else if(PPCContext.getTipoCabecalho() == 2L){

                        PPCContext.getAmostraVARTO().setPedaco(0D);
                        Intent it = new Intent(PedacoActivity.this, RepiqueActivity.class);
                        startActivity(it);
                        finish();

                    }

                }

            }
        });

        buttonCancPedaco.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else{
                    Intent it = new Intent(PedacoActivity.this, TocoActivity.class);
                    startActivity(it);
                }
            }
        });

    }
}
