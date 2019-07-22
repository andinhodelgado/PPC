package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ppc.to.tb.estaticas.AuditorESTTO;

public class Auditor1Activity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auditor1);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkAuditor1 = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancAuditor1 = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkAuditor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!editTextPadrao.getText().toString().equals("")){

                    Long auditor = Long.parseLong(editTextPadrao.getText().toString());

                    AuditorESTTO auditorESTTO = new AuditorESTTO();
                    List listaAuditorPesq = auditorESTTO.get("codAuditor", auditor);

                    if(listaAuditorPesq.size() > 0){

                        PPCContext.getCabecalhoVARTO().setAuditor1(auditor);
                        Intent it = new Intent(Auditor1Activity.this, Auditor2Activity.class);
                        startActivity(it);

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(Auditor1Activity.this);
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

                    AlertDialog.Builder alerta = new AlertDialog.Builder(Auditor1Activity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("POR FAVOR, INSIRA A MATRICULA DO AUDITOR 1.");

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

        buttonCancAuditor1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else{
                    Intent it = new Intent(Auditor1Activity.this, ListaMenuActivity.class);
                    startActivity(it);
                }
            }
        });

    }
}
