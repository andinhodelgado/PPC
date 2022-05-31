package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ppc.to.tb.estaticas.AuditorESTTO;

public class Auditor2Activity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditor2);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkAuditor2 = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancAuditor2 = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkAuditor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextPadrao.getText().toString().equals("")){

                    Long auditor = Long.parseLong(editTextPadrao.getText().toString());

                    if(PPCContext.getCabecalhoVARTO().getAuditor1() != auditor){

                        AuditorESTTO auditorESTTO = new AuditorESTTO();
                        List listaAuditorPesq = auditorESTTO.get("codAuditor", auditor);

                        if(listaAuditorPesq.size() > 0){

                            PPCContext.getCabecalhoVARTO().setAuditor2(auditor);
                            Intent it = new Intent(Auditor2Activity.this, Auditor3Activity.class);
                            startActivity(it);
                            finish();

                        }
                        else{

                            AlertDialog.Builder alerta = new AlertDialog.Builder(Auditor2Activity.this);
                            alerta.setTitle("ATENÇÃO");
                            alerta.setMessage("AUDITOR INEXISTENTE!");

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

                        AlertDialog.Builder alerta = new AlertDialog.Builder(Auditor2Activity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("AUDITOR JÁ FOI INSERIDO ANTERIORMENTE. VERIFIQUE A MATRICULA DE AUDITOR.");

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

                    AlertDialog.Builder alerta = new AlertDialog.Builder(Auditor2Activity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR, INSIRA A MATRICULA DO AUDITOR 2.");

                    alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            editTextPadrao.setText("");
                        }
                    });
                    alerta.show();

                }

            }

        });

        buttonCancAuditor2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else{
                    Intent it = new Intent(Auditor2Activity.this, Auditor1Activity.class);
                    startActivity(it);
                    finish();
                }
            }

        });

    }
}
