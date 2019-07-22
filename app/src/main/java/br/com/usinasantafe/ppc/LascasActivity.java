package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

import br.com.usinasantafe.ppc.to.tb.variaveis.AmostraVARTO;

public class LascasActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lascas);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkLascas = (Button) findViewById(R.id.buttonOkPadrao);
        Button buttonCancLascas = (Button) findViewById(R.id.buttonCancPadrao);

        buttonOkLascas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!editTextPadrao.getText().toString().equals("")) {

                    String lascas = editTextPadrao.getText().toString();
                    Double lascasNum = Double.valueOf(lascas.replace(",", "."));

                    if (PPCContext.getAmostraVARTO().getTara() < lascasNum) {

                        PPCContext.getAmostraVARTO().setLascas(lascasNum);
                        PPCContext.getAmostraVARTO().setSoqueiraKg(0D);
                        PPCContext.getAmostraVARTO().setSoqueiraNum(0D);

                        AlertDialog.Builder alerta = new AlertDialog.Builder(LascasActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("DESEJA INSERIR OBSERVAÇÃO NA AMOSTRA?");

                        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                                Intent it = new Intent(LascasActivity.this, ListaObservacaoActivity.class);
                                startActivity(it);

                            }
                        });

                        alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // TODO Auto-generated method stub

                                PPCContext.getAmostraVARTO().setObsv("null");
                                AmostraVARTO amostraVARTO = new AmostraVARTO();

                                if(!amostraVARTO.hasElements()){
                                    PPCContext.getAmostraVARTO().setId((long) 1);
                                    PPCContext.getAmostraVARTO().setNum((long) 1);
                                }
                                else{
                                    List listAmostra = amostraVARTO.orderBy("id", false);
                                    if(listAmostra.size() == 0){
                                        PPCContext.getAmostraVARTO().setId((long) 1);
                                        PPCContext.getAmostraVARTO().setNum((long) 1);
                                    }
                                    else{
                                        amostraVARTO = (AmostraVARTO) listAmostra.get(0);
                                        Long codigo = amostraVARTO.getId() + 1;
                                        PPCContext.getAmostraVARTO().setId(codigo);

                                        listAmostra = amostraVARTO.get("idCabecalho", PPCContext.getAmostraVARTO().getIdCabecalho());
                                        Long num = listAmostra.size() + 1L;
                                        PPCContext.getAmostraVARTO().setNum(num);

                                    }
                                }

                                PPCContext.getAmostraVARTO().insert();

                                Intent it = new Intent(LascasActivity.this, MsgFecharAnaliseActivity.class);
                                startActivity(it);

                            }
                        });

                        alerta.show();

                    } else {

                        AlertDialog.Builder alerta = new AlertDialog.Builder(LascasActivity.this);
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

                    PPCContext.getAmostraVARTO().setLascas(0D);
//                    Intent it = new Intent(LascasActivity.this, SoqueiraKgActivity.class);
                    PPCContext.getAmostraVARTO().setSoqueiraKg(0D);
                    PPCContext.getAmostraVARTO().setSoqueiraNum(0D);

                    AlertDialog.Builder alerta = new AlertDialog.Builder(LascasActivity.this);
                    alerta.setTitle("ATENÇÃO");
                    alerta.setMessage("DESEJA INSERIR OBSERVAÇÃO NA AMOSTRA?");

                    alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                            Intent it = new Intent(LascasActivity.this, ListaObservacaoActivity.class);
                            startActivity(it);

                        }
                    });

                    alerta.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub

                            PPCContext.getAmostraVARTO().setObsv("null");
                            AmostraVARTO amostraVARTO = new AmostraVARTO();

                            if(!amostraVARTO.hasElements()){
                                PPCContext.getAmostraVARTO().setId((long) 1);
                                PPCContext.getAmostraVARTO().setNum((long) 1);
                            }
                            else{
                                List listAmostra = amostraVARTO.orderBy("id", false);
                                if(listAmostra.size() == 0){
                                    PPCContext.getAmostraVARTO().setId((long) 1);
                                    PPCContext.getAmostraVARTO().setNum((long) 1);
                                }
                                else{
                                    amostraVARTO = (AmostraVARTO) listAmostra.get(0);
                                    Long codigo = amostraVARTO.getId() + 1;
                                    PPCContext.getAmostraVARTO().setId(codigo);

                                    listAmostra = amostraVARTO.get("idCabecalho", PPCContext.getAmostraVARTO().getIdCabecalho());
                                    Long num = listAmostra.size() + 1L;
                                    PPCContext.getAmostraVARTO().setNum(num);

                                }
                            }

                            PPCContext.getAmostraVARTO().insert();

                            Intent it = new Intent(LascasActivity.this, MsgFecharAnaliseActivity.class);
                            startActivity(it);

                        }
                    });

                    alerta.show();

                }

            }
        });


        buttonCancLascas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (editTextPadrao.getText().toString().length() > 0) {
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                } else {
                    Intent it = new Intent(LascasActivity.this, PonteiroActivity.class);
                    startActivity(it);
                }
            }
        });

    }
}
