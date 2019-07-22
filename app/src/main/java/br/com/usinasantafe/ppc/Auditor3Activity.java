package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ppc.to.tb.estaticas.AuditorESTTO;

public class Auditor3Activity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditor3);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkAuditor3 = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancAuditor3 = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkAuditor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextPadrao.getText().toString().equals("")){

                    Long auditor = Long.parseLong(editTextPadrao.getText().toString());

                    if((PPCContext.getCabecalhoVARTO().getAuditor1() != auditor)
                            && (PPCContext.getCabecalhoVARTO().getAuditor2() != auditor)){

                        AuditorESTTO auditorESTTO = new AuditorESTTO();
                        List listaAuditorPesq = auditorESTTO.get("codAuditor", auditor);

                        if(listaAuditorPesq.size() > 0){

                            PPCContext.getCabecalhoVARTO().setAuditor3(auditor);
                            Intent it = new Intent(Auditor3Activity.this, DataActivity.class);
                            startActivity(it);

                        }
                        else{

                            AlertDialog.Builder alerta = new AlertDialog.Builder(Auditor3Activity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("AUDITOR INEXISTENTE!");

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

                        AlertDialog.Builder alerta = new AlertDialog.Builder(Auditor3Activity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("AUDITOR JÁ FOI INSERIDO ANTERIORMENTE. VERIFIQUE A MATRICULA DE AUDITOR.");

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

                    PPCContext.getCabecalhoVARTO().setAuditor3(0L);
                    Intent it = new Intent(Auditor3Activity.this, DataActivity.class);
                    startActivity(it);

                }

            }

        });

        buttonCancAuditor3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else{
                    Intent it = new Intent(Auditor3Activity.this, Auditor2Activity.class);
                    startActivity(it);
                }
            }

        });

    }
}
