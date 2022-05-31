package br.com.usinasantafe.ppc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.ppc.pst.EspecificaPesquisa;
import br.com.usinasantafe.ppc.to.tb.estaticas.ColhedoraESTTO;
import br.com.usinasantafe.ppc.to.tb.variaveis.CabecalhoVARTO;

public class ColhedoraActivity extends ActivityGeneric {

    private PPCContext PPCContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colhedora);

        PPCContext = (PPCContext) getApplication();

        Button buttonOkColhedora = findViewById(R.id.buttonOkPadrao);
        Button buttonCancColhedora = findViewById(R.id.buttonCancPadrao);

        buttonOkColhedora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Long idColhedora = Long.parseLong(editTextPadrao.getText().toString());

                if(editTextPadrao.getText().toString().length() > 0){

                    ColhedoraESTTO colhedoraESTTO = new ColhedoraESTTO();
                    List listaColhedoraPesq = colhedoraESTTO.get("idColhedora", idColhedora);

                    if(listaColhedoraPesq.size() > 0){

                        CabecalhoVARTO cabecalhoVARTO = new CabecalhoVARTO();

                        if(!cabecalhoVARTO.hasElements()){

                            PPCContext.getCabecalhoVARTO().setColhedora(idColhedora);
                            Intent it = new Intent(ColhedoraActivity.this, OperadorActivity.class);
                            startActivity(it);
                            finish();

                        }
                        else{

                            ArrayList<EspecificaPesquisa> listaPesq = new ArrayList<EspecificaPesquisa>();

                            EspecificaPesquisa especificaPesquisaColhed = new EspecificaPesquisa();
                            especificaPesquisaColhed.setCampo("colhedora");
                            especificaPesquisaColhed.setValor(idColhedora);

                            listaPesq.add(especificaPesquisaColhed);

                            EspecificaPesquisa especificaPesquisaStatus = new EspecificaPesquisa();
                            especificaPesquisaStatus.setCampo("status");
                            especificaPesquisaStatus.setValor(1L);

                            listaPesq.add(especificaPesquisaStatus);

                            List listaCabecalhoPesq = cabecalhoVARTO.get(listaPesq);

                            if(listaCabecalhoPesq.size() == 0){

                                PPCContext.getCabecalhoVARTO().setColhedora(idColhedora);

                                Intent it = new Intent(ColhedoraActivity.this, OperadorActivity.class);
                                startActivity(it);
                                finish();

                            }
                            else{

                                AlertDialog.Builder alerta = new AlertDialog.Builder(ColhedoraActivity.this);
                                alerta.setTitle("ATENÇÃO");
                                alerta.setMessage("ESSA COLHEDORA JÁ FOI INSERIDA EM OUTRO CABECALHO.");

                                alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        editTextPadrao.setText("");
                                    }
                                });
                                alerta.show();

                            }

                        }

                    }
                    else{

                        AlertDialog.Builder alerta = new AlertDialog.Builder(ColhedoraActivity.this);
                        alerta.setTitle("ATENÇÃO");
                        alerta.setMessage("ESSA COLHEDORA INEXISTENTE!");

                        alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editTextPadrao.setText("");
                            }
                        });
                        alerta.show();

                    }

                }

            }
        });


        buttonCancColhedora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextPadrao.getText().toString().length() > 0){
                    editTextPadrao.setText(editTextPadrao.getText().toString().substring(0, editTextPadrao.getText().toString().length() - 1));
                }
                else{

                    Intent it = new Intent(ColhedoraActivity.this, FrenteActivity.class);
                    startActivity(it);
                    finish();

                }

            }
        });

    }
}
