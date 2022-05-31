package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CanaInteiraActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cana_inteira);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkCanaInteira = findViewById(R.id.buttonOkPadrao);
        Button buttonCancCanaInteira = findViewById(R.id.buttonCancPadrao);

        buttonOkCanaInteira.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(!editTextPadrao.getText().toString().equals("")){

                    String canaInteira = editTextPadrao.getText().toString();
                    Double canaInteiraNum = Double.valueOf(canaInteira.replace(",", "."));

                    if(PPCContext.getAmostraVARTO().getTara() < canaInteiraNum){

                        PPCContext.getAmostraVARTO().setCanaInteira(canaInteiraNum);
                        Intent it = new Intent(CanaInteiraActivity.this, TocoActivity.class);
                        startActivity(it);
                        finish();

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(CanaInteiraActivity.this);
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

                    PPCContext.getAmostraVARTO().setCanaInteira(0D);
                    Intent it = new Intent(CanaInteiraActivity.this, TocoActivity.class);
                    startActivity(it);
                    finish();

                }
            }
        });

        buttonCancCanaInteira.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else{

                    if(PPCContext.getTipoCabecalho() == 1L){
                        Intent it = new Intent(CanaInteiraActivity.this, ToleteActivity.class);
                        startActivity(it);
                        finish();
                    }
                    else if(PPCContext.getTipoCabecalho() == 2L){
                        Intent it = new Intent(CanaInteiraActivity.this, TaraActivity.class);
                        startActivity(it);
                        finish();
                    }

                }
            }
        });

    }
}
